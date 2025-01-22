-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versi server:                 8.0.40 - MySQL Community Server - GPL
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

-- membuang struktur untuk table vocasia_db.payments
CREATE TABLE IF NOT EXISTS `payments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `order_number` varchar(255) NOT NULL,
  `total_price` double NOT NULL,
  `additional_fee` double NOT NULL,
  `total_payment` double NOT NULL,
  `snap_token` varchar(255) NOT NULL,
  `payment_status` varchar(255) NOT NULL,
  `payment_expire_at` datetime NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `payments` (`id`, `order_id`, `order_number`, `total_price`, `additional_fee`, `total_payment`, `snap_token`, `payment_status`, `payment_expire_at`, `created_at`, `updated_at`) VALUES
	(3, 12, '202412153QLYI', 100000, 5000, 105000, '82cabcfd-911c-4e6f-ab2a-5d104f393d3c', 'success', '2024-12-16 14:29:18', '2024-12-15 14:29:18', '2024-12-15 14:29:42'),
	(4, 13, '202412153TGMM', 499000, 5000, 504000, '607dc76b-e5bc-4ef5-af6a-e66b518cd6f2', 'success', '2024-12-16 14:30:22', '2024-12-15 14:30:22', '2024-12-15 14:30:32'),
	(5, 14, '202412155YEUF', 119000, 5000, 124000, '557ba4d6-20fa-423a-acb9-3541e1906b3b', 'success', '2024-12-16 14:34:30', '2024-12-15 14:34:30', '2024-12-15 14:34:42'),
	(6, 18, '202412155BIQU', 29000, 5000, 34000, '8e01ac3f-03f1-4078-849c-e31fcd677d03', 'success', '2024-12-16 14:37:24', '2024-12-15 14:37:24', '2024-12-15 14:37:35'),
	(7, 20, '202412156MKYL', 150000, 5000, 155000, 'd681aee1-f59c-407f-b11d-e081b1fc78bf', 'success', '2024-12-16 14:46:05', '2024-12-15 14:46:05', '2024-12-15 14:46:17'),
	(8, 21, '202412156UOQD', 5000000, 5000, 5005000, 'ba12d5b7-9d9e-44e6-addd-c009ee3c6ec2', 'success', '2024-12-16 14:46:35', '2024-12-15 14:46:35', '2024-12-15 14:46:51');


/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
