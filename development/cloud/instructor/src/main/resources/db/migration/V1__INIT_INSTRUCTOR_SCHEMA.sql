CREATE TABLE instructors
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    user_id      BIGINT                NOT NULL,
    status       VARCHAR(255)          NOT NULL,
    phone_number VARCHAR(255)          NULL,
    summary      TEXT                  NULL,
    deleted_at   datetime              NULL,
    CONSTRAINT pk_instructors PRIMARY KEY (id)
);

ALTER TABLE instructors
    ADD CONSTRAINT uc_instructors_user UNIQUE (user_id);