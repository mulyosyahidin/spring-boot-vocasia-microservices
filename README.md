# Vocasia
**Vocasia** merupakan layanan *massive open online courses* yang dikelola oleh Yayasan Adipurna Inovasi Asia yang dapat diakses melalui [vocasia.id](https://vocasia.id). Vocasia.id mulai dapat diakses pada tahun 2020 yang dibangun menggunakan bahasa pemrograman PHP dan *framework* CodeIgniter 3.

Proyek ini merupakan bagian dari tugas akhir Martin Mulyo Syahidin (Universitas Bengkulu) yang berjudul "**Rancang Bangun Arsitektur Microservices untuk Massive Open Online Courses (Studi Kasus: Vocasia.id)**". Secara garis besar, proyek ini membangun aplikasi *microservices* dari Vocasia.id berdasarkan rancangan dan alur bisnis yang sudah berjalan.

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
