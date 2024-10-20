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

-- membuang struktur untuk table payment_db.flyway_schema_history
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

-- Membuang data untuk tabel payment_db.flyway_schema_history: ~0 rows (lebih kurang)
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'INIT PAYMENT SCHEMA', 'SQL', 'V1__INIT_PAYMENT_SCHEMA.sql', -1533778824, 'root', '2024-10-10 12:25:33', 43, 1);

-- membuang struktur untuk table payment_db.payments
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel payment_db.payments: ~6 rows (lebih kurang)
INSERT INTO `payments` (`id`, `order_id`, `order_number`, `total_price`, `additional_fee`, `total_payment`, `snap_token`, `payment_status`, `payment_expire_at`, `created_at`, `updated_at`) VALUES
	(45, 51, '202410166JEMU', 100000, 5000, 105000, '681f6bac-3d90-470a-b06c-b6ae7ea22ae6', 'success', '2024-10-17 20:43:03', '2024-10-16 20:43:03', '2024-10-16 20:43:13'),
	(46, 52, '202410163YDZQ', 100000, 5000, 105000, 'f34f729a-57fa-4916-b23a-15fb10e24b98', 'success', '2024-10-17 20:44:14', '2024-10-16 20:44:14', '2024-10-16 20:44:24'),
	(47, 53, '202410163IHAR', 99000, 5000, 104000, '7a68cae5-6c08-4cdb-baf8-f61c2f5b8bfc', 'success', '2024-10-17 20:46:04', '2024-10-16 20:46:04', '2024-10-16 20:46:12'),
	(48, 54, '202410166SMLA', 99000, 5000, 104000, '830e5ef2-8402-43f8-970b-3e27bd365a6a', 'success', '2024-10-17 20:47:19', '2024-10-16 20:47:19', '2024-10-16 20:47:27'),
	(49, 55, '202410165BUAF', 199000, 5000, 204000, 'ee24960e-8735-4339-ba6c-c5e0aabf5302', 'success', '2024-10-17 20:49:05', '2024-10-16 20:49:05', '2024-10-16 20:49:19'),
	(50, 56, '2024101613JZIT', 100000, 5000, 105000, 'd55645e8-fb75-4329-adbc-e1a144b4e10b', 'success', '2024-10-17 20:58:47', '2024-10-16 20:58:47', '2024-10-16 20:58:56'),
	(51, 57, '202410165OSUI', 150000, 5000, 155000, '4a4f4b01-d428-4132-8044-2ca5a3e17d22', 'PENDING', '2024-10-17 18:59:19', '2024-10-16 18:59:20', '2024-10-16 18:59:20'),
	(52, 58, '202410176UMTE', 150000, 5000, 155000, 'aab3a35c-6ef4-4024-8a1a-2b4342a0dccb', 'success', '2024-10-18 22:47:06', '2024-10-17 22:47:06', '2024-10-17 22:48:25');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
