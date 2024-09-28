package com.vocasia.authentication.service;

import com.vocasia.authentication.mapper.AccessTokenMapper;

import java.io.IOException;

public interface IKeyCloackService {

    /**
     * Mendaftarkan pengguna baru di sistem Keycloak dengan informasi yang diberikan.
     * Jika pengguna sudah ada, mengembalikan ID pengguna yang sudah ada.
     *
     * @param email     Alamat email pengguna baru.
     * @param username  Nama pengguna (username) yang diinginkan untuk pengguna baru.
     * @param password  Kata sandi untuk akun pengguna baru.
     * @param name      Nama lengkap pengguna (akan dibagi menjadi nama depan dan nama belakang).
     * @param roleName  Peran yang akan diberikan kepada pengguna baru (misalnya: admin, student, lecturer).
     * @return String   ID Keycloak dari pengguna yang baru terdaftar atau pengguna yang sudah ada jika sudah terdaftar.
     */
    String registerNewUser(String email, String username, String password, String name, String roleName);

    /**
     * Mengambil token akses untuk pengguna dari Keycloak dengan memberikan username dan password mereka.
     * Token akses dapat digunakan untuk mengautentikasi permintaan selanjutnya.
     *
     * @param username  Nama pengguna (username) dari pengguna yang diminta token aksesnya.
     * @param password  Kata sandi dari pengguna.
     * @return AccessTokenMapper   Informasi token akses termasuk token refresh dan detail kadaluwarsa.
     * @throws IOException Jika terjadi kesalahan selama permintaan HTTP ke Keycloak.
     */
    AccessTokenMapper getAccessToken(String username, String password) throws IOException;

    /**
     * Menyegarkan (refresh) token akses yang sudah ada dengan menggunakan token refresh.
     *
     * @param accessToken  Token akses yang dimiliki pengguna untuk disegarkan.
     * @return AccessTokenMapper   Informasi token akses yang diperbarui termasuk token refresh dan detail kadaluwarsa.
     * @throws IOException Jika terjadi kesalahan selama permintaan HTTP untuk menyegarkan token.
     */
    AccessTokenMapper refreshAccessToken(String accessToken) throws IOException;
}
