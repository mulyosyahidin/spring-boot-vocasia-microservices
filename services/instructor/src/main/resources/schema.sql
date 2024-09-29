CREATE TABLE IF NOT EXISTS`instructors` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL UNIQUE,
    `status` VARCHAR(255) NOT NULL DEFAULT 'pending',
    `phone_number` VARCHAR(255) DEFAULT NULL,
    `summary` TEXT DEFAULT NULL,
    `deleted_at` DATETIME DEFAULT NULL,
    PRIMARY KEY (`id`)
);
