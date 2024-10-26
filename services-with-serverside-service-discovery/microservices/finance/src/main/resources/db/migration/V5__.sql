CREATE TABLE withdrawal_process
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    withdrawal_request_id BIGINT                NULL,
    amount                DOUBLE                NOT NULL,
    processed_at          datetime              NULL,
    proof_document        VARCHAR(255)          NULL,
    note                  VARCHAR(255)          NULL,
    status                VARCHAR(255)          NOT NULL,
    created_at            DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at            DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_withdrawal_process PRIMARY KEY (id)
);