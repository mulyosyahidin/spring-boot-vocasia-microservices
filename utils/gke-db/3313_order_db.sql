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

-- membuang struktur untuk table vocasia_db.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `total_items` int NOT NULL,
  `total_price` double NOT NULL,
  `total_discount` double NOT NULL,
  `payment_status` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- membuang struktur untuk table vocasia_db.order_items
CREATE TABLE IF NOT EXISTS `order_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  `course_instructor_id` bigint DEFAULT NULL,
  `course_title` varchar(255) NOT NULL,
  `course_slug` varchar(255) NOT NULL,
  `course_featured_picture_url` varchar(255) NOT NULL,
  `course_price` double NOT NULL,
  `course_is_free` bit(1) NOT NULL,
  `course_is_discount` bit(1) NOT NULL,
  `course_discount` double DEFAULT NULL,
  `course_subtotal` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ORDER_ITEMS_ON_ORDER` (`order_id`),
  CONSTRAINT `FK_ORDER_ITEMS_ON_ORDER` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `orders` (`id`, `user_id`, `order_number`, `total_items`, `total_price`, `total_discount`, `payment_status`, `created_at`, `updated_at`) VALUES
	(12, 3, '202412153QLYI', 1, 100000, 50000, 'success', '2024-12-15 14:29:17', '2024-12-15 14:29:43'),
	(13, 3, '202412153TGMM', 2, 499000, 0, 'success', '2024-12-15 14:30:22', '2024-12-15 14:30:32'),
	(14, 5, '202412155YEUF', 1, 119000, 10000, 'success', '2024-12-15 14:34:29', '2024-12-15 14:34:42'),
	(18, 5, '202412155BIQU', 1, 29000, 0, 'success', '2024-12-15 14:37:24', '2024-12-15 14:37:35'),
	(20, 6, '202412156MKYL', 1, 150000, 0, 'success', '2024-12-15 14:46:05', '2024-12-15 14:46:18'),
	(21, 6, '202412156UOQD', 1, 5000000, 0, 'success', '2024-12-15 14:46:35', '2024-12-15 14:46:51');

INSERT INTO `order_items` (`id`, `order_id`, `course_id`, `course_instructor_id`, `course_title`, `course_slug`, `course_featured_picture_url`, `course_price`, `course_is_free`, `course_is_discount`, `course_discount`, `course_subtotal`) VALUES
	(21, 12, 28, 2, 'Kelas Fotografi Online', 'kelas-fotografi-online', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730742993991-fotografiteknik.jpg', 150000, b'0', b'1', 50000, 100000),
	(22, 13, 26, 2, 'Real Estate Exam Concepts, Definitions, and More', 'real-estate-exam-concepts-definitions-and-more', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730742295438-real estate.jpeg', 300000, b'0', b'0', 0, 300000),
	(23, 13, 25, 2, 'Akuntansi Keuangan Menengah 1', 'akuntansi-keuangan-menengah-1', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730741510940-Akuntansi Keuangan Menengah 1.jpeg', 199000, b'0', b'0', 0, 199000),
	(24, 14, 21, 2, 'Tutorial Microsoft Word Untuk Pemula', 'tutorial-microsoft-word-untuk-pemula', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730740427424-Tutorial Microsoft Word Untuk Pemula.jpg', 129000, b'0', b'1', 10000, 119000),
	(31, 18, 22, 2, 'Belajar Excel Dasar dari Nol', 'belajar-excel-dasar-dari-nol', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730740733278-Belajar Excel Dasar dari Nol.jpg', 29000, b'0', b'0', 0, 29000),
	(33, 20, 27, 2, 'Ternak bebek peking 100 ekor analisa dan keuntungan', 'ternak-bebek-peking-100-ekor-analisa-dan-keuntungan', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730742655475-ternak-bebek-petelur.jpg', 150000, b'0', b'0', 0, 150000),
	(34, 21, 24, 2, 'Digital Marketing Course', 'digital-marketing-playlist-2024-updated-digital-marketing-course-digital-marketing-tutorial-for-beginners', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730741289923-dm.jpg', 5000000, b'0', b'0', 0, 5000000);


/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
