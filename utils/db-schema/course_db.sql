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

-- membuang struktur untuk table course_db.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(8) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CATEGORIES_ON_PARENT` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel course_db.categories: ~140 rows (lebih kurang)
INSERT INTO `categories` (`id`, `type`, `parent_id`, `name`, `slug`, `icon`, `created_at`, `updated_at`) VALUES
	(1, 'parent', NULL, 'Akademik', 'akademik', NULL, '2024-10-11 11:54:54', '2024-10-11 14:19:38'),
	(2, 'parent', NULL, 'Akuntansi dan Keuangan', 'akuntansi-dan-keuangan', NULL, '2024-10-11 11:58:52', '2024-10-11 06:11:50'),
	(3, 'parent', NULL, 'Bisnis', 'bisnis', NULL, '2024-10-11 13:06:53', '2024-10-11 06:12:23'),
	(4, 'parent', NULL, 'Desain', 'desain', NULL, '2024-10-11 13:09:37', '2024-10-11 06:12:23'),
	(5, 'parent', NULL, 'Hobi & Lifestyle', 'hobi-lifestyle', NULL, '2024-10-11 13:09:59', '2024-10-11 06:12:23'),
	(6, 'parent', NULL, 'Ilmu Komputer', 'ilmu-komputer', NULL, '2024-10-11 13:12:39', '2024-10-11 06:12:38'),
	(7, 'parent', NULL, 'Pemasaran', 'pemasaran', NULL, '2024-10-11 13:12:45', '2024-10-11 06:12:44'),
	(8, 'parent', NULL, 'Personal Development', 'personal-development', NULL, '2024-10-11 13:12:49', '2024-10-11 06:12:48'),
	(9, 'parent', NULL, 'Produktivitas Kantor', 'produktivitas-kantor', NULL, '2024-10-11 13:12:58', '2024-10-11 06:12:57'),
	(10, 'parent', NULL, 'Teknik', 'teknik', NULL, '2024-10-11 13:13:04', '2024-10-11 06:13:03'),
	(11, 'parent', NULL, 'Video dan Fotografi', 'video-dan-fotografi', NULL, '2024-10-11 13:13:10', '2024-10-11 06:13:10'),
	(12, 'child', 1, 'Bahasa', 'bahasa', NULL, '2024-10-11 13:15:35', '2024-10-11 06:15:34'),
	(13, 'child', 1, 'Engineering', 'engineering', NULL, '2024-10-11 13:15:42', '2024-10-11 06:15:41'),
	(14, 'child', 1, 'Humanities', 'humanities', NULL, '2024-10-11 13:16:00', '2024-10-11 06:15:59'),
	(15, 'child', 1, 'Math', 'math', NULL, '2024-10-11 13:16:04', '2024-10-11 06:16:04'),
	(16, 'child', 1, 'Online Education', 'online-education', NULL, '2024-10-11 13:16:15', '2024-10-11 06:16:15'),
	(17, 'child', 1, 'Other Teaching & Academics', 'other-teaching-academics', NULL, '2024-10-11 13:16:30', '2024-10-11 06:16:29'),
	(18, 'child', 1, 'Social Science', 'social-science', NULL, '2024-10-11 13:16:39', '2024-10-11 06:16:39'),
	(19, 'child', 1, 'Teacher Training', 'teacher-training', NULL, '2024-10-11 13:16:53', '2024-10-11 06:16:52'),
	(20, 'child', 1, 'Test Preparation', 'test-preparation', NULL, '2024-10-11 13:17:00', '2024-10-11 06:16:59'),
	(21, 'child', 1, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:17:06', '2024-10-11 06:17:06'),
	(22, 'child', 2, 'Akuntansi dan Pembukuan', 'akuntansi-dan-pembukuan', NULL, '2024-10-11 13:18:57', '2024-10-11 06:18:56'),
	(23, 'child', 2, 'Compliance', 'compliance', NULL, '2024-10-11 13:19:08', '2024-10-11 06:19:08'),
	(24, 'child', 2, 'Cryptocurrency and Blockchain', 'cryptocurrency-and-blockchain', NULL, '2024-10-11 13:19:25', '2024-10-11 06:19:25'),
	(25, 'child', 2, 'Ekonomi', 'ekonomi', NULL, '2024-10-11 13:19:35', '2024-10-11 06:19:35'),
	(26, 'child', 2, 'Finance Management Tools', 'finance-management-tools', NULL, '2024-10-11 13:19:47', '2024-10-11 06:19:46'),
	(27, 'child', 2, 'Inventasi & Trading', 'inventasi-trading', NULL, '2024-10-11 13:19:58', '2024-10-11 06:19:57'),
	(28, 'child', 2, 'Keuangan', 'keuangan', NULL, '2024-10-11 13:20:05', '2024-10-11 06:20:05'),
	(29, 'child', 2, 'Pajak', 'pajak', NULL, '2024-10-11 13:20:10', '2024-10-11 06:20:09'),
	(30, 'child', 2, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:20:17', '2024-10-11 06:20:17'),
	(31, 'child', 3, 'Finance', 'finance', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(32, 'child', 3, 'Entrepreneurship', 'entrepreneurship', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(33, 'child', 3, 'Communications', 'communications', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(34, 'child', 3, 'Management', 'management', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(35, 'child', 3, 'Sales', 'sales', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(36, 'child', 3, 'Strategy', 'strategy', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(37, 'child', 3, 'Operations', 'operations', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(38, 'child', 3, 'Project Management', 'project-management', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(39, 'child', 3, 'Business Law', 'business-law', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(40, 'child', 3, 'Data and Analytics', 'data-and-analytics', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(41, 'child', 3, 'Home Business', 'home-business', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(42, 'child', 3, 'Human Resources', 'human-resources', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(43, 'child', 3, 'Industry', 'industry', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(44, 'child', 3, 'Media', 'media', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(45, 'child', 3, 'Real Estate', 'real-estate', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(46, 'child', 3, 'Lainnya', 'Lainnya', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(47, 'child', 3, 'Online Shop', 'online-shop', NULL, '2024-10-11 13:21:50', '2024-10-11 06:21:49'),
	(48, 'child', 4, 'Desain Arsitektur', 'desain-arsitektur', NULL, '2024-10-11 13:21:50', '2024-10-11 06:26:47'),
	(49, 'child', 4, '3D & Animation', '3d-animation', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(50, 'child', 4, 'Desain Web', 'desain-web', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(51, 'child', 4, 'Desain Grafis', 'desain-grafis', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(52, 'child', 4, 'Game Design', 'game-design', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(53, 'child', 4, 'UI/UX', 'ui-ux', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(54, 'child', 4, 'Rancangan Desain', 'rancangan-desain', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(55, 'child', 4, 'Fashion', 'fashion', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(56, 'child', 4, 'Desain Interior', 'desain-interior', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(57, 'child', 4, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(58, 'child', 4, 'Desain Web', 'desain-web', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(59, 'child', 4, 'Desain Grafis', 'desain-grafis', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(60, 'child', 4, 'Game Design', 'game-design', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(61, 'child', 4, 'UI/UX', 'ui-ux', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(62, 'child', 4, 'Rancangan Desain', 'rancangan-desain', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(63, 'child', 4, 'Fashion', 'fashion', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(64, 'child', 4, 'Desain Interior', 'desain-interior', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(65, 'child', 4, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:26:20', '2024-10-11 06:26:20'),
	(66, 'child', 5, 'Arts and Crafts', 'arts-and-crafts', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(67, 'child', 5, 'Music', 'music', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(68, 'child', 5, 'Robotics', 'robotics', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(69, 'child', 5, 'Ternak & Budidaya', 'ternak-budidaya', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(70, 'child', 5, 'Food & Beverage', 'food-beverage', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(71, 'child', 5, 'Beauty & Makeup', 'beauty-makeup', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(72, 'child', 5, 'Travel', 'travel', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(73, 'child', 5, 'Gaming', 'gaming', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(74, 'child', 5, 'Home Improvement', 'home-improvement', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(75, 'child', 5, 'Pet Care & Training', 'pet-care-training', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(76, 'child', 5, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:28:55', '2024-10-11 06:28:55'),
	(77, 'child', 6, 'Bahasa Pemrograman', 'bahasa-pemrograman', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(78, 'child', 6, 'Mobile Apps', 'mobile-apps', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(79, 'child', 6, 'Data Science', 'data-science', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(80, 'child', 6, 'Game Development', 'game-development', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(81, 'child', 6, 'Database', 'database', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(82, 'child', 6, 'Software Testing', 'software-testing', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(83, 'child', 6, 'Development Tools', 'development-tools', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(84, 'child', 6, 'IT & Certification', 'it-certification', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(85, 'child', 6, 'Network & Security', 'network-security', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(86, 'child', 6, 'Hardware', 'hardware', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(87, 'child', 6, 'Sistem Operasi', 'sistem-operasi', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(88, 'child', 6, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(89, 'child', 6, 'Web Development', 'web-development', NULL, '2024-10-11 13:31:39', '2024-10-11 06:31:38'),
	(90, 'child', 7, 'Branding', 'branding', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(91, 'child', 7, 'Digital Marketing', 'digital-marketing', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(92, 'child', 7, 'Search Engine Optimization', 'search-engine-optimization', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(93, 'child', 7, 'Social Media Marketing', 'social-media-marketing', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(94, 'child', 7, 'Dasar-Dasar Pemasaran', 'dasar-dasar-pemasaran', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(95, 'child', 7, 'Analythic & Automation', 'analythic-automation', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(96, 'child', 7, 'Public Relations', 'public-relations', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(97, 'child', 7, 'Iklan', 'iklan', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(98, 'child', 7, 'Video & Mobile Marketing', 'video-mobile-marketing', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(99, 'child', 7, 'Pemasaran Konten', 'pemasaran-konten', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(100, 'child', 7, 'Growth Hacking', 'growth-hacking', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(101, 'child', 7, 'Pemasaran afiliasi', 'pemasaran-afiliasi', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(102, 'child', 7, 'Pemasaran Produk', 'pemasaran-produk', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(103, 'child', 7, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(104, 'child', 7, 'Copywriting', 'copywriting', NULL, '2024-10-11 13:34:59', '2024-10-11 06:34:59'),
	(105, 'child', 8, 'Career Development', 'career-development', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(106, 'child', 8, 'Personal Transformation', 'personal-transformation', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(107, 'child', 8, 'Productivity', 'productivity', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(108, 'child', 8, 'Leadership', 'leadership', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(109, 'child', 8, 'Personal Finance', 'personal-finance', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(110, 'child', 8, 'Parenting & Relationships', 'parenting-relationships', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(111, 'child', 8, 'Happiness', 'happiness', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(112, 'child', 8, 'Religion & Spirituality', 'religion-spirituality', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(113, 'child', 8, 'Personal Brand Building', 'personal-brand-building', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(114, 'child', 8, 'Creativity', 'creativity', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(115, 'child', 8, 'Influence', 'influence', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(116, 'child', 8, 'Self Esteem', 'self-esteem', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(117, 'child', 8, 'Stress Management', 'stress-management', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(118, 'child', 8, 'Memory & Study Skills', 'memory-study-skills', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(119, 'child', 8, 'Motivation', 'motivation', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(120, 'child', 8, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:38:33', '2024-10-11 06:38:33'),
	(121, 'child', 9, 'Apple', 'apple', NULL, '2024-10-11 13:41:53', '2024-10-11 06:41:53'),
	(122, 'child', 9, 'Microsoft', 'microsoft', NULL, '2024-10-11 13:41:53', '2024-10-11 06:41:53'),
	(123, 'child', 9, 'Google', 'google', NULL, '2024-10-11 13:41:53', '2024-10-11 06:41:53'),
	(124, 'child', 9, 'SAP', 'sap', NULL, '2024-10-11 13:41:53', '2024-10-11 06:41:53'),
	(125, 'child', 9, 'Oracle', 'oracle', NULL, '2024-10-11 13:41:53', '2024-10-11 06:41:53'),
	(126, 'child', 9, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:41:53', '2024-10-11 06:41:53'),
	(127, 'child', 10, 'Las dan Metal', 'las-dan-metal', NULL, '2024-10-11 13:43:06', '2024-10-11 06:43:05'),
	(128, 'child', 10, 'Mesin & Otomotif', 'mesin-otomotif', NULL, '2024-10-11 13:43:06', '2024-10-11 06:43:05'),
	(129, 'child', 10, 'Listrik & Elektronika', 'listrik-elektronika', NULL, '2024-10-11 13:43:06', '2024-10-11 06:43:05'),
	(130, 'child', 10, 'Sipil & Pembangunan', 'sipil-pembangunan', NULL, '2024-10-11 13:43:06', '2024-10-11 06:43:05'),
	(131, 'child', 10, 'Pabrik & Industri', 'pabrik-industri', NULL, '2024-10-11 13:43:06', '2024-10-11 06:43:05'),
	(132, 'child', 10, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:43:06', '2024-10-11 06:43:05'),
	(133, 'child', 11, 'Video Design', 'video-design', NULL, '2024-10-11 13:45:10', '2024-10-11 06:45:10'),
	(134, 'child', 11, 'Digital Photography', 'digital-photography', NULL, '2024-10-11 13:45:10', '2024-10-11 06:45:10'),
	(135, 'child', 11, 'Photography Fundamentals', 'photography-fundamentals', NULL, '2024-10-11 13:45:10', '2024-10-11 06:45:10'),
	(136, 'child', 11, 'Portraits', 'portraits', NULL, '2024-10-11 13:45:10', '2024-10-11 06:45:10'),
	(137, 'child', 11, 'Photography Tools', 'photography-tools', NULL, '2024-10-11 13:45:10', '2024-10-11 06:45:10'),
	(138, 'child', 11, 'Commercial Photography', 'commercial-photography', NULL, '2024-10-11 13:45:10', '2024-10-11 06:45:10'),
	(139, 'child', 11, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:45:10', '2024-10-11 06:45:10'),
	(163, 'parent', NULL, 'TEST INDUK [edit 2]', 'test-induk', '1728859607534-5.svg', '2024-10-14 05:46:49', '2024-10-14 06:03:47');

-- membuang struktur untuk table course_db.chapters
CREATE TABLE IF NOT EXISTS `chapters` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint NOT NULL,
  `title` varchar(255) NOT NULL,
  `total_duration` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CHAPTERS_ON_COURSE` (`course_id`),
  CONSTRAINT `FK_CHAPTERS_ON_COURSE` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel course_db.chapters: ~16 rows (lebih kurang)
