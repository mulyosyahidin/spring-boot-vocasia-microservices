CREATE TABLE IF NOT EXISTS `orders` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT NULL,
    `order_number` VARCHAR(255) NOT NULL,
    `total_items` INT NOT NULL,
    `total_price` DOUBLE(15, 2) NOT NULL,
    `total_discount` DOUBLE(15, 2) NOT NULL,
    `payment_status` VARCHAR(50) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `order_items` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_id` INT NOT NULL,
    `course_id` INT NOT NULL,
    `course_title` VARCHAR(255) NOT NULL,
    `course_slug` VARCHAR(255) NOT NULL,
    `course_featured_picture_url` VARCHAR(255) NOT NULL,
    `course_price` DOUBLE(15, 2) NOT NULL,
    `course_is_free` BOOLEAN NOT NULL DEFAULT 0,
    `course_is_discount` BOOLEAN NOT NULL DEFAULT 0,
    `course_discount` DECIMAL(10, 2) DEFAULT 0,
    `course_subtotal` DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`) ON DELETE CASCADE
);
