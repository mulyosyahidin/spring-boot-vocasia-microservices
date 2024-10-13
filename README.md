# Vocasia
**Vocasia** merupakan layanan *massive open online courses* yang dikelola oleh Yayasan Adipurna Inovasi Asia yang dapat diakses melalui [vocasia.id](https://vocasia.id). Vocasia.id mulai dapat diakses pada tahun 2020 yang dibangun menggunakan bahasa pemrograman PHP dan *framework* CodeIgniter 3.

Proyek ini merupakan bagian dari tugas akhir Martin Mulyo Syahidin (Universitas Bengkulu) yang berjudul "**Rancang Bangun Arsitektur Microservices untuk Massive Open Online Courses (Studi Kasus: Vocasia.id)**". Secara garis besar, proyek ini membangun aplikasi *microservices* dari Vocasia.id berdasarkan rancangan dan alur bisnis yang sudah berjalan.

> Repository ini bukan merupakan repository kode dari vocasia.id, hanya merupakan proyek akademik yang mengimplementasikan arsitektur microservices berdasarkan vocasia.id

## *Tech Stack*
**Microservices**
1. Java 17
2. Spring Boot

**User App**
1. React JS

**Build Tools**
1. Maven
2. Google Jib

## Log
27/09/2024
1. Inisialisasi proyek
2. Setup Config Server, Eureka Server, dan Gateway
3. Setup Authentication Service (Skeleton)

28/09/2024
1. Setup Keycloack Authorization Server
2. Menyelesaikan: Registrasi, Login, Refresh Access Token
3. Inisialisasi Vocasia App [React JS]
4. Inisialisasi Instructor Service
5. Menyelesaikan: Endpoint pendaftaran instruktur
6. Inisialisasi Course Service

01/10/2024:
1. Menyelesaikan: Kategori kursus [Index, Show]
2. Menyelesaikan: Instruktur [Get Data]
3. Menyelesaikan: Kursus [Tambah, Edit, Edit Thumbnail, Bab]
4. Inisialisasi Playgroud Service
5. Menyelesaikan: Pelajaran [CRUD]
6. Refactor routing
7. Menyelesaikan: Implementasi Manajemen Pelajaran (5) [Tambah, ~~Edit~~, ~~Hapus~~]
8. Menyelesaikan: Implementasi Manajemen Kategori Kursus [CRUD, App]

05/10/2024:
1. Menyelesaikan: Update profile instruktur

06/10/2024:
1. Inisialisasi Order Service
2. Inisialisasi Payment Service
3. Menyelesaikan: Checkout Keranjang Belanja
4. Inisialisasi Enrollment Service
5. Menyelesaikan: Membuat enrollment saat mengupdate status pembayaran

09/10/2024:
1. Inisisalisasi Logging and Monitoring System

10/10/2024:
1. Menyelesaikan: Logging and Monitoring System
2. Menyelesaikan: Setup Migrasi Database dangan FlyWay

11/10/2024:
1. Improving Code Quality and Consistency
2. Inisialisasi Finance Service

12/10/2024:
1. Menyelesaikan: Mencatat Pendapatan Instruktur dan Platform
2. Menyelesaikan: Manajemen Siswa Instruktur
3. Menyelesaikan: Data Transaksi Instruktur

13/10/2024:
1. Menyelesaikan: Sistem Withdrawal Saldo Instruktur
2. Menyelesaikan: Sistem Balance dan Riwayat Transaksi Instruktur

14/10/2024:
1. Menyelesaikan: Manajemen Transaksi Siswa
