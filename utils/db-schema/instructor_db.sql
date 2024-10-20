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

-- membuang struktur untuk table instructor_db.flyway_schema_history
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

-- Membuang data untuk tabel instructor_db.flyway_schema_history: ~0 rows (lebih kurang)
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'INIT INSTRUCTOR SCHEMA', 'SQL', 'V1__INIT_INSTRUCTOR_SCHEMA.sql', 983115277, 'root', '2024-10-10 12:20:08', 59, 1),
	(2, '2', 'CREATE INSTRUCTOR STUDENTS TABLE', 'SQL', 'V2__CREATE_INSTRUCTOR_STUDENTS_TABLE.sql', 2046592213, 'root', '2024-10-12 09:47:04', 382, 1);

-- membuang struktur untuk table instructor_db.instructors
CREATE TABLE IF NOT EXISTS `instructors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `status` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `summary` text,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_instructors_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel instructor_db.instructors: ~4 rows (lebih kurang)
INSERT INTO `instructors` (`id`, `user_id`, `status`, `phone_number`, `summary`, `deleted_at`) VALUES
	(1, 1, 'pending', '0822341234', 'New summary2', NULL),
	(2, 4, 'pending', '082281666584', '<p>&nbsp;Lorem ipsum dolor sit amet, consectetur adipiscing elit. In a sapien sapien. Praesent in purus faucibus, condimentum nulla a, convallis massa. Morbi purus nulla, tincidunt id massa at, interdum fringilla erat. Nam aliquam, urna sit amet finibus varius, ex arcu gravida risus, nec malesuada lorem nibh non diam. Nulla sed interdum elit. Fusce aliquet ultricies enim id rutrum. Suspendisse potenti. Vivamus ante purus, egestas sit amet accumsan at, suscipit sed odio. Curabitur bibendum non nunc in sagittis. Sed a risus ac neque vulputate pellentesque. Cras pellentesque volutpat nulla, eget vehicula eros rutrum at. Curabitur volutpat augue non velit venenatis, sit amet convallis sapien tempus. In sodales ante vitae libero aliquam cursus. Sed a risus porttitor, blandit risus hendrerit, luctus libero. Ut interdum, nulla a suscipit auctor, ante turpis sagittis dolor, vel sodales mi libero eget lectus.</p>\r\n<p>Proin condimentum cursus elit et lacinia. In hac habitasse platea dictumst. Duis euismod feugiat sem, sed consectetur nunc dictum finibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Proin dictum quis ex sed bibendum. Duis ac tellus lorem. Suspendisse ut viverra tortor. Suspendisse non odio odio. Mauris eu blandit velit. Vivamus auctor auctor enim, quis suscipit nibh lacinia vitae. Fusce sodales risus sit amet mi molestie, vitae ullamcorper nisl tincidunt. Interdum et malesuada fames ac ante ipsum primis in faucibus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec maximus augue diam, sed facilisis velit venenatis id.&nbsp;</p>', NULL),
	(3, 10, 'pending', '08123456789', NULL, NULL),
	(4, 11, 'pending', '+1 (817) 705-2131', 'Totam quia dolorem e', NULL),
	(5, 12, 'pending', '08134567890', 'cia adalah valencia', NULL);

-- membuang struktur untuk table instructor_db.instructor_students
CREATE TABLE IF NOT EXISTS `instructor_students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel instructor_db.instructor_students: ~4 rows (lebih kurang)
INSERT INTO `instructor_students` (`id`, `instructor_id`, `user_id`, `created_at`, `updated_at`) VALUES
	(16, 2, 6, '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(17, 2, 3, '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(18, 2, 5, '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(19, 2, 13, '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(20, 1, 6, '2024-10-17 22:48:29', '2024-10-17 15:48:29');

-- membuang struktur untuk table instructor_db.instructor_student_courses
CREATE TABLE IF NOT EXISTS `instructor_student_courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_student_id` bigint DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_INSTRUCTOR_STUDENT_COURSES_ON_INSTRUCTOR_STUDENT` (`instructor_student_id`),
  CONSTRAINT `FK_INSTRUCTOR_STUDENT_COURSES_ON_INSTRUCTOR_STUDENT` FOREIGN KEY (`instructor_student_id`) REFERENCES `instructor_students` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel instructor_db.instructor_student_courses: ~6 rows (lebih kurang)
INSERT INTO `instructor_student_courses` (`id`, `instructor_student_id`, `course_id`, `order_id`, `created_at`, `updated_at`) VALUES
	(37, 16, 5, 51, '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(38, 17, 5, 52, '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(39, 17, 4, 53, '2024-10-16 20:46:13', '2024-10-16 13:46:12'),
	(40, 16, 4, 54, '2024-10-16 20:47:27', '2024-10-16 13:47:27'),
	(41, 18, 4, 55, '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(42, 18, 5, 55, '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(43, 19, 5, 56, '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(44, 20, 18, 58, '2024-10-17 22:48:29', '2024-10-17 15:48:29');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
