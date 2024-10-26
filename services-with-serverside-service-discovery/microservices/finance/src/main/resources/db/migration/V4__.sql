CREATE TABLE withdrawal_requests
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    instructor_id       BIGINT                NOT NULL,
    amount              DOUBLE                NOT NULL,
    status              VARCHAR(255)          NOT NULL,
    requested_at        datetime              NOT NULL,
    processed_at        datetime              NULL,
    bank_name           VARCHAR(255)          NULL,
    bank_account_name   VARCHAR(255)          NULL,
    bank_account_number VARCHAR(255)          NULL,
    remarks             VARCHAR(255)          NULL,
    created_at          DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_withdrawal_requests PRIMARY KEY (id)
);