INSERT INTO `chapters` (`id`, `course_id`, `title`, `total_duration`, `created_at`, `updated_at`) VALUES
	(1, 4, 'Pendahuluan', '30m', '2024-10-11 17:05:26', '2024-10-11 10:05:26'),
	(2, 4, 'Providers', '12m', '2024-10-11 17:05:53', '2024-10-11 10:05:53'),
	(3, 4, 'Consumers', '12m', '2024-10-11 17:05:59', '2024-10-11 10:30:52'),
	(5, 4, 'Notifiers', '10m', '2024-10-11 17:18:10', '2024-10-11 10:30:52'),
	(6, 4, 'Penutup', '20m', '2024-10-11 17:19:08', '2024-10-11 10:30:52'),
	(10, 5, 'Bab 1', '1j 49m 42d', '2024-10-12 12:19:29', '2024-10-12 05:19:28'),
	(14, 5, 'Bab 2', '1j 22m 24d', '2024-10-12 12:25:13', '2024-10-12 05:25:12'),
	(15, 5, 'Bab 3', '22m 21d', '2024-10-12 12:25:24', '2024-10-12 05:25:23'),
	(18, 17, 'Bab 1', '20m', '2024-10-15 02:04:04', '2024-10-15 02:32:04'),
	(19, 17, 'Bab 2', '31m', '2024-10-15 02:05:24', '2024-10-15 02:33:38'),
	(20, 17, 'Bab 3', '1j 12m', '2024-10-15 02:05:30', '2024-10-15 02:33:50'),
	(21, 18, 'Dasar-Dasar Spring Boot', '8j 9m 7d', '2024-10-16 22:45:30', NULL),
	(22, 18, 'Pengelolaan Data dan Validasi', '6j 14m 35d', '2024-10-16 22:46:41', NULL),
	(23, 18, 'Arsitektur dan Pengelolaan Aplikasi', '1j 13m 50d', '2024-10-16 22:47:22', NULL),
	(24, 18, 'Pengembangan Web dan API', '8j 25m 8d', '2024-10-16 22:48:03', NULL),
	(25, 18, 'Pemantauan dan Keandalan Aplikasi', '57m 24d', '2024-10-16 22:48:18', NULL);

