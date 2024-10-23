CREATE TABLE users
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    uid             VARCHAR(255)          NOT NULL,
    email           VARCHAR(255)          NOT NULL,
    username        VARCHAR(255)          NOT NULL,
    name            VARCHAR(255)          NOT NULL,
    password        VARCHAR(255)          NOT NULL,
    `role`          VARCHAR(255)          NOT NULL,
    profile_picture VARCHAR(255)          NULL,
    created_at      DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_uid UNIQUE (uid);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);