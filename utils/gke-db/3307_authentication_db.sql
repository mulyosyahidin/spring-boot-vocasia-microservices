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
	(1, '11cbd864-cf8e-4834-80e7-2515c51bf737', 'andi.wiyanto@gmail.com', 'andiwiyanto', 'Andi wyt', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', 'lionel-messi-2324-miami.jpg', '2024-10-11 10:51:39', '2024-10-27 17:37:44'),
	(2, '8c86c151-a734-409e-9b5a-5c8aeef47ab4', 'admin@local.test', 'admin', 'admin', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'admin', NULL, '2024-10-11 11:04:25', '2024-10-27 17:18:47'),
	(3, '3e63762c-2485-45b3-95ac-10ee6d246f24', 'martinms.za@gmail.com', 'martinms', 'Martin Mulyo Syahidin', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-11 11:38:46', '2024-10-30 00:52:22'),
	(4, '4bfcd82c-ec3b-4c5e-ba1f-d543920a12dc', 'i1@local.test', 'instructor1', 'Instructor 1', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', 'lionel-messi-2324-miami.jpg', '2024-10-11 11:52:39', '2024-10-28 20:03:17'),
	(5, 'bd05b4dc-d8aa-49dc-95bc-1824b21f9050', 'zumratulaini@gmail.com', 'zumratulaini', 'Zumratul Aini', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-11 18:01:24', '2024-10-11 11:01:23'),
	(6, '14e2baf5-4f28-4850-8238-758d32f578a1', 'ayuana@gmail.com', 'ayuana', 'Ayu Febriana', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-12 14:01:47', '2024-10-12 07:01:47'),
	(9, 'b46adc55-7715-491e-81db-5efd975142e2', 'sindi.vn@gmail.com', 'sindi', 'Sindi Vinatha', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-16 00:36:38', '2024-10-15 17:36:38');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