-- membuang struktur untuk table course_db.courses
CREATE TABLE IF NOT EXISTS `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `total_duration` varchar(255) DEFAULT NULL,
  `level` varchar(255) NOT NULL DEFAULT 'all',
  `language` varchar(255) NOT NULL,
  `description` longtext,
  `short_description` text,
  `featured_picture` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `is_free` bit(1) NOT NULL,
  `is_discount` bit(1) NOT NULL,
  `discount` double DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'draft',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CATEGORY_COURSE` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel course_db.courses: ~4 rows (lebih kurang)
INSERT INTO `courses` (`id`, `instructor_id`, `category_id`, `title`, `slug`, `total_duration`, `level`, `language`, `description`, `short_description`, `featured_picture`, `price`, `is_free`, `is_discount`, `discount`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
	(4, 2, 78, 'Riverpod Crash Course', 'riverpod-crash-course', '1j 12m', 'beginner', 'id', '<p><strong>Riverpod Crash Course untuk Pemula: Menguasai State Management di Flutter</strong></p>\n<p>Riverpod adalah salah satu solusi state management yang modern dan efisien untuk aplikasi Flutter. Dibandingkan dengan <em>provider</em>, Riverpod menawarkan beberapa keunggulan, seperti lebih fleksibel dan lebih mudah diatur dalam hal <em>dependency injection</em>. Dalam kursus ini, kita akan mempelajari dasar-dasar Riverpod, mulai dari memahami konsep state hingga bagaimana cara mengimplementasikannya dalam aplikasi Flutter. <strong>Kursus ini dirancang untuk pemula</strong>, jadi Anda tidak perlu khawatir jika belum memiliki pengalaman dengan state management sebelumnya.</p>\n<h5><strong>Apa Itu Riverpod?</strong></h5>\n<p>Riverpod adalah pustaka yang dibangun di atas <em>provider</em>, namun dengan beberapa penyempurnaan yang membuatnya lebih kuat dan aman. Salah satu perbedaan utamanya adalah Riverpod menggunakan <em>syntax</em> yang lebih bersih dan tipe yang lebih jelas, sehingga meminimalisir kesalahan saat coding. Dalam kursus ini, kita akan membahas <strong>cara kerja Riverpod</strong> dan bagaimana Anda bisa memanfaatkan keunggulannya untuk menciptakan aplikasi yang lebih terstruktur dan mudah dipelihara.</p>\n<h5><strong>Memulai dengan Riverpod</strong></h5>\n<p>Tahap pertama dari kursus ini akan mengajarkan Anda cara menginstal dan mengkonfigurasi Riverpod dalam proyek Flutter. Kita akan mulai dari langkah-langkah dasar seperti menambahkan <em>dependencies</em> di <em>pubspec.yaml</em> hingga membuat <em>provider</em> sederhana. Di sini, <strong>kita akan mengenal dua jenis provider utama</strong>, yaitu <code>StateProvider</code> untuk state sederhana dan <code>FutureProvider</code> untuk pengelolaan state asinkron. Pemahaman tentang perbedaan dan penggunaan kedua provider ini akan menjadi landasan kuat untuk menguasai Riverpod.</p>\n<h5><strong>Membangun Aplikasi dengan Riverpod</strong></h5>\n<p>Setelah memahami dasar-dasarnya, kita akan langsung mempraktikkannya dengan membangun aplikasi Flutter sederhana. Anda akan belajar <strong>bagaimana cara menghubungkan UI Flutter dengan state yang dikelola oleh Riverpod</strong>, serta bagaimana memisahkan logika bisnis dari tampilan agar aplikasi lebih terstruktur. Pada tahap ini, Anda akan memahami pentingnya pemisahan <em>concern</em> dalam pengembangan aplikasi yang scalable dan mudah di-debug.</p>\n<h5><strong>Menangani State yang Kompleks</strong></h5>\n<p>Di bagian akhir kursus, kita akan mempelajari bagaimana menangani state yang lebih kompleks, seperti <em>state</em> yang berasal dari API atau database. Anda akan belajar menggunakan <code>StreamProvider</code> dan <code>StateNotifierProvider</code> untuk skenario yang lebih dinamis. Selain itu, <strong>kita juga akan membahas teknik terbaik dalam menggunakan Riverpod</strong>, seperti <em>caching</em>, <em>error handling</em>, dan <em>testing</em>. Setelah menyelesaikan kursus ini, Anda akan siap untuk membangun aplikasi Flutter dengan Riverpod secara lebih percaya diri.</p>\n<p>Kursus ini memberikan <strong>fondasi yang solid</strong> bagi siapa pun yang ingin memahami dan menguasai Riverpod di Flutter. Anda akan diajak melangkah dari pemahaman dasar hingga skenario penggunaan yang lebih kompleks, sehingga mampu menerapkannya dalam proyek nyata.</p>', '<p>Kursus Riverpod ini memberikan pemahaman mendalam tentang state management di Flutter, dimulai dari konsep dasar hingga implementasi dalam skenario yang kompleks. Dengan mempelajari berbagai jenis <em>provider</em> seperti <code>StateProvider</code>, <code>FutureProvider</code>, dan <code>StateNotifierProvider</code>, Anda akan mampu mengelola state dengan lebih efisien dan terstruktur. Setelah menyelesaikan kursus ini, Anda akan memiliki keterampilan yang solid untuk membangun aplikasi Flutter yang scalable dan mudah dipelihara menggunakan Riverpod.</p>', 'Riverpod Crash Course.jpg', 99000, b'0', b'0', 0, 'published', '2024-10-11 16:49:53', '2024-10-11 21:10:37', NULL),
	(5, 2, 81, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', '3j 33m 49d', 'intermediate', 'id', '<p>Kursus "Tutorial Redis Dasar (Bahasa Indonesia)" adalah panduan komprehensif untuk memahami dasar-dasar Redis, salah satu sistem penyimpanan data berbasis memori yang populer. Redis sering digunakan untuk caching, pub/sub, dan berbagai aplikasi data real-time lainnya. Kursus ini dibagi menjadi tiga bagian yang masing-masing mencakup topik penting dalam pengoperasian dan pemanfaatan Redis secara efisien.</p>\n<p>Pada <strong>bagian pertama</strong>, peserta akan mempelajari dasar-dasar Redis, mulai dari pengenalan dan instalasi hingga konfigurasi. Anda akan diajak memahami struktur data yang mendasari Redis, termasuk penggunaan strings, expiration untuk pengelolaan masa simpan data, dan teknik increment serta decrement. Selain itu, materi ini juga mengupas fitur-fitur penting lainnya seperti transaction, monitor, dan pipeline yang sering digunakan dalam pengelolaan transaksi dan performa Redis.</p>\n<p><strong>Bagian kedua</strong> berfokus pada struktur data yang lebih kompleks yang ditawarkan Redis. Mulai dari lists, sets, hashes, hingga sorted sets, Anda akan belajar bagaimana memanfaatkan masing-masing untuk kasus penggunaan yang sesuai. Selain itu, fitur geospatial dan HyperLogLog juga akan dibahas untuk mengelola data spasial dan perkiraan jumlah data yang unik secara efisien.</p>\n<p>Di <strong>bagian ketiga</strong>, topik yang diangkat adalah Pub/Sub, yang memungkinkan komunikasi pesan real-time antar server dan klien. Peserta akan belajar cara kerja Pub/Sub, bagaimana mengelola channel, serta konsep publisher dan subscriber. Bagian ini penting untuk memahami bagaimana Redis digunakan dalam skenario aplikasi real-time, di mana batasan dan limitasi teknis Redis dalam penggunaan Pub/Sub juga akan dibahas.</p>\n<p>Setiap bagian kursus ini dirancang untuk memandu Anda dari konsep paling dasar hingga fitur yang lebih kompleks, dengan contoh praktis dan skenario dunia nyata. Dengan mengikuti kursus ini, peserta akan mendapatkan pemahaman menyeluruh tentang Redis dan bagaimana memanfaatkannya dalam proyek aplikasi skala besar atau kecil.</p>', '<p>Kursus "Tutorial Redis Dasar (Bahasa Indonesia)" adalah panduan lengkap untuk memahami dasar-dasar Redis, mulai dari instalasi hingga fitur-fitur penting seperti strings, sets, hashes, pub/sub, dan transaksi. Kursus ini dibagi menjadi tiga bagian, membahas struktur data, manajemen koneksi, keamanan, hingga komunikasi real-time menggunakan Pub/Sub. Dengan contoh praktis dan penjelasan mendalam, kursus ini cocok bagi pemula maupun pengguna Redis yang ingin memperdalam pemahaman mereka dalam membangun aplikasi yang efisien dan real-time.</p>', 'Belajar Redis.jpg', 100000, b'0', b'0', 0, 'published', '2024-10-12 12:14:32', '2024-10-12 12:29:41', NULL),
	(17, 2, 81, 'React JS', 'test-kursus-123', '1j 2m', 'all', 'Bahasa Indonesia', '<p>lagi</p>', '<p>lagi</p>', '1728914131233-Belajar React JS Bahasa Indonesia.jpg', 10000, b'0', b'0', 0, 'draft', '2024-10-14 20:28:29', '2024-10-15 01:39:38', NULL),
	(18, 1, 77, 'TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)', 'tutorial-spring-boot-dasar-bahasa-indonesia', '25j 50m 4d', 'all', 'id', '<p>Spring Boot adalah framework berbasis Java yang dirancang untuk memudahkan pengembangan aplikasi berbasis Spring dengan cepat dan efisien. Dalam tutorial ini, Anda akan diajak memahami dasar-dasar penggunaan Spring Boot, mulai dari konfigurasi proyek hingga pengelolaan dependensi. Tutorial ini sangat cocok bagi pemula yang ingin membangun aplikasi backend menggunakan Java, serta mereka yang ingin memahami bagaimana cara kerja Spring Boot dalam menyederhanakan pengembangan aplikasi web, REST API, dan microservices.</p>\n<p>Pada tahap awal, Anda akan belajar cara membuat proyek Spring Boot menggunakan Spring Initializr, sebuah alat yang memungkinkan Anda menghasilkan struktur proyek dengan cepat. Anda juga akan diajarkan bagaimana cara mengatur konfigurasi yang tepat serta memahami file seperti <code>application.properties</code> atau <code>application.yml</code>. Pengaturan ini penting karena berperan dalam menentukan perilaku aplikasi, mulai dari pengaturan port server, koneksi database, hingga pengaturan keamanan dasar.</p>\n<p>Selain itu, tutorial ini akan membahas konsep penting seperti Dependency Injection (DI) dan Inversion of Control (IoC) yang merupakan inti dari Spring Framework. Dengan memahami cara kerja DI, Anda akan lebih mudah membuat kode yang lebih modular dan mudah dikelola. Selain itu, akan dijelaskan cara membuat controller untuk menangani request HTTP dan menggunakan RESTful API untuk berinteraksi dengan data dalam aplikasi Anda.</p>\n<p>Tutorial ini juga akan mengajak Anda mempelajari integrasi Spring Boot dengan database menggunakan JPA (Java Persistence API) dan Hibernate. Anda akan dipandu langkah demi langkah mulai dari koneksi ke database, membuat entitas, hingga melakukan operasi CRUD (Create, Read, Update, Delete) dengan repository Spring Data JPA. Dengan demikian, Anda dapat membangun aplikasi yang tidak hanya bisa menerima dan mengelola request, tetapi juga menyimpan dan mengambil data dari database.</p>\n<p>Terakhir, tutorial ini akan menyinggung beberapa fitur tambahan Spring Boot seperti penggunaan Spring Security untuk melindungi aplikasi Anda dari akses tidak sah, serta cara deploy aplikasi ke platform cloud seperti Heroku atau AWS. Dengan demikian, setelah menyelesaikan tutorial ini, Anda akan memiliki fondasi yang kuat dalam pengembangan aplikasi menggunakan Spring Boot, yang dapat langsung diterapkan untuk membangun proyek nyata.</p>', '<p><strong>Tutorial Spring Boot Dasar (Bahasa Indonesia)</strong> memberikan pemahaman mendalam tentang dasar-dasar pengembangan aplikasi menggunakan Spring Boot, mulai dari konfigurasi awal proyek, implementasi Dependency Injection, hingga integrasi dengan database menggunakan JPA. Dengan mempelajari konsep-konsep inti seperti REST API dan Spring Security, Anda akan memiliki fondasi yang kokoh untuk mengembangkan aplikasi web dan microservices yang efisien dan scalable. Tutorial ini cocok bagi pemula dan memberikan panduan langkah demi langkah untuk menerapkan Spring Boot dalam proyek nyata.</p>', '1729093329452-TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA).jpg', 200000, b'0', b'1', 50000, 'published', '2024-10-16 22:41:59', '2024-10-16 22:59:24', NULL);

-- membuang struktur untuk table course_db.flyway_schema_history
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

-- Membuang data untuk tabel course_db.flyway_schema_history: ~4 rows (lebih kurang)
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'INIT COURSE SCHEMA', 'SQL', 'V1__INIT_COURSE_SCHEMA.sql', 1382165224, 'root', '2024-10-10 12:11:51', 403, 1),
	(2, '2', 'UPDATE SHORT DESCRIPTION COLUMN FROM COURSES TABLE', 'SQL', 'V2__UPDATE_SHORT_DESCRIPTION_COLUMN_FROM_COURSES_TABLE.sql', 718135719, 'root', '2024-10-11 07:27:28', 287, 1),
	(3, '6', '', 'SQL', 'V6__.sql', 2070832565, 'root', '2024-10-13 20:23:08', 1309, 1),
	(4, '9', '', 'SQL', 'V9__.sql', -553481894, 'root', '2024-10-17 08:01:20', 232, 1);

-- membuang struktur untuk table course_db.lessons
CREATE TABLE IF NOT EXISTS `lessons` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `chapter_id` bigint NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `need_previous_lesson` bit(1) NOT NULL,
  `is_free` bit(1) NOT NULL,
  `content_video_duration` varchar(255) DEFAULT NULL,
  `content_video_url` varchar(255) DEFAULT NULL,
  `content_text` text,
  `attachment_file_name` varchar(255) DEFAULT NULL,
  `attachment_file_url` varchar(255) DEFAULT NULL,
  `attachment_link` varchar(255) DEFAULT NULL,
  `attachment_link_name` varchar(255) DEFAULT NULL,
  `attachment_type` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_LESSONS_ON_CHAPTER` (`chapter_id`),
  CONSTRAINT `FK_LESSONS_ON_CHAPTER` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel course_db.lessons: ~29 rows (lebih kurang)
INSERT INTO `lessons` (`id`, `chapter_id`, `title`, `type`, `need_previous_lesson`, `is_free`, `content_video_duration`, `content_video_url`, `content_text`, `attachment_file_name`, `attachment_file_url`, `attachment_link`, `attachment_link_name`, `attachment_type`, `created_at`, `updated_at`) VALUES
	(1, 1, 'Riverpod Crash Course #1 - Why Use Riverpod?', 'video', b'0', b'0', '6m 50d', 'https://www.youtube.com/watch?v=4zDYqKEQcDQ&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:18:50', '2024-10-11 10:18:49'),
	(2, 1, 'Riverpod Crash Course #2 - Setup & Installing Riverpod', 'video', b'0', b'0', '16m 12d', 'https://www.youtube.com/watch?v=8194MeQfuJs&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=2', NULL, NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:29:12', '2024-10-11 10:43:45'),
	(3, 2, 'Riverpod Crash Course #3 - Providers', 'video', b'0', b'0', '5m 47d', 'https://www.youtube.com/watch?v=L9b5LN4Uyjs&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=3', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:29:38', '2024-10-11 10:29:38'),
	(4, 3, 'Riverpod Crash Course #4 - Stateless Consumers', 'video', b'0', b'0', '7m 29d', 'https://www.youtube.com/watch?v=Jun1sss_6u4&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=4', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:48:58', '2024-10-11 10:49:09'),
	(5, 3, 'Riverpod Crash Course #5 - Stateful Consumers', 'video', b'0', b'0', '5m 46d', 'https://www.youtube.com/watch?v=vQpEzU8s1EI&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=5', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:30:18', '2024-10-11 10:30:18'),
	(6, 5, 'Riverpod Crash Course #6 - Generated Providers', 'video', b'0', b'0', '8m 21d', 'https://www.youtube.com/watch?v=EtyWwkQXTn0&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=6', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:31:08', '2024-10-11 10:31:08'),
	(7, 5, 'Riverpod Crash Course #7 - Notifier Providers', 'video', b'0', b'0', '10m 11d', 'https://www.youtube.com/watch?v=DzPyVYXwFRM&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=7', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:31:26', '2024-10-11 10:31:26'),
	(8, 5, 'Riverpod Crash Course #8 - Updating State', 'video', b'0', b'0', '6m 51d', 'https://www.youtube.com/watch?v=5_wU4zpGZ2I&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=8', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:31:44', '2024-10-11 10:31:44'),
	(9, 5, 'Riverpod Crash Course #9 - Generated Notifier Provider', 'video', b'0', b'0', '5m 2d', 'https://www.youtube.com/watch?v=-phSGLYolQw&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=9', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:32:01', '2024-10-11 10:32:01'),
	(10, 5, 'Riverpod Crash Course #10 - Dependent Providers', 'video', b'0', b'0', '5m 1d', 'https://www.youtube.com/watch?v=JyJ19DwXCkI&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=10', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:32:16', '2024-10-11 10:32:44'),
	(11, 6, 'Riverpod Crash Course #11 - Final Touches', 'video', b'0', b'0', '4m 7d', 'https://www.youtube.com/watch?v=WgC6xfNPZC0&list=PL4cUxeGkcC9i88WGZ9eIfQUWRgPstLFLp&index=11', '', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:33:04', '2024-10-11 10:33:03'),
	(12, 6, 'Catatan', 'text', b'0', b'0', '', '', '<div id="lipsum">\n<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi consectetur ex vel suscipit tempor. Praesent at sodales ligula. Curabitur luctus sollicitudin ex at sodales. Aliquam quis mi dignissim, aliquet quam non, aliquam lorem. Vivamus in tellus in eros iaculis volutpat. Suspendisse et gravida lectus. Donec molestie at justo eu viverra. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Quisque blandit, felis eget fermentum tincidunt, elit dolor interdum nisl, ac pellentesque mauris turpis vitae dui. Praesent tempor rutrum fermentum. Mauris consectetur, metus et pellentesque placerat, urna orci lacinia augue, non maximus orci erat a libero. Curabitur eget orci justo.</p>\n<p>Maecenas dictum pharetra est, in molestie ipsum posuere vel. Proin feugiat ante non urna euismod suscipit. Donec nec turpis nulla. Vestibulum eu suscipit arcu, et tincidunt orci. Cras nec efficitur felis. Mauris tincidunt magna eros, vel congue dolor scelerisque at. Aenean porta id sem et pharetra. Aenean pretium dolor sed dapibus dignissim. Pellentesque sit amet molestie elit. Nulla facilisi. Maecenas condimentum condimentum eros quis pellentesque. Maecenas eget urna malesuada, pulvinar ante eu, tincidunt mauris. Praesent in finibus magna, vel auctor enim. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>\n<p>In velit libero, iaculis eget velit ut, malesuada auctor massa. Duis ac faucibus arcu, et sodales ante. Aliquam dignissim lobortis justo, posuere consequat orci posuere in. Curabitur vulputate libero nec ante auctor, lobortis bibendum mi interdum. Proin a diam a dolor sollicitudin ultricies. Curabitur purus erat, eleifend a felis at, tempor aliquet nibh. Maecenas tristique massa eget fermentum mollis.</p>\n<p>Duis vel commodo enim. Nunc in auctor lacus. Nulla ac lacus pulvinar, viverra quam et, consequat diam. Suspendisse sit amet libero vulputate, congue lorem sit amet, cursus orci. Cras pulvinar libero vel elit condimentum fringilla. Curabitur vitae porta eros. Nunc vitae tincidunt elit. Vestibulum efficitur eleifend pellentesque. Curabitur purus quam, commodo porttitor viverra sed, fermentum nec risus. Curabitur consectetur dolor vel odio tempor consectetur. Vivamus eu tincidunt leo, in faucibus tortor. Mauris eleifend turpis in orci fringilla, interdum consequat augue imperdiet.</p>\n<p>Sed arcu nunc, interdum id maximus non, scelerisque id felis. Donec sagittis risus a posuere porta. Phasellus porta justo nec elementum semper. Fusce maximus eros sed tincidunt egestas. Suspendisse posuere fringilla accumsan. Aenean commodo augue sed lectus tincidunt pellentesque. Nunc vel odio egestas arcu malesuada aliquam in aliquam tellus. Quisque pharetra, est ut finibus fringilla, sapien lectus bibendum ligula, eu auctor odio lacus non libero. Nullam aliquet dolor ut enim molestie, placerat mollis lorem volutpat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Mauris sit amet magna a mauris pharetra consequat id a leo.</p>\n</div>', NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:33:46', '2024-10-11 10:33:46'),
	(13, 3, 'TEST LESSON [EDIT]', 'video', b'0', b'0', '16m 12d', 'https://www.youtube.com/watch?v=zkJZ9H0Xp3k', NULL, NULL, NULL, NULL, NULL, NULL, '2024-10-11 17:41:29', '2024-10-11 17:46:44'),
	(15, 10, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'video', b'0', b'0', '1j 49m 42d', 'https://www.youtube.com/watch?v=5N0fD0GMm2g&list=PL-CtdCApEFH8dCbx_jnFR2SKqUWAw-HQX&index=1', '', NULL, NULL, 'https://docs.google.com/presentation/d/1kDwmRom2R7JioqkUh6mT1ohjy0t1kRQQHR1VwWgT-b0/edit', 'Google Slide', 'link', '2024-10-12 12:26:00', '2024-10-17 16:12:53'),
	(16, 14, 'Tutorial Redis Data Structure (Bahasa Indonesia)', 'video', b'0', b'0', '1m 22d 24d', 'https://www.youtube.com/watch?v=X_1-0tBdb-I&list=PL-CtdCApEFH8dCbx_jnFR2SKqUWAw-HQX&index=2', '', 'Data Contoh', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/lesson-attachment-1729156412435-table.csv', NULL, NULL, 'file', '2024-10-12 12:26:25', '2024-10-17 16:13:33'),
	(17, 15, 'Tutorial Redis PubSub (Bahasa Indonesia)', 'video', b'0', b'0', '22m 21d', 'https://www.youtube.com/watch?v=ej8NveP_xcc&list=PL-CtdCApEFH8dCbx_jnFR2SKqUWAw-HQX&index=3', '', NULL, NULL, NULL, NULL, NULL, '2024-10-12 12:26:41', '2024-10-12 05:27:27'),
	(20, 18, 'Lagu Enak Didengar Saat Santai & Kerja - Lagu Pop Indonesia Tahun 2000an', 'video', b'0', b'0', '3j 12m 13d', 'https://www.youtube.com/watch?v=HB-hb7goSeA', '', NULL, NULL, NULL, NULL, NULL, '2024-10-15 03:02:43', '2024-10-15 03:33:52'),
	(28, 18, 'Catatan', 'text', b'0', b'0', '15m 30d', 'https://www.youtube.com/watch?v=zkJZ9H0Xp3k', '<div id="lipsum">\n<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id pellentesque ipsum. Nunc egestas lacinia nunc non pellentesque. Nam eget viverra enim. Vestibulum vehicula massa non elit elementum volutpat. Phasellus tempor fermentum hendrerit. Aliquam auctor tellus in ullamcorper porttitor. Duis porttitor risus eu justo rhoncus, et tempus lacus suscipit. In eget elit eget orci ultricies blandit. Mauris maximus tortor quis malesuada semper. Morbi sed felis sapien. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla interdum sit amet nunc non malesuada. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed hendrerit mi eget velit placerat fringilla. Mauris viverra nec nunc id sodales. Nam porttitor justo placerat mi sollicitudin, sed cursus sapien tincidunt.</p>\n<p>Etiam id magna magna. Morbi pretium ante quis enim consectetur, dignissim varius nulla porta. Nulla dictum ornare nunc. Aliquam ornare velit ligula, a lacinia est dignissim quis. Quisque ut rhoncus turpis, sed sodales augue. Suspendisse suscipit, arcu in dapibus congue, risus ligula faucibus massa, sed eleifend leo massa ac nibh. Vivamus vitae dapibus arcu. Aenean tempor pellentesque laoreet.</p>\n<p>Phasellus in scelerisque libero. Ut pulvinar nisi id diam tincidunt, eget lobortis nulla tincidunt. Nullam vel tellus a nulla rutrum malesuada. Donec euismod nisi velit, ac pellentesque metus venenatis id. In lectus sem, accumsan ac lectus quis, placerat sollicitudin felis. In bibendum felis nec augue vestibulum, sed mollis urna consequat. Pellentesque fringilla justo felis, in auctor enim vestibulum in.</p>\n<p>Suspendisse euismod ullamcorper faucibus. Nam auctor, enim eget convallis ultrices, elit nibh varius augue, quis lobortis ante dui a ipsum. Duis blandit viverra mi. Vestibulum quam lectus, imperdiet dignissim malesuada placerat, consequat id ante. Mauris mi augue, finibus in mollis vel, tincidunt quis est. Nam elementum nibh sed elementum ultricies. Sed dolor sapien, porttitor a neque ut, varius consequat urna.</p>\n<p>Aenean accumsan, mauris varius interdum rutrum, erat lorem viverra augue, pharetra tincidunt dui ex in leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent at massa a tellus mattis molestie. In porta ultrices leo, eget tincidunt orci posuere non. Donec ultrices, nisl sit amet pretium blandit, odio velit pretium tortor, quis vestibulum libero lorem a risus. Sed eu gravida nisl, vel fermentum urna. Vestibulum mollis nunc libero, vel convallis orci feugiat vitae. Praesent ultricies ante quis laoreet fringilla. Nam leo turpis, vulputate eu ullamcorper ac, venenatis in magna. Morbi ligula risus, tempus a blandit non, accumsan sit amet nisl. Nullam pellentesque leo sed sem lobortis vulputate.</p>\n</div>', NULL, NULL, NULL, NULL, NULL, '2024-10-15 03:18:40', '2024-10-15 03:36:30'),
	(31, 21, 'TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)', 'video', b'0', b'0', '4j 48d 40d', 'https://www.youtube.com/watch?v=VM3rwdMBORY&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=1', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:49:00', NULL),
	(32, 21, 'TUTORIAL SPRING CONFIG PROPERTIES (BAHASA INDONESIA)', 'video', b'0', b'0', '2j 47m 54d', 'https://www.youtube.com/watch?v=3PmP2oFSVoY&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=2', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:49:27', NULL),
	(33, 21, 'TUTORIAL SPRING LOGGING (BAHASA INDONESIA)', 'video', b'0', b'0', '32m 32d', 'https://www.youtube.com/watch?v=FjP3St2j-2k&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=3', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:49:42', NULL),
	(34, 22, 'TUTORIAL SPRING VALIDATION (BAHASA INDONESIA)', 'video', b'0', b'0', '43m 28d', 'https://www.youtube.com/watch?v=dBnIH9kt0Sg&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=4', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:50:09', NULL),
	(35, 22, 'TUTORIAL SPRING DATA JPA (BAHASA INDONESIA)', 'video', b'0', b'0', '3j 03m 14d', 'https://www.youtube.com/watch?v=iXbQhfNKDUg&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=7', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:50:32', NULL),
	(36, 22, 'Tutorial Spring Data Redis (Bahasa Indonesia)', 'video', b'0', b'0', '2j 27m 53d', 'https://www.youtube.com/watch?v=svOrNgh8Mi4&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=11', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:51:07', NULL),
	(37, 23, 'TUTORIAL SPRING AOP (BAHASA INDONESIA)', 'video', b'0', b'0', '1j 12m 11d', 'https://www.youtube.com/watch?v=WpSNOTwzbUI&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=5', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:51:36', NULL),
	(38, 23, 'SPRING ASYNC', 'video', b'0', b'0', '51m 39d', 'https://www.youtube.com/watch?v=J9erkQY3DpE&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=9', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:51:53', NULL),
	(39, 24, 'TUTORIAL SPRING WEB MVC (BAHASA INDONESIA)', 'video', b'0', b'0', '4j 19m 12d', 'https://www.youtube.com/watch?v=No-p-Va0viU&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=6', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:52:24', NULL),
	(40, 24, 'TUTORIAL SPRING BOOT RESTFUL API (BAHASA INDONESIA)', 'video', b'0', b'0', '4j 05m 55d', 'https://www.youtube.com/watch?v=eFIBOVXilK4&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=8', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:52:48', NULL),
	(41, 25, 'Tutorial Spring Monitoring (Bahasa Indonesia)', 'video', b'0', b'0', '57m 24d', 'https://www.youtube.com/watch?v=DMMzPxYjd2Q&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=10', '', NULL, NULL, NULL, NULL, NULL, '2024-10-16 22:55:25', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
