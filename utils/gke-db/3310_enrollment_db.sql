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

-- membuang struktur untuk table vocasia_db.course_reviews
CREATE TABLE IF NOT EXISTS `course_reviews` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `enrollment_id` bigint DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `rating` tinyint NOT NULL,
  `review` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- membuang struktur untuk table vocasia_db.enrollments
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- membuang struktur untuk table vocasia_db.progress
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `enrollments` (`id`, `user_id`, `order_id`, `course_id`, `enrollment_date`, `status`, `progress_percentage`, `completion_date`, `created_at`, `updated_at`, `last_lesson_id`) VALUES
	(5, 3, 12, 28, '2024-12-15 14:29:44', 'active', 0, NULL, '2024-12-15 14:29:44', NULL, NULL),
	(6, 3, 13, 26, '2024-12-15 14:30:32', 'active', 0, NULL, '2024-12-15 14:30:32', NULL, NULL),
	(7, 3, 13, 25, '2024-12-15 14:30:32', 'active', 0, NULL, '2024-12-15 14:30:32', NULL, NULL),
	(8, 5, 14, 21, '2024-12-15 14:34:42', 'active', 0, NULL, '2024-12-15 14:34:42', NULL, NULL),
	(9, 5, 18, 22, '2024-12-15 14:37:35', 'active', 0, NULL, '2024-12-15 14:37:35', NULL, NULL),
	(10, 6, 20, 27, '2024-12-15 14:46:18', 'active', 4, NULL, '2024-12-15 14:46:18', '2024-12-15 14:49:19', 300),
	(11, 6, 21, 24, '2024-12-15 14:46:51', 'active', 0, NULL, '2024-12-15 14:46:51', NULL, NULL);

INSERT INTO `progress` (`id`, `enrollment_id`, `lesson_id`, `status`, `created_at`, `updated_at`, `completed_at`, `started_at`) VALUES
	(5, 10, 298, 'COMPLETED', '2024-12-15 14:49:03', '2024-12-15 14:49:10', '2024-12-15 14:49:10', '2024-12-15 14:49:03'),
	(6, 10, 299, 'COMPLETED', '2024-12-15 14:49:13', '2024-12-15 14:49:16', '2024-12-15 14:49:16', '2024-12-15 14:49:13'),
	(7, 10, 300, 'IN_PROGRESS', '2024-12-15 14:49:19', NULL, NULL, '2024-12-15 14:49:19');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
