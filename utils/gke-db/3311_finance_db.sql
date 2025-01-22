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

-- membuang struktur untuk table vocasia_db.instructor_balances
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `instructor_balances` (`id`, `instructor_id`, `current_balance`, `total_income`, `total_pending_withdrawal`, `total_withdrawn`, `total_platform_fee`, `last_history_id`, `created_at`, `updated_at`) VALUES
	(2, 2, 4892500, 5602150, 0, 709650, 294850, 13, '2024-12-15 14:29:48', '2024-12-15 14:46:51');

-- membuang struktur untuk table vocasia_db.instructor_balance_histories
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `instructor_balance_histories` (`id`, `instructor_id`, `transaction_type`, `amount`, `previous_balance`, `new_balance`, `transaction_date`, `reference_id`, `reference_type`, `transaction_status`, `remarks`, `created_at`, `updated_at`) VALUES
	(6, 2, 'income', 95000, 0, 95000, '2024-12-15 14:29:48', 12, 'order', 'completed', 'New income from order #202412153QLYI', '2024-12-15 14:29:48', '2024-12-15 07:29:48'),
	(7, 2, 'income', 285000, 95000, 380000, '2024-12-15 14:30:33', 13, 'order', 'completed', 'New income from order #202412153TGMM', '2024-12-15 14:30:33', '2024-12-15 07:30:32'),
	(8, 2, 'income', 189050, 380000, 569050, '2024-12-15 14:30:33', 13, 'order', 'completed', 'New income from order #202412153TGMM', '2024-12-15 14:30:33', '2024-12-15 07:30:32'),
	(9, 2, 'income', 113050, 569050, 682100, '2024-12-15 14:34:43', 14, 'order', 'completed', 'New income from order #202412155YEUF', '2024-12-15 14:34:43', '2024-12-15 07:34:42'),
	(10, 2, 'income', 27550, 682100, 709650, '2024-12-15 14:37:35', 18, 'order', 'completed', 'New income from order #202412155BIQU', '2024-12-15 14:37:35', '2024-12-15 07:37:35'),
	(11, 2, 'withdrawal', 709650, 709650, 0, '2024-12-15 14:38:49', 2, 'withdrawal', 'success', 'Withdrawal request processed', '2024-12-15 14:38:49', '2024-12-15 07:38:48'),
	(12, 2, 'income', 142500, 0, 142500, '2024-12-15 14:46:22', 20, 'order', 'completed', 'New income from order #202412156MKYL', '2024-12-15 14:46:22', '2024-12-15 07:46:21'),
	(13, 2, 'income', 4750000, 142500, 4892500, '2024-12-15 14:46:51', 21, 'order', 'completed', 'New income from order #202412156UOQD', '2024-12-15 14:46:51', '2024-12-15 07:46:51');

-- membuang struktur untuk table vocasia_db.instuctor_income
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `instuctor_income` (`id`, `instructor_id`, `order_id`, `course_id`, `total_user_payment`, `platform_fee_in_percent`, `total_instructor_income`, `remarks`, `created_at`, `updated_at`) VALUES
	(5, 2, 12, 28, 100000, 5, 95000, 'New income from order #202412153QLYI', '2024-12-15 14:29:47', '2024-12-15 07:29:47'),
	(6, 2, 13, 26, 300000, 5, 285000, 'New income from order #202412153TGMM', '2024-12-15 14:30:32', '2024-12-15 07:30:32'),
	(7, 2, 13, 25, 199000, 5, 189050, 'New income from order #202412153TGMM', '2024-12-15 14:30:33', '2024-12-15 07:30:32'),
	(8, 2, 14, 21, 119000, 5, 113050, 'New income from order #202412155YEUF', '2024-12-15 14:34:42', '2024-12-15 07:34:42'),
	(9, 2, 18, 22, 29000, 5, 27550, 'New income from order #202412155BIQU', '2024-12-15 14:37:35', '2024-12-15 07:37:35'),
	(10, 2, 20, 27, 150000, 5, 142500, 'New income from order #202412156MKYL', '2024-12-15 14:46:19', '2024-12-15 07:46:19'),
	(11, 2, 21, 24, 5000000, 5, 4750000, 'New income from order #202412156UOQD', '2024-12-15 14:46:51', '2024-12-15 07:46:50');

-- membuang struktur untuk table vocasia_db.platform_balances
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

INSERT INTO `platform_balances` (`id`, `current_balance`, `total_income`, `total_pending_withdrawal`, `total_withdrawn`, `last_history_id`, `created_at`, `updated_at`) VALUES
	(2, 294850, 294850, 0, 0, 11, '2024-12-15 14:29:48', '2024-12-15 14:46:51');

