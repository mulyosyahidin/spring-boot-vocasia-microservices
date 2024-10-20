-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versi server:                 8.0.39 - MySQL Community Server - GPL
-- OS Server:                    Linux
-- HeidiSQL Versi:               12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- membuang struktur untuk table order_db.flyway_schema_history
CREATE TABLE IF NOT EXISTS `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel order_db.flyway_schema_history: ~0 rows (lebih kurang)
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'INIT ORDER SCHEMA', 'SQL', 'V1__INIT_ORDER_SCHEMA.sql', -1022369048, 'root', '2024-10-10 12:22:31', 152, 1),
	(2, '2', 'ADD COLUMN TO ORDER ITEMS TABLE', 'SQL', 'V2__ADD_COLUMN_TO_ORDER_ITEMS_TABLE.sql', -307248809, 'root', '2024-10-11 16:54:59', 140, 1);

-- membuang struktur untuk table order_db.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `total_items` int NOT NULL,
  `total_price` double NOT NULL,
  `total_discount` double NOT NULL,
  `payment_status` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel order_db.orders: ~6 rows (lebih kurang)
INSERT INTO `orders` (`id`, `user_id`, `order_number`, `total_items`, `total_price`, `total_discount`, `payment_status`, `created_at`, `updated_at`) VALUES
	(51, 6, '202410166JEMU', 1, 100000, 0, 'success', '2024-10-16 20:43:02', '2024-10-16 20:43:13'),
	(52, 3, '202410163YDZQ', 1, 100000, 0, 'success', '2024-10-16 20:44:14', '2024-10-16 20:44:24'),
	(53, 3, '202410163IHAR', 1, 99000, 0, 'success', '2024-10-16 20:46:03', '2024-10-16 20:46:12'),
	(54, 6, '202410166SMLA', 1, 99000, 0, 'success', '2024-10-16 20:47:18', '2024-10-16 20:47:27'),
	(55, 5, '202410165BUAF', 2, 199000, 0, 'success', '2024-10-16 20:49:05', '2024-10-16 20:49:19'),
	(56, 13, '2024101613JZIT', 1, 100000, 0, 'success', '2024-10-16 20:58:46', '2024-10-16 20:58:56'),
	(57, 5, '202410165OSUI', 1, 150000, 50000, 'PENDING', '2024-10-16 18:59:11', '2024-10-16 18:59:11'),
	(58, 6, '202410176UMTE', 1, 150000, 50000, 'success', '2024-10-17 22:47:03', '2024-10-17 22:48:26');

-- membuang struktur untuk table order_db.order_items
CREATE TABLE IF NOT EXISTS `order_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  `course_instructor_id` bigint DEFAULT NULL,
  `course_title` varchar(255) NOT NULL,
  `course_slug` varchar(255) NOT NULL,
  `course_featured_picture_url` varchar(255) NOT NULL,
  `course_price` double NOT NULL,
  `course_is_free` bit(1) NOT NULL,
  `course_is_discount` bit(1) NOT NULL,
  `course_discount` double DEFAULT NULL,
  `course_subtotal` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ORDER_ITEMS_ON_ORDER` (`order_id`),
  CONSTRAINT `FK_ORDER_ITEMS_ON_ORDER` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel order_db.order_items: ~8 rows (lebih kurang)
INSERT INTO `order_items` (`id`, `order_id`, `course_id`, `course_instructor_id`, `course_title`, `course_slug`, `course_featured_picture_url`, `course_price`, `course_is_free`, `course_is_discount`, `course_discount`, `course_subtotal`) VALUES
	(62, 51, 5, 2, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Belajar Redis.jpg', 100000, b'0', b'0', 0, 100000),
	(63, 52, 5, 2, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Belajar Redis.jpg', 100000, b'0', b'0', 0, 100000),
	(64, 53, 4, 2, 'Riverpod Crash Course', 'riverpod-crash-course', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Riverpod Crash Course.jpg', 99000, b'0', b'0', 0, 99000),
	(65, 54, 4, 2, 'Riverpod Crash Course', 'riverpod-crash-course', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Riverpod Crash Course.jpg', 99000, b'0', b'0', 0, 99000),
	(66, 55, 4, 2, 'Riverpod Crash Course', 'riverpod-crash-course', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Riverpod Crash Course.jpg', 99000, b'0', b'0', 0, 99000),
	(67, 55, 5, 2, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Belajar Redis.jpg', 100000, b'0', b'0', 0, 100000),
	(68, 56, 5, 2, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Belajar Redis.jpg', 100000, b'0', b'0', 0, 100000),
	(69, 57, 18, 1, 'TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)', 'tutorial-spring-boot-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1729093329452-TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA).jpg', 200000, b'0', b'1', 50000, 150000),
	(70, 58, 18, 1, 'TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)', 'tutorial-spring-boot-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1729093329452-TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA).jpg', 200000, b'0', b'1', 50000, 150000);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
