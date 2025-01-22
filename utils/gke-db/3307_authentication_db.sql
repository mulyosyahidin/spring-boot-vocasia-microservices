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

-- membuang struktur untuk table vocasia_db.forget_password
CREATE TABLE IF NOT EXISTS `forget_password` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `expired_at` timestamp NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- membuang struktur untuk table vocasia_db.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_users_email` (`email`),
  UNIQUE KEY `uc_users_uid` (`uid`),
  UNIQUE KEY `uc_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.users: ~12 rows (lebih kurang)
INSERT INTO `users` (`id`, `uid`, `email`, `username`, `name`, `password`, `role`, `profile_picture`, `created_at`, `updated_at`) VALUES
	(1, '11cbd864-cf8e-4834-80e7-2515c51bf737', 'instructor2@vocasia.app', 'instructor2', 'User - Instructor 2', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', 'lionel-messi-2324-miami.jpg', '2024-10-11 10:51:39', '2024-10-27 17:37:44'),
	(2, '8c86c151-a734-409e-9b5a-5c8aeef47ab4', 'admin@vocasia.app', 'admin', 'admin', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'admin', NULL, '2024-10-11 11:04:25', '2024-10-27 17:18:47'),
	(3, '4dcc94de-9c1a-4265-9986-9f8279b8c235', 'student1@vocasia.app', 'student1', 'User - Student 1', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-11 11:38:46', '2024-12-15 14:24:34'),
	(4, '8cb83ab8-b27e-40d4-9ce2-112b6d20025c', 'instructor1@vocasia.app', 'instructor1', 'User - Instructor 1', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', 'lionel-messi-2324-miami.jpg', '2024-10-11 11:52:39', '2024-12-15 14:32:10'),
	(5, '9a7a3ea0-b1b5-416e-9a98-b6b044c66c9e', 'student2@vocasia.app', 'student2', 'User - Student 2', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-11 18:01:24', '2024-12-15 14:33:59'),
	(6, 'dd30d61b-b5e9-4393-b965-da988d2974ea', 'student3@vocasia.app', 'student3', 'User - Student 3', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-12 14:01:47', '2024-12-15 14:39:43'),
	(9, 'b46adc55-7715-491e-81db-5efd975142e2', 'student4@vocasia.app', 'student4', 'User - Student 4', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-16 00:36:38', '2024-10-15 17:36:38'),
	(15, 'c28f9c1c-c585-4ad7-b84d-8ff202541b6e', 'martinms.za@gmail.com', 'martinms', 'Instruktur Mendaftar', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', NULL, '2024-12-15 14:51:17', '2024-12-15 07:51:16'),
	(16, '07b14db0-b854-4e8b-95ab-4c0dfafc3113', 'steve@gmail.com', 'steve', 'Steve Rogers', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', NULL, '2024-12-15 14:52:31', '2024-12-15 07:52:30');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
