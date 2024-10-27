CREATE TABLE instructor_balance_histories
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    instructor_id      BIGINT                NOT NULL,
    transaction_type   VARCHAR(50)           NOT NULL,
    amount             DOUBLE                NOT NULL,
    previous_balance   DOUBLE                NULL     DEFAULT 0,
    new_balance        DOUBLE                NULL     DEFAULT 0,
    transaction_date   datetime              NULL     DEFAULT CURRENT_TIMESTAMP,
    reference_id       BIGINT                NULL,
    reference_type     VARCHAR(50)           NULL,
    transaction_status VARCHAR(255)          NULL,
    remarks            VARCHAR(255)          NULL,
    created_at         DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_instructor_balance_histories PRIMARY KEY (id)
);

CREATE TABLE instructor_balances
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    instructor_id            BIGINT                NULL,
    current_balance          DOUBLE                NULL,
    total_income             DOUBLE                NULL,
    total_pending_withdrawal DOUBLE                NULL,
    total_withdrawn          DOUBLE                NULL,
    total_platform_fee       DOUBLE                NULL,
    last_history_id          BIGINT                NULL,
    created_at               DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at               DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_instructor_balances PRIMARY KEY (id)
);