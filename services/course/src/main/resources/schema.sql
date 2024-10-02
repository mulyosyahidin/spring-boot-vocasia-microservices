CREATE TABLE IF NOT EXISTS `categories` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `parent_id` INT DEFAULT NULL,
    `name` VARCHAR(255) NOT NULL,
    `slug` VARCHAR(255) NOT NULL,
    `icon` VARCHAR(255) DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_parent_category` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS `courses` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `instructor_id` INT NOT NULL,
    `category_id` INT DEFAULT NULL,
    `title` VARCHAR(255) NOT NULL,
    `slug` VARCHAR(255) NOT NULL,
    `total_duration` VARCHAR(255) DEFAULT NULL,
    `level` VARCHAR(255) NOT NULL DEFAULT 'all',
    `language` VARCHAR(255) DEFAULT NULL,
    `description` TEXT DEFAULT NULL,
    `short_description` TEXT DEFAULT NULL,
    `featured_picture` VARCHAR(255) DEFAULT NULL,
    `price` INT NOT NULL,
    `is_free` BOOLEAN DEFAULT FALSE,
    `is_discount` BOOLEAN DEFAULT FALSE,
    `discount` INT DEFAULT NULL,
    `status` VARCHAR(255) NULL DEFAULT 'draft',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` DATETIME DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_category_course` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS `chapters` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `course_id` INT NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `total_duration` VARCHAR(255) DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_course_chapter` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `lessons` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `chapter_id` INT NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `type` VARCHAR(255) NOT NULL,
    `need_previous_lesson` BOOLEAN NOT NULL DEFAULT true,
    `is_free` BOOLEAN NOT NULL DEFAULT false,
    `content_video_duration` VARCHAR(255) DEFAULT NULL,
    `content_video_url` VARCHAR(255) DEFAULT NULL,
    `content_text` TEXT DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_chapter_lesson` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`id`) ON DELETE CASCADE
);
