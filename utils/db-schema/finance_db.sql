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

-- membuang struktur untuk table finance_db.flyway_schema_history
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

-- Membuang data untuk tabel finance_db.flyway_schema_history: ~5 rows (lebih kurang)
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'INIT FINANCE SCHEMA', 'SQL', 'V1__INIT_FINANCE_SCHEMA.sql', -1713330486, 'root', '2024-10-11 15:35:01', 265, 1),
	(2, '2', '', 'SQL', 'V2__.sql', 831338726, 'root', '2024-10-12 18:28:44', 131, 1),
	(3, '3', '', 'SQL', 'V3__.sql', -1586714772, 'root', '2024-10-12 19:19:54', 152, 1),
	(4, '4', '', 'SQL', 'V4__.sql', 1071857640, 'root', '2024-10-13 08:59:49', 68, 1),
	(5, '5', '', 'SQL', 'V5__.sql', -334696159, 'root', '2024-10-13 13:17:58', 65, 1);

-- membuang struktur untuk table finance_db.instructor_balances
CREATE TABLE IF NOT EXISTS `instructor_balances` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint DEFAULT NULL,
  `current_balance` double DEFAULT NULL,
  `total_income` double DEFAULT NULL,
  `total_pending_withdrawal` double DEFAULT NULL,
  `total_withdrawn` double DEFAULT NULL,
  `total_platform_fee` double DEFAULT NULL,
  `last_history_id` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel finance_db.instructor_balances: ~0 rows (lebih kurang)
INSERT INTO `instructor_balances` (`id`, `instructor_id`, `current_balance`, `total_income`, `total_pending_withdrawal`, `total_withdrawn`, `total_platform_fee`, `last_history_id`, `created_at`, `updated_at`) VALUES
	(1, 2, 95000, 662150, 0, 567150, 34850, 10, '2024-10-16 20:43:13', '2024-10-16 20:59:34'),
	(2, 1, 142500, 142500, 0, 0, 7500, 11, '2024-10-17 22:48:29', '2024-10-17 22:48:29');

-- membuang struktur untuk table finance_db.instructor_balance_histories
CREATE TABLE IF NOT EXISTS `instructor_balance_histories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint NOT NULL,
  `transaction_type` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  `previous_balance` double DEFAULT '0',
  `new_balance` double DEFAULT '0',
  `transaction_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `reference_id` bigint DEFAULT NULL,
  `reference_type` varchar(50) DEFAULT NULL,
  `transaction_status` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel finance_db.instructor_balance_histories: ~8 rows (lebih kurang)
