ALTER TABLE progress
    ADD completed_at datetime NULL;

ALTER TABLE progress
    ADD started_at datetime NULL;

ALTER TABLE progress
    MODIFY completed_at datetime NOT NULL;

ALTER TABLE progress
    MODIFY started_at datetime NOT NULL;

ALTER TABLE progress
    DROP COLUMN last_accessed;

ALTER TABLE progress
    DROP COLUMN watched_duration;

ALTER TABLE enrollments
    MODIFY created_at datetime NULL;

ALTER TABLE progress
    MODIFY created_at datetime NULL;

ALTER TABLE enrollments
    MODIFY updated_at datetime NULL;

ALTER TABLE progress
    MODIFY updated_at datetime NULL;