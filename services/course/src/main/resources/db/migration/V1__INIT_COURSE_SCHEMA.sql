CREATE TABLE categories
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    parent_id  BIGINT                NULL,
    name       VARCHAR(255)          NOT NULL,
    slug       VARCHAR(255)          NOT NULL,
    icon       VARCHAR(255)          NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE chapters
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    course_id      BIGINT                NOT NULL,
    title          VARCHAR(255)          NOT NULL,
    total_duration VARCHAR(255)          NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_chapters PRIMARY KEY (id)
);

CREATE TABLE courses
(
    id                BIGINT AUTO_INCREMENT        NOT NULL,
    instructor_id     BIGINT                       NOT NULL,
    category_id       BIGINT                       NULL,
    title             VARCHAR(255)                 NOT NULL,
    slug              VARCHAR(255)                 NOT NULL,
    total_duration    VARCHAR(255)                 NULL,
    level             VARCHAR(255) DEFAULT 'all'   NOT NULL,
    language          VARCHAR(255)                 NOT NULL,
    description     LONGTEXT                     NULL,
    short_description VARCHAR(255)                 NOT NULL,
    featured_picture  VARCHAR(255)                 NULL,
    price             DOUBLE                       NOT NULL,
    is_free           BIT(1)                       NOT NULL,
    is_discount       BIT(1)                       NOT NULL,
    discount          DOUBLE                       NULL,
    status            VARCHAR(255) DEFAULT 'draft' NOT NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at        datetime                     NULL,
    CONSTRAINT pk_courses PRIMARY KEY (id)
);

CREATE TABLE lessons
(
    id                     BIGINT AUTO_INCREMENT NOT NULL,
    chapter_id             BIGINT                NOT NULL,
    title                  VARCHAR(255)          NOT NULL,
    type                   VARCHAR(255)          NOT NULL,
    need_previous_lesson   BIT(1)                NOT NULL,
    is_free                BIT(1)                NOT NULL,
    content_video_duration VARCHAR(255)          NULL,
    content_video_url      VARCHAR(255)          NULL,
    content_text           TEXT                  NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_lessons PRIMARY KEY (id)
);

ALTER TABLE categories
    ADD CONSTRAINT FK_CATEGORIES_ON_PARENT FOREIGN KEY (parent_id) REFERENCES categories (id);

ALTER TABLE courses
    ADD CONSTRAINT FK_CATEGORY_COURSE FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE chapters
    ADD CONSTRAINT FK_CHAPTERS_ON_COURSE FOREIGN KEY (course_id) REFERENCES courses (id);

ALTER TABLE lessons
    ADD CONSTRAINT FK_LESSONS_ON_CHAPTER FOREIGN KEY (chapter_id) REFERENCES chapters (id);