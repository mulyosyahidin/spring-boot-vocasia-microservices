CREATE TABLE instuctor_income
(
    id                      BIGINT AUTO_INCREMENT NOT NULL,
    instructor_id           BIGINT                NOT NULL,
    order_id                BIGINT                NOT NULL,
    course_id               BIGINT                NOT NULL,
    total_user_payment      DOUBLE                NOT NULL,
    platform_fee_in_percent DECIMAL               NOT NULL,
    total_instructor_income DOUBLE                NOT NULL,
    remarks                 VARCHAR(255)          NULL,
    created_at              DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_instuctor_income PRIMARY KEY (id)
);

CREATE TABLE platform_income
(
    id                      BIGINT AUTO_INCREMENT NOT NULL,
    instructor_id           BIGINT                NOT NULL,
    order_id                BIGINT                NOT NULL,
    course_id               BIGINT                NOT NULL,
    total_user_payment      DOUBLE                NOT NULL,
    platform_fee_in_percent DECIMAL               NOT NULL,
    total_platform_income   DOUBLE                NOT NULL,
    remarks                 VARCHAR(255)          NULL,
    created_at              DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_platform_income PRIMARY KEY (id)
);