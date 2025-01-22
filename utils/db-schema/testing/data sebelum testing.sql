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

-- Membuang data untuk tabel vocasia_db.categories: ~139 rows (lebih kurang)
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
	(222, 'child', 1, 'TEST PSD 123', 'test-psd-123', NULL, '2024-10-28 22:28:18', NULL),
	(223, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:28:49', NULL),
	(224, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:28:56', NULL),
	(225, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:28:58', NULL),
	(226, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:29:05', NULL),
	(227, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:29:36', NULL),
	(228, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:30:36', NULL),
	(229, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:32:49', NULL),
	(230, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:33:56', NULL),
	(231, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:33:58', NULL),
	(232, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:33:59', NULL),
	(233, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:34:00', NULL),
	(234, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:34:01', NULL),
	(235, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:34:01', NULL),
	(236, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:34:02', NULL),
	(237, 'child', 1, 'KODING', 'koding', NULL, '2024-10-28 22:34:03', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.chapters: ~16 rows (lebih kurang)
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.courses: ~4 rows (lebih kurang)
INSERT INTO `courses` (`id`, `instructor_id`, `category_id`, `title`, `slug`, `total_duration`, `level`, `language`, `description`, `short_description`, `featured_picture`, `price`, `is_free`, `is_discount`, `discount`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
	(4, 2, 78, 'Riverpod Crash Course', 'riverpod-crash-course', '1j 12m', 'beginner', 'id', '<p><strong>Riverpod Crash Course untuk Pemula: Menguasai State Management di Flutter</strong></p>\n<p>Riverpod adalah salah satu solusi state management yang modern dan efisien untuk aplikasi Flutter. Dibandingkan dengan <em>provider</em>, Riverpod menawarkan beberapa keunggulan, seperti lebih fleksibel dan lebih mudah diatur dalam hal <em>dependency injection</em>. Dalam kursus ini, kita akan mempelajari dasar-dasar Riverpod, mulai dari memahami konsep state hingga bagaimana cara mengimplementasikannya dalam aplikasi Flutter. <strong>Kursus ini dirancang untuk pemula</strong>, jadi Anda tidak perlu khawatir jika belum memiliki pengalaman dengan state management sebelumnya.</p>\n<h5><strong>Apa Itu Riverpod?</strong></h5>\n<p>Riverpod adalah pustaka yang dibangun di atas <em>provider</em>, namun dengan beberapa penyempurnaan yang membuatnya lebih kuat dan aman. Salah satu perbedaan utamanya adalah Riverpod menggunakan <em>syntax</em> yang lebih bersih dan tipe yang lebih jelas, sehingga meminimalisir kesalahan saat coding. Dalam kursus ini, kita akan membahas <strong>cara kerja Riverpod</strong> dan bagaimana Anda bisa memanfaatkan keunggulannya untuk menciptakan aplikasi yang lebih terstruktur dan mudah dipelihara.</p>\n<h5><strong>Memulai dengan Riverpod</strong></h5>\n<p>Tahap pertama dari kursus ini akan mengajarkan Anda cara menginstal dan mengkonfigurasi Riverpod dalam proyek Flutter. Kita akan mulai dari langkah-langkah dasar seperti menambahkan <em>dependencies</em> di <em>pubspec.yaml</em> hingga membuat <em>provider</em> sederhana. Di sini, <strong>kita akan mengenal dua jenis provider utama</strong>, yaitu <code>StateProvider</code> untuk state sederhana dan <code>FutureProvider</code> untuk pengelolaan state asinkron. Pemahaman tentang perbedaan dan penggunaan kedua provider ini akan menjadi landasan kuat untuk menguasai Riverpod.</p>\n<h5><strong>Membangun Aplikasi dengan Riverpod</strong></h5>\n<p>Setelah memahami dasar-dasarnya, kita akan langsung mempraktikkannya dengan membangun aplikasi Flutter sederhana. Anda akan belajar <strong>bagaimana cara menghubungkan UI Flutter dengan state yang dikelola oleh Riverpod</strong>, serta bagaimana memisahkan logika bisnis dari tampilan agar aplikasi lebih terstruktur. Pada tahap ini, Anda akan memahami pentingnya pemisahan <em>concern</em> dalam pengembangan aplikasi yang scalable dan mudah di-debug.</p>\n<h5><strong>Menangani State yang Kompleks</strong></h5>\n<p>Di bagian akhir kursus, kita akan mempelajari bagaimana menangani state yang lebih kompleks, seperti <em>state</em> yang berasal dari API atau database. Anda akan belajar menggunakan <code>StreamProvider</code> dan <code>StateNotifierProvider</code> untuk skenario yang lebih dinamis. Selain itu, <strong>kita juga akan membahas teknik terbaik dalam menggunakan Riverpod</strong>, seperti <em>caching</em>, <em>error handling</em>, dan <em>testing</em>. Setelah menyelesaikan kursus ini, Anda akan siap untuk membangun aplikasi Flutter dengan Riverpod secara lebih percaya diri.</p>\n<p>Kursus ini memberikan <strong>fondasi yang solid</strong> bagi siapa pun yang ingin memahami dan menguasai Riverpod di Flutter. Anda akan diajak melangkah dari pemahaman dasar hingga skenario penggunaan yang lebih kompleks, sehingga mampu menerapkannya dalam proyek nyata.</p>', '<p>Kursus Riverpod ini memberikan pemahaman mendalam tentang state management di Flutter, dimulai dari konsep dasar hingga implementasi dalam skenario yang kompleks. Dengan mempelajari berbagai jenis <em>provider</em> seperti <code>StateProvider</code>, <code>FutureProvider</code>, dan <code>StateNotifierProvider</code>, Anda akan mampu mengelola state dengan lebih efisien dan terstruktur. Setelah menyelesaikan kursus ini, Anda akan memiliki keterampilan yang solid untuk membangun aplikasi Flutter yang scalable dan mudah dipelihara menggunakan Riverpod.</p>', 'Riverpod Crash Course.jpg', 99000, b'0', b'0', 0, 'published', '2024-10-11 16:49:53', '2024-10-27 17:37:31', NULL),
	(5, 2, 81, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', '3j 33m 49d', 'intermediate', 'id', '<p>Kursus "Tutorial Redis Dasar (Bahasa Indonesia)" adalah panduan komprehensif untuk memahami dasar-dasar Redis, salah satu sistem penyimpanan data berbasis memori yang populer. Redis sering digunakan untuk caching, pub/sub, dan berbagai aplikasi data real-time lainnya. Kursus ini dibagi menjadi tiga bagian yang masing-masing mencakup topik penting dalam pengoperasian dan pemanfaatan Redis secara efisien.</p>\n<p>Pada <strong>bagian pertama</strong>, peserta akan mempelajari dasar-dasar Redis, mulai dari pengenalan dan instalasi hingga konfigurasi. Anda akan diajak memahami struktur data yang mendasari Redis, termasuk penggunaan strings, expiration untuk pengelolaan masa simpan data, dan teknik increment serta decrement. Selain itu, materi ini juga mengupas fitur-fitur penting lainnya seperti transaction, monitor, dan pipeline yang sering digunakan dalam pengelolaan transaksi dan performa Redis.</p>\n<p><strong>Bagian kedua</strong> berfokus pada struktur data yang lebih kompleks yang ditawarkan Redis. Mulai dari lists, sets, hashes, hingga sorted sets, Anda akan belajar bagaimana memanfaatkan masing-masing untuk kasus penggunaan yang sesuai. Selain itu, fitur geospatial dan HyperLogLog juga akan dibahas untuk mengelola data spasial dan perkiraan jumlah data yang unik secara efisien.</p>\n<p>Di <strong>bagian ketiga</strong>, topik yang diangkat adalah Pub/Sub, yang memungkinkan komunikasi pesan real-time antar server dan klien. Peserta akan belajar cara kerja Pub/Sub, bagaimana mengelola channel, serta konsep publisher dan subscriber. Bagian ini penting untuk memahami bagaimana Redis digunakan dalam skenario aplikasi real-time, di mana batasan dan limitasi teknis Redis dalam penggunaan Pub/Sub juga akan dibahas.</p>\n<p>Setiap bagian kursus ini dirancang untuk memandu Anda dari konsep paling dasar hingga fitur yang lebih kompleks, dengan contoh praktis dan skenario dunia nyata. Dengan mengikuti kursus ini, peserta akan mendapatkan pemahaman menyeluruh tentang Redis dan bagaimana memanfaatkannya dalam proyek aplikasi skala besar atau kecil.</p>', '<p>Kursus "Tutorial Redis Dasar (Bahasa Indonesia)" adalah panduan lengkap untuk memahami dasar-dasar Redis, mulai dari instalasi hingga fitur-fitur penting seperti strings, sets, hashes, pub/sub, dan transaksi. Kursus ini dibagi menjadi tiga bagian, membahas struktur data, manajemen koneksi, keamanan, hingga komunikasi real-time menggunakan Pub/Sub. Dengan contoh praktis dan penjelasan mendalam, kursus ini cocok bagi pemula maupun pengguna Redis yang ingin memperdalam pemahaman mereka dalam membangun aplikasi yang efisien dan real-time.</p>', 'Belajar Redis.jpg', 100000, b'0', b'0', 0, 'published', '2024-10-12 12:14:32', '2024-10-27 17:37:11', NULL),
	(17, 2, 81, 'React JS', 'test-kursus-123', '1j 2m', 'all', 'Bahasa Indonesia', '<p>lagi</p>', '<p>lagi</p>', '1728914131233-Belajar React JS Bahasa Indonesia.jpg', 10000, b'0', b'0', 0, 'draft', '2024-10-14 20:28:29', '2024-10-15 01:39:38', NULL),
	(18, 1, 77, 'TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)', 'tutorial-spring-boot-dasar-bahasa-indonesia', '25j 50m 4d', 'all', 'id', '<p>Spring Boot adalah framework berbasis Java yang dirancang untuk memudahkan pengembangan aplikasi berbasis Spring dengan cepat dan efisien. Dalam tutorial ini, Anda akan diajak memahami dasar-dasar penggunaan Spring Boot, mulai dari konfigurasi proyek hingga pengelolaan dependensi. Tutorial ini sangat cocok bagi pemula yang ingin membangun aplikasi backend menggunakan Java, serta mereka yang ingin memahami bagaimana cara kerja Spring Boot dalam menyederhanakan pengembangan aplikasi web, REST API, dan microservices.</p>\n<p>Pada tahap awal, Anda akan belajar cara membuat proyek Spring Boot menggunakan Spring Initializr, sebuah alat yang memungkinkan Anda menghasilkan struktur proyek dengan cepat. Anda juga akan diajarkan bagaimana cara mengatur konfigurasi yang tepat serta memahami file seperti <code>application.properties</code> atau <code>application.yml</code>. Pengaturan ini penting karena berperan dalam menentukan perilaku aplikasi, mulai dari pengaturan port server, koneksi database, hingga pengaturan keamanan dasar.</p>\n<p>Selain itu, tutorial ini akan membahas konsep penting seperti Dependency Injection (DI) dan Inversion of Control (IoC) yang merupakan inti dari Spring Framework. Dengan memahami cara kerja DI, Anda akan lebih mudah membuat kode yang lebih modular dan mudah dikelola. Selain itu, akan dijelaskan cara membuat controller untuk menangani request HTTP dan menggunakan RESTful API untuk berinteraksi dengan data dalam aplikasi Anda.</p>\n<p>Tutorial ini juga akan mengajak Anda mempelajari integrasi Spring Boot dengan database menggunakan JPA (Java Persistence API) dan Hibernate. Anda akan dipandu langkah demi langkah mulai dari koneksi ke database, membuat entitas, hingga melakukan operasi CRUD (Create, Read, Update, Delete) dengan repository Spring Data JPA. Dengan demikian, Anda dapat membangun aplikasi yang tidak hanya bisa menerima dan mengelola request, tetapi juga menyimpan dan mengambil data dari database.</p>\n<p>Terakhir, tutorial ini akan menyinggung beberapa fitur tambahan Spring Boot seperti penggunaan Spring Security untuk melindungi aplikasi Anda dari akses tidak sah, serta cara deploy aplikasi ke platform cloud seperti Heroku atau AWS. Dengan demikian, setelah menyelesaikan tutorial ini, Anda akan memiliki fondasi yang kuat dalam pengembangan aplikasi menggunakan Spring Boot, yang dapat langsung diterapkan untuk membangun proyek nyata.</p>', '<p><strong>Tutorial Spring Boot Dasar (Bahasa Indonesia)</strong> memberikan pemahaman mendalam tentang dasar-dasar pengembangan aplikasi menggunakan Spring Boot, mulai dari konfigurasi awal proyek, implementasi Dependency Injection, hingga integrasi dengan database menggunakan JPA. Dengan mempelajari konsep-konsep inti seperti REST API dan Spring Security, Anda akan memiliki fondasi yang kokoh untuk mengembangkan aplikasi web dan microservices yang efisien dan scalable. Tutorial ini cocok bagi pemula dan memberikan panduan langkah demi langkah untuk menerapkan Spring Boot dalam proyek nyata.</p>', '1729093329452-TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA).jpg', 200000, b'0', b'1', 50000, 'published', '2024-10-16 22:41:59', '2024-10-27 17:37:58', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.enrollments: ~8 rows (lebih kurang)
INSERT INTO `enrollments` (`id`, `user_id`, `order_id`, `course_id`, `enrollment_date`, `status`, `progress_percentage`, `completion_date`, `created_at`, `updated_at`, `last_lesson_id`) VALUES
	(38, 6, 51, 5, '2024-10-16 20:43:13', 'active', 0, NULL, '2024-10-16 20:43:13', NULL, NULL),
	(39, 3, 52, 5, '2024-10-16 20:44:24', 'active', 0, NULL, '2024-10-16 20:44:24', NULL, NULL),
	(40, 3, 53, 4, '2024-10-16 20:46:12', 'active', 0, NULL, '2024-10-16 20:46:12', NULL, NULL),
	(41, 6, 54, 4, '2024-10-16 20:47:27', 'active', 0, NULL, '2024-10-16 20:47:27', NULL, NULL),
	(42, 5, 55, 4, '2024-10-16 20:49:19', 'active', 0, NULL, '2024-10-16 20:49:19', NULL, NULL),
	(43, 5, 55, 5, '2024-10-16 20:49:19', 'active', 0, NULL, '2024-10-16 20:49:19', '2024-10-17 22:21:43', 17),
	(44, 13, 56, 5, '2024-10-16 20:58:56', 'active', 0, NULL, '2024-10-16 20:58:56', NULL, NULL),
	(45, 6, 58, 18, '2024-10-17 22:48:26', 'active', 0, NULL, '2024-10-17 22:48:26', NULL, NULL);

-- membuang struktur untuk table vocasia_db.forget_password
CREATE TABLE IF NOT EXISTS `forget_password` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `expired_at` timestamp NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.forget_password: ~77 rows (lebih kurang)

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
	(1, 1, 'pending', '0822341234', 'New summary2', NULL),
	(2, 4, 'pending', '082281666584', 'summary', NULL),
	(3, 10, 'pending', '08123456789', NULL, NULL),
	(4, 11, 'pending', '+1 (817) 705-2131', 'Totam quia dolorem e', NULL),
	(5, 12, 'pending', '08134567890', 'cia adalah valencia', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instructor_balances: ~2 rows (lebih kurang)
INSERT INTO `instructor_balances` (`id`, `instructor_id`, `current_balance`, `total_income`, `total_pending_withdrawal`, `total_withdrawn`, `total_platform_fee`, `last_history_id`, `created_at`, `updated_at`) VALUES
	(1, 2, 95000, 662150, 0, 567150, 34850, 10, '2024-10-16 20:43:13', '2024-10-16 20:59:34'),
	(2, 1, 142500, 142500, 0, 0, 7500, 11, '2024-10-17 22:48:29', '2024-10-17 22:48:29');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instructor_balance_histories: ~11 rows (lebih kurang)
INSERT INTO `instructor_balance_histories` (`id`, `instructor_id`, `transaction_type`, `amount`, `previous_balance`, `new_balance`, `transaction_date`, `reference_id`, `reference_type`, `transaction_status`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 'income', 95000, 0, 95000, '2024-10-16 20:43:13', 51, 'order', 'completed', 'New income from order #202410166JEMU', '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(2, 2, 'income', 95000, 95000, 190000, '2024-10-16 20:44:24', 52, 'order', 'completed', 'New income from order #202410163YDZQ', '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(3, 2, 'withdrawal', 95000, 190000, 95000, '2024-10-16 20:45:01', 1, 'withdrawal', 'success', 'Withdrawal request processed', '2024-10-16 20:45:01', '2024-10-16 13:45:00'),
	(4, 2, 'income', 94050, 95000, 189050, '2024-10-16 20:46:13', 53, 'order', 'completed', 'New income from order #202410163IHAR', '2024-10-16 20:46:13', '2024-10-16 13:46:12'),
	(5, 2, 'income', 94050, 189050, 283100, '2024-10-16 20:47:27', 54, 'order', 'completed', 'New income from order #202410166SMLA', '2024-10-16 20:47:27', '2024-10-16 13:47:27'),
	(6, 2, 'income', 94050, 283100, 377150, '2024-10-16 20:49:19', 55, 'order', 'completed', 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(7, 2, 'income', 95000, 377150, 472150, '2024-10-16 20:49:19', 55, 'order', 'completed', 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(8, 2, 'withdrawal', 283100, 472150, 189050, '2024-10-16 20:51:50', 2, 'withdrawal', 'success', 'Withdrawal request processed', '2024-10-16 20:51:50', '2024-10-16 13:51:50'),
	(9, 2, 'income', 95000, 189050, 284050, '2024-10-16 20:58:56', 56, 'order', 'completed', 'New income from order #2024101613JZIT', '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(10, 2, 'withdrawal', 189050, 284050, 95000, '2024-10-16 20:59:34', 3, 'withdrawal', 'success', 'Withdrawal request processed', '2024-10-16 20:59:34', '2024-10-16 13:59:33'),
	(11, 1, 'income', 142500, 0, 142500, '2024-10-17 22:48:29', 58, 'order', 'completed', 'New income from order #202410176UMTE', '2024-10-17 22:48:29', '2024-10-17 15:48:29');

-- membuang struktur untuk table vocasia_db.instructor_students
CREATE TABLE IF NOT EXISTS `instructor_students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instructor_students: ~5 rows (lebih kurang)
INSERT INTO `instructor_students` (`id`, `instructor_id`, `user_id`, `created_at`, `updated_at`) VALUES
	(16, 2, 6, '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(17, 2, 3, '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(18, 2, 5, '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(19, 2, 13, '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(20, 1, 6, '2024-10-17 22:48:29', '2024-10-17 15:48:29');

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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instructor_student_courses: ~8 rows (lebih kurang)
INSERT INTO `instructor_student_courses` (`id`, `instructor_student_id`, `course_id`, `order_id`, `created_at`, `updated_at`) VALUES
	(37, 16, 5, 51, '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(38, 17, 5, 52, '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(39, 17, 4, 53, '2024-10-16 20:46:13', '2024-10-16 13:46:12'),
	(40, 16, 4, 54, '2024-10-16 20:47:27', '2024-10-16 13:47:27'),
	(41, 18, 4, 55, '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(42, 18, 5, 55, '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(43, 19, 5, 56, '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(44, 20, 18, 58, '2024-10-17 22:48:29', '2024-10-17 15:48:29');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.instuctor_income: ~8 rows (lebih kurang)
INSERT INTO `instuctor_income` (`id`, `instructor_id`, `order_id`, `course_id`, `total_user_payment`, `platform_fee_in_percent`, `total_instructor_income`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 51, 5, 100000, 5, 95000, 'New income from order #202410166JEMU', '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(2, 2, 52, 5, 100000, 5, 95000, 'New income from order #202410163YDZQ', '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(3, 2, 53, 4, 99000, 5, 94050, 'New income from order #202410163IHAR', '2024-10-16 20:46:12', '2024-10-16 13:46:12'),
	(4, 2, 54, 4, 99000, 5, 94050, 'New income from order #202410166SMLA', '2024-10-16 20:47:27', '2024-10-16 13:47:26'),
	(5, 2, 55, 4, 99000, 5, 94050, 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:18'),
	(6, 2, 55, 5, 100000, 5, 95000, 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(7, 2, 56, 5, 100000, 5, 95000, 'New income from order #2024101613JZIT', '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(8, 1, 58, 18, 150000, 5, 142500, 'New income from order #202410176UMTE', '2024-10-17 22:48:28', '2024-10-17 15:48:28');

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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.lessons: ~29 rows (lebih kurang)
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
	(41, 25, 'Tutorial Spring Monitoring (Bahasa Indonesia)', 'video', b'0', b'0', '57m 24d', 'https://www.youtube.com/watch?v=DMMzPxYjd2Q&list=PL-CtdCApEFH8bV3sla0QfmMnJQ4YX_wR1&index=10', '', 'Data', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/lesson-attachment-1729156412435-table.csv', NULL, NULL, 'file', '2024-10-16 22:55:25', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.orders: ~8 rows (lebih kurang)
INSERT INTO `orders` (`id`, `user_id`, `order_number`, `total_items`, `total_price`, `total_discount`, `payment_status`, `created_at`, `updated_at`) VALUES
	(51, 6, '202410166JEMU', 1, 100000, 0, 'success', '2024-10-16 20:43:02', '2024-10-16 20:43:13'),
	(52, 3, '202410163YDZQ', 1, 100000, 0, 'success', '2024-10-16 20:44:14', '2024-10-16 20:44:24'),
	(53, 3, '202410163IHAR', 1, 99000, 0, 'success', '2024-10-16 20:46:03', '2024-10-16 20:46:12'),
	(54, 6, '202410166SMLA', 1, 99000, 0, 'success', '2024-10-16 20:47:18', '2024-10-16 20:47:27'),
	(55, 5, '202410165BUAF', 2, 199000, 0, 'success', '2024-10-16 20:49:05', '2024-10-16 20:49:19'),
	(56, 13, '2024101613JZIT', 1, 100000, 0, 'success', '2024-10-16 20:58:46', '2024-10-16 20:58:56'),
	(57, 5, '202410165OSUI', 1, 150000, 50000, 'PENDING', '2024-10-16 18:59:11', '2024-10-16 18:59:11'),
	(58, 6, '202410176UMTE', 1, 150000, 50000, 'success', '2024-10-17 22:47:03', '2024-10-17 22:48:26'),
	(59, 3, '202410303MUHJ', 1, 99000, 0, 'PENDING', '2024-10-30 01:01:05', '2024-10-29 18:01:05');

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
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.order_items: ~9 rows (lebih kurang)
INSERT INTO `order_items` (`id`, `order_id`, `course_id`, `course_instructor_id`, `course_title`, `course_slug`, `course_featured_picture_url`, `course_price`, `course_is_free`, `course_is_discount`, `course_discount`, `course_subtotal`) VALUES
	(62, 51, 5, 2, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Belajar Redis.jpg', 100000, b'0', b'0', 0, 100000),
	(63, 52, 5, 2, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Belajar Redis.jpg', 100000, b'0', b'0', 0, 100000),
	(64, 53, 4, 2, 'Riverpod Crash Course', 'riverpod-crash-course', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Riverpod Crash Course.jpg', 99000, b'0', b'0', 0, 99000),
	(65, 54, 4, 2, 'Riverpod Crash Course', 'riverpod-crash-course', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Riverpod Crash Course.jpg', 99000, b'0', b'0', 0, 99000),
	(66, 55, 4, 2, 'Riverpod Crash Course', 'riverpod-crash-course', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Riverpod Crash Course.jpg', 99000, b'0', b'0', 0, 99000),
	(67, 55, 5, 2, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Belajar Redis.jpg', 100000, b'0', b'0', 0, 100000),
	(68, 56, 5, 2, 'Tutorial Redis Dasar (Bahasa Indonesia)', 'tutorial-redis-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Belajar Redis.jpg', 100000, b'0', b'0', 0, 100000),
	(69, 57, 18, 1, 'TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)', 'tutorial-spring-boot-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1729093329452-TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA).jpg', 200000, b'0', b'1', 50000, 150000),
	(70, 58, 18, 1, 'TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)', 'tutorial-spring-boot-dasar-bahasa-indonesia', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1729093329452-TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA).jpg', 200000, b'0', b'1', 50000, 150000),
	(71, 59, 4, 2, 'Riverpod Crash Course', 'riverpod-crash-course', 'https://vocasia.s3.ap-southeast-1.amazonaws.com/Riverpod%20Crash%20Course.jpg', 99000, b'0', b'0', 0, 99000);

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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.payments: ~8 rows (lebih kurang)
INSERT INTO `payments` (`id`, `order_id`, `order_number`, `total_price`, `additional_fee`, `total_payment`, `snap_token`, `payment_status`, `payment_expire_at`, `created_at`, `updated_at`) VALUES
	(45, 51, '202410166JEMU', 100000, 5000, 105000, '681f6bac-3d90-470a-b06c-b6ae7ea22ae6', 'success', '2024-10-17 20:43:03', '2024-10-16 20:43:03', '2024-10-16 20:43:13'),
	(46, 52, '202410163YDZQ', 100000, 5000, 105000, 'f34f729a-57fa-4916-b23a-15fb10e24b98', 'success', '2024-10-17 20:44:14', '2024-10-16 20:44:14', '2024-10-16 20:44:24'),
	(47, 53, '202410163IHAR', 99000, 5000, 104000, '7a68cae5-6c08-4cdb-baf8-f61c2f5b8bfc', 'success', '2024-10-17 20:46:04', '2024-10-16 20:46:04', '2024-10-16 20:46:12'),
	(48, 54, '202410166SMLA', 99000, 5000, 104000, '830e5ef2-8402-43f8-970b-3e27bd365a6a', 'success', '2024-10-17 20:47:19', '2024-10-16 20:47:19', '2024-10-16 20:47:27'),
	(49, 55, '202410165BUAF', 199000, 5000, 204000, 'ee24960e-8735-4339-ba6c-c5e0aabf5302', 'success', '2024-10-17 20:49:05', '2024-10-16 20:49:05', '2024-10-16 20:49:19'),
	(50, 56, '2024101613JZIT', 100000, 5000, 105000, 'd55645e8-fb75-4329-adbc-e1a144b4e10b', 'success', '2024-10-17 20:58:47', '2024-10-16 20:58:47', '2024-10-16 20:58:56'),
	(51, 57, '202410165OSUI', 150000, 5000, 155000, '4a4f4b01-d428-4132-8044-2ca5a3e17d22', 'PENDING', '2024-10-17 18:59:19', '2024-10-16 18:59:20', '2024-10-16 18:59:20'),
	(52, 58, '202410176UMTE', 150000, 5000, 155000, 'aab3a35c-6ef4-4024-8a1a-2b4342a0dccb', 'success', '2024-10-18 22:47:06', '2024-10-17 22:47:06', '2024-10-17 22:48:25'),
	(53, 59, '202410303MUHJ', 99000, 5000, 104000, 'b0f8e2ee-2e4c-4e0e-a202-947fd1c192aa', 'PENDING', '2024-10-31 01:01:08', '2024-10-30 01:01:08', '2024-10-29 18:01:08');

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
	(1, 42350, 42350, 0, 0, 8, '2024-10-16 20:43:13', '2024-10-17 22:48:30');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.platform_balance_histories: ~8 rows (lebih kurang)
INSERT INTO `platform_balance_histories` (`id`, `transaction_type`, `amount`, `previous_balance`, `new_balance`, `transaction_date`, `reference_id`, `reference_type`, `transaction_status`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 'fee', 5000, 0, 5000, '2024-10-16 20:43:13', 51, 'order', 'completed', 'New fee income from order #202410166JEMU', '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(2, 'fee', 5000, 5000, 10000, '2024-10-16 20:44:24', 52, 'order', 'completed', 'New fee income from order #202410163YDZQ', '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(3, 'fee', 4950, 10000, 14950, '2024-10-16 20:46:13', 53, 'order', 'completed', 'New fee income from order #202410163IHAR', '2024-10-16 20:46:13', '2024-10-16 13:46:12'),
	(4, 'fee', 4950, 14950, 19900, '2024-10-16 20:47:27', 54, 'order', 'completed', 'New fee income from order #202410166SMLA', '2024-10-16 20:47:27', '2024-10-16 13:47:27'),
	(5, 'fee', 4950, 19900, 24850, '2024-10-16 20:49:19', 55, 'order', 'completed', 'New fee income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(6, 'fee', 5000, 24850, 29850, '2024-10-16 20:49:19', 55, 'order', 'completed', 'New fee income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(7, 'fee', 5000, 29850, 34850, '2024-10-16 20:58:56', 56, 'order', 'completed', 'New fee income from order #2024101613JZIT', '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(8, 'fee', 7500, 34850, 42350, '2024-10-17 22:48:30', 58, 'order', 'completed', 'New fee income from order #202410176UMTE', '2024-10-17 22:48:30', '2024-10-17 15:48:29');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.platform_income: ~8 rows (lebih kurang)
INSERT INTO `platform_income` (`id`, `instructor_id`, `order_id`, `course_id`, `total_user_payment`, `platform_fee_in_percent`, `total_platform_income`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 51, 5, 100000, 5, 5000, 'New income from order #202410166JEMU', '2024-10-16 20:43:13', '2024-10-16 13:43:12'),
	(2, 2, 52, 5, 100000, 5, 5000, 'New income from order #202410163YDZQ', '2024-10-16 20:44:24', '2024-10-16 13:44:23'),
	(3, 2, 53, 4, 99000, 5, 4950, 'New income from order #202410163IHAR', '2024-10-16 20:46:13', '2024-10-16 13:46:12'),
	(4, 2, 54, 4, 99000, 5, 4950, 'New income from order #202410166SMLA', '2024-10-16 20:47:27', '2024-10-16 13:47:27'),
	(5, 2, 55, 4, 99000, 5, 4950, 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:18'),
	(6, 2, 55, 5, 100000, 5, 5000, 'New income from order #202410165BUAF', '2024-10-16 20:49:19', '2024-10-16 13:49:19'),
	(7, 2, 56, 5, 100000, 5, 5000, 'New income from order #2024101613JZIT', '2024-10-16 20:58:56', '2024-10-16 13:58:56'),
	(8, 1, 58, 18, 150000, 5, 7500, 'New income from order #202410176UMTE', '2024-10-17 22:48:29', '2024-10-17 15:48:28');

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.progress: ~3 rows (lebih kurang)
INSERT INTO `progress` (`id`, `enrollment_id`, `lesson_id`, `status`, `created_at`, `updated_at`, `completed_at`, `started_at`) VALUES
	(25, 43, 15, 'IN_PROGRESS', '2024-10-17 16:14:07', NULL, NULL, '2024-10-17 16:14:07'),
	(26, 43, 16, 'IN_PROGRESS', '2024-10-17 17:40:52', NULL, NULL, '2024-10-17 17:40:52'),
	(27, 43, 17, 'IN_PROGRESS', '2024-10-17 17:41:08', NULL, NULL, '2024-10-17 17:41:08');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.qna: ~3 rows (lebih kurang)
INSERT INTO `qna` (`id`, `created_at`, `updated_at`, `course_id`, `lesson_id`, `user_id`, `title`, `question`, `is_solved`, `solved_at`) VALUES
	(1, '2024-10-17 19:24:43', NULL, 5, 17, 5, 'Service tidak bisa terhubung ke docker', '<p>Halo pak, saya menggunakan Docker untuk mengkontainerkan aplikasi saya. Namun, kenapa saat saya menjalankan service di dalam Docker, service tersebut tidak bisa terhubung ke jaringan atau service lain di dalam Docker? Apakah ada konfigurasi yang perlu saya cek atau setting tambahan yang diperlukan?</p>', b'0', NULL),
	(3, '2024-10-17 19:52:19', NULL, 5, 17, 5, 'Service Docker Tidak Mau Start', '<p>Saya sedang&nbsp;mengalami masalah dengan service Docker saya yang tidak mau berjalan. Berikut adalah beberapa detail masalahnya:</p>\n<ul>\n<li>Saya sudah membangun image Docker menggunakan <code>docker build</code> tanpa masalah.</li>\n<li>Ketika saya menjalankan container menggunakan <code>docker-compose up</code>, salah satu service tidak bisa start.</li>\n<li>Ini adalah pesan error yang saya dapatkan di log:</li>\n</ul>\n<blockquote>\n<p>Error response from daemon: OCI runtime create failed: container_linux.go:380: starting container process caused: exec: "/bin/sh": stat /bin/sh: no such file or directory: unknown</p>\n</blockquote>\n<p>&nbsp;</p>\n<p><strong>Langkah-langkah yang sudah saya lakukan:</strong></p>\n<ol>\n<li>Saya sudah mencoba menjalankan container dengan mode <code>--verbose</code> tapi tidak mendapatkan detail tambahan yang signifikan.</li>\n<li>Saya sudah mengecek Dockerfile dan memastikan entry point serta semua dependencies sudah ada.</li>\n<li>Saya mencoba menjalankan image yang sama secara manual menggunakan <code>docker run</code> dan mendapatkan pesan error yang sama.</li>\n<li>Sudah saya pastikan bahwa base image yang digunakan sesuai dan semua step di Dockerfile berjalan dengan baik saat build.</li>\n</ol>\n<p>&nbsp;</p>\n<p>Kira-kira apa yang menyebabkan service ini gagal start? Apakah ada yang perlu saya perbaiki di konfigurasi Dockerfile atau ada setting tambahan yang harus saya cek?</p>\n<p>Terima kasih atas bantuannya!</p>', b'0', NULL),
	(4, '2024-10-17 20:13:14', NULL, 5, 16, 5, 'pertanyaan terus', '<p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</p>', b'0', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.qna_answers: ~6 rows (lebih kurang)
INSERT INTO `qna_answers` (`id`, `created_at`, `updated_at`, `qna_id`, `user_id`, `answer`, `is_instructor`) VALUES
	(1, '2024-10-17 20:03:03', NULL, 1, 5, 'test', 0),
	(2, '2024-10-17 20:10:38', NULL, 1, 5, '<p>test jawaban <strong>baru</strong></p>', 0),
	(3, '2024-10-17 20:12:22', NULL, 1, 5, '<p>oke</p>', 0),
	(4, '2024-10-17 22:01:19', NULL, 1, 4, 'test 123', 0),
	(8, '2024-10-17 22:16:31', NULL, 1, 4, '<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</p>', 1),
	(9, '2024-10-17 22:21:54', NULL, 1, 5, '<p>thanks</p>', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.withdrawal_process: ~3 rows (lebih kurang)
INSERT INTO `withdrawal_process` (`id`, `withdrawal_request_id`, `amount`, `processed_at`, `proof_document`, `note`, `status`, `created_at`, `updated_at`) VALUES
	(1, 1, 95000, '2024-10-16 20:45:00', '1729086300063_Daftar-Dospem-KP-Ganjil-2024-Lanjutan.pdf', 'sss', 'completed', '2024-10-16 20:45:01', '2024-10-16 13:45:00'),
	(2, 2, 283100, '2024-10-16 20:51:49', '1729086709405_Daftar-Dospem-KP-Ganjil-2024-Lanjutan.pdf', 'xxx', 'completed', '2024-10-16 20:51:50', '2024-10-16 13:51:50'),
	(3, 3, 189050, '2024-10-16 20:59:33', '1729087173337_Daftar-Dospem-KP-Ganjil-2024-Lanjutan.pdf', 'sss', 'completed', '2024-10-16 20:59:34', '2024-10-16 13:59:33');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Membuang data untuk tabel vocasia_db.withdrawal_requests: ~3 rows (lebih kurang)
INSERT INTO `withdrawal_requests` (`id`, `instructor_id`, `amount`, `status`, `requested_at`, `processed_at`, `bank_name`, `bank_account_name`, `bank_account_number`, `remarks`, `created_at`, `updated_at`) VALUES
	(1, 2, 95000, 'PAID', '2024-10-16 20:43:41', '2024-10-16 20:45:00', 'BANK BRI', 'MMS', '1234567890', 'Request processed successfully', '2024-10-16 20:43:41', '2024-10-16 20:45:00'),
	(2, 2, 283100, 'PAID', '2024-10-16 20:47:44', '2024-10-16 20:51:49', 'BANK BRI', 'MMS', '1234567890', 'Request processed successfully', '2024-10-16 20:47:44', '2024-10-16 20:51:49'),
	(3, 2, 189050, 'PAID', '2024-10-16 20:57:45', '2024-10-16 20:59:33', 'BANK BRI', 'MMS', '1234567890', 'Request processed successfully', '2024-10-16 20:57:45', '2024-10-16 20:59:33');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
