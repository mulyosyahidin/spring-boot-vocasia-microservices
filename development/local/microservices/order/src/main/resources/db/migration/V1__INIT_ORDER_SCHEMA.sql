CREATE TABLE order_items
(
    id                          BIGINT AUTO_INCREMENT NOT NULL,
    order_id                    BIGINT                NOT NULL,
    course_id                   BIGINT                NOT NULL,
    course_title                VARCHAR(255)          NOT NULL,
    course_slug                 VARCHAR(255)          NOT NULL,
    course_featured_picture_url VARCHAR(255)          NOT NULL,
    course_price                DOUBLE                NOT NULL,
    course_is_free              BIT(1)                NOT NULL,
    course_is_discount          BIT(1)                NOT NULL,
    course_discount             DOUBLE                NULL,
    course_subtotal             DOUBLE                NOT NULL,
    CONSTRAINT pk_order_items PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    user_id        BIGINT                NULL,
    order_number   VARCHAR(255)          NULL,
    total_items    INT                   NOT NULL,
    total_price    DOUBLE                NOT NULL,
    total_discount DOUBLE                NOT NULL,
    payment_status VARCHAR(255)          NOT NULL,
    created_at     DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE order_items
    ADD CONSTRAINT FK_ORDER_ITEMS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);