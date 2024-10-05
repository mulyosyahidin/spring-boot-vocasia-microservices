CREATE TABLE IF NOT EXISTS `payments` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_id` INT NOT NULL,
    `order_number` VARCHAR(50) NOT NULL,
    `total_price` DOUBLE(15, 2) NOT NULL,
    `additional_fee` DOUBLE(15, 2) NOT NULL,
    `total_payment` DOUBLE(15, 2) NOT NULL,
    `snap_token` VARCHAR(255) NOT NULL,
    `payment_status` VARCHAR(50) NOT NULL,
    `payment_expire_at` TIMESTAMP NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