INSERT INTO `instructor_balance_histories` (`id`, `instructor_id`, `transaction_type`, `amount`, `previous_balance`, `new_balance`, `transaction_date`, `reference_id`, `reference_type`, `transaction_status`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 'income', 95000, 0, 95000, '2024-10-16 20:43:13', 51, 'order', 'completed', 'New income from order #202410166JEMU', '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(2, 2, 'income', 95000, 95000, 190000, '2024-10-16 20:44:24', 52, 'order', 'completed', 'New income from order #202410163YDZQ', '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(3, 2, 'withdrawal', 95000, 190000, 95000, '2024-10-16 20:45:01', 1, 'withdrawal', 'success', 'Withdrawal request processed', '2024-10-16 20:45:01', '2024-10-16 13:45:00'),
	(4, 2, 'income', 94050, 95000, 189050, '2024-10-16 20:46:13', 53, 'order', 'completed', 'New income from order #202410163IHAR', '2024-10-16 20:46:13', '2024-10-16 13:46:12'),
	(5, 2, 'income', 94050, 189050, 283100, '2024-10-16 20:47:27', 54, 'order', 'completed', 'New income from order #202410166SMLA', '2024-10-16 20:47:27', '2024-10-16 13:47:27'),
	(6, 2, 'income', 94050, 283100, 377150, '2024-10-16 20:49:19', 55, 'order', 'completed', 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(7, 2, 'income', 95000, 377150, 472150, '2024-10-16 20:49:19', 55, 'order', 'completed', 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(8, 2, 'withdrawal', 283100, 472150, 189050, '2024-10-16 20:51:50', 2, 'withdrawal', 'success', 'Withdrawal request processed', '2024-10-16 20:51:50', '2024-10-16 13:51:50'),
	(9, 2, 'income', 95000, 189050, 284050, '2024-10-16 20:58:56', 56, 'order', 'completed', 'New income from order #2024101613JZIT', '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(10, 2, 'withdrawal', 189050, 284050, 95000, '2024-10-16 20:59:34', 3, 'withdrawal', 'success', 'Withdrawal request processed', '2024-10-16 20:59:34', '2024-10-16 13:59:33'),
	(11, 1, 'income', 142500, 0, 142500, '2024-10-17 22:48:29', 58, 'order', 'completed', 'New income from order #202410176UMTE', '2024-10-17 22:48:29', '2024-10-17 15:48:29');

-- membuang struktur untuk table finance_db.instuctor_income
CREATE TABLE IF NOT EXISTS `instuctor_income` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  `total_user_payment` double NOT NULL,
  `platform_fee_in_percent` decimal(10,0) NOT NULL,
  `total_instructor_income` double NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel finance_db.instuctor_income: ~6 rows (lebih kurang)
INSERT INTO `instuctor_income` (`id`, `instructor_id`, `order_id`, `course_id`, `total_user_payment`, `platform_fee_in_percent`, `total_instructor_income`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 51, 5, 100000, 5, 95000, 'New income from order #202410166JEMU', '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(2, 2, 52, 5, 100000, 5, 95000, 'New income from order #202410163YDZQ', '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(3, 2, 53, 4, 99000, 5, 94050, 'New income from order #202410163IHAR', '2024-10-16 20:46:12', '2024-10-16 13:46:12'),
	(4, 2, 54, 4, 99000, 5, 94050, 'New income from order #202410166SMLA', '2024-10-16 20:47:27', '2024-10-16 13:47:26'),
	(5, 2, 55, 4, 99000, 5, 94050, 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:18'),
	(6, 2, 55, 5, 100000, 5, 95000, 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(7, 2, 56, 5, 100000, 5, 95000, 'New income from order #2024101613JZIT', '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(8, 1, 58, 18, 150000, 5, 142500, 'New income from order #202410176UMTE', '2024-10-17 22:48:28', '2024-10-17 15:48:28');

-- membuang struktur untuk table finance_db.platform_balances
CREATE TABLE IF NOT EXISTS `platform_balances` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `current_balance` double NOT NULL,
  `total_income` double NOT NULL,
  `total_pending_withdrawal` double DEFAULT NULL,
  `total_withdrawn` double NOT NULL,
  `last_history_id` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel finance_db.platform_balances: ~0 rows (lebih kurang)
INSERT INTO `platform_balances` (`id`, `current_balance`, `total_income`, `total_pending_withdrawal`, `total_withdrawn`, `last_history_id`, `created_at`, `updated_at`) VALUES
	(1, 42350, 42350, 0, 0, 8, '2024-10-16 20:43:13', '2024-10-17 22:48:30');

-- membuang struktur untuk table finance_db.platform_balance_histories
CREATE TABLE IF NOT EXISTS `platform_balance_histories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  `previous_balance` double NOT NULL,
  `new_balance` double NOT NULL,
  `transaction_date` datetime NOT NULL,
  `reference_id` bigint DEFAULT NULL,
  `reference_type` varchar(255) DEFAULT NULL,
  `transaction_status` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel finance_db.platform_balance_histories: ~6 rows (lebih kurang)
INSERT INTO `platform_balance_histories` (`id`, `transaction_type`, `amount`, `previous_balance`, `new_balance`, `transaction_date`, `reference_id`, `reference_type`, `transaction_status`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 'fee', 5000, 0, 5000, '2024-10-16 20:43:13', 51, 'order', 'completed', 'New fee income from order #202410166JEMU', '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(2, 'fee', 5000, 5000, 10000, '2024-10-16 20:44:24', 52, 'order', 'completed', 'New fee income from order #202410163YDZQ', '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(3, 'fee', 4950, 10000, 14950, '2024-10-16 20:46:13', 53, 'order', 'completed', 'New fee income from order #202410163IHAR', '2024-10-16 20:46:13', '2024-10-16 13:46:12'),
	(4, 'fee', 4950, 14950, 19900, '2024-10-16 20:47:27', 54, 'order', 'completed', 'New fee income from order #202410166SMLA', '2024-10-16 20:47:27', '2024-10-16 13:47:27'),
	(5, 'fee', 4950, 19900, 24850, '2024-10-16 20:49:19', 55, 'order', 'completed', 'New fee income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(6, 'fee', 5000, 24850, 29850, '2024-10-16 20:49:19', 55, 'order', 'completed', 'New fee income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(7, 'fee', 5000, 29850, 34850, '2024-10-16 20:58:56', 56, 'order', 'completed', 'New fee income from order #2024101613JZIT', '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(8, 'fee', 7500, 34850, 42350, '2024-10-17 22:48:30', 58, 'order', 'completed', 'New fee income from order #202410176UMTE', '2024-10-17 22:48:30', '2024-10-17 15:48:29');

-- membuang struktur untuk table finance_db.platform_income
CREATE TABLE IF NOT EXISTS `platform_income` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  `total_user_payment` double NOT NULL,
  `platform_fee_in_percent` decimal(10,0) NOT NULL,
  `total_platform_income` double NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel finance_db.platform_income: ~6 rows (lebih kurang)
INSERT INTO `platform_income` (`id`, `instructor_id`, `order_id`, `course_id`, `total_user_payment`, `platform_fee_in_percent`, `total_platform_income`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 51, 5, 100000, 5, 5000, 'New income from order #202410166JEMU', '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(2, 2, 52, 5, 100000, 5, 5000, 'New income from order #202410163YDZQ', '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(3, 2, 53, 4, 99000, 5, 4950, 'New income from order #202410163IHAR', '2024-10-16 20:46:13', '2024-10-16 13:46:12'),
	(4, 2, 54, 4, 99000, 5, 4950, 'New income from order #202410166SMLA', '2024-10-16 20:47:27', '2024-10-16 13:47:27'),
	(5, 2, 55, 4, 99000, 5, 4950, 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:18'),
	(6, 2, 55, 5, 100000, 5, 5000, 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(7, 2, 56, 5, 100000, 5, 5000, 'New income from order #2024101613JZIT', '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(8, 1, 58, 18, 150000, 5, 7500, 'New income from order #202410176UMTE', '2024-10-17 22:48:29', '2024-10-17 15:48:28');

-- membuang struktur untuk table finance_db.withdrawal_process
CREATE TABLE IF NOT EXISTS `withdrawal_process` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `withdrawal_request_id` bigint DEFAULT NULL,
  `amount` double NOT NULL,
  `processed_at` datetime DEFAULT NULL,
  `proof_document` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel finance_db.withdrawal_process: ~2 rows (lebih kurang)
INSERT INTO `withdrawal_process` (`id`, `withdrawal_request_id`, `amount`, `processed_at`, `proof_document`, `note`, `status`, `created_at`, `updated_at`) VALUES
	(1, 1, 95000, '2024-10-16 20:45:00', '1729086300063_Daftar-Dospem-KP-Ganjil-2024-Lanjutan.pdf', 'sss', 'completed', '2024-10-16 20:45:01', '2024-10-16 13:45:00'),
	(2, 2, 283100, '2024-10-16 20:51:49', '1729086709405_Daftar-Dospem-KP-Ganjil-2024-Lanjutan.pdf', 'xxx', 'completed', '2024-10-16 20:51:50', '2024-10-16 13:51:50'),
	(3, 3, 189050, '2024-10-16 20:59:33', '1729087173337_Daftar-Dospem-KP-Ganjil-2024-Lanjutan.pdf', 'sss', 'completed', '2024-10-16 20:59:34', '2024-10-16 13:59:33');

-- membuang struktur untuk table finance_db.withdrawal_requests
CREATE TABLE IF NOT EXISTS `withdrawal_requests` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint NOT NULL,
  `amount` double NOT NULL,
  `status` varchar(255) NOT NULL,
  `requested_at` datetime NOT NULL,
  `processed_at` datetime DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `bank_account_name` varchar(255) DEFAULT NULL,
  `bank_account_number` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel finance_db.withdrawal_requests: ~2 rows (lebih kurang)
INSERT INTO `withdrawal_requests` (`id`, `instructor_id`, `amount`, `status`, `requested_at`, `processed_at`, `bank_name`, `bank_account_name`, `bank_account_number`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 95000, 'PAID', '2024-10-16 20:43:41', '2024-10-16 20:45:00', 'BANK BRI', 'MMS', '1234567890', 'Request processed successfully', '2024-10-16 20:43:41', '2024-10-16 20:45:00'),
	(2, 2, 283100, 'PAID', '2024-10-16 20:47:44', '2024-10-16 20:51:49', 'BANK BRI', 'MMS', '1234567890', 'Request processed successfully', '2024-10-16 20:47:44', '2024-10-16 20:51:49'),
	(3, 2, 189050, 'PAID', '2024-10-16 20:57:45', '2024-10-16 20:59:33', 'BANK BRI', 'MMS', '1234567890', 'Request processed successfully', '2024-10-16 20:57:45', '2024-10-16 20:59:33');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
