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

-- membuang struktur untuk table vocasia_db.instructors
CREATE TABLE IF NOT EXISTS `instructors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `status` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `summary` text,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_instructors_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instructors: ~5 rows (lebih kurang)
INSERT INTO `instructors` (`id`, `user_id`, `status`, `phone_number`, `summary`, `deleted_at`) VALUES
	(1, 1, 'approved', '0822341234', 'New summary2', NULL),
	(2, 4, 'approved', '082281666584', 'summary', NULL),
	(3, 10, 'approved', '08123456789', NULL, NULL),
	(4, 11, 'approved', '+1 (817) 705-2131', 'Totam quia dolorem e', NULL),
	(5, 12, 'approved', '08134567890', 'cia adalah valencia', NULL),
	(6, 14, 'pending', '08121731385112375', NULL, NULL);

-- membuang struktur untuk table vocasia_db.instructor_students
CREATE TABLE IF NOT EXISTS `instructor_students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- membuang struktur untuk table vocasia_db.instructor_student_courses
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
