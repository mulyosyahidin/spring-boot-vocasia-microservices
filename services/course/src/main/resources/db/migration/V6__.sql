ALTER TABLE categories
    ADD type VARCHAR(8) NULL AFTER id;

ALTER TABLE categories
    MODIFY created_at datetime NULL;

ALTER TABLE chapters
    MODIFY created_at datetime NULL;

ALTER TABLE courses
    MODIFY created_at datetime NULL;

ALTER TABLE lessons
    MODIFY created_at datetime NULL;

ALTER TABLE categories
    MODIFY updated_at datetime NULL;

ALTER TABLE chapters
    MODIFY updated_at datetime NULL;

ALTER TABLE courses
    MODIFY updated_at datetime NULL;

ALTER TABLE lessons
    MODIFY updated_at datetime NULL;