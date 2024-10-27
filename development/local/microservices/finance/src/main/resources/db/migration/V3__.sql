CREATE TABLE platform_balance_histories
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    transaction_type   VARCHAR(50)           NOT NULL,
    amount             DOUBLE                NOT NULL,
    previous_balance   DOUBLE                NOT NULL,
    new_balance        DOUBLE                NOT NULL,
    transaction_date   datetime              NOT NULL,
    reference_id       BIGINT                NULL,
    reference_type     VARCHAR(255)          NULL,
    transaction_status VARCHAR(255)          NULL,
    remarks            VARCHAR(255)          NULL,
    created_at         DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_platform_balance_histories PRIMARY KEY (id)
);

CREATE TABLE platform_balances
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    current_balance          DOUBLE                NOT NULL,
    total_income             DOUBLE                NOT NULL,
    total_pending_withdrawal DOUBLE                NULL,
    total_withdrawn          DOUBLE                NOT NULL,
    last_history_id          BIGINT                NULL,
    created_at               DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at               DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_platform_balances PRIMARY KEY (id)
);