-- membuang struktur untuk table vocasia_db.platform_balance_histories
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `platform_balance_histories` (`id`, `transaction_type`, `amount`, `previous_balance`, `new_balance`, `transaction_date`, `reference_id`, `reference_type`, `transaction_status`, `remarks`, `created_at`, `updated_at`) VALUES
	(5, 'fee', 5000, 0, 5000, '2024-12-15 14:29:48', 12, 'order', 'completed', 'New fee income from order #202412153QLYI', '2024-12-15 14:29:48', '2024-12-15 07:29:48'),
	(6, 'fee', 15000, 5000, 20000, '2024-12-15 14:30:33', 13, 'order', 'completed', 'New fee income from order #202412153TGMM', '2024-12-15 14:30:33', '2024-12-15 07:30:32'),
	(7, 'fee', 9950, 20000, 29950, '2024-12-15 14:30:33', 13, 'order', 'completed', 'New fee income from order #202412153TGMM', '2024-12-15 14:30:33', '2024-12-15 07:30:32'),
	(8, 'fee', 5950, 29950, 35900, '2024-12-15 14:34:43', 14, 'order', 'completed', 'New fee income from order #202412155YEUF', '2024-12-15 14:34:43', '2024-12-15 07:34:42'),
	(9, 'fee', 1450, 35900, 37350, '2024-12-15 14:37:35', 18, 'order', 'completed', 'New fee income from order #202412155BIQU', '2024-12-15 14:37:35', '2024-12-15 07:37:35'),
	(10, 'fee', 7500, 37350, 44850, '2024-12-15 14:46:22', 20, 'order', 'completed', 'New fee income from order #202412156MKYL', '2024-12-15 14:46:22', '2024-12-15 07:46:22'),
	(11, 'fee', 250000, 44850, 294850, '2024-12-15 14:46:51', 21, 'order', 'completed', 'New fee income from order #202412156UOQD', '2024-12-15 14:46:51', '2024-12-15 07:46:51');

-- membuang struktur untuk table vocasia_db.platform_income
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `platform_income` (`id`, `instructor_id`, `order_id`, `course_id`, `total_user_payment`, `platform_fee_in_percent`, `total_platform_income`, `remarks`, `created_at`, `updated_at`) VALUES
	(5, 2, 12, 28, 100000, 5, 5000, 'New income from order #202412153QLYI', '2024-12-15 14:29:47', '2024-12-15 07:29:47'),
	(6, 2, 13, 26, 300000, 5, 15000, 'New income from order #202412153TGMM', '2024-12-15 14:30:32', '2024-12-15 07:30:32'),
	(7, 2, 13, 25, 199000, 5, 9950, 'New income from order #202412153TGMM', '2024-12-15 14:30:33', '2024-12-15 07:30:32'),
	(8, 2, 14, 21, 119000, 5, 5950, 'New income from order #202412155YEUF', '2024-12-15 14:34:43', '2024-12-15 07:34:42'),
	(9, 2, 18, 22, 29000, 5, 1450, 'New income from order #202412155BIQU', '2024-12-15 14:37:35', '2024-12-15 07:37:35'),
	(10, 2, 20, 27, 150000, 5, 7500, 'New income from order #202412156MKYL', '2024-12-15 14:46:19', '2024-12-15 07:46:19'),
	(11, 2, 21, 24, 5000000, 5, 250000, 'New income from order #202412156UOQD', '2024-12-15 14:46:51', '2024-12-15 07:46:50');

-- membuang struktur untuk table vocasia_db.withdrawal_process
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `withdrawal_process` (`id`, `withdrawal_request_id`, `amount`, `processed_at`, `proof_document`, `note`, `status`, `created_at`, `updated_at`) VALUES
	(2, 2, 709650, '2024-12-15 14:38:48', '1734248327699_Hasil Ujian UJIAN 1_13.pdf', 'berhasil', 'completed', '2024-12-15 14:38:49', '2024-12-15 07:38:48');

-- membuang struktur untuk table vocasia_db.withdrawal_requests
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `withdrawal_requests` (`id`, `instructor_id`, `amount`, `status`, `requested_at`, `processed_at`, `bank_name`, `bank_account_name`, `bank_account_number`, `remarks`, `created_at`, `updated_at`) VALUES
	(2, 2, 709650, 'PAID', '2024-12-15 14:38:22', '2024-12-15 14:38:48', 'BANK BRI', 'MARTIN MULYO SYAHIDIN', '1234567890', 'Request processed successfully', '2024-12-15 14:38:22', '2024-12-15 14:38:48');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
