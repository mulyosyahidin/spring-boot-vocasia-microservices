CREATE TABLE payments
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    order_id          BIGINT                NOT NULL,
    order_number      VARCHAR(255)          NOT NULL,
    total_price       DOUBLE                NOT NULL,
    additional_fee    DOUBLE                NOT NULL,
    total_payment     DOUBLE                NOT NULL,
    snap_token        VARCHAR(255)          NOT NULL,
    payment_status    VARCHAR(255)          NOT NULL,
    payment_expire_at datetime              NOT NULL,
    created_at        DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_payments PRIMARY KEY (id)
);