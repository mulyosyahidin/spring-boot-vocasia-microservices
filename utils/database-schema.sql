-- authentication service
CREATE TABLE IF NOT EXISTS `users`
(
    `id`              INT AUTO_INCREMENT PRIMARY KEY,
    `uid`             VARCHAR(255) NOT NULL UNIQUE,
    `email`           VARCHAR(255) NOT NULL UNIQUE,
    `username`        VARCHAR(255) NOT NULL UNIQUE,
    `name`            VARCHAR(255) NOT NULL,
    `password`        VARCHAR(255) NOT NULL,
    `role`            VARCHAR(50) DEFAULT 'student',
    `profile_picture` VARCHAR(255),
    `created_at`      TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- course service
CREATE TABLE IF NOT EXISTS `categories`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `parent_id`  INT                   DEFAULT NULL,
    `name`       VARCHAR(255) NOT NULL,
    `slug`       VARCHAR(255) NOT NULL,
    `icon`       VARCHAR(255)          DEFAULT NULL,
    `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_parent_category` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS `courses`
(
    `id`                INT          NOT NULL AUTO_INCREMENT,
    `instructor_id`     INT          NOT NULL,
    `category_id`       INT                   DEFAULT NULL,
    `title`             VARCHAR(255) NOT NULL,
    `slug`              VARCHAR(255) NOT NULL,
    `total_duration`    VARCHAR(255)          DEFAULT NULL,
    `level`             VARCHAR(255) NOT NULL DEFAULT 'all',
    `language`          VARCHAR(255)          DEFAULT NULL,
    `description`       TEXT                  DEFAULT NULL,
    `short_description` TEXT                  DEFAULT NULL,
    `featured_picture`  VARCHAR(255)          DEFAULT NULL,
    `price`             INT          NOT NULL,
    `is_free`           BOOLEAN               DEFAULT FALSE,
    `is_discount`       BOOLEAN               DEFAULT FALSE,
    `discount`          INT                   DEFAULT NULL,
    `status`            VARCHAR(255) NULL     DEFAULT 'draft',
    `created_at`        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`        DATETIME              DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_category_course` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS `chapters`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `course_id`      INT          NOT NULL,
    `title`          VARCHAR(255) NOT NULL,
    `total_duration` VARCHAR(255)          DEFAULT NULL,
    `created_at`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_course_chapter` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `lessons`
(
    `id`                     INT          NOT NULL AUTO_INCREMENT,
    `chapter_id`             INT          NOT NULL,
    `title`                  VARCHAR(255) NOT NULL,
    `type`                   VARCHAR(255) NOT NULL,
    `need_previous_lesson`   BOOLEAN      NOT NULL DEFAULT true,
    `is_free`                BOOLEAN      NOT NULL DEFAULT false,
    `content_video_duration` VARCHAR(255)          DEFAULT NULL,
    `content_video_url`      VARCHAR(255)          DEFAULT NULL,
    `content_text`           TEXT                  DEFAULT NULL,
    `created_at`             DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`             DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_chapter_lesson` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`id`) ON DELETE CASCADE
);

-- enrollment service
CREATE TABLE IF NOT EXISTS enrollments
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id             BIGINT       NOT NULL,
    order_id            BIGINT       NOT NULL,
    course_id           BIGINT       NOT NULL,
    enrollment_date     DATETIME     NULL,
    status              VARCHAR(255) NOT NULL DEFAULT 'active',
    progress_percentage DECIMAL(5, 2)         DEFAULT 0.0,
    completion_date     DATETIME     NULL,
    CONSTRAINT uq_enrollment UNIQUE (user_id, course_id)
);

CREATE TABLE IF NOT EXISTS progress
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    enrollment_id    BIGINT       NOT NULL,
    lesson_id        BIGINT       NOT NULL,
    status           VARCHAR(255) NOT NULL,
    watched_duration VARCHAR(50)  NOT NULL,                                                -- Menyimpan durasi ditonton (misalnya dalam format "HH:mm:ss")
    last_accessed    DATETIME     NOT NULL,
    CONSTRAINT fk_enrollment FOREIGN KEY (enrollment_id) REFERENCES enrollments (id) ON DELETE CASCADE,
    CONSTRAINT fk_lesson FOREIGN KEY (lesson_id) REFERENCES lessons (id) ON DELETE CASCADE -- Jika ada tabel lessons
);

-- instructor service
CREATE TABLE IF NOT EXISTS `instructors`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `user_id`      INT          NOT NULL UNIQUE,
    `status`       VARCHAR(255) NOT NULL DEFAULT 'pending',
    `phone_number` VARCHAR(255)          DEFAULT NULL,
    `summary`      TEXT                  DEFAULT NULL,
    `deleted_at`   DATETIME              DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- order service
CREATE TABLE IF NOT EXISTS `orders`
(
    `id`             INT AUTO_INCREMENT PRIMARY KEY,
    `user_id`        INT           NULL,
    `order_number`   VARCHAR(255)  NOT NULL,
    `total_items`    INT           NOT NULL,
    `total_price`    DOUBLE(15, 2) NOT NULL,
    `total_discount` DOUBLE(15, 2) NOT NULL,
    `payment_status` VARCHAR(50)   NOT NULL,
    `created_at`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `order_items`
(
    `id`                          INT AUTO_INCREMENT PRIMARY KEY,
    `order_id`                    INT            NOT NULL,
    `course_id`                   INT            NOT NULL,
    `course_title`                VARCHAR(255)   NOT NULL,
    `course_slug`                 VARCHAR(255)   NOT NULL,
    `course_featured_picture_url` VARCHAR(255)   NOT NULL,
    `course_price`                DOUBLE(15, 2)  NOT NULL,
    `course_is_free`              BOOLEAN        NOT NULL DEFAULT 0,
    `course_is_discount`          BOOLEAN        NOT NULL DEFAULT 0,
    `course_discount`             DECIMAL(10, 2)          DEFAULT 0,
    `course_subtotal`             DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE
);

-- payment service
CREATE TABLE IF NOT EXISTS `payments`
(
    `id`                INT AUTO_INCREMENT PRIMARY KEY,
    `order_id`          INT           NOT NULL,
    `order_number`      VARCHAR(50)   NOT NULL,
    `total_price`       DOUBLE(15, 2) NOT NULL,
    `additional_fee`    DOUBLE(15, 2) NOT NULL,
    `total_payment`     DOUBLE(15, 2) NOT NULL,
    `snap_token`        VARCHAR(255)  NOT NULL,
    `payment_status`    VARCHAR(50)   NOT NULL,
    `payment_expire_at` TIMESTAMP     NOT NULL,
    `created_at`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
