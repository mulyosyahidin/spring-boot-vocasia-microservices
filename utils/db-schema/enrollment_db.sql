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

-- membuang struktur untuk table enrollment_db.enrollments
CREATE TABLE IF NOT EXISTS `enrollments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  `enrollment_date` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  `progress_percentage` decimal(10,0) DEFAULT NULL,
  `completion_date` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `last_lesson_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel enrollment_db.enrollments: ~6 rows (lebih kurang)
INSERT INTO `enrollments` (`id`, `user_id`, `order_id`, `course_id`, `enrollment_date`, `status`, `progress_percentage`, `completion_date`, `created_at`, `updated_at`, `last_lesson_id`) VALUES
	(38, 6, 51, 5, '2024-10-16 20:43:13', 'active', 0, NULL, '2024-10-16 20:43:13', NULL, NULL),
	(39, 3, 52, 5, '2024-10-16 20:44:24', 'active', 0, NULL, '2024-10-16 20:44:24', NULL, NULL),
	(40, 3, 53, 4, '2024-10-16 20:46:12', 'active', 0, NULL, '2024-10-16 20:46:12', NULL, NULL),
	(41, 6, 54, 4, '2024-10-16 20:47:27', 'active', 0, NULL, '2024-10-16 20:47:27', NULL, NULL),
	(42, 5, 55, 4, '2024-10-16 20:49:19', 'active', 0, NULL, '2024-10-16 20:49:19', NULL, NULL),
	(43, 5, 55, 5, '2024-10-16 20:49:19', 'active', 0, NULL, '2024-10-16 20:49:19', '2024-10-17 22:21:43', 17),
	(44, 13, 56, 5, '2024-10-16 20:58:56', 'active', 0, NULL, '2024-10-16 20:58:56', NULL, NULL),
	(45, 6, 58, 18, '2024-10-17 22:48:26', 'active', 0, NULL, '2024-10-17 22:48:26', NULL, NULL);

-- membuang struktur untuk table enrollment_db.flyway_schema_history
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

-- Membuang data untuk tabel enrollment_db.flyway_schema_history: ~3 rows (lebih kurang)
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'INIT ENROLLMENT SCHEMA', 'SQL', 'V1__INIT_ENROLLMENT_SCHEMA.sql', -1603269534, 'root', '2024-10-10 12:17:23', 71, 1),
	(2, '7', '', 'SQL', 'V7__.sql', -106756458, 'root', '2024-10-15 19:42:50', 2988, 1),
	(3, '8', '', 'SQL', 'V8__.sql', -741034127, 'root', '2024-10-15 20:31:41', 286, 1);

-- membuang struktur untuk table enrollment_db.progress
CREATE TABLE IF NOT EXISTS `progress` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `enrollment_id` bigint NOT NULL,
  `lesson_id` bigint NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `completed_at` datetime DEFAULT NULL,
  `started_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel enrollment_db.progress: ~2 rows (lebih kurang)
INSERT INTO `progress` (`id`, `enrollment_id`, `lesson_id`, `status`, `created_at`, `updated_at`, `completed_at`, `started_at`) VALUES
	(25, 43, 15, 'IN_PROGRESS', '2024-10-17 16:14:07', NULL, NULL, '2024-10-17 16:14:07'),
	(26, 43, 16, 'IN_PROGRESS', '2024-10-17 17:40:52', NULL, NULL, '2024-10-17 17:40:52'),
	(27, 43, 17, 'IN_PROGRESS', '2024-10-17 17:41:08', NULL, NULL, '2024-10-17 17:41:08');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
