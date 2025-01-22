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

-- membuang struktur untuk table authentication_db.flyway_schema_history
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

-- Membuang data untuk tabel authentication_db.flyway_schema_history: ~1 rows (lebih kurang)
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'INIT AUTHENTICATION SCHEMA', 'SQL', 'V1__INIT_AUTHENTICATION_SCHEMA.sql', -1924168065, 'root', '2024-10-10 12:11:30', 105, 1);

-- membuang struktur untuk table authentication_db.users
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel authentication_db.users: ~11 rows (lebih kurang)
INSERT INTO `users` (`id`, `uid`, `email`, `username`, `name`, `password`, `role`, `profile_picture`, `created_at`, `updated_at`) VALUES
	(1, '618db28b-5fe5-490e-a644-14b332e06845', 'andi.wiyanto@gmail.com', 'andiwiyanto', 'Andi wyt', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', 'lionel-messi-2324-miami.jpg', '2024-10-11 10:51:39', '2024-10-16 01:03:03'),
	(2, '655a7c48-8d03-421a-b840-4cd670d979a4', 'admin@local.test', 'admin', 'admin', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'admin', NULL, '2024-10-11 11:04:25', '2024-10-11 04:04:25'),
	(3, '81e91275-bb95-4484-822f-99a199427244', 'martinms.za@gmail.com', 'martinms', 'Martin Mulyo Syahidin', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-11 11:38:46', '2024-10-11 04:38:46'),
	(4, 'c3a412c2-a1ae-43d7-9b33-912ed28a0128', 'jessy.mandasari@gmail.com', 'jessy', 'Jessy Mandasari', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', 'lionel-messi-2324-miami.jpg', '2024-10-11 11:52:39', '2024-10-14 21:08:20'),
	(5, 'bd05b4dc-d8aa-49dc-95bc-1824b21f9050', 'zumratulaini@gmail.com', 'zumratulaini', 'Zumratul Aini', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-11 18:01:24', '2024-10-11 11:01:23'),
	(6, '14e2baf5-4f28-4850-8238-758d32f578a1', 'ayuana@gmail.com', 'ayuana', 'Ayu Febriana', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-12 14:01:47', '2024-10-12 07:01:47'),
	(9, 'b46adc55-7715-491e-81db-5efd975142e2', 'sindi.vn@gmail.com', 'sindi', 'Sindi Vinatha', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-16 00:36:38', '2024-10-15 17:36:38'),
	(10, '5675f6dd-d5aa-4520-8002-d33a1a3ea2c1', 'steve@gmail.com', 'steve', 'Steve Rogers', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', NULL, '2024-10-16 05:33:57', '2024-10-15 22:33:56'),
	(11, '026b56c6-ed28-4a3b-9a9f-b2c77f18c658', 'tetun@mailinator.com', 'potabeko', 'Lisandra Chavez', '7EjSB/m0KHYUBJgAMqUiC0bCXme47X3QOzf4Vx4q2W0=', 'instructor', NULL, '2024-10-16 05:38:41', '2024-10-15 22:38:40'),
	(12, '343b1275-7a03-4d78-afe1-470ed112a301', 'cia@gmail.com', 'valencia', 'Valencia Gempita Anggana', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', NULL, '2024-10-16 05:39:25', '2024-10-15 22:39:24'),
	(13, 'f2921f44-4e93-46d0-8467-4fccd3fcd364', 'user1@local.test', 'user1', 'User 1', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-16 20:58:35', '2024-10-16 13:58:34');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
