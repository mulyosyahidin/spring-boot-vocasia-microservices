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

-- membuang struktur untuk table qna_db.flyway_schema_history
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

-- Membuang data untuk tabel qna_db.flyway_schema_history: ~2 rows (lebih kurang)
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'INIT QNA SCHEMA', 'SQL', 'V1__INIT_QNA_SCHEMA.sql', -2041152737, 'root', '2024-10-17 12:22:56', 136, 1),
	(2, '2', 'ALTER QNA ANSWES TABLE', 'SQL', 'V2__ALTER_QNA_ANSWES_TABLE.sql', 744258574, 'root', '2024-10-17 15:11:33', 163, 1);

-- membuang struktur untuk table qna_db.qna
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

-- Membuang data untuk tabel qna_db.qna: ~2 rows (lebih kurang)
INSERT INTO `qna` (`id`, `created_at`, `updated_at`, `course_id`, `lesson_id`, `user_id`, `title`, `question`, `is_solved`, `solved_at`) VALUES
	(1, '2024-10-17 19:24:43', NULL, 5, 17, 5, 'Service tidak bisa terhubung ke docker', '<p>Halo pak, saya menggunakan Docker untuk mengkontainerkan aplikasi saya. Namun, kenapa saat saya menjalankan service di dalam Docker, service tersebut tidak bisa terhubung ke jaringan atau service lain di dalam Docker? Apakah ada konfigurasi yang perlu saya cek atau setting tambahan yang diperlukan?</p>', b'0', NULL),
	(3, '2024-10-17 19:52:19', NULL, 5, 17, 5, 'Service Docker Tidak Mau Start', '<p>Saya sedang&nbsp;mengalami masalah dengan service Docker saya yang tidak mau berjalan. Berikut adalah beberapa detail masalahnya:</p>\n<ul>\n<li>Saya sudah membangun image Docker menggunakan <code>docker build</code> tanpa masalah.</li>\n<li>Ketika saya menjalankan container menggunakan <code>docker-compose up</code>, salah satu service tidak bisa start.</li>\n<li>Ini adalah pesan error yang saya dapatkan di log:</li>\n</ul>\n<blockquote>\n<p>Error response from daemon: OCI runtime create failed: container_linux.go:380: starting container process caused: exec: "/bin/sh": stat /bin/sh: no such file or directory: unknown</p>\n</blockquote>\n<p>&nbsp;</p>\n<p><strong>Langkah-langkah yang sudah saya lakukan:</strong></p>\n<ol>\n<li>Saya sudah mencoba menjalankan container dengan mode <code>--verbose</code> tapi tidak mendapatkan detail tambahan yang signifikan.</li>\n<li>Saya sudah mengecek Dockerfile dan memastikan entry point serta semua dependencies sudah ada.</li>\n<li>Saya mencoba menjalankan image yang sama secara manual menggunakan <code>docker run</code> dan mendapatkan pesan error yang sama.</li>\n<li>Sudah saya pastikan bahwa base image yang digunakan sesuai dan semua step di Dockerfile berjalan dengan baik saat build.</li>\n</ol>\n<p>&nbsp;</p>\n<p>Kira-kira apa yang menyebabkan service ini gagal start? Apakah ada yang perlu saya perbaiki di konfigurasi Dockerfile atau ada setting tambahan yang harus saya cek?</p>\n<p>Terima kasih atas bantuannya!</p>', b'0', NULL),
	(4, '2024-10-17 20:13:14', NULL, 5, 16, 5, 'pertanyaan terus', '<p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</p>', b'0', NULL);

-- membuang struktur untuk table qna_db.qna_answers
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

-- Membuang data untuk tabel qna_db.qna_answers: ~5 rows (lebih kurang)
INSERT INTO `qna_answers` (`id`, `created_at`, `updated_at`, `qna_id`, `user_id`, `answer`, `is_instructor`) VALUES
	(1, '2024-10-17 20:03:03', NULL, 1, 5, 'test', 0),
	(2, '2024-10-17 20:10:38', NULL, 1, 5, '<p>test jawaban <strong>baru</strong></p>', 0),
	(3, '2024-10-17 20:12:22', NULL, 1, 5, '<p>oke</p>', 0),
	(4, '2024-10-17 22:01:19', NULL, 1, 4, 'test 123', 0),
	(8, '2024-10-17 22:16:31', NULL, 1, 4, '<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</p>', 1),
	(9, '2024-10-17 22:21:54', NULL, 1, 5, '<p>thanks</p>', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
