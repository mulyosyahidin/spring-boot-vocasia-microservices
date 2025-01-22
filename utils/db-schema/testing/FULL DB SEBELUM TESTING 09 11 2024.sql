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

-- membuang struktur untuk table vocasia_db.categories
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
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.categories: ~141 rows (lebih kurang)
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
	(139, 'child', 11, 'Lainnya', 'lainnya', NULL, '2024-10-11 13:45:10', '2024-10-11 06:45:10');

-- membuang struktur untuk table vocasia_db.chapters
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.chapters: ~26 rows (lebih kurang)
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
	(25, 18, 'Pemantauan dan Keandalan Aplikasi', '57m 24d', '2024-10-16 22:48:18', NULL),
	(28, 19, 'Chapter 1', '11j 11m 9d', '2024-11-04 23:33:55', NULL),
	(29, 20, 'Chapter 1', '1j 18m 16d', '2024-11-04 23:55:26', NULL),
	(30, 21, 'Chapter 1', '2j 52m 28d', '2024-11-05 00:12:53', NULL),
	(31, 22, 'Chapter 1', '1j 14m 42d', '2024-11-05 00:19:04', NULL),
	(32, 23, 'Chapter 1', '1j 46m 50d', '2024-11-05 00:22:34', NULL),
	(33, 24, 'Chapter 1', '76j 48m 10d', '2024-11-05 00:27:07', NULL),
	(34, 25, 'Chapter 1', '8j 24m 39d', '2024-11-05 00:31:54', NULL),
	(35, 26, 'Chapter 1', '3j 57m 4d', '2024-11-05 00:36:04', NULL),
	(36, 27, 'Chapter 1', '6j 4m 43d', '2024-11-05 00:50:27', NULL),
	(37, 28, 'Chapter 1', '1j 10m 3d', '2024-11-05 00:56:09', NULL);

