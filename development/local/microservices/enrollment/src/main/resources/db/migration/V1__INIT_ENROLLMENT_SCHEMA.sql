CREATE TABLE enrollments
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    user_id             BIGINT                NOT NULL,
    order_id            BIGINT                NOT NULL,
    course_id           BIGINT                NOT NULL,
    enrollment_date     datetime              NOT NULL,
    status              VARCHAR(255)          NOT NULL,
    progress_percentage DECIMAL               NULL,
    completion_date     datetime              NULL,
    created_at          DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_enrollments PRIMARY KEY (id)
);

CREATE TABLE progress
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    enrollment_id    BIGINT                NOT NULL,
    lesson_id        BIGINT                NOT NULL,
    status           VARCHAR(255)          NOT NULL,
    watched_duration VARCHAR(255)          NOT NULL,
    last_accessed    datetime              NOT NULL,
    created_at       DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_progress PRIMARY KEY (id)
);