-- membuang struktur untuk table vocasia_db.courses
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.courses: ~14 rows (lebih kurang)
INSERT INTO `courses` (`id`, `instructor_id`, `category_id`, `title`, `slug`, `total_duration`, `level`, `language`, `description`, `short_description`, `featured_picture`, `price`, `is_free`, `is_discount`, `discount`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
	(4, 2, 78, 'Riverpod Crash Course', 'riverpod-crash-course', '1j 12m', 'beginner', 'id', '<p><strong>Riverpod Crash Course untuk Pemula: Menguasai State Management di Flutter</strong></p>\n<p>Riverpod adalah salah satu solusi state management yang modern dan efisien untuk aplikasi Flutter. Dibandingkan dengan <em>provider</em>, Riverpod menawarkan beberapa keunggulan, seperti lebih fleksibel dan lebih mudah diatur dalam hal <em>dependency injection</em>. Dalam kursus ini, kita akan mempelajari dasar-dasar Riverpod, mulai dari memahami konsep state hingga bagaimana cara mengimplementasikannya dalam aplikasi Flutter. <strong>Kursus ini dirancang untuk pemula</strong>, jadi Anda tidak perlu khawatir jika belum memiliki pengalaman dengan state management sebelumnya.</p>\n<h5><strong>Apa Itu Riverpod?</strong></h5>\n<p>Riverpod adalah pustaka yang dibangun di atas <em>provider</em>, namun dengan beberapa penyempurnaan yang membuatnya lebih kuat dan aman. Salah satu perbedaan utamanya adalah Riverpod menggunakan <em>syntax</em> yang lebih bersih dan tipe yang lebih jelas, sehingga meminimalisir kesalahan saat coding. Dalam kursus ini, kita akan membahas <strong>cara kerja Riverpod</strong> dan bagaimana Anda bisa memanfaatkan keunggulannya untuk menciptakan aplikasi yang lebih terstruktur dan mudah dipelihara.</p>\n<h5><strong>Memulai dengan Riverpod</strong></h5>\n<p>Tahap pertama dari kursus ini akan mengajarkan Anda cara menginstal dan mengkonfigurasi Riverpod dalam proyek Flutter. Kita akan mulai dari langkah-langkah dasar seperti menambahkan <em>dependencies</em> di <em>pubspec.yaml</em> hingga membuat <em>provider</em> sederhana. Di sini, <strong>kita akan mengenal dua jenis provider utama</strong>, yaitu <code>StateProvider</code> untuk state sederhana dan <code>FutureProvider</code> untuk pengelolaan state asinkron. Pemahaman tentang perbedaan dan penggunaan kedua provider ini akan menjadi landasan kuat untuk menguasai Riverpod.</p>\n<h5><strong>Membangun Aplikasi dengan Riverpod</strong></h5>\n<p>Setelah memahami dasar-dasarnya, kita akan langsung mempraktikkannya dengan membangun aplikasi Flutter sederhana. Anda akan belajar <strong>bagaimana cara menghubungkan UI Flutter dengan state yang dikelola oleh Riverpod</strong>, serta bagaimana memisahkan logika bisnis dari tampilan agar aplikasi lebih terstruktur. Pada tahap ini, Anda akan memahami pentingnya pemisahan <em>concern</em> dalam pengembangan aplikasi yang scalable dan mudah di-debug.</p>\n<h5><strong>Menangani State yang Kompleks</strong></h5>\n<p>Di bagian akhir kursus, kita akan mempelajari bagaimana menangani state yang lebih kompleks, seperti <em>state</em> yang berasal dari API atau database. Anda akan belajar menggunakan <code>StreamProvider</code> dan <code>StateNotifierProvider</code> untuk skenario yang lebih dinamis. Selain itu, <strong>kita juga akan membahas teknik terbaik dalam menggunakan Riverpod</strong>, seperti <em>caching</em>, <em>error handling</em>, dan <em>testing</em>. Setelah menyelesaikan kursus ini, Anda akan siap untuk membangun aplikasi Flutter dengan Riverpod secara lebih percaya diri.</p>\n<p>Kursus ini memberikan <strong>fondasi yang solid</strong> bagi siapa pun yang ingin memahami dan menguasai Riverpod di Flutter. Anda akan diajak melangkah dari pemahaman dasar hingga skenario penggunaan yang lebih kompleks, sehingga mampu menerapkannya dalam proyek nyata.</p>', '<p>Kursus Riverpod ini memberikan pemahaman mendalam tentang state management di Flutter, dimulai dari konsep dasar hingga implementasi dalam skenario yang kompleks. Dengan mempelajari berbagai jenis <em>provider</em> seperti <code>StateProvider</code>, <code>FutureProvider</code>, dan <code>StateNotifierProvider</code>, Anda akan mampu mengelola state dengan lebih efisien dan terstruktur. Setelah menyelesaikan kursus ini, Anda akan memiliki keterampilan yang solid untuk membangun aplikasi Flutter yang scalable dan mudah dipelihara menggunakan Riverpod.</p>', 'Riverpod Crash Course.jpg', 99000, b'0', b'0', 0, 'published', '2024-10-11 16:49:53', '2024-10-27 17:37:31', NULL),
	(5, 2, 81, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', '3j 33m 49d', 'intermediate', 'id', '<p>Kursus "Tutorial Redis Dasar (Bahasa Indonesia)" adalah panduan komprehensif untuk memahami dasar-dasar Redis, salah satu sistem penyimpanan data berbasis memori yang populer. Redis sering digunakan untuk caching, pub/sub, dan berbagai aplikasi data real-time lainnya. Kursus ini dibagi menjadi tiga bagian yang masing-masing mencakup topik penting dalam pengoperasian dan pemanfaatan Redis secara efisien.</p>\n<p>Pada <strong>bagian pertama</strong>, peserta akan mempelajari dasar-dasar Redis, mulai dari pengenalan dan instalasi hingga konfigurasi. Anda akan diajak memahami struktur data yang mendasari Redis, termasuk penggunaan strings, expiration untuk pengelolaan masa simpan data, dan teknik increment serta decrement. Selain itu, materi ini juga mengupas fitur-fitur penting lainnya seperti transaction, monitor, dan pipeline yang sering digunakan dalam pengelolaan transaksi dan performa Redis.</p>\n<p><strong>Bagian kedua</strong> berfokus pada struktur data yang lebih kompleks yang ditawarkan Redis. Mulai dari lists, sets, hashes, hingga sorted sets, Anda akan belajar bagaimana memanfaatkan masing-masing untuk kasus penggunaan yang sesuai. Selain itu, fitur geospatial dan HyperLogLog juga akan dibahas untuk mengelola data spasial dan perkiraan jumlah data yang unik secara efisien.</p>\n<p>Di <strong>bagian ketiga</strong>, topik yang diangkat adalah Pub/Sub, yang memungkinkan komunikasi pesan real-time antar server dan klien. Peserta akan belajar cara kerja Pub/Sub, bagaimana mengelola channel, serta konsep publisher dan subscriber. Bagian ini penting untuk memahami bagaimana Redis digunakan dalam skenario aplikasi real-time, di mana batasan dan limitasi teknis Redis dalam penggunaan Pub/Sub juga akan dibahas.</p>\n<p>Setiap bagian kursus ini dirancang untuk memandu Anda dari konsep paling dasar hingga fitur yang lebih kompleks, dengan contoh praktis dan skenario dunia nyata. Dengan mengikuti kursus ini, peserta akan mendapatkan pemahaman menyeluruh tentang Redis dan bagaimana memanfaatkannya dalam proyek aplikasi skala besar atau kecil.</p>', '<p>Kursus "Tutorial Redis Dasar (Bahasa Indonesia)" adalah panduan lengkap untuk memahami dasar-dasar Redis, mulai dari instalasi hingga fitur-fitur penting seperti strings, sets, hashes, pub/sub, dan transaksi. Kursus ini dibagi menjadi tiga bagian, membahas struktur data, manajemen koneksi, keamanan, hingga komunikasi real-time menggunakan Pub/Sub. Dengan contoh praktis dan penjelasan mendalam, kursus ini cocok bagi pemula maupun pengguna Redis yang ingin memperdalam pemahaman mereka dalam membangun aplikasi yang efisien dan real-time.</p>', 'Belajar Redis.jpg', 100000, b'0', b'0', 0, 'published', '2024-10-12 12:14:32', '2024-10-27 17:37:11', NULL),
	(17, 2, 81, 'React JS', 'test-kursus-123', '1j 2m', 'all', 'Bahasa Indonesia', '<p>lagi</p>', '<p>lagi</p>', '1728914131233-Belajar React JS Bahasa Indonesia.jpg', 10000, b'0', b'0', 0, 'draft', '2024-10-14 20:28:29', '2024-10-15 01:39:38', NULL),
	(18, 1, 77, 'TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)', 'tutorial-spring-boot-dasar-bahasa-indonesia', '25j 50m 4d', 'all', 'id', '<p>Spring Boot adalah framework berbasis Java yang dirancang untuk memudahkan pengembangan aplikasi berbasis Spring dengan cepat dan efisien. Dalam tutorial ini, Anda akan diajak memahami dasar-dasar penggunaan Spring Boot, mulai dari konfigurasi proyek hingga pengelolaan dependensi. Tutorial ini sangat cocok bagi pemula yang ingin membangun aplikasi backend menggunakan Java, serta mereka yang ingin memahami bagaimana cara kerja Spring Boot dalam menyederhanakan pengembangan aplikasi web, REST API, dan microservices.</p>\n<p>Pada tahap awal, Anda akan belajar cara membuat proyek Spring Boot menggunakan Spring Initializr, sebuah alat yang memungkinkan Anda menghasilkan struktur proyek dengan cepat. Anda juga akan diajarkan bagaimana cara mengatur konfigurasi yang tepat serta memahami file seperti <code>application.properties</code> atau <code>application.yml</code>. Pengaturan ini penting karena berperan dalam menentukan perilaku aplikasi, mulai dari pengaturan port server, koneksi database, hingga pengaturan keamanan dasar.</p>\n<p>Selain itu, tutorial ini akan membahas konsep penting seperti Dependency Injection (DI) dan Inversion of Control (IoC) yang merupakan inti dari Spring Framework. Dengan memahami cara kerja DI, Anda akan lebih mudah membuat kode yang lebih modular dan mudah dikelola. Selain itu, akan dijelaskan cara membuat controller untuk menangani request HTTP dan menggunakan RESTful API untuk berinteraksi dengan data dalam aplikasi Anda.</p>\n<p>Tutorial ini juga akan mengajak Anda mempelajari integrasi Spring Boot dengan database menggunakan JPA (Java Persistence API) dan Hibernate. Anda akan dipandu langkah demi langkah mulai dari koneksi ke database, membuat entitas, hingga melakukan operasi CRUD (Create, Read, Update, Delete) dengan repository Spring Data JPA. Dengan demikian, Anda dapat membangun aplikasi yang tidak hanya bisa menerima dan mengelola request, tetapi juga menyimpan dan mengambil data dari database.</p>\n<p>Terakhir, tutorial ini akan menyinggung beberapa fitur tambahan Spring Boot seperti penggunaan Spring Security untuk melindungi aplikasi Anda dari akses tidak sah, serta cara deploy aplikasi ke platform cloud seperti Heroku atau AWS. Dengan demikian, setelah menyelesaikan tutorial ini, Anda akan memiliki fondasi yang kuat dalam pengembangan aplikasi menggunakan Spring Boot, yang dapat langsung diterapkan untuk membangun proyek nyata.</p>', '<p><strong>Tutorial Spring Boot Dasar (Bahasa Indonesia)</strong> memberikan pemahaman mendalam tentang dasar-dasar pengembangan aplikasi menggunakan Spring Boot, mulai dari konfigurasi awal proyek, implementasi Dependency Injection, hingga integrasi dengan database menggunakan JPA. Dengan mempelajari konsep-konsep inti seperti REST API dan Spring Security, Anda akan memiliki fondasi yang kokoh untuk mengembangkan aplikasi web dan microservices yang efisien dan scalable. Tutorial ini cocok bagi pemula dan memberikan panduan langkah demi langkah untuk menerapkan Spring Boot dalam proyek nyata.</p>', '1729093329452-TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA).jpg', 200000, b'0', b'1', 50000, 'published', '2024-10-16 22:41:59', '2024-10-27 17:37:58', NULL),
	(19, 2, 12, 'TOEFL Bagi Pemula', 'toefl-bagi-pemula', '11j 11m 9d', 'beginner', 'id', '<p>Kursus "TOEFL Bagi Pemula" dirancang khusus untuk membantu peserta memahami dan menguasai konsep dasar yang diuji dalam tes TOEFL. Mulai dari pemahaman dasar tentang tata bahasa hingga keterampilan membaca, kursus ini menyajikan materi secara bertahap, sehingga pemula dapat mengikuti tanpa kesulitan. Pada awal kursus, peserta akan mempelajari subjek dan predikat (Skill 1), serta memahami peran objek preposisi (Skill 2). Materi ini dilengkapi dengan informasi dasar mengenai TOEFL yang membantu peserta memahami format dan tujuan dari tes ini.</p>\n<p>Selanjutnya, kursus ini menelusuri beberapa topik tata bahasa lanjutan yang sering muncul dalam soal TOEFL. Peserta akan belajar mengenai participle dalam bentuk present dan past, konektor koordinat, serta klausa adverbial (Skill 3-6). Untuk memastikan pemahaman, setiap topik diikuti oleh latihan soal yang mirip dengan soal TOEFL sesungguhnya, memberikan kesempatan bagi peserta untuk mengaplikasikan pengetahuan yang baru dipelajari.</p>\n<p>Kursus juga menyentuh aspek-aspek penting lain dalam tata bahasa, seperti penggunaan klausa noun dan adjective, kesesuaian subjek dan kata benda setelah ekspresi kuantitas, serta struktur paralel yang sering diuji dalam TOEFL (Skill 7-15). Materi ini disajikan secara menyeluruh, dengan penjelasan mendalam dan contoh soal, agar peserta dapat memahami aturan yang lebih kompleks dalam bahasa Inggris.</p>\n<p>Di bagian akhir sesi struktur, peserta akan belajar mengenai bentuk kata kerja dasar setelah modals, penggunaan kata benda tunggal dan jamak, serta kata sifat dan adverbia. Materi ini diakhiri dengan pembahasan khusus mengenai pronoun dan kata ganti kepemilikan, yang memberikan pemahaman komprehensif tentang unsur-unsur penting dalam tata bahasa TOEFL (Skill 16-25).</p>\n<p>Bagian pembelajaran terakhir berfokus pada keterampilan membaca, di mana peserta akan dilatih untuk menjawab berbagai tipe soal, seperti main ideas, stated detail, dan vocabulary in context (Skill 1-5). Dengan memahami teknik untuk menjawab pertanyaan-pertanyaan ini, peserta dapat meningkatkan kemampuan membaca mereka dalam konteks akademis, yang merupakan elemen penting dalam TOEFL.</p>', '<p>Kursus "TOEFL Bagi Pemula" memberikan pembelajaran menyeluruh tentang tata bahasa dan keterampilan membaca yang diperlukan dalam tes TOEFL. Mulai dari pemahaman dasar subjek-predikat hingga keterampilan membaca yang lebih kompleks, kursus ini dirancang untuk membimbing pemula dengan latihan-latihan interaktif dan pembahasan mendalam, memastikan persiapan yang matang untuk menghadapi TOEFL.</p>', '1730736768539-TOEFL.png', 150000, b'0', b'0', 0, 'published', '2024-11-04 16:11:39', '2024-11-04 23:46:50', NULL),
	(20, 2, 12, 'BELAJAR TOEFL dari NOL', 'belajar-toefl-dari-nol', '1j 18m 16d', 'beginner', 'id', '<p>Kursus "BELAJAR TOEFL UNTUK PEMULA" dirancang untuk memberikan pondasi yang kuat bagi mereka yang ingin mempersiapkan diri menghadapi tes TOEFL. Dengan pendekatan yang sistematis dan materi yang mudah dipahami, kursus ini cocok untuk peserta yang baru memulai perjalanan belajar bahasa Inggris. Materi kursus mencakup berbagai aspek penting yang akan membantu peserta memahami cara menjawab soal TOEFL secara efektif dan efisien.</p>\n<p>Di awal kursus, peserta akan belajar cara cepat untuk menjawab soal TOEFL. Metode yang digunakan mencakup teknik-teknik dasar serta trik khusus yang akan mempermudah pemahaman terhadap tipe-tipe soal yang sering muncul. Dengan pemahaman ini, peserta akan lebih percaya diri dalam menghadapi tes. Kursus juga membahas adverb of manner, urutan kata, dan frasa kata benda, yang merupakan elemen penting dalam tata bahasa yang sering muncul dalam soal TOEFL.</p>\n<p>Selanjutnya, kursus ini memberikan fokus pada penggunaan tenses dalam bahasa Inggris. Peserta akan diajarkan cara cepat untuk memahami dan menjawab soal-soal yang berkaitan dengan tenses, sehingga mereka dapat merespons pertanyaan dengan lebih akurat. Selain itu, kursus ini dilengkapi dengan ebook TOEFL yang berisi berbagai tips dan panduan belajar dari pengalaman seorang pengajar berpengalaman, Miss Anti, yang sangat berguna bagi peserta dalam persiapan tes.</p>\n<p>Materi berikutnya mengupas tentang parallel structure, yang merupakan topik yang wajib diketahui karena sering muncul dalam ujian TOEFL. Peserta akan mendapatkan penjelasan mendalam tentang struktur paralel dan contoh-contoh soal yang membantu memperkuat pemahaman mereka. Pembelajaran ini diikuti dengan bagian tentang subject-verb agreement, yang juga penting untuk memastikan kesesuaian antara subjek dan predikat dalam kalimat.</p>\n<p>Terakhir, kursus ini juga mencakup penggunaan pronoun dan artikel "the" dalam bahasa Inggris, serta memberikan pembahasan soal-soal dan materi dari kurikulum kelas 10/X. Dengan demikian, peserta tidak hanya mempelajari teori, tetapi juga mendapatkan pengalaman praktis dalam menjawab soal-soal yang relevan.</p>', '<p>Kursus "BELAJAR TOEFL UNTUK PEMULA" menawarkan pendekatan komprehensif untuk mempersiapkan peserta dalam menghadapi tes TOEFL. Dengan materi yang mudah dipahami dan teknik-teknik cepat dalam menjawab soal, peserta akan mendapatkan kepercayaan diri dan keterampilan yang dibutuhkan untuk sukses dalam ujian. Dengan bimbingan yang tepat, setiap pemula dapat meraih hasil yang memuaskan dalam tes TOEFL.</p>', '1730739295994-BELAJAR TOEFL dari NOL.webp', 70000, b'0', b'0', 0, 'published', '2024-11-04 23:54:08', '2024-11-04 23:55:37', NULL),
	(21, 2, 122, 'Tutorial Microsoft Word Untuk Pemula', 'tutorial-microsoft-word-untuk-pemula', '2j 52m 28d', 'beginner', 'id', '<p>Kursus "Tutorial Microsoft Word Untuk Pemula" merupakan panduan lengkap bagi siapa saja yang ingin mempelajari cara menggunakan Microsoft Word secara efektif. Kursus ini dibagi menjadi beberapa bagian yang akan memandu peserta dari tahap pengenalan hingga keterampilan yang lebih kompleks. Dengan materi yang mudah dipahami, kursus ini cocok untuk semua kalangan, terutama bagi mereka yang baru pertama kali menggunakan aplikasi pengolah kata ini.</p>\n<p>Pada bagian pertama kursus, peserta akan mengenal berbagai menu dan ikon yang terdapat dalam Microsoft Word. Dengan pemahaman yang baik mengenai antarmuka pengguna, peserta akan lebih mudah menjelajahi berbagai fitur yang ditawarkan. Di bagian kedua, kursus akan menjelaskan cara menyimpan, membuka, dan menutup dokumen, yang merupakan keterampilan dasar penting untuk mengelola file dengan baik.</p>\n<p>Selanjutnya, peserta akan belajar bagaimana mengatur ukuran kertas, margin, dan orientasi kertas pada bagian ketiga. Pengaturan ini sangat penting untuk memastikan dokumen memiliki tampilan yang profesional. Di bagian keempat, kursus ini akan membahas cara mengetik dengan baik dan membuat paragraf yang rapi, sehingga dokumen yang dihasilkan tidak hanya informatif tetapi juga enak dibaca.</p>\n<p>Kursus ini juga mencakup berbagai teknik untuk mengubah huruf dan format teks pada bagian kelima, serta cara membuat format teks berkolom dan menggunakan fitur DropCap di bagian keenam. Peserta akan diajarkan bagaimana cara mengatur dan menggunakan tabulasi dengan efektif di bagian ketujuh. Selain itu, pada bagian kedelapan, peserta akan mempelajari cara mengetik pangkat rumus kimia dan simbol, yang sering diperlukan dalam dokumen teknis.</p>\n<p>Di bagian-bagian terakhir, peserta akan diajarkan cara membuat header dan footer (bagian kesembilan), membuat nomor halaman otomatis (bagian kesepuluh), dan membuat rumus matematika di Word (bagian kesebelas). Kursus ini diakhiri dengan pembelajaran tentang cara membuat penomoran otomatis di Word (bagian kedua belas) dan cara memasukkan gambar ke dalam dokumen (bagian ketiga belas), sehingga peserta dapat memperkaya dokumen mereka dengan elemen visual.</p>', '<p>Kursus "Tutorial Microsoft Word Untuk Pemula" menyediakan semua yang perlu diketahui oleh pengguna baru tentang Microsoft Word. Dari pengenalan dasar hingga teknik yang lebih kompleks, peserta akan mendapatkan keterampilan praktis yang diperlukan untuk menciptakan dokumen yang rapi dan profesional. Dengan mengikuti kursus ini, pemula dapat merasa lebih percaya diri dalam menggunakan Microsoft Word untuk berbagai keperluan.</p>', '1730740427424-Tutorial Microsoft Word Untuk Pemula.jpg', 129000, b'0', b'1', 10000, 'published', '2024-11-05 00:12:48', '2024-11-05 00:13:57', NULL),
	(22, 2, 122, 'Belajar Excel Dasar dari Nol', 'belajar-excel-dasar-dari-nol', '1j 14m 42d', 'beginner', 'id', '<p>Kursus "Belajar Excel Dasar dari Nol" adalah program pembelajaran yang dirancang khusus untuk pemula yang ingin memahami dan menguasai Microsoft Excel. Dalam kursus ini, peserta akan dibimbing langkah demi langkah untuk mengenali fitur dan fungsi dasar Excel yang sangat berguna dalam mengelola data dan membuat analisis sederhana. Dengan pendekatan yang praktis dan mudah dipahami, kursus ini cocok untuk siapa saja yang ingin memulai perjalanan mereka dalam menggunakan Excel.</p>\n<p>Pada bagian pertama kursus, peserta akan diperkenalkan dengan antarmuka Excel, termasuk menu, toolbar, dan area kerja. Pengenalan ini penting agar peserta dapat merasa nyaman saat menggunakan aplikasi. Selanjutnya, pada bagian kedua, peserta akan belajar cara memasukkan data ke dalam lembar kerja, serta teknik dasar untuk mengedit dan menghapus data. Dengan keterampilan ini, peserta akan memiliki fondasi yang kuat untuk bekerja dengan data di Excel.</p>\n<p>Di bagian ketiga, kursus akan membahas cara mengatur dan memformat data agar lebih mudah dibaca dan dipahami. Peserta akan belajar tentang berbagai opsi pemformatan, seperti jenis huruf, warna, dan gaya, yang dapat meningkatkan presentasi data mereka. Bagian keempat akan berfokus pada penggunaan rumus dasar dan fungsi, termasuk penjumlahan, pengurangan, perkalian, dan pembagian. Pemahaman tentang rumus dan fungsi ini sangat penting dalam melakukan perhitungan otomatis dalam spreadsheet.</p>\n<p>Pada bagian kelima, peserta akan diajarkan cara membuat grafik dan diagram untuk memvisualisasikan data. Visualisasi yang baik dapat membantu dalam memahami tren dan pola dalam data. Kursus ini ditutup dengan bagian final yang mengulas semua materi yang telah dipelajari, serta memberikan tips dan trik untuk menggunakan Excel dengan lebih efisien. Peserta juga akan diberi tugas untuk menerapkan keterampilan yang telah mereka pelajari.</p>', '<p>Kursus "Belajar Excel Dasar dari Nol" memberikan semua alat dan pengetahuan yang diperlukan bagi pemula untuk mulai menggunakan Microsoft Excel dengan percaya diri. Dari pengenalan dasar hingga teknik yang lebih canggih, peserta akan mampu mengelola data dan melakukan analisis sederhana secara efektif. Dengan mengikuti kursus ini, peserta akan memiliki keterampilan dasar yang penting untuk meningkatkan produktivitas dalam pekerjaan atau studi mereka.</p>', '1730740733278-Belajar Excel Dasar dari Nol.jpg', 29000, b'0', b'0', 0, 'published', '2024-11-05 00:16:51', '2024-11-05 00:19:09', NULL),
	(23, 2, 53, 'Ecourse UIUX Design Gratis Bahasa Indonesia', 'ecourse-uiux-design-gratis-bahasa-indonesia', '1j 46m 50d', 'all', 'id', '<p>Ecourse "UIUX Design Gratis Bahasa Indonesia" adalah program pembelajaran yang dirancang untuk memperkenalkan konsep dan praktik desain antarmuka pengguna (UI) dan pengalaman pengguna (UX) dengan pendekatan yang mudah dipahami. Kursus ini menawarkan materi yang komprehensif, mulai dari pengantar dasar hingga teknik yang lebih canggih dalam menggunakan alat desain seperti Figma. Dengan kursus ini, peserta diharapkan dapat mengembangkan keterampilan yang dibutuhkan untuk menciptakan desain yang menarik dan efektif.</p>\n<p>Pada episode pertama, peserta akan dikenalkan dengan pentingnya UI/UX Design sebagai salah satu keterampilan yang paling dicari di dunia industri saat ini. Dalam dunia yang semakin digital, kemampuan untuk merancang pengalaman pengguna yang baik menjadi sangat penting. Selanjutnya, di episode kedua, peserta akan mempelajari berbagai trik dan teknik yang dapat membantu mereka menjadi ahli dalam desain UI/UX. Materi ini disampaikan dengan cara yang menarik, sehingga peserta dapat langsung mengaplikasikan pengetahuan yang didapat.</p>\n<p>Di episode ketiga, peserta akan diajarkan tentang rumus-rumus penting yang dapat membuat desain mereka lebih spesial di hati pengguna. Fokus utama dari pembelajaran ini adalah menciptakan solusi yang interaktif dan intuitif. Episode keempat akan membahas trik khusus yang membantu desainer memberikan solusi interaktif yang tepat bagi pengguna, memastikan bahwa setiap elemen desain dapat digunakan dengan mudah dan memberikan pengalaman positif.</p>\n<p>Melanjutkan ke episode kelima dan keenam, peserta akan diajarkan bagaimana semakin memanjakan pengguna, semakin canggih aplikasi yang mereka desain. Pada episode ketujuh, peserta akan mempelajari teknik handal UI/UX Design dengan menggunakan Figma, alat desain populer yang banyak digunakan oleh para desainer. Di episode kedelapan, peserta akan diajarkan cara cepat membuat layout UI/UX menggunakan Figma, serta mendesain mockup design high fidelity di episode kesembilan.</p>', '<p>Ecourse "UIUX Design Gratis Bahasa Indonesia" adalah kesempatan emas bagi siapa saja yang ingin mempelajari desain UI/UX secara mendalam. Dari pengantar hingga praktik terbaik, kursus ini menyediakan semua sumber daya yang diperlukan untuk membantu peserta menciptakan desain yang tidak hanya menarik tetapi juga efektif dalam meningkatkan pengalaman pengguna. Dengan mengikuti kursus ini, peserta akan memiliki landasan yang kuat untuk memulai karier di bidang desain yang sangat menjanjikan ini.</p>', '1730740945146-Ecourse UIUX Design Gratis Bahasa Indonesia.jpg', 0, b'1', b'0', 0, 'published', '2024-11-05 00:22:19', '2024-11-05 00:22:46', NULL),
	(24, 2, 91, 'Digital Marketing Playlist [2024 Updated]🔥 | Digital Marketing Course | Digital Marketing Tutorial For Beginners', 'digital-marketing-playlist-2024-updated-digital-marketing-course-digital-marketing-tutorial-for-beginners', '76j 48m 10d', 'all', 'en', '<p>Unlock the secrets of digital marketing with our comprehensive course designed specifically for beginners! The "Digital Marketing Course [2024 Updated]" offers a structured learning experience that equips you with the essential skills and knowledge needed to thrive in today&rsquo;s digital landscape. As businesses increasingly rely on online presence, mastering digital marketing has become a crucial asset for anyone looking to advance their career or grow their own business.</p>\n<p>This course begins with an engaging introduction to digital marketing, where you\'ll discover what digital marketing is and why it is vital in the modern business environment. You\'ll learn the foundational concepts and benefits of implementing effective digital marketing strategies, setting the stage for more advanced topics. Each module is crafted to deepen your understanding, ensuring that you not only learn but also apply what you\'ve acquired.</p>\n<p>As you progress, the course dives into specific areas of digital marketing, including Search Engine Optimization (SEO), Google Ads, and social media marketing. You&rsquo;ll gain hands-on experience with essential tools and techniques that are critical for driving traffic to your website and enhancing your online visibility. Our expert instructors will guide you through the intricacies of SEO, teaching you how to improve your search rankings and conduct effective keyword research. Additionally, you\'ll explore how to create impactful Google Ads campaigns and analyze their performance using Google Analytics.</p>\n<p>Social media marketing is another crucial component of this course. You&rsquo;ll learn strategies for building and engaging an audience on platforms like Facebook and Instagram. Our tips and tricks will empower you to craft compelling content that resonates with your target audience, increasing your brand&rsquo;s reach and customer engagement. By the end of this course, you&rsquo;ll have a toolkit of strategies that can be directly applied to your marketing efforts.</p>', '<p>The "Digital Marketing Course [2024 Updated]" is your gateway to mastering the art of digital marketing. Whether you\'re a budding entrepreneur or a professional looking to enhance your skills, this course offers invaluable insights and practical knowledge. Join us today to elevate your digital marketing expertise and achieve your business goals with confidence!</p>', '1730741289923-dm.jpg', 5000000, b'0', b'0', 0, 'published', '2024-11-05 00:26:56', '2024-11-05 00:28:29', NULL),
	(25, 2, 22, 'Akuntansi Keuangan Menengah 1', 'akuntansi-keuangan-menengah-1', '8j 24m 39d', 'intermediate', 'id', '<p>Unlock the fundamentals of financial accounting with our comprehensive course, <strong>"Intermediate Financial Accounting 1."</strong> This course is designed for individuals seeking to deepen their understanding of accounting principles and financial reporting standards. Whether you are a student, a professional looking to enhance your skills, or someone entering the field of accounting, this course provides a solid foundation for your career.</p>\n<p>In <strong>"Intermediate Financial Accounting 1,"</strong> you will explore essential topics, starting with a thorough understanding of accounting and financial accounting standards. You will delve into the framework of financial statements, learning how to prepare and interpret various reports that are crucial for decision-making processes within an organization.</p>\n<p>The course covers the following key areas:</p>\n<ol>\n<li>\n<p><strong>Financial Reporting Standards</strong>: Gain insights into the principles and standards that govern financial accounting, ensuring that you are well-versed in the latest regulations and practices.</p>\n</li>\n<li>\n<p><strong>Framework for Financial Statements</strong>: Understand the structure and components of financial statements, including the income statement, statement of changes in equity, and statement of financial position. You\'ll learn how these statements interact and what they reveal about a company\'s financial health.</p>\n</li>\n<li>\n<p><strong>Comprehensive Income Statement and Changes in Equity</strong>: Discover how to prepare a comprehensive income statement and analyze changes in equity, providing valuable insights into a company\'s profitability and financial performance.</p>\n</li>\n<li>\n<p><strong>Statement of Financial Position</strong>: Learn how to create and interpret the statement of financial position, also known as the balance sheet, which outlines a company&rsquo;s assets, liabilities, and equity.</p>\n</li>\n<li>\n<p><strong>Cash Flow Statement</strong>: Understand the importance of cash flow management through the preparation and analysis of cash flow statements, which reflect how well a company generates cash to fund its obligations.</p>\n</li>\n<li>\n<p><strong>Key Asset Categories</strong>: Dive into the details of various asset categories, including cash, receivables, inventory, fixed assets, and intangible assets. You will learn how to account for and report these assets accurately in financial statements.</p>\n</li>\n</ol>\n<p>By the end of this course, you will have gained the knowledge and skills necessary to navigate the complexities of financial accounting confidently. You will be equipped to prepare financial statements that comply with accounting standards, interpret the information they convey, and make informed financial decisions.</p>', '<p>Join us in <strong>"Intermediate Financial Accounting 1"</strong> to elevate your accounting expertise and pave the way for a successful career in finance and accounting. Enroll today and take the first step towards mastering financial accounting!</p>', '1730741510940-Akuntansi Keuangan Menengah 1.jpeg', 199000, b'0', b'0', 0, 'published', '2024-11-05 00:31:00', '2024-11-05 00:32:34', NULL),
	(26, 2, 45, 'Real Estate Exam Concepts, Definitions, and More', 'real-estate-exam-concepts-definitions-and-more', '3j 57m 4d', 'all', 'id', '<p>Prepare for success in your real estate career with our comprehensive course, <strong>"Real Estate Exam Concepts, Definitions, and More."</strong> This course is meticulously designed for aspiring real estate professionals seeking to master the essential concepts, terminology, and principles required to excel in their licensing exams. Whether you\'re a novice or looking to refresh your knowledge, this course will provide you with the tools needed to navigate the complexities of real estate.</p>\n<h3>Course Highlights:</h3>\n<ul>\n<li>\n<p><strong>Understanding Real Estate Licenses</strong>: Begin with a thorough overview of what a real estate license is, including common exam questions that can help you pass the licensing exam with confidence.</p>\n</li>\n<li>\n<p><strong>Obsolescence in Real Estate</strong>: Learn about functional and economic obsolescence&mdash;key concepts that every real estate professional should understand to evaluate property value accurately.</p>\n</li>\n<li>\n<p><strong>Property Rights and Easements</strong>: Dive into crucial topics like emblements, easements, and water rights, exploring their implications on property ownership and usage.</p>\n</li>\n<li>\n<p><strong>Contract Fundamentals</strong>: Gain clarity on bilateral vs. unilateral contracts, valid contracts, and the nuances of implied and option contracts to ensure you understand their significance in real estate transactions.</p>\n</li>\n<li>\n<p><strong>Real Estate Relationships</strong>: Explore agency relationships, dual agency, and the roles involved in real estate transactions, equipping yourself with the knowledge to navigate complex scenarios.</p>\n</li>\n<li>\n<p><strong>Financial Clauses and Their Implications</strong>: Familiarize yourself with important contract clauses such as alienation, defeasance, acceleration, and prepayment penalty clauses, learning how they affect property transactions.</p>\n</li>\n<li>\n<p><strong>Title and Ownership Concepts</strong>: Understand the difference between title and deed, the concept of equitable title, and what adverse possession entails, providing you with a solid foundation in property rights.</p>\n</li>\n<li>\n<p><strong>Valuation Approaches</strong>: Discover various methods for valuing real estate, including the market data approach, cost approach, and income approach, essential for making informed decisions in your real estate career.</p>\n</li>\n<li>\n<p><strong>Economic Principles</strong>: Grasp key economic principles such as conformity, substitution, and contribution, and learn how they apply to real estate valuation and investment.</p>\n</li>\n</ul>\n<p>This course is packed with real estate exam questions and answers that will prepare you for the challenges of the licensing exam. By the end of this course, you will have a thorough understanding of critical real estate concepts, enabling you to approach your exam with confidence and competence.</p>', '<p>Enroll in <strong>"Real Estate Exam Concepts, Definitions, and More"</strong> today and take the first step towards acing your real estate exam and launching your successful career in real estate!</p>', '1730742295438-real estate.jpeg', 300000, b'0', b'0', 0, 'published', '2024-11-05 00:36:00', '2024-11-05 00:47:03', NULL),
	(27, 2, 69, 'Ternak bebek peking 100 ekor analisa dan keuntungan', 'ternak-bebek-peking-100-ekor-analisa-dan-keuntungan', '6j 4m 43d', 'all', 'id', '<p>Kursus "Ternak Bebek Peking 100 Ekor: Analisa dan Keuntungan" dirancang untuk memberikan pemahaman mendalam mengenai teknik dan strategi dalam budidaya bebek peking. Dalam kursus ini, peserta akan belajar mulai dari persiapan modal awal, perlengkapan yang dibutuhkan, hingga cara pemeliharaan bebek agar dapat menghasilkan keuntungan maksimal. Dengan bimbingan dari instruktur berpengalaman, peserta akan memperoleh pengetahuan praktis yang dapat langsung diterapkan dalam usaha ternak mereka sendiri.</p>\n<p>Selama kursus, peserta akan diperkenalkan pada konsep dasar pemeliharaan bebek, termasuk pemilihan bibit yang berkualitas, manajemen pakan, dan pembuatan kandang yang efisien. Kami akan membahas berbagai jenis pakan yang dapat digunakan, mulai dari pakan pabrikan hingga pakan alternatif yang lebih ekonomis. Peserta juga akan diajarkan cara membuat tempat minum otomatis untuk meningkatkan efisiensi dan menjaga kebersihan kandang.</p>\n<p>Kesehatan bebek merupakan salah satu aspek penting dalam usaha ternak. Oleh karena itu, kursus ini juga akan memberikan informasi tentang pencegahan penyakit dan cara mengobati penyakit umum yang sering menyerang bebek. Kami akan membahas pengobatan alami dan teknik perawatan yang dapat membantu bebek tetap sehat dan produktif, termasuk cara mengatasi masalah seperti rontok bulu dan penyakit snot.</p>\n<p>Dalam sesi lanjutan, peserta akan mempelajari cara melakukan analisis biaya dan potensi keuntungan dari ternak bebek peking. Kami akan membahas strategi pemasaran yang efektif untuk meningkatkan penjualan, termasuk bagaimana menjangkau pasar lokal dan memanfaatkan media sosial untuk promosi. Dengan pemahaman yang baik tentang pasar, peserta akan lebih siap untuk mengambil keputusan yang tepat dalam mengembangkan usaha mereka.</p>\n<p>Akhirnya, kursus ini akan menyiapkan peserta untuk mengambil langkah nyata dalam memulai usaha ternak bebek. Dengan pengetahuan yang didapat selama kursus, peserta diharapkan dapat mengelola usaha ternak bebek peking dengan baik, meminimalkan risiko, dan memaksimalkan keuntungan. Bergabunglah dengan kami untuk menjelajahi dunia ternak bebek dan raih sukses dalam usaha sampingan yang menjanjikan ini!</p>', '<p>Ternak bebek peking 100 ekor merupakan usaha yang menjanjikan dengan potensi keuntungan yang menarik. Modal awal yang diperlukan mencakup pembangunan kandang yang efisien, pembelian bibit bebek berkualitas, serta pakan bernutrisi tinggi untuk mendukung pertumbuhan optimal. Dalam proses pemeliharaan, penting untuk menjaga kebersihan kandang dan memberikan perawatan kesehatan yang tepat, termasuk obat dan vitamin, agar bebek tetap sehat dan produktif. Dengan waktu panen yang relatif singkat dan permintaan pasar yang terus meningkat, usaha ternak bebek ini menawarkan peluang yang baik untuk menghasilkan pendapatan yang signifikan, asalkan dikelola dengan baik dan strategi pemasaran yang efektif diterapkan.</p>', '1730742655475-ternak-bebek-petelur.jpg', 150000, b'0', b'0', 0, 'published', '2024-11-05 00:50:18', '2024-11-05 00:51:20', NULL),
	(28, 2, 134, 'Kelas Fotografi Online', 'kelas-fotografi-online', '1j 10m 3d', 'all', 'id', '<p>Kursus "Kelas Fotografi Online" adalah program pembelajaran yang dirancang untuk membantu para pemula memahami dasar-dasar fotografi dengan mudah dan menyenangkan. Dalam kursus ini, peserta akan diajak untuk menjelajahi berbagai aspek penting dalam dunia fotografi, mulai dari pengenalan dasar hingga teknik-teknik yang lebih kompleks. Dengan modul-modul yang terstruktur dan mudah diikuti, kursus ini menjadi pilihan tepat bagi siapa saja yang ingin mengembangkan keterampilan fotografi mereka tanpa harus mengeluarkan biaya yang besar.</p>\n<p>Pada sesi pertama, peserta akan diperkenalkan pada konsep dasar fotografi dan menjawab pertanyaan mendasar: "Apa Itu Fotografi?" Setelah itu, mereka akan mempelajari tiga elemen penting dalam pengaturan kamera, yaitu ISO, aperture, dan shutter speed. Modul-modul ini menjelaskan dengan jelas bagaimana masing-masing elemen berfungsi dan saling berhubungan untuk menghasilkan foto yang berkualitas. Dengan pemahaman yang baik tentang ketiga elemen tersebut, peserta akan mampu mengatur exposure dengan tepat untuk berbagai situasi pencahayaan.</p>\n<p>Selanjutnya, kursus ini akan membahas teknik-teknik praktis, seperti cara memegang DSLR yang benar, postur tubuh yang tepat saat memotret, dan pemahaman tentang tombol-tombol pada kamera. Ini sangat penting bagi pemula agar dapat merasa nyaman dan percaya diri saat menggunakan kamera mereka. Selain itu, peserta juga akan mempelajari tentang white balance, perbedaan antara format RAW dan JPEG, serta mode metering pada kamera DSLR atau mirrorless.</p>\n<p>Kursus ini tidak hanya berfokus pada pengaturan teknis, tetapi juga memberikan pemahaman tentang komposisi fotografi, seperti penggunaan Rule of Thirds. Dengan belajar tentang komposisi, peserta akan dapat menciptakan gambar yang lebih menarik dan estetik. Pengalaman belajar di kursus ini didukung oleh berbagai materi video yang informatif, tugas praktis, dan forum diskusi yang memungkinkan peserta untuk bertukar pikiran dan mendapatkan umpan balik.</p>\n<p>Dengan harga yang terjangkau, "Kelas Fotografi Online" menyediakan kesempatan emas bagi siapa saja yang ingin mengeksplorasi hobi baru atau bahkan mempertimbangkan karier di bidang fotografi. Peserta akan mendapatkan sertifikat penyelesaian kursus sebagai bukti keterampilan yang telah diperoleh, yang dapat meningkatkan nilai tambah dalam portofolio mereka. Bergabunglah dengan kami dan mulailah perjalanan fotografi Anda sekarang!</p>', '<p>kursus ini adalah pilihan ideal bagi mereka yang ingin belajar fotografi dengan cara yang menyenangkan dan tidak memberatkan kantong. Dengan modul yang lengkap dan metode pengajaran yang efektif, peserta dapat mengembangkan keterampilan fotografi mereka dari dasar hingga tingkat lanjutan. Jadikan "Kelas Fotografi Online" sebagai langkah pertama Anda menuju keahlian fotografi yang lebih baik!</p>', '1730742993991-fotografiteknik.jpg', 150000, b'0', b'1', 50000, 'published', '2024-11-05 00:55:58', '2024-11-05 00:56:44', NULL);

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

-- Membuang data untuk tabel vocasia_db.course_reviews: ~0 rows (lebih kurang)
INSERT INTO `course_reviews` (`id`, `enrollment_id`, `course_id`, `user_id`, `rating`, `review`, `created_at`, `updated_at`) VALUES
	(6, 1, 5, 3, 5, '<p>Kursus ini sangat bagus. Penyampaian materi dijelaskan secara jelas dan beruntun.</p>\n<p>Terima kasih!</p>', '2024-10-30 20:20:04', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.enrollments: ~3 rows (lebih kurang)
INSERT INTO `enrollments` (`id`, `user_id`, `order_id`, `course_id`, `enrollment_date`, `status`, `progress_percentage`, `completion_date`, `created_at`, `updated_at`, `last_lesson_id`) VALUES
	(1, 3, 1, 5, '2024-10-30 15:47:48', 'active', 100, '2024-10-30 18:02:15', '2024-10-30 15:47:49', '2024-10-30 18:03:45', 15),
	(2, 3, 2, 21, '2024-11-05 06:52:29', 'active', 0, NULL, '2024-11-05 06:52:30', NULL, NULL),
	(3, 3, 2, 28, '2024-11-05 06:52:29', 'active', 0, NULL, '2024-11-05 06:52:31', NULL, NULL);

-- membuang struktur untuk table vocasia_db.forget_password
CREATE TABLE IF NOT EXISTS `forget_password` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `expired_at` timestamp NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.forget_password: ~0 rows (lebih kurang)

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instructors: ~5 rows (lebih kurang)
INSERT INTO `instructors` (`id`, `user_id`, `status`, `phone_number`, `summary`, `deleted_at`) VALUES
	(1, 1, 'approved', '0822341234', 'New summary2', NULL),
	(2, 4, 'approved', '082281666584', 'summary', NULL),
	(3, 10, 'approved', '08123456789', NULL, NULL),
	(4, 11, 'approved', '+1 (817) 705-2131', 'Totam quia dolorem e', NULL),
	(5, 12, 'approved', '08134567890', 'cia adalah valencia', NULL);

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

-- Membuang data untuk tabel vocasia_db.instructor_balances: ~0 rows (lebih kurang)
INSERT INTO `instructor_balances` (`id`, `instructor_id`, `current_balance`, `total_income`, `total_pending_withdrawal`, `total_withdrawn`, `total_platform_fee`, `last_history_id`, `created_at`, `updated_at`) VALUES
	(1, 2, 208050, 303050, 0, 95000, 15950, 4, '2024-10-30 15:47:53', '2024-11-05 06:52:35');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instructor_balance_histories: ~4 rows (lebih kurang)
INSERT INTO `instructor_balance_histories` (`id`, `instructor_id`, `transaction_type`, `amount`, `previous_balance`, `new_balance`, `transaction_date`, `reference_id`, `reference_type`, `transaction_status`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 'income', 95000, 0, 95000, '2024-10-30 15:47:53', 1, 'order', 'completed', 'New income from order #202410303ZKEF', '2024-10-30 15:47:53', '2024-10-30 08:47:52'),
	(2, 2, 'withdrawal', 95000, 95000, 0, '2024-10-30 17:34:21', 1, 'withdrawal', 'success', 'Withdrawal request processed', '2024-10-30 17:34:21', '2024-10-30 10:34:20'),
	(3, 2, 'income', 113050, 0, 113050, '2024-11-05 06:52:35', 2, 'order', 'completed', 'New income from order #202411053AOKW', '2024-11-05 06:52:35', '2024-11-05 06:52:34'),
	(4, 2, 'income', 95000, 113050, 208050, '2024-11-05 06:52:35', 2, 'order', 'completed', 'New income from order #202411053AOKW', '2024-11-05 06:52:35', '2024-11-05 06:52:35');

-- membuang struktur untuk table vocasia_db.instructor_students
CREATE TABLE IF NOT EXISTS `instructor_students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instructor_students: ~0 rows (lebih kurang)
INSERT INTO `instructor_students` (`id`, `instructor_id`, `user_id`, `created_at`, `updated_at`) VALUES
	(1, 2, 3, '2024-10-30 15:47:52', '2024-10-30 08:47:52');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instructor_student_courses: ~3 rows (lebih kurang)
INSERT INTO `instructor_student_courses` (`id`, `instructor_student_id`, `course_id`, `order_id`, `created_at`, `updated_at`) VALUES
	(1, 1, 5, 1, '2024-10-30 15:47:53', '2024-10-30 08:47:52'),
	(2, 1, 21, 2, '2024-11-05 06:52:34', '2024-11-05 06:52:34'),
	(3, 1, 28, 2, '2024-11-05 06:52:35', '2024-11-05 06:52:35');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instuctor_income: ~3 rows (lebih kurang)
INSERT INTO `instuctor_income` (`id`, `instructor_id`, `order_id`, `course_id`, `total_user_payment`, `platform_fee_in_percent`, `total_instructor_income`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 1, 5, 100000, 5, 95000, 'New income from order #202410303ZKEF', '2024-10-30 15:47:50', '2024-10-30 08:47:50'),
	(2, 2, 2, 21, 119000, 5, 113050, 'New income from order #202411053AOKW', '2024-11-05 06:52:31', '2024-11-05 06:52:31'),
	(3, 2, 2, 28, 100000, 5, 95000, 'New income from order #202411053AOKW', '2024-11-05 06:52:35', '2024-11-05 06:52:35');

-- membuang struktur untuk table vocasia_db.lessons
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
) ENGINE=InnoDB AUTO_INCREMENT=359 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.lessons: ~316 rows (lebih kurang)
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
	(41, 25, 'Tutorial Spring Monitoring (Bahasa Indonesia)', 'video', b'0', b'0', '57m 24d', 'https://www.youtube.com/watch?v=DMMzPxYjd2Q&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=10', '', 'Data', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/lesson-attachment-1729156412435-table.csv', NULL, NULL, 'file', '2024-10-16 22:55:25', NULL),
	(58, 28, 'Belajar TOEFL Bagi Pemula. Structure - Skill 1 (Subjects & Verbs)', 'video', b'1', b'0', '17m 10d', 'https://www.youtube.com/watch?v=ZIB0IY_W2og', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(59, 28, 'Belajar TOEFL Bagi Pemula. Structure Pembahasan Soal skill 1 & 2', 'video', b'1', b'0', '16m 20d', 'https://www.youtube.com/watch?v=fCXMJeaf4DE', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(60, 28, 'Belajar TOEFL Bagi Pemula (TOEFL INFORMATION)', 'video', b'1', b'0', '23m 32d', 'https://www.youtube.com/watch?v=-TvJSvYQah4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(61, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 2 (Objects of Prepositions)', 'video', b'1', b'0', '12m 56d', 'https://www.youtube.com/watch?v=PPGbnQxiJCw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(62, 28, 'Belajar TOEFL Bagi Pemula . Structure Skill 3 (Present Participles)', 'video', b'1', b'0', '13m 13d', 'https://www.youtube.com/watch?v=nN0ozxead6Y', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(63, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 4 (Past Participles)', 'video', b'1', b'0', '13m 15d', 'https://www.youtube.com/watch?v=Dkj4rt1lx2A', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(64, 28, 'Belajar TOEFL Bagi Pemula. Structure Pembahasan Soal TOEFL Exercise 3 & 4', 'video', b'1', b'0', '12m 52d', 'https://www.youtube.com/watch?v=UQxkEjMJpA8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(65, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 5 (Coordinate Connectors)', 'video', b'1', b'0', '17m 33d', 'https://www.youtube.com/watch?v=YXNcCHNUFns', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(66, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 6 (Adverb Clause Connectors)', 'video', b'1', b'0', '14m 5d', 'https://www.youtube.com/watch?v=lcW_vYvPeps', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(67, 28, 'Belajar TOEFL Bagi Pemula. Pembahasan Soal Exercise Structure Skill 5 & 6', 'video', b'1', b'0', '11m 5d', 'https://www.youtube.com/watch?v=3oAfGB0cphE', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(68, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 7 (Noun Clause Connectors)', 'video', b'1', b'0', '12m 15d', 'https://www.youtube.com/watch?v=9oa6lDJN9Ic', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(69, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 8 (Noun Clause Connector/Subjects)', 'video', b'1', b'0', '11m 33d', 'https://www.youtube.com/watch?v=EqS2wJPLbGU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(70, 28, 'Belajar TOEFL Bagi Pemula. Pembahasan Soal TOEFL Sikll 7 & 8', 'video', b'1', b'0', '14m 53d', 'https://www.youtube.com/watch?v=XQxImQBgHqg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(71, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 9 (Adjective Clause Connectors)', 'video', b'1', b'0', '14m 7d', 'https://www.youtube.com/watch?v=qlqBXbGlfSQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(72, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 10 (Adjective Clause Connector/Subjects)', 'video', b'1', b'0', '12m 36d', 'https://www.youtube.com/watch?v=dXnCMp4zhFM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(73, 28, 'Belajar TOEFL Bagi Pemula. Pembahasan Soal Structure Exercise 9 & 10.', 'video', b'1', b'0', '12m 8d', 'https://www.youtube.com/watch?v=o6MIQiHusPc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(74, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 11 (Agreement After Prepositional Phrases)', 'video', b'1', b'0', '17m 20d', 'https://www.youtube.com/watch?v=g9tDJ53psBo', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(75, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 12 (Agreement After Expression of Quantity)', 'video', b'1', b'0', '8m 42d', 'https://www.youtube.com/watch?v=J8Yrm0FP5GQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(76, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 13 (Agreement After Certain Words)', 'video', b'1', b'0', '8m 26d', 'https://www.youtube.com/watch?v=QFsrnaqLrg4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(77, 28, 'Belajar TOEFL Bagi Pemula. Pembahasan Soal Skill 11 -13', 'video', b'1', b'0', '12m 48d', 'https://www.youtube.com/watch?v=e52Cxs5IIhk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(78, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 14 (Parallel Structure with Coordinate Conjunctions)', 'video', b'1', b'0', '18m 16d', 'https://www.youtube.com/watch?v=aWDmE5MjtRI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(79, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 15 (Parallel Structure with Paired Conjunctions)', 'video', b'1', b'0', '13m 33d', 'https://www.youtube.com/watch?v=EdGNEQXQcT0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(80, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 15 (Parallel Structure with Paired Conjunctions)', 'video', b'1', b'0', '13m 33d', 'https://www.youtube.com/watch?v=EdGNEQXQcT0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(81, 28, 'Belajar TOEFL Bagi Pemula. Pembahasan Soal Structure Skill 14 & 15.', 'video', b'1', b'0', '14m 21d', 'https://www.youtube.com/watch?v=UoGOSVWPrDw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(82, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 16 (Past Participles After Have)', 'video', b'1', b'0', '8m', 'https://www.youtube.com/watch?v=Wj4kCs_kNco', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(83, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 17 (Prsent Participles or Past Participles After Be).', 'video', b'1', b'0', '9m 15d', 'https://www.youtube.com/watch?v=qk1aKMyXHKk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(84, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 18 (Base Form Verbs After Modals)', 'video', b'1', b'0', '6m 27d', 'https://www.youtube.com/watch?v=WJT9dSu59jQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(85, 28, 'Belajar TOEFL Bagi Pemula. Pembahasan Soal Structure Skill 16 -18.', 'video', b'1', b'0', '7m 23d', 'https://www.youtube.com/watch?v=vpzP2KZASZg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(86, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 19  (Singular and Plural Nouns).', 'video', b'1', b'0', '9m 20d', 'https://www.youtube.com/watch?v=zEQC6ZWFD4c', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(87, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 20 (Countable and Uncountable Nouns).', 'video', b'1', b'0', '7m 23d', 'https://www.youtube.com/watch?v=nv2RrzXhlKw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:55', NULL),
	(88, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 21 (Subject and Object Pronouns).', 'video', b'1', b'0', '6m 29d', 'https://www.youtube.com/watch?v=xsa0D3IGrn0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(89, 28, 'Belajar TOEFL Bagi Pemula. Pembahasan Structure Skill 19 & 20.', 'video', b'1', b'0', '8m 27d', 'https://www.youtube.com/watch?v=HEuDD2WfaCw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(90, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 22 (Possessives).', 'video', b'1', b'0', '7m 6d', 'https://www.youtube.com/watch?v=ijlHLqO4-lo', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(91, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 23 (Pronoun Reference).', 'video', b'1', b'0', '9m 16d', 'https://www.youtube.com/watch?v=EzFB4-6zLB8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(92, 28, 'Belajar TOEFL Bagi Pemula. Pembahasan Structure Skill 21- 23.', 'video', b'1', b'0', '9m 5d', 'https://www.youtube.com/watch?v=RY_8NFs3K5Q', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(93, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 24 (Adjectives and Adverbs).', 'video', b'1', b'0', '14m 58d', 'https://www.youtube.com/watch?v=gMN-Mvbgkys', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(94, 28, 'Belajar TOEFL Bagi Pemula. Structure Skill 25 (Adjectives After Linking Verbs).', 'video', b'1', b'0', '11m', 'https://www.youtube.com/watch?v=n0KEDuqvHXo', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(95, 28, 'Belajar TOEFL Bagi Pemula. Pembahasan Structure Skill 24 & 25.', 'video', b'1', b'0', '7m 59d', 'https://www.youtube.com/watch?v=vnlKuIZQBqA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(96, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 1 (Main Ideas Questions)', 'video', b'1', b'0', '19m 41d', 'https://www.youtube.com/watch?v=p89uSpHE1Ts', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(97, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 1 (Main Ideas Questions)', 'video', b'1', b'0', '19m 41d', 'https://www.youtube.com/watch?v=p89uSpHE1Ts', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(98, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 1 (Main Ideas Questions)', 'video', b'1', b'0', '19m 41d', 'https://www.youtube.com/watch?v=p89uSpHE1Ts', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(99, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 2 (Stated Detail Questions)', 'video', b'1', b'0', '21m 20d', 'https://www.youtube.com/watch?v=b7taPE-ZoYM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(100, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 2 (Stated Detail Questions)', 'video', b'1', b'0', '21m 20d', 'https://www.youtube.com/watch?v=b7taPE-ZoYM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(101, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 2 (Stated Detail Questions)', 'video', b'1', b'0', '21m 20d', 'https://www.youtube.com/watch?v=b7taPE-ZoYM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(102, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 3 (Unstated Detail Questions)', 'video', b'1', b'0', '15m 21d', 'https://www.youtube.com/watch?v=Cpn7iauwjq0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(103, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 3 (Unstated Detail Questions)', 'video', b'1', b'0', '15m 21d', 'https://www.youtube.com/watch?v=Cpn7iauwjq0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(104, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 4 (Implied Detail Questions)', 'video', b'1', b'0', '19m 28d', 'https://www.youtube.com/watch?v=OcmGfWYCxbs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(105, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 4 (Implied Detail Questions)', 'video', b'1', b'0', '19m 28d', 'https://www.youtube.com/watch?v=OcmGfWYCxbs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(106, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 5 (Vocabulary in Context Questions)', 'video', b'1', b'0', '9m 54d', 'https://www.youtube.com/watch?v=oGWWgR616QA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(107, 28, 'Belajar TOEFL Bagi Pemula. Reading Skill 5 (Vocabulary in Context Questions)', 'video', b'1', b'0', '9m 54d', 'https://www.youtube.com/watch?v=oGWWgR616QA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:33:56', NULL),
	(108, 29, 'Belajar TOEFL dari Nol /Cara cepat jawab soal TOEFL', 'video', b'1', b'0', '11m 22d', 'https://www.youtube.com/watch?v=mrSFureczcw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:55:27', NULL),
	(109, 29, 'adverb of manner/word order/noun phrase/ trik jawab soal toefl', 'video', b'1', b'0', '11m 3d', 'https://www.youtube.com/watch?v=rncH8Af5FlQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:55:27', NULL),
	(110, 29, 'Cara Cepat Jawab Soal TOEFL tentang TENSES / belajar TOEFL secara otodidak', 'video', b'1', b'0', '5m 5d', 'https://www.youtube.com/watch?v=XJvoi91VvRI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:55:27', NULL),
	(111, 29, 'EBOOK TOEFL Miss Anti', 'video', b'1', b'0', '1m 28d', 'https://www.youtube.com/watch?v=8YjPQG_PzgI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:55:27', NULL),
	(112, 29, 'WAJIB TAHU!! Parallel Structure SOAL INI SELALU KELUAR DI UJIAN TOEFL', 'video', b'1', b'0', '8m 20d', 'https://www.youtube.com/watch?v=ti-AXIcWO0o', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:55:27', NULL),
	(113, 29, 'Belajar TOEFL dari Nol Part 2 -  Pembahasan soal toefl written  expression', 'video', b'1', b'0', '17m 9d', 'https://www.youtube.com/watch?v=jTeMjROymfg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:55:27', NULL),
	(114, 29, 'Subject Verb Agreement |Tips Cepat Jawab Soal Toefl', 'video', b'1', b'0', '8m 33d', 'https://www.youtube.com/watch?v=KUX2PLDFt84', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:55:28', NULL),
	(115, 29, 'Pronoun / Penggunaan article \'the\',  soal & materi bahasa Inggris kelas 10/X', 'video', b'1', b'0', '15m 16d', 'https://www.youtube.com/watch?v=nuaDVyi7Azc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-04 23:55:28', NULL),
	(116, 30, 'Mengenal Menu & Icon Microsoft Word - Tutorial Microsoft Word PART 1', 'video', b'1', b'0', '13m 5d', 'https://www.youtube.com/watch?v=jg8WqQwSgG0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(117, 30, 'Cara Menyimpan, Membuka dan Menutup Dokumen - Tutorial Microsoft Word PART 2', 'video', b'1', b'0', '11m 55d', 'https://www.youtube.com/watch?v=AZNpffIEv6A', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(118, 30, 'Cara Mengatur Ukuran Kertas, Margin dan Orientasi Kertas - Tutorial Microsoft Word PART 3', 'video', b'1', b'0', '8m 1d', 'https://www.youtube.com/watch?v=zz1qAs-5-u4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(119, 30, 'Cara Mengetik yang Baik dan Membuat Paragraf yang Rapi - Tutorial Microsoft Word PART 4', 'video', b'1', b'0', '28m 3d', 'https://www.youtube.com/watch?v=ojZsphXnthQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(120, 30, 'Cara Mengubah Huruf dan Format Teks - Tutorial Microsoft Word PART 5', 'video', b'1', b'0', '9m 44d', 'https://www.youtube.com/watch?v=pIombwihqqM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(121, 30, 'Cara Membuat Format Teks Berkolom dan Format DropCap - Tutorial Microsoft Word PART 6', 'video', b'1', b'0', '6m 5d', 'https://www.youtube.com/watch?v=VelPdxVssnc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(122, 30, 'Cara Mengatur dan Menggunakan Tabulasi - Tutorial Microsoft Word Part 7', 'video', b'1', b'0', '19m 33d', 'https://www.youtube.com/watch?v=tV_tD_qRFc8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(123, 30, 'Cara Mengetik Pangkat Rumus Kimia dan Simbol - Tutorial Ms Word Part 8', 'video', b'1', b'0', '8m 44d', 'https://www.youtube.com/watch?v=z01aFEJaQZE', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(124, 30, 'Cara Membuat Header dan Footer - Tutorial Ms Word Part 9', 'video', b'1', b'0', '10m 58d', 'https://www.youtube.com/watch?v=t0LfJ3SzbDM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(125, 30, 'Cara Membuat Nomor Halaman Otomatis - Tutorial Ms Word Part 10', 'video', b'1', b'0', '7m 58d', 'https://www.youtube.com/watch?v=MWkkmdUHVOU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(126, 30, 'Cara Membuat Rumus Matematika di Word   Tutorial Ms Word Part 11', 'video', b'1', b'0', '19m 3d', 'https://www.youtube.com/watch?v=d-TM7yhYcU8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(127, 30, 'Cara Membuat Penomoran Otomatis di Word - Tutorial Ms Word Part 12', 'video', b'1', b'0', '16m 45d', 'https://www.youtube.com/watch?v=5sUeNtXes7k', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(128, 30, 'Cara Memasukkan Gambar ke Dalam Dokumen - Tutorial Ms Word Part 13', 'video', b'1', b'0', '12m 34d', 'https://www.youtube.com/watch?v=LingGUcRqgA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:12:54', NULL),
	(129, 31, 'Belajar Excel Dasar dari Nol Part 1 - Tutorial Excel Pemula', 'video', b'1', b'0', '11m 10d', 'https://www.youtube.com/watch?v=Jssd3CISjcg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:19:04', NULL),
	(130, 31, 'Belajar Excel Dasar dari Nol Part 2 - Tutorial Excel Pemula', 'video', b'1', b'0', '13m 28d', 'https://www.youtube.com/watch?v=xSCw4tDpsRY', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:19:04', NULL),
	(131, 31, 'Belajar Excel Dasar dari Nol Part 3 - Tutorial Excel Pemula', 'video', b'1', b'0', '10m 57d', 'https://www.youtube.com/watch?v=NpDhRUZ2FEU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:19:04', NULL),
	(132, 31, 'Belajar Excel Dasar dari Nol Part 4 - Tutorial Excel Pemula', 'video', b'1', b'0', '14m 50d', 'https://www.youtube.com/watch?v=i-zE5fwdvcg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:19:04', NULL),
	(133, 31, 'Belajar Excel dari Nol Part 5 - Tutorial Excel Pemula', 'video', b'1', b'0', '12m 29d', 'https://www.youtube.com/watch?v=1fyNxp8EftU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:19:04', NULL),
	(134, 31, 'Belajar Excel Dasar dari Nol Final Part - Tutorial Excel Pemula', 'video', b'1', b'0', '11m 48d', 'https://www.youtube.com/watch?v=zzlvn7vezvY', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:19:04', NULL),
	(135, 32, 'Course UIUX Design Gratis - Bahasa Indonesia (INTRO)', 'video', b'1', b'0', '3m 41d', 'https://www.youtube.com/watch?v=0V2jrWr1bDY', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(136, 32, 'UI/UX Design, Skill IT Paling Dicari di Masa Depan (Eps 01)', 'video', b'1', b'0', '4m 50d', 'https://www.youtube.com/watch?v=D2Ipi15spno', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(137, 32, 'Trik Sakti Jago UI/UX Design (Eps 2)', 'video', b'1', b'0', '5m 54d', 'https://www.youtube.com/watch?v=lL8aToZvvfQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(138, 32, 'Rumus Ampuh UI/UX Design Menjadi Spesial di Hati Pengguna (Eps 3)', 'video', b'1', b'0', '5m 14d', 'https://www.youtube.com/watch?v=VuBrwKd3eQA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(139, 32, 'Trik Khusus UI/UX Design Memberikan Solusi Interaktif yang Tepat Bagi Pengguna (Eps 4)', 'video', b'1', b'0', '7m 57d', 'https://www.youtube.com/watch?v=94DKW9bnV_8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(140, 32, 'Semakin Memanjakan User Semakin Canggih Sebuah Aplikasi (Eps 5)', 'video', b'1', b'0', '4m 50d', 'https://www.youtube.com/watch?v=OoKE7acXVtQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(141, 32, 'Trik Praktis Membuat Sketsa Pada UI/UX Design (Eps 6)', 'video', b'1', b'0', '6m 5d', 'https://www.youtube.com/watch?v=mmdW_2_aips', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(142, 32, 'Teknik Handal UI/UX Design dengan Figma (Eps 7)', 'video', b'1', b'0', '7m 15d', 'https://www.youtube.com/watch?v=Thxfu0F5xyg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(143, 32, 'Cara cepat membuat layout UI/UX menggunakan Figma (Eps 8)', 'video', b'1', b'0', '25m 42d', 'https://www.youtube.com/watch?v=41_zU4pheWg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(144, 32, 'Mendesain Mockup Design High Fidelity (Eps 9)', 'video', b'1', b'0', '14m 38d', 'https://www.youtube.com/watch?v=6aUf1Vj3wdA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:34', NULL),
	(145, 32, 'Trik Cerdas Optimalisasi Desain UI/UX dengan Komponen dan Plugin di Figma (Eps 10)', 'video', b'1', b'0', '9m 7d', 'https://www.youtube.com/watch?v=64j9OjngGT4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:35', NULL),
	(146, 32, 'Membangun Best Practice Desain UI/UX bagi Pengguna (Eps 11)', 'video', b'1', b'0', '5m 32d', 'https://www.youtube.com/watch?v=3DVJ7TGZL10', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:35', NULL),
	(147, 32, 'Implementasi Interaktivitas Terbaik antara UI/UX dengan Pengguna (Eps 12)', 'video', b'1', b'0', '6m 5d', 'https://www.youtube.com/watch?v=pkpbitW163Q', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:22:35', NULL),
	(148, 33, 'Digital Marketing In 5 Minutes | What Is Digital Marketing? | Learn Digital Marketing | Simplilearn', 'video', b'1', b'0', '5m 25d', 'https://www.youtube.com/watch?v=bixR-KIJKYM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(149, 33, 'Digital Marketing Course Part - 1 🔥| Digital Marketing Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '10j 50m 20d', 'https://www.youtube.com/watch?v=nU-IIXBWlS4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(150, 33, 'Digital Marketing Course Part - 2 🔥 | Digital Marketing Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '10j 56m 40d', 'https://www.youtube.com/watch?v=fsDwHJa_xcE', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(151, 33, 'Digital Marketing Course Part - 3 🔥 | Digital Marketing Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '6j 58m 53d', 'https://www.youtube.com/watch?v=gNBXwFsNm-8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(152, 33, 'Digital Marketing Course In 11 Hours | Digital Marketing Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '11j 27m 7d', 'https://www.youtube.com/watch?v=gkjafTTU_Go', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(153, 33, 'What Is Digital Marketing? | Introduction To Digital Marketing | Digital Marketing | Simplilearn', 'video', b'1', b'0', '37m 34d', 'https://www.youtube.com/watch?v=ZVuHLPl69mM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(154, 33, 'Digital Marketing Course | Digital Marketing Tutorial For Beginners | Digital Marketing |Simplilearn', 'video', b'1', b'0', '1j 17m 35d', 'https://www.youtube.com/watch?v=kpgerCE095A', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(155, 33, 'Digital Marketing Training | Digital Marketing Course | Digital Marketing Tutorial | Simplilearn', 'video', b'1', b'0', '6m 2d', 'https://www.youtube.com/watch?v=xA_yMYN19ug', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(156, 33, 'Best Digital Marketing Tools | Digital Marketing Tools 2023 | Digital Marketing | Simplilearn', 'video', b'1', b'0', '2j 37m 29d', 'https://www.youtube.com/watch?v=44trTB16YV4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(157, 33, 'What Is SEO? | What Is SEO And How Does It Work? | SEO Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '49m 24d', 'https://www.youtube.com/watch?v=HeeUHugrG5U', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(158, 33, 'SEO Tutorial For Beginners | Learn SEO Step By Step | SEO Tutorial | Advanced SEO 2020 | Simplilearn', 'video', b'1', b'0', '2j 31m 22d', 'https://www.youtube.com/watch?v=Qs0_Qu22v4M', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(159, 33, '7 SEO Tips And Tricks - That Actually Work | SEO Tips 2020 | SEO Tutorial For Beginners |Simplilearn', 'video', b'1', b'0', '54m 51d', 'https://www.youtube.com/watch?v=ZMVVWfiojd4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(160, 33, 'Keyword Research Tutorial | Keyword Research For SEO 2021 | SEO Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '44m 47d', 'https://www.youtube.com/watch?v=RRPuWPtT7YY', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(161, 33, 'How To Rank #1 On Google | How To Improve Google Ranking | SEO Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '1j 14m 30d', 'https://www.youtube.com/watch?v=atIdSVitWTg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(162, 33, 'Google Tag Manager | Google Tag Manager Tutorial 2020 | Google Tag Manager Setup | Simplilearn', 'video', b'1', b'0', '36m 35d', 'https://www.youtube.com/watch?v=4PMlwLmSKNU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(163, 33, 'Google Ads | Google Ads Tutorial 2020 | Google AdWords Tutorial 2020 | PPC Advertising | Simplilearn', 'video', b'1', b'0', '1j 2m 5d', 'https://www.youtube.com/watch?v=YkDXgJLpLVI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(164, 33, 'Google Display Network Tutorial | Google Display Ads | Google Ads | Digital Marketing | Simplilearn', 'video', b'1', b'0', '35m 36d', 'https://www.youtube.com/watch?v=gvOL5zk7q4A', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(165, 33, 'Google Analytics | Google Analytics Tutorial For Beginners | Google Analytics Setup | Simplilearn', 'video', b'1', b'0', '57m 29d', 'https://www.youtube.com/watch?v=jrNhpd2_auI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(166, 33, 'How To Set Up Goals In Google Analytics 2021 | Google Analytics Advanced | Simplilearn', 'video', b'1', b'0', '39m 3d', 'https://www.youtube.com/watch?v=BwMcrzP7cPU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(167, 33, 'How To Setup Event Tracking In Google Analytics | Event Tracking In Google Analytics | Simplilearn', 'video', b'1', b'0', '27m 5d', 'https://www.youtube.com/watch?v=oQNA8itUXOk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(168, 33, 'Google Data Studio | Google Data Studio Tutorial 2020 | Google Data Studio Dashboard | Simplilearn', 'video', b'1', b'0', '25m 34d', 'https://www.youtube.com/watch?v=zieh2hr1toQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(169, 33, 'How To Start Social Media Marketing | Social Media Marketing Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '50m 13d', 'https://www.youtube.com/watch?v=KEirK5QWgrA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(170, 33, 'Facebook Ads Tutorial 2020 | How To Run Facebook Ads | Facebook Ads Manager 2020 | Simplilearn', 'video', b'1', b'0', '1j 1m 19d', 'https://www.youtube.com/watch?v=mMvoA1k_qHA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(171, 33, 'Facebook Advertising Tips & Strategies 2020 | Facebook Advertising Tips | Facebook Ads | Simplilearn', 'video', b'1', b'0', '43m 12d', 'https://www.youtube.com/watch?v=Xx-iLff0R1E', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(172, 33, 'How To Rank YouTube Videos | How To Rank YouTube Videos Fast In 2020 | YouTube SEO Tips |Simplilearn', 'video', b'1', b'0', '54m 46d', 'https://www.youtube.com/watch?v=NBXC6-9dYfc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(173, 33, 'Complete SEO Guide For 2019 | SEO Guide 2020 | SEO Guide For Beginners | SEO Tutorial | Simplilearn', 'video', b'1', b'0', '57m 17d', 'https://www.youtube.com/watch?v=BvXRW2x5Ank', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(174, 33, 'How To Create A YouTube Channel 2020 | How To Start A YouTube Channel For Beginners | Simplilearn', 'video', b'1', b'0', '35m 25d', 'https://www.youtube.com/watch?v=A8GPpETfNuE', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(175, 33, 'How To Increase YouTube Subscribers | How To Get YouTube Subscribers Fast 2020 | Simplilearn', 'video', b'1', b'0', '48m 7d', 'https://www.youtube.com/watch?v=9HJTvh3XAt8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(176, 33, 'YouTube Ads | YouTube Advertising | How To Run YouTube Ads 2020 | YouTube Ads Tutorial | Simplilearn', 'video', b'1', b'0', '51m 47d', 'https://www.youtube.com/watch?v=mHnN_dhCVt4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(177, 33, 'How To Increase Followers On Instagram | 20 Tips To Increase Instagram Followers 2020 | Simplilearn', 'video', b'1', b'0', '36m 52d', 'https://www.youtube.com/watch?v=ZXgfGnVUwnk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(178, 33, 'How To Increase Twitter Followers | 20 Tips On How To Get Followers On Twitter 2020 | Simplilearn', 'video', b'1', b'0', '39m 29d', 'https://www.youtube.com/watch?v=kLKXnpMLJj4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(179, 33, 'Content Marketing Strategy | Content Marketing Examples | Content Marketing 2020 | Simplilearn', 'video', b'1', b'0', '33m 58d', 'https://www.youtube.com/watch?v=6Oj6GoYrF-s', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(180, 33, 'How To Get Traffic To Your Website | Increase Website Traffic 2020 | Digital Marketing | Simplilearn', 'video', b'1', b'0', '41m 15d', 'https://www.youtube.com/watch?v=6O14SVVarUM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(181, 33, 'Email Marketing | Email Marketing Tutorial | What Is Email Marketing & How Does It Work |Simplilearn', 'video', b'1', b'0', '1j 2m 37d', 'https://www.youtube.com/watch?v=RkiX6zcjJBk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(182, 33, 'Mailchimp Tutorial 2020 | Mailchimp Email Marketing | How To Use Mailchimp For Beginner |Simplilearn', 'video', b'1', b'0', '36m 13d', 'https://www.youtube.com/watch?v=rSQSvKlZLF4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(183, 33, 'Affiliate Marketing | Affiliate Marketing For Beginners | What Is Affiliate Marketing? | Simplilearn', 'video', b'1', b'0', '45m 51d', 'https://www.youtube.com/watch?v=x6EbMfFICQU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(184, 33, 'How To Become A Digital Marketer | How To Start Career In Digital Marketing In 2020 | Simplilearn', 'video', b'1', b'0', '13m 8d', 'https://www.youtube.com/watch?v=8ZOfPh4OwOQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(185, 33, 'Digital Marketing Career, Jobs, Resume, Salary, Skills, Roles & Responsibilities | Simplilearn', 'video', b'1', b'0', '1j 2m 39d', 'https://www.youtube.com/watch?v=xxH34j9sri0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(186, 33, 'Digital Marketing Course | Digital Marketing Training | Digtial Marketing For Beginners |Simplilearn', 'video', b'1', b'0', '6m 1d', 'https://www.youtube.com/watch?v=wUtqAOgfP6c', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(187, 33, 'Digital Marketing Interview Questions And Answers 2023 | Digital Marketing Interview | Simplilearn', 'video', b'1', b'0', '2j 10m 31d', 'https://www.youtube.com/watch?v=rtuLlTHeaqU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(188, 33, 'Search Engine Marketing Interview Questions | Google Ads Interview Questions & Answers | Simplilearn', 'video', b'1', b'0', '35m 8d', 'https://www.youtube.com/watch?v=K6Kqa5O_hCE', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(189, 33, 'Facebook Shop Tutorial 2020 | Facebook Shop Setup | How to Set up A Facebook Shop Page?| Simplilearn', 'video', b'1', b'0', '16m 13d', 'https://www.youtube.com/watch?v=kqa9iXCI2m8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(190, 33, 'Facebook Algorithm 2020 | How Facebook Algorithm Works | Facebook Algorithm Explained | Simplilearn', 'video', b'1', b'0', '16m', 'https://www.youtube.com/watch?v=7QT7V2uSPuk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(191, 33, 'Facebook Live Streaming Tutorial | How To Set Up Livestream On Facebook | Simplilearn', 'video', b'1', b'0', '7m 25d', 'https://www.youtube.com/watch?v=mlAIwu9W6AQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(192, 33, 'Facebook Ads Tutorial | How To Run Facebook Ads | Facebook Advertising Tutorial 2020 | Simplilearn', 'video', b'1', b'0', '51m 38d', 'https://www.youtube.com/watch?v=pVMSglkGRIg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(193, 33, 'SEO Tutorial For Beginners | What Is SEO & How Does It Work? | Learn SEO Step By Step | Simplilearn', 'video', b'1', b'0', '38m 42d', 'https://www.youtube.com/watch?v=5qEbCaEWju0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(194, 33, 'SEO Backlinks Tutorial | SEO Link Building Tutorial | Simplilearn', 'video', b'1', b'0', '59m 37d', 'https://www.youtube.com/watch?v=6McePZz4XZM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(195, 33, 'Social Media Marketing Strategy | Social Media Marketing Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '12m 31d', 'https://www.youtube.com/watch?v=qbH8jH1gg-g', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(196, 33, 'Introduction To Web Analytics Certification Training | Simplilearn', 'video', b'1', b'0', '5m 30d', 'https://www.youtube.com/watch?v=BuEYkI2_b5I', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(197, 33, 'Google Analytics Tutorial For Beginners | Digital Marketing Tutorial For Beginners | Simplilearn', 'video', b'1', b'0', '1j 39m 20d', 'https://www.youtube.com/watch?v=OJ7D66gUACU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:27:07', NULL),
	(198, 34, 'Akuntansi dan Standar Akuntansi Keuangan', 'video', b'1', b'0', '16m 34d', 'https://www.youtube.com/watch?v=E6fZ1jqT2Uk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:54', NULL),
	(199, 34, 'Akuntansi dan Standar Akuntansi Keuangan', 'video', b'1', b'0', '9m 34d', 'https://www.youtube.com/watch?v=jL7HlEMcqGo', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:54', NULL),
	(200, 34, 'Akuntansi dan Standar Akuntansi Keuangan', 'video', b'1', b'0', '9m 34d', 'https://www.youtube.com/watch?v=NKasrLs6baM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:54', NULL),
	(201, 34, 'Akuntansi dan Standar Akuntansi Keuangan', 'video', b'1', b'0', '10m 16d', 'https://www.youtube.com/watch?v=Wgl40kjXEeQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:54', NULL),
	(202, 34, 'Akuntansi dan Standar Akuntansi Keuangan', 'video', b'1', b'0', '7m 28d', 'https://www.youtube.com/watch?v=q5x4RswVQks', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:54', NULL),
	(203, 34, 'Kerangka Dasar Laporan Keuangan', 'video', b'1', b'0', '10m 34d', 'https://www.youtube.com/watch?v=Z-1IRuLNcSs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:54', NULL),
	(204, 34, 'Kerangka Dasar Laporan Keuangan', 'video', b'1', b'0', '9m 30d', 'https://www.youtube.com/watch?v=eQ519wNBLZU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:54', NULL),
	(205, 34, 'Kerangka Dasar Laporan Keuangan', 'video', b'1', b'0', '9m 32d', 'https://www.youtube.com/watch?v=Bpyjarf5Pu4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:54', NULL),
	(206, 34, 'Kerangka Dasar Laporan Keuangan', 'video', b'1', b'0', '10m 14d', 'https://www.youtube.com/watch?v=ND-PB-pi7m4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:54', NULL),
	(207, 34, 'Kerangka Dasar Laporan Keuangan', 'video', b'1', b'0', '15m 14d', 'https://www.youtube.com/watch?v=6PBL8bqCH4U', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(208, 34, 'Kerangka Dasar Laporan Keuangan', 'video', b'1', b'0', '8m 49d', 'https://www.youtube.com/watch?v=BzdUotb2lhM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(209, 34, 'Income Statement Comprehensive and Statement of Change in Equity', 'video', b'1', b'0', '10m 39d', 'https://www.youtube.com/watch?v=Pd9qGT7-be8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(210, 34, 'Income Statement Comprehensive and Statement of Change in Equity', 'video', b'1', b'0', '10m 17d', 'https://www.youtube.com/watch?v=dqZyyOxwgks', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(211, 34, 'Income Statement Comprehensive and Statement of Change in Equity', 'video', b'1', b'0', '12m 20d', 'https://www.youtube.com/watch?v=MVTfKaUclZU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(212, 34, 'Income Statement Comprehensive and Statement of Change in Equity', 'video', b'1', b'0', '8m 34d', 'https://www.youtube.com/watch?v=PWrKpD9OA20', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(213, 34, 'Income Statement Comprehensive and Statement of Change in Equity', 'video', b'1', b'0', '10m 51d', 'https://www.youtube.com/watch?v=xbT9ce1mvu0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(214, 34, 'Laporan Posisi Keuangan (Statement of Financial Position)', 'video', b'1', b'0', '11m 56d', 'https://www.youtube.com/watch?v=u6pXb4s7XHU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(215, 34, 'Laporan Posisi Keuangan (Statement of Financial Position)', 'video', b'1', b'0', '11m 21d', 'https://www.youtube.com/watch?v=geiOfPGnsak', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(216, 34, 'Laporan Posisi Keuangan (Statement of Financial Position)', 'video', b'1', b'0', '7m 54d', 'https://www.youtube.com/watch?v=d1xbc7m_t2U', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(217, 34, 'Laporan Posisi Keuangan (Statement of Financial Position)', 'video', b'1', b'0', '8m 48d', 'https://www.youtube.com/watch?v=UWP1x9wZjCk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(218, 34, 'Laporan Posisi Keuangan (Statement of Financial Position)', 'video', b'1', b'0', '10m 24d', 'https://www.youtube.com/watch?v=xNFZzj34UN4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(219, 34, 'Laporan Arus Kas', 'video', b'1', b'0', '11m 14d', 'https://www.youtube.com/watch?v=G88fO58ir6k', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(220, 34, 'Laporan Arus Kas', 'video', b'1', b'0', '12m 44d', 'https://www.youtube.com/watch?v=4qgFpUrJeSc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(221, 34, 'Laporan Arus Kas', 'video', b'1', b'0', '9m 42d', 'https://www.youtube.com/watch?v=1npy0ZOxxC4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(222, 34, 'Laporan Arus Kas', 'video', b'1', b'0', '6m 8d', 'https://www.youtube.com/watch?v=Jfj2OF3NOPs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(223, 34, 'Laporan Arus Kas', 'video', b'1', b'0', '9m 45d', 'https://www.youtube.com/watch?v=_7fQZEvAyGc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(224, 34, 'Cash', 'video', b'1', b'0', '8m 11d', 'https://www.youtube.com/watch?v=mgu8RxGWi0Y', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(225, 34, 'Cash', 'video', b'1', b'0', '3m 18d', 'https://www.youtube.com/watch?v=kEes63CxWQI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(226, 34, 'Cash', 'video', b'1', b'0', '14m 4d', 'https://www.youtube.com/watch?v=mgSefEJxj7M', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(227, 34, 'Cash', 'video', b'1', b'0', '7m 45d', 'https://www.youtube.com/watch?v=rwqr-lfpMws', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(228, 34, 'Receivables', 'video', b'1', b'0', '10m 11d', 'https://www.youtube.com/watch?v=bg6VrfDhG7I', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(229, 34, 'Receivables', 'video', b'1', b'0', '11m 47d', 'https://www.youtube.com/watch?v=o7Wx8ytcfX4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(230, 34, 'Receivables', 'video', b'1', b'0', '10m 45d', 'https://www.youtube.com/watch?v=iwTegEnZsmw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(231, 34, 'Receivables', 'video', b'1', b'0', '13m 34d', 'https://www.youtube.com/watch?v=XfqhOq_hDxg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(232, 34, 'Receivables', 'video', b'1', b'0', '12m 15d', 'https://www.youtube.com/watch?v=VFCcc-zmHIA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(233, 34, 'Inventory', 'video', b'1', b'0', '7m 59d', 'https://www.youtube.com/watch?v=clIXrrHQE9g', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(234, 34, 'Inventory', 'video', b'1', b'0', '9m 31d', 'https://www.youtube.com/watch?v=eNSCSHA-BFY', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(235, 34, 'Inventory', 'video', b'1', b'0', '10m 41d', 'https://www.youtube.com/watch?v=CeakIaDj3rA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(236, 34, 'Inventory', 'video', b'1', b'0', '8m 20d', 'https://www.youtube.com/watch?v=lz3TWWaY77g', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(237, 34, 'Inventory', 'video', b'1', b'0', '11m 11d', 'https://www.youtube.com/watch?v=ktlYzezQwOs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(238, 34, 'Fix Asset (Aset Tetap)', 'video', b'1', b'0', '10m 41d', 'https://www.youtube.com/watch?v=qCnKUdyZGM8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(239, 34, 'Fix Asset (Aset Tetap)', 'video', b'1', b'0', '9m 12d', 'https://www.youtube.com/watch?v=XfnCtYQnqZ8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(240, 34, 'Fix Asset (Aset Tetap)', 'video', b'1', b'0', '8m 53d', 'https://www.youtube.com/watch?v=vmyntEencek', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(241, 34, 'Fix Asset (Aset Tetap)', 'video', b'1', b'0', '7m 40d', 'https://www.youtube.com/watch?v=9AUX0tk5lCA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(242, 34, 'Fix Asset (Aset Tetap)', 'video', b'1', b'0', '9m 10d', 'https://www.youtube.com/watch?v=E3YNkHq61HI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(243, 34, 'Fix Asset (Aset Tetap)', 'video', b'1', b'0', '9m 50d', 'https://www.youtube.com/watch?v=lAK-EzcXRLU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(244, 34, 'Aset Tidak Berwujud', 'video', b'1', b'0', '6m 49d', 'https://www.youtube.com/watch?v=S9IriuIr5Dc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(245, 34, 'Aset Tidak Berwujud', 'video', b'1', b'0', '10m 29d', 'https://www.youtube.com/watch?v=tpJqzbiyVOQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(246, 34, 'Aset Tidak Berwujud', 'video', b'1', b'0', '8m 55d', 'https://www.youtube.com/watch?v=ga5ujM8fsHY', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(247, 34, 'Aset Tidak Berwujud', 'video', b'1', b'0', '13m 32d', 'https://www.youtube.com/watch?v=fvguVv-60_0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:31:55', NULL),
	(248, 35, 'Real Estate License: What is it? Real estate license exam questions.', 'video', b'1', b'0', '2m', 'https://www.youtube.com/watch?v=untmTBn4qQU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(249, 35, 'Functional Obsolescence: What is it? Real estate license exam questions.', 'video', b'1', b'0', '1m 16d', 'https://www.youtube.com/watch?v=JFOMncNU3Cg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(250, 35, 'Economic Obsolescence: What is it? Real estate license exam questions.', 'video', b'1', b'0', '1m 4d', 'https://www.youtube.com/watch?v=Yf3ByyoWmTs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(251, 35, 'Emblements: What are they? Real estate license exam questions.', 'video', b'1', b'0', '1m 56d', 'https://www.youtube.com/watch?v=s5SoZrEhK1k', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(252, 35, 'Easements: What are they? Real estate license exam questions.', 'video', b'1', b'0', '1m 56d', 'https://www.youtube.com/watch?v=3HRyPzgbOVA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(253, 35, 'Puffing: What is it? Real estate license exam questions.', 'video', b'1', b'0', '1m 36d', 'https://www.youtube.com/watch?v=Qw9pp3XoOjw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(254, 35, 'Freehold Estates: What are they? Real estate license exam questions.', 'video', b'1', b'0', '2m 38d', 'https://www.youtube.com/watch?v=V4KvJwwkE_s', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(255, 35, 'Leasehold Estates: What are they? Real estate license exam questions.', 'video', b'1', b'0', '2m 49d', 'https://www.youtube.com/watch?v=opPc-Jp_-jc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(256, 35, 'Contingent Property: What is it? Real estate license exam questions.', 'video', b'1', b'0', '4m 36d', 'https://www.youtube.com/watch?v=ha3okEHjKJA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(257, 35, 'Government Powers: What are they? Real estate license exam questions.', 'video', b'1', b'0', '3m 20d', 'https://www.youtube.com/watch?v=BJbk71kzBN4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(258, 35, 'Water Rights: What are they? Real estate license exam questions.', 'video', b'1', b'0', '4m 49d', 'https://www.youtube.com/watch?v=MKWcnpqhU-E', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(259, 35, 'Appurtenant: What does it mean? Real estate license exam questions.', 'video', b'1', b'0', '3m 13d', 'https://www.youtube.com/watch?v=WeUtix-p8zM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(260, 35, 'Escrow: What is it? Real estate license exam questions.', 'video', b'1', b'0', '3m 54d', 'https://www.youtube.com/watch?v=d2XtfRNusiY', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(261, 35, 'Bilateral Contracts vs Unilateral Contracts: The difference? Real estate license exam questions.', 'video', b'1', b'0', '4m 4d', 'https://www.youtube.com/watch?v=2Qh0TO7nfnU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(262, 35, 'Fraud: What is it? Real estate license exam questions.', 'video', b'1', b'0', '3m 55d', 'https://www.youtube.com/watch?v=twyjbl4UXgw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(263, 35, 'Agency Relationships: What are they? Real estate license exam questions.', 'video', b'1', b'0', '6m 7d', 'https://www.youtube.com/watch?v=mnHNU_ttdyw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(264, 35, 'Air Rights: What are they? Real estate license exam questions.', 'video', b'1', b'0', '3m 23d', 'https://www.youtube.com/watch?v=Kh2BCBGu6eo', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(265, 35, 'Dominant vs Servient Estate: The difference? Real estate license exam questions.', 'video', b'1', b'0', '4m 19d', 'https://www.youtube.com/watch?v=4Eyn5JY6BFk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(266, 35, 'Dual Agency: What is it? Real estate license exam questions.', 'video', b'1', b'0', '5m 19d', 'https://www.youtube.com/watch?v=gKu0kvMlt_A', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(267, 35, 'Habendum Clause: What is it? Real estate license exam questions.', 'video', b'1', b'0', '3m 2d', 'https://www.youtube.com/watch?v=lt86dJOB0dA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(268, 35, 'Alienation Clause: What is it? Real estate license exam questions.', 'video', b'1', b'0', '2m 51d', 'https://www.youtube.com/watch?v=i4nj_XDL7i8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(269, 35, 'Defeasance Clause: What is it? Real estate license exam questions.', 'video', b'1', b'0', '3m 21d', 'https://www.youtube.com/watch?v=nVdzkeyQ_i0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(270, 35, 'Acceleration Clause: What is it? Real estate license exam questions.', 'video', b'1', b'0', '3m 25d', 'https://www.youtube.com/watch?v=wkHON-EDjF8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(271, 35, 'Prepayment Penalty Clause: What is it? Real estate license exam questions.', 'video', b'1', b'0', '4m 9d', 'https://www.youtube.com/watch?v=C-fXxq3Zv9s', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(272, 35, 'Subordination Clause: What is it? Real estate license exam questions.', 'video', b'1', b'0', '4m 14d', 'https://www.youtube.com/watch?v=Chaya_01X-I', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(273, 35, 'Release Clause: What is it? Real estate license exam questions.', 'video', b'1', b'0', '3m 14d', 'https://www.youtube.com/watch?v=FnZpYWAtgpU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(274, 35, 'Escheat: What is it? Real estate license exam questions.', 'video', b'1', b'0', '3m 39d', 'https://www.youtube.com/watch?v=PTe27m7yAWs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(275, 35, 'Eminent Domain: What is it? Real estate license exam questions.', 'video', b'1', b'0', '6m 19d', 'https://www.youtube.com/watch?v=TAzWciE4obc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(276, 35, 'Attorney-in-Fact: Who is that? Real estate license exam questions.', 'video', b'1', b'0', '3m 54d', 'https://www.youtube.com/watch?v=nx6hfhcLorI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(277, 35, 'Title vs Deed: The difference? Real estate license exam questions.', 'video', b'1', b'0', '4m 46d', 'https://www.youtube.com/watch?v=0C4RkX0-G_w', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(278, 35, 'Equitable Title: What is it? Real estate license exam questions.', 'video', b'1', b'0', '3m 23d', 'https://www.youtube.com/watch?v=tymRSxMsaTA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(279, 35, 'Types of Deeds: What are they? Real estate license exam questions.', 'video', b'1', b'0', '5m 30d', 'https://www.youtube.com/watch?v=E5grAjKIG0k', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(280, 35, 'Adverse Possession: What is it? Real estate license exam questions.', 'video', b'1', b'0', '6m 10d', 'https://www.youtube.com/watch?v=BnlFjynvgSw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(281, 35, 'Color of Title: What is it? Real estate license exam questions.', 'video', b'1', b'0', '2m 55d', 'https://www.youtube.com/watch?v=_oRRipt6wF0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(282, 35, 'Ingress and Egress: The Difference? Real estate license exam questions.', 'video', b'1', b'0', '4m 21d', 'https://www.youtube.com/watch?v=3Jf3_3cQq98', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(283, 35, 'Debits and Credits: The Difference? Real estate license exam questions.', 'video', b'1', b'0', '4m 16d', 'https://www.youtube.com/watch?v=YEziTyUwn4E', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(284, 35, 'Real Estate Economics: What is it? Real estate license exam questions.', 'video', b'1', b'0', '10m 26d', 'https://www.youtube.com/watch?v=ednHdhSSV_8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(285, 35, 'Real Estate Novation: What is it? Real estate license exam questions.', 'video', b'1', b'0', '9m 35d', 'https://www.youtube.com/watch?v=NmjdyxgliPE', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(286, 35, 'Commingling: What is it? Real estate license exam questions.', 'video', b'1', b'0', '9m 2d', 'https://www.youtube.com/watch?v=611i2NIZ5ho', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(287, 35, 'Valid Contracts: What are they? Real estate license exam questions.', 'video', b'1', b'0', '8m 13d', 'https://www.youtube.com/watch?v=sDzZLkc7Skc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(288, 35, 'Valid, Void, and Voidable: The Difference? Real estate license exam questions.', 'video', b'1', b'0', '8m 25d', 'https://www.youtube.com/watch?v=Ksmf-6J9GSA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(289, 35, 'Executed and Executory: The Difference? Real estate license exam questions.', 'video', b'1', b'0', '6m 22d', 'https://www.youtube.com/watch?v=UVw7X5FPCNU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(290, 35, 'Implied Contracts: What are they? Real estate license exam questions.', 'video', b'1', b'0', '8m 40d', 'https://www.youtube.com/watch?v=Wi1WthTGZRQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(291, 35, 'Option Contracts: What are they? Real estate license exam questions.', 'video', b'1', b'0', '9m 7d', 'https://www.youtube.com/watch?v=mXD4qFzwJCc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(292, 35, 'Market Data Approach: What is it? Real estate license exam questions.', 'video', b'1', b'0', '6m 48d', 'https://www.youtube.com/watch?v=5MBxthAiuOc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(293, 35, 'Cost Approach: What is it? Real estate license exam questions.', 'video', b'1', b'0', '8m 28d', 'https://www.youtube.com/watch?v=8JhuEM6Aoxo', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(294, 35, 'Income Approach: What is it? Real estate license exam questions.', 'video', b'1', b'0', '7m 26d', 'https://www.youtube.com/watch?v=rrgba2pySBo', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(295, 35, 'Principle of Conformity: What is it? Real estate license exam questions.', 'video', b'1', b'0', '4m 28d', 'https://www.youtube.com/watch?v=5qg2j1DSTPA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(296, 35, 'Principle of Substitution: What is it? Real estate license exam questions.', 'video', b'1', b'0', '4m 7d', 'https://www.youtube.com/watch?v=KnCLzHlgWqs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(297, 35, 'Principle of Contribution: What is it? Real estate license exam questions.', 'video', b'1', b'0', '4m 14d', 'https://www.youtube.com/watch?v=OpDDiyRJ9SM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:36:04', NULL),
	(298, 36, 'modal ternak bebek pedaging 100 ekor dan perlengkapan awal |ternakbebekpemula |ternakbebekpedaging', 'video', b'1', b'0', '11m 22d', 'https://www.youtube.com/watch?v=mJk5YZofmlw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(299, 36, 'pakan murah bernutrisi tinggi |ternakbebek  |ternakbebekpemula |ternakbebekpedaging |ternakbebek', 'video', b'1', b'0', '5m 18d', 'https://www.youtube.com/watch?v=VZ2K_gGRfsw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(300, 36, 'cara ampuh mengusir lalat di kandang |ternakbebekpedaging |ternakbebekpemula |ternakbebekpetelur', 'video', b'1', b'0', '9m 42d', 'https://www.youtube.com/watch?v=YteTeEFe8qA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(301, 36, 'cara mengatasi rontok bulu pada bebek agar bebek cepat bertelur|obat alami bebek di awal musim hujan', 'video', b'1', b'0', '5m 36d', 'https://www.youtube.com/watch?v=cb5tPPDWqRc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(302, 36, 'SUKSES 7 KANDANG SISTEM ESTAFET BEBEK PEDAGING     | ternakbebek |pakanbebek |bebek |kandang |pakan', 'video', b'1', b'0', '11m 19d', 'https://www.youtube.com/watch?v=4P2NIKkPiWY', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(303, 36, 'bebek pedaging baru datang cara alami terbukti manjur langkah awal bebek segar tidak stres |bebek', 'video', b'1', b'0', '5m 11d', 'https://www.youtube.com/watch?v=Ph1W1TMEIEg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(304, 36, 'TERNAK BEBEK PETELUR MUSIM PENGHUJAN MENCEGAH STRES | ternakbebekpetelur | ternakbebek |bebekpeking', 'video', b'1', b'0', '7m 56d', 'https://www.youtube.com/watch?v=IK7leNy-aMo', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(305, 36, 'PAKAN ALTERNATIF BEBEK PEDAGING HEMAT PAKAN 70% BERNUTRISI TINGGI |ternakbebekpemula |ternakbebek', 'video', b'1', b'0', '6m 15d', 'https://www.youtube.com/watch?v=MZLc8YmqxAw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(306, 36, 'cara memasang tempat minum otomatis bebek dan ayam broiler |ternakbebek |ternakbebekpetelur', 'video', b'1', b'0', '9m 16d', 'https://www.youtube.com/watch?v=YOeq6PRAh4A', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(307, 36, 'cara membedakan dod jantan dan betina |ternakbebekpetelur |ternakbebek |ternakbebekpemula | kandang', 'video', b'1', b'0', '5m 26d', 'https://www.youtube.com/watch?v=s5CqqEM17E4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(308, 36, 'Pakan bebek pedaging agar cepat besar | Memberi pakan satu mingu sekali |ternakbebek |bebekpemula', 'video', b'1', b'0', '6m 21d', 'https://www.youtube.com/watch?v=w3f1beJBfUU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(309, 36, 'Perawatan dod umur 1 sampai 15 hari |ternakbebek |ternakbebekpemula |ternakbebekpetelur |bebekdaging', 'video', b'1', b'0', '9m 53d', 'https://www.youtube.com/watch?v=8B1WnOxsOKY', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(310, 36, 'ternak bebek pedaging pemberian vitamin |ternakbebek |ternakbebekpetelur |bebek |ternakbebekpedaging', 'video', b'1', b'0', '5m 15d', 'https://www.youtube.com/watch?v=C-E-SQvO72M', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(311, 36, 'cara menyebuhkan penyakit snot bebek |ternakbebek |ternakbebekpetelur |ternakbebekpemula |bebek', 'video', b'1', b'0', '5m 37d', 'https://www.youtube.com/watch?v=YQgRAxVoMiA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(312, 36, 'PAKAN GRATIS KAYA PROTEIN | HEMAT 50RB PERHARI ternakbebek |ternakbebekpetelur |ternakbebekpemula', 'video', b'1', b'0', '11m 16d', 'https://www.youtube.com/watch?v=vUrk2ri41vI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(313, 36, 'CARA MENGOBATI DAN MENERAPI PENYAKIT SNOT ATAU PIPI BENGKAK TANPA OBAT |ternakbebek |bebekdaging dod', 'video', b'1', b'0', '4m 40d', 'https://www.youtube.com/watch?v=raR0v1hheew', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(314, 36, 'sukses ternak bebek resign dari karyawan |ternakbebekpetelur |ternakbebek |ternak bebek | dod', 'video', b'1', b'0', '6m 8d', 'https://www.youtube.com/watch?v=Pkpu3-7ewMg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(315, 36, 'cara mengobati penyakit mata biru | ternak | ternakbebek | kandang | penyakit bebek |pakanbebek |dod', 'video', b'1', b'0', '13m 16d', 'https://www.youtube.com/watch?v=GR4ieIQ8XN4', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(316, 36, 'bebek pedaging perawatan di musim hujan | pemberian vitamin bebek cepat gemuk | ternakbebekpedaging', 'video', b'1', b'0', '5m 47d', 'https://www.youtube.com/watch?v=pu2Pxxx7Lw0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(317, 36, 'CARA MEMBUAT KANDANG BEBEK SEDERHANA  MINIMALIS TANPA BAU |ternakbebekpedaging |bebek  |ternakbebek', 'video', b'1', b'0', '5m 26d', 'https://www.youtube.com/watch?v=ZCQXzhkqnAk', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(318, 36, 'PERAWATAN ANAK BEBEK (DOD) UMUR 1- 12 HARI BEBEK SEHAT CEPAT BESAR | bebekpedaging | bebekpetelur', 'video', b'1', b'0', '5m 10d', 'https://www.youtube.com/watch?v=0p8yV6up7zM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(319, 36, 'Lakukan tiga cara ini agar ternak di musim penghujan tidak terjungkal | bebek | bebekpetelur | pakan', 'video', b'1', b'0', '10m 29d', 'https://www.youtube.com/watch?v=ryRbCiNWccQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(320, 36, 'Ternak bebek pedaging solusi usaha sampingan dekat rumah |bebekpedaging | bebek | bebekpedaging |dod', 'video', b'1', b'0', '5m 22d', 'https://www.youtube.com/watch?v=nc9oMscL4O8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(321, 36, 'pakan jadi bebek pedaging kelebihan dan kekurangannya | ternakbebek | pakan bebek | dod bebek |panen', 'video', b'1', b'0', '8m 46d', 'https://www.youtube.com/watch?v=M81Z-U29PN8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(322, 36, 'kandang bebek untuk dod dari kandang panggung ke kandang umbaran  | ternakbebek | ternakpedaging', 'video', b'1', b'0', '6m 48d', 'https://www.youtube.com/watch?v=9RSjetuA58c', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(323, 36, 'cara memberi pakan anak bebek (dod) satu minggu sekali | ternakbebek | bebekpedaging | ternakpemula', 'video', b'1', b'0', '6m 33d', 'https://www.youtube.com/watch?v=P7OXEFSHC1c', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(324, 36, 'pakan yang baik untuk anak bebek (dod) bebek sehat cepat gemuk | ternakbebek  | pakanbebek | dod', 'video', b'1', b'0', '4m 57d', 'https://www.youtube.com/watch?v=8zbUhZzHZOI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(325, 36, 'PAKAN UNTUK BEBEK PEDAGING DI MASA PEMBESARAN UMUR 15 SAMPAI 30 HARI |caramudahternakbebek | bebek', 'video', b'1', b'0', '5m 18d', 'https://www.youtube.com/watch?v=Q-2HKt-p6fI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(326, 36, 'DOD BEBEK HIBRIDA PABRIKAN PEKING INI PERBANDINGANNYA! |ternakbebekpemula |ternakbebek |modalbebek', 'video', b'1', b'0', '8m 3d', 'https://www.youtube.com/watch?v=BSfDGe0FpF8', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(327, 36, 'PAKAN BEBEK PEDAGING MASA PENGGEMUKAN UMUR 30 SAMPAI PANEN | Pakan |Ternakbebekpemula | Kansang |dod', 'video', b'1', b'0', '7m 1d', 'https://www.youtube.com/watch?v=zm_an59MYmc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(328, 36, 'ternak bebek pedaging penyortiran untuk pengemukan |ternakbebek |bebek |ternakbebekpedaging |pakan', 'video', b'1', b'0', '4m 40d', 'https://www.youtube.com/watch?v=b9U0H3xjG8U', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(329, 36, 'panen bebek pedaging pakai pakan jadi full pabrikan |ternakbebek |bebek |pakan |kandang |bebekpeking', 'video', b'1', b'0', '8m 50d', 'https://www.youtube.com/watch?v=JUB3lvVlFs0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(330, 36, 'cara menghilangkan bau kandang dan membunuh virus penyakit |bebek |kandang |pakan |ternakbebek |dod', 'video', b'1', b'0', '10m 45d', 'https://www.youtube.com/watch?v=-l3xZATmAug', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(331, 36, 'Panen bebek pesanan tetangga |ternakbebek |kandang |caramudahternakbebek |pakanbebek |bebek |dod', 'video', b'1', b'0', '5m 2d', 'https://www.youtube.com/watch?v=TyqEoMZZ3bI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(332, 36, 'Modal dan laba bebek pedaging full pakan pabrikan | ternakbebek | pakan | kandangbebekpedaging|panen', 'video', b'1', b'0', '10m 14d', 'https://www.youtube.com/watch?v=_sCw74UWoWE', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(333, 36, 'cara mudah menghilangkan bau kandang mencegah virus dan penyakit |ternakbebek | pakan | kandangbebek', 'video', b'1', b'0', '8m 58d', 'https://www.youtube.com/watch?v=wDUmBhKHKYE', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(334, 36, 'cara mengobati penyakit bebek tetelo (ND) bisa sembuh dengan cara ini | bebek pedaging |ternak bebek', 'video', b'1', b'0', '13m 4d', 'https://www.youtube.com/watch?v=cjC2WgEs9AI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(335, 36, 'CARA MUDAH DAN MURAH BAU KANDANG , PENYAKIT VIRUS HILANG. | peternak bebek pemula | bebek pedaging', 'video', b'1', b'0', '7m 58d', 'https://www.youtube.com/watch?v=y_2QH2zvKXU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(336, 36, 'pembangunan kandang berkapasitas 2000 ekor\'an | ternak bebek | bebek pedaging | kandang bebek |pakan', 'video', b'1', b'0', '8m 36d', 'https://www.youtube.com/watch?v=3e8dWVGLHrA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(337, 36, 'pakan bebek seminggu sekali | cara membuat tempat pakan | kandang bebek | ternak bebek | modal bebek', 'video', b'1', b'0', '9m 53d', 'https://www.youtube.com/watch?v=2DjzdobSrJA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(338, 36, 'BAHAYA HAMA PEMAKAN ANAK BEBEK | TERNAK BEBEK | KANDANG BEBEK | PETERNAK BEBEK PEMULA | PAKAN BEBEK', 'video', b'1', b'0', '9m 56d', 'https://www.youtube.com/watch?v=Wmv8RKTUaKI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(339, 36, 'pakan masa pembesaran bebek pedaging umur 11 hari | pakan bebek | kandang bebek | modal ternak bebek', 'video', b'1', b'0', '9m 2d', 'https://www.youtube.com/watch?v=kh-huVOfagA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(340, 36, 'MODAL DAN LABA TERNAK BEBEK PEDAGING 100 EKOR | ternak bebek | pakan bebek | kandang bebek | Dod', 'video', b'1', b'0', '9m 12d', 'https://www.youtube.com/watch?v=rYhRwF2PpT0', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(341, 36, 'PAKAN BEBEK PEDAGING MASA PENGGEMUKAN UMUR 25 HARI SAMPAI PANEN | ternak bebek | kandang bebek | dod', 'video', b'1', b'0', '7m 36d', 'https://www.youtube.com/watch?v=S4utAO4N9SM', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(342, 36, 'PETERNAK MUDA SUKSES BEROMSET PULUHAN JUTA DARI BEBEK PEDAGING !!? | kandang bebek | pakan bebek', 'video', b'1', b'0', '11m 10d', 'https://www.youtube.com/watch?v=OjQkt5swBZI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(343, 36, 'ADA tamu anhani kba raja kentrung youtuber mojokerto gaes. sedikit mernyayi boleh donk| ternak bebek', 'video', b'1', b'0', '2m 51d', 'https://www.youtube.com/watch?v=TgAMxLRe1JQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(344, 36, 'CARA MUDAH PENGGENALAN PAKAN PADI SEBELUM BEBEK DI ANGGON | ternak bebek | pakan bebek | hibrida', 'video', b'1', b'0', '7m 29d', 'https://www.youtube.com/watch?v=Iyd6julC2FA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:50:27', NULL),
	(345, 37, 'Intro | Kelas Fotografi Online #0', 'video', b'1', b'0', '2m 45d', 'https://www.youtube.com/watch?v=xjcYCPXpsyU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(346, 37, 'Apa Itu Fotografi? | Kelas Fotografi Online #1', 'video', b'1', b'0', '2m 44d', 'https://www.youtube.com/watch?v=V3bGYIfT01U', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(347, 37, 'Belajar Fotografi: Apa Itu ISO? dan Cara Memahami ISO | Kelas Fotografi Online #2', 'video', b'1', b'0', '4m 59d', 'https://www.youtube.com/watch?v=NJPs-NN770o', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(348, 37, 'Belajar Fotografi: Apa Itu Aperture? dan Cara Memahami Aperture | Kelas Fotografi Online #3', 'video', b'1', b'0', '4m 44d', 'https://www.youtube.com/watch?v=_ba3zCkR_rU', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(349, 37, 'Belajar Fotografi: Apa itu Shutter Speed? Dan Cara Kerja Shutter Speed | Kelas Fotografi Online #4', 'video', b'1', b'0', '6m 52d', 'https://www.youtube.com/watch?v=H4jLC33ikMw', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(350, 37, 'Memahami Exposure dan Cara Mengatur Exposure | Kelas Fotografi Online #5', 'video', b'1', b'0', '4m 12d', 'https://www.youtube.com/watch?v=GvouSQ_3m18', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(351, 37, 'Cara Memegang DSLR yang Benar | Kelas Fotografi Online #6', 'video', b'1', b'0', '2m 44d', 'https://www.youtube.com/watch?v=Ddxogfx5oCI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(352, 37, 'Postur Tubuh Yang Benar Saat Motret | Kelas Fotografi Online #7', 'video', b'1', b'0', '2m 33d', 'https://www.youtube.com/watch?v=jdOViSMtN9U', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(353, 37, 'Penjelasan tentang tombol Pada DSLR | Kelas Fotografi Online #8', 'video', b'1', b'0', '7m 56d', 'https://www.youtube.com/watch?v=nVYtlmkQplI', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(354, 37, 'Apa Itu White Balance dan Cara Memahami White Balance | Kelas Fotografi Online #9', 'video', b'1', b'0', '5m 34d', 'https://www.youtube.com/watch?v=HxZ5yHkQg1E', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(355, 37, 'Perbedaan Motret Pakai RAW vs JPEG | Kelas Fotografi Online #10', 'video', b'1', b'0', '4m 37d', 'https://www.youtube.com/watch?v=WSES8Ll5ReQ', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(356, 37, 'Memahami Mode Metering Pada DSLR/Mirrorless | Kelas Fotografi Online #11', 'video', b'1', b'0', '4m 54d', 'https://www.youtube.com/watch?v=hGRK1Eg0Nsc', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(357, 37, 'Memahami Focal Length | Kelas Fotografi Online #12', 'video', b'1', b'0', '3m 59d', 'https://www.youtube.com/watch?v=_SrRnEQH8HA', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL),
	(358, 37, 'Belajar Foto Menggunakan Komposisi Rule Of Third #13', 'video', b'1', b'0', '11m 30d', 'https://www.youtube.com/watch?v=k3zosOnKaXg', NULL, NULL, NULL, NULL, NULL, NULL, '2024-11-05 00:56:09', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.orders: ~0 rows (lebih kurang)
INSERT INTO `orders` (`id`, `user_id`, `order_number`, `total_items`, `total_price`, `total_discount`, `payment_status`, `created_at`, `updated_at`) VALUES
	(1, 3, '202410303ZKEF', 1, 100000, 0, 'success', '2024-10-30 15:44:53', '2024-10-30 15:47:47'),
	(2, 3, '202411053AOKW', 2, 219000, 60000, 'success', '2024-11-05 06:44:42', '2024-11-05 06:52:26');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.order_items: ~3 rows (lebih kurang)
INSERT INTO `order_items` (`id`, `order_id`, `course_id`, `course_instructor_id`, `course_title`, `course_slug`, `course_featured_picture_url`, `course_price`, `course_is_free`, `course_is_discount`, `course_discount`, `course_subtotal`) VALUES
	(1, 1, 5, 2, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Belajar Redis.jpg', 100000, b'0', b'0', 0, 100000),
	(2, 2, 21, 2, 'Tutorial Microsoft Word Untuk Pemula', 'tutorial-microsoft-word-untuk-pemula', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730740427424-Tutorial Microsoft Word Untuk Pemula.jpg', 129000, b'0', b'1', 10000, 119000),
	(3, 2, 28, 2, 'Kelas Fotografi Online', 'kelas-fotografi-online', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730742993991-fotografiteknik.jpg', 150000, b'0', b'1', 50000, 100000);

-- membuang struktur untuk table vocasia_db.payments
CREATE TABLE IF NOT EXISTS `payments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `order_number` varchar(255) NOT NULL,
  `total_price` double NOT NULL,
  `additional_fee` double NOT NULL,
  `total_payment` double NOT NULL,
  `snap_token` varchar(255) NOT NULL,
  `payment_status` varchar(255) NOT NULL,
  `payment_expire_at` datetime NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.payments: ~0 rows (lebih kurang)
INSERT INTO `payments` (`id`, `order_id`, `order_number`, `total_price`, `additional_fee`, `total_payment`, `snap_token`, `payment_status`, `payment_expire_at`, `created_at`, `updated_at`) VALUES
	(1, 1, '202410303ZKEF', 100000, 5000, 105000, '612b2162-3410-4923-93f0-ffb9d4cfd7f7', 'success', '2024-10-31 15:44:54', '2024-10-30 15:44:54', '2024-10-30 15:47:47'),
	(2, 2, '202411053AOKW', 219000, 5000, 224000, '66b6b6e7-cd7b-4d1e-b5dd-17dcdde9e534', 'success', '2024-11-06 06:44:49', '2024-11-05 06:44:49', '2024-11-05 06:52:23');

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

-- Membuang data untuk tabel vocasia_db.platform_balances: ~0 rows (lebih kurang)
INSERT INTO `platform_balances` (`id`, `current_balance`, `total_income`, `total_pending_withdrawal`, `total_withdrawn`, `last_history_id`, `created_at`, `updated_at`) VALUES
	(1, 15950, 15950, 0, 0, 3, '2024-10-30 15:47:53', '2024-11-05 06:52:35');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.platform_balance_histories: ~3 rows (lebih kurang)
INSERT INTO `platform_balance_histories` (`id`, `transaction_type`, `amount`, `previous_balance`, `new_balance`, `transaction_date`, `reference_id`, `reference_type`, `transaction_status`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 'fee', 5000, 0, 5000, '2024-10-30 15:47:53', 1, 'order', 'completed', 'New fee income from order #202410303ZKEF', '2024-10-30 15:47:53', '2024-10-30 08:47:52'),
	(2, 'fee', 5950, 5000, 10950, '2024-11-05 06:52:35', 2, 'order', 'completed', 'New fee income from order #202411053AOKW', '2024-11-05 06:52:35', '2024-11-05 06:52:35'),
	(3, 'fee', 5000, 10950, 15950, '2024-11-05 06:52:35', 2, 'order', 'completed', 'New fee income from order #202411053AOKW', '2024-11-05 06:52:35', '2024-11-05 06:52:35');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.platform_income: ~3 rows (lebih kurang)
INSERT INTO `platform_income` (`id`, `instructor_id`, `order_id`, `course_id`, `total_user_payment`, `platform_fee_in_percent`, `total_platform_income`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 1, 5, 100000, 5, 5000, 'New income from order #202410303ZKEF', '2024-10-30 15:47:50', '2024-10-30 08:47:50'),
	(2, 2, 2, 21, 119000, 5, 5950, 'New income from order #202411053AOKW', '2024-11-05 06:52:31', '2024-11-05 06:52:31'),
	(3, 2, 2, 28, 100000, 5, 5000, 'New income from order #202411053AOKW', '2024-11-05 06:52:35', '2024-11-05 06:52:35');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.progress: ~2 rows (lebih kurang)
INSERT INTO `progress` (`id`, `enrollment_id`, `lesson_id`, `status`, `created_at`, `updated_at`, `completed_at`, `started_at`) VALUES
	(1, 1, 15, 'COMPLETED', '2024-10-30 15:48:50', '2024-10-30 18:01:58', '2024-10-30 18:01:58', '2024-10-30 15:48:50'),
	(2, 1, 16, 'COMPLETED', '2024-10-30 18:02:03', '2024-10-30 18:02:04', '2024-10-30 18:02:04', '2024-10-30 18:02:03'),
	(3, 1, 17, 'COMPLETED', '2024-10-30 18:02:14', '2024-10-30 18:02:15', '2024-10-30 18:02:15', '2024-10-30 18:02:14');

-- membuang struktur untuk table vocasia_db.qna
CREATE TABLE IF NOT EXISTS `qna` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `course_id` bigint NOT NULL,
  `lesson_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `question` longtext,
  `is_solved` bit(1) DEFAULT NULL,
  `solved_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.qna: ~0 rows (lebih kurang)
INSERT INTO `qna` (`id`, `created_at`, `updated_at`, `course_id`, `lesson_id`, `user_id`, `title`, `question`, `is_solved`, `solved_at`) VALUES
	(1, '2024-10-30 16:11:02', NULL, 5, 15, 3, 'Pertanyaan 1', '<p>Sebuah pertanyaan</p>', b'0', NULL);

-- membuang struktur untuk table vocasia_db.qna_answers
CREATE TABLE IF NOT EXISTS `qna_answers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `qna_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `answer` longtext,
  `is_instructor` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.qna_answers: ~0 rows (lebih kurang)
INSERT INTO `qna_answers` (`id`, `created_at`, `updated_at`, `qna_id`, `user_id`, `answer`, `is_instructor`) VALUES
	(1, NULL, NULL, 1, 4, 'test answer', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.users: ~11 rows (lebih kurang)
INSERT INTO `users` (`id`, `uid`, `email`, `username`, `name`, `password`, `role`, `profile_picture`, `created_at`, `updated_at`) VALUES
	(1, '11cbd864-cf8e-4834-80e7-2515c51bf737', 'andi.wiyanto@gmail.com', 'andiwiyanto', 'Andi wyt', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', 'lionel-messi-2324-miami.jpg', '2024-10-11 10:51:39', '2024-10-27 17:37:44'),
	(2, '8c86c151-a734-409e-9b5a-5c8aeef47ab4', 'admin@local.test', 'admin', 'admin', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'admin', NULL, '2024-10-11 11:04:25', '2024-10-27 17:18:47'),
	(3, '3e63762c-2485-45b3-95ac-10ee6d246f24', 'martinms.za@gmail.com', 'martinms', 'Martin Mulyo Syahidin', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-11 11:38:46', '2024-10-30 00:52:22'),
	(4, '4bfcd82c-ec3b-4c5e-ba1f-d543920a12dc', 'i1@local.test', 'instructor1', 'Instructor 1', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', 'lionel-messi-2324-miami.jpg', '2024-10-11 11:52:39', '2024-10-28 20:03:17'),
	(5, 'bd05b4dc-d8aa-49dc-95bc-1824b21f9050', 'zumratulaini@gmail.com', 'zumratulaini', 'Zumratul Aini', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-11 18:01:24', '2024-10-11 11:01:23'),
	(6, '14e2baf5-4f28-4850-8238-758d32f578a1', 'ayuana@gmail.com', 'ayuana', 'Ayu Febriana', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-12 14:01:47', '2024-10-12 07:01:47'),
	(9, 'b46adc55-7715-491e-81db-5efd975142e2', 'sindi.vn@gmail.com', 'sindi', 'Sindi Vinatha', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-16 00:36:38', '2024-10-15 17:36:38'),
	(10, '5675f6dd-d5aa-4520-8002-d33a1a3ea2c1', 'steve@gmail.com', 'steve', 'Steve Rogers', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', NULL, '2024-10-16 05:33:57', '2024-10-15 22:33:56'),
	(11, '026b56c6-ed28-4a3b-9a9f-b2c77f18c658', 'tetun@mailinator.com', 'potabeko', 'Lisandra Chavez', '7EjSB/m0KHYUBJgAMqUiC0bCXme47X3QOzf4Vx4q2W0=', 'instructor', NULL, '2024-10-16 05:38:41', '2024-10-15 22:38:40'),
	(12, '343b1275-7a03-4d78-afe1-470ed112a301', 'cia@gmail.com', 'valencia', 'Valencia Gempita Anggana', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'instructor', NULL, '2024-10-16 05:39:25', '2024-10-15 22:39:24'),
	(13, 'f2921f44-4e93-46d0-8467-4fccd3fcd364', 'user1@local.test', 'user1', 'User 1', 'XsArkaS1nG9Z3V++TKZJ7OT6hWjNuLo2z0FCbogFUis=', 'student', NULL, '2024-10-16 20:58:35', '2024-10-16 13:58:34');

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

-- Membuang data untuk tabel vocasia_db.withdrawal_process: ~0 rows (lebih kurang)
INSERT INTO `withdrawal_process` (`id`, `withdrawal_request_id`, `amount`, `processed_at`, `proof_document`, `note`, `status`, `created_at`, `updated_at`) VALUES
	(1, 1, 95000, '2024-10-30 17:34:19', '1730284459034_K8S PROBLEM.png', 'Pembayaran telah dilakukan', 'completed', '2024-10-30 17:34:21', '2024-10-30 10:34:20');

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

-- Membuang data untuk tabel vocasia_db.withdrawal_requests: ~0 rows (lebih kurang)
INSERT INTO `withdrawal_requests` (`id`, `instructor_id`, `amount`, `status`, `requested_at`, `processed_at`, `bank_name`, `bank_account_name`, `bank_account_number`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 95000, 'PAID', '2024-10-30 17:25:27', '2024-10-30 17:34:19', 'BANK BRI', 'MARTIN MULYO SYAHIDIN', '12345678901', 'Request processed successfully', '2024-10-30 17:25:27', '2024-10-30 17:34:19');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
