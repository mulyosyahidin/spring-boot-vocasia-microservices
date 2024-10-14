import {apiBaseUrl} from "../config/consts.js";
import moment from "moment";
import "moment/dist/locale/id";
import {authenticatedUser} from "../config/auth.js";

/**
 * Membuat URL lengkap untuk API dengan menggantikan placeholder opsional.
 *
 * @param {string} endpoint - Template endpoint yang digunakan (contoh: '/orders/:orderId').
 * @param {object} replaces - (Opsional) Objek untuk menggantikan placeholder dalam endpoint (contoh: { orderId: 123 }).
 * @returns {string} - URL akhir untuk API dengan base URL dan placeholder yang telah digantikan.
 *
 * @throws {Error} - Jika parameter 'endpoint' tidak diberikan.
 *
 * Fungsi ini menggabungkan `apiBaseUrl` dengan `endpoint`, memastikan tidak ada slash (/) ganda di antara mereka.
 * Placeholder yang terdapat dalam `endpoint` akan digantikan dengan nilai dari objek `replaces`, jika disediakan.
 *
 * Contoh Penggunaan:
 * 1. Dengan menggantikan placeholder:
 *    const url = apiEndpoint('/orders/:orderId/status/:status', { orderId: 123, status: 'confirmed' });
 *    // Hasil: 'https://api.example.com/orders/123/status/confirmed'
 *
 * 2. Tanpa menggantikan placeholder (menggunakan endpoint apa adanya):
 *    const url = apiEndpoint('/orders');
 *    // Hasil: 'https://api.example.com/orders'
 */
export const apiEndpoint = (endpoint, replaces = {}) => {
    if (!endpoint) {
        throw new Error('Endpoint harus diisi.');
    }

    // Menghapus trailing slash dari apiBaseUrl jika ada
    let baseUrl = apiBaseUrl.endsWith('/') ? apiBaseUrl.slice(0, -1) : apiBaseUrl;

    // Menghapus leading slash dari endpoint jika ada
    endpoint = endpoint.startsWith('/') ? endpoint.slice(1) : endpoint;

    // Mengganti placeholder di dalam endpoint dengan nilai dari 'replaces'
    Object.keys(replaces).forEach((key) => {
        const placeholder = `:${key}`;
        endpoint = endpoint.replace(placeholder, replaces[key]);
    });

    return `${baseUrl}/${endpoint}`;
};

/**
 * Mengorganisir daftar kategori menjadi array yang dapat digunakan pada field select.
 *
 * @param {Array} categories - Daftar kategori yang akan diorganisir.
 * @param {number|null} defaultSelectedId - (Opsional) ID kategori yang secara default akan dipilih.
 * @param {boolean} addNull - (Opsional) Jika true, menambahkan opsi "Pilih Kategori" di awal array.
 * @returns {Array} - Array objek kategori yang siap digunakan dalam komponen select, dengan format `{ value: id, label: name, selected: isSelected }`.
 *
 * Fungsi ini mengubah daftar kategori menjadi format yang dapat digunakan pada komponen select HTML.
 * Jika `addNull` diatur ke `true`, fungsi akan menambahkan opsi dengan value `null` dan label "Pilih Kategori" di awal array.
 * Setiap kategori akan dicek apakah ID-nya cocok dengan `defaultSelectedId`, untuk menentukan apakah kategori tersebut akan disetel sebagai yang dipilih.
 *
 * Contoh Penggunaan:
 *
 * const categories = [
 *     { id: 1, name: "Akademik" },
 *     { id: 2, name: "Bisnis" },
 * ];
 *
 * // Menghasilkan array untuk select field dengan default ID terpilih:
 * const organized = organizeCategories(categories, 2, true);
 * // Hasil: [
 * //   { value: null, label: "Pilih Kategori", selected: false },
 * //   { value: 1, label: "Akademik", selected: false },
 * //   { value: 2, label: "Bisnis", selected: true }
 * // ]
 */
export const organizeCategories = (categories, defaultSelectedId = null, addNull = false) => {
    let organizedCategories = [];

    if (addNull) {
        organizedCategories.push({
            value: null,
            label: 'Pilih Kategori',
            selected: false,
        });
    }

    categories.forEach(category => {
        const isSelected = category.id === defaultSelectedId;

        organizedCategories.push({
            value: category.id,
            label: category.name,
            selected: isSelected,
        });
    });

    return organizedCategories;
};

/**
 * Mengorganisir daftar kategori menjadi array yang dapat digunakan pada field select.
 *
 * @param {Array} categories - Daftar kategori yang akan diorganisir.
 *                             Harus berisi objek dengan properti `children` dan `category`.
 * @param {number|null} defaultSelectedId - (Opsional) ID kategori yang secara default akan dipilih.
 * @param {boolean} addNull - (Opsional) Jika true, menambahkan opsi "Pilih Kategori" di awal array.
 * @returns {Array} - Array objek kategori yang siap digunakan dalam komponen select,
 *                    dengan format `{ value: id, label: name, selected: isSelected }`.
 *
 * Fungsi ini mengubah daftar kategori menjadi format yang dapat digunakan pada komponen select HTML.
 * Jika `addNull` diatur ke `true`, fungsi akan menambahkan opsi dengan value `null` dan label "Pilih Kategori" di awal array.
 * Setiap kategori anak akan dicek apakah ID-nya cocok dengan `defaultSelectedId`, untuk menentukan apakah kategori tersebut akan disetel sebagai yang dipilih.
 *
 * Contoh Penggunaan:
 *
 * const categories = [
 *     {
 *         category: { id: 1, name: "Akademik" },
 *         children: [ /* ... * / ]
 *     },
 *     {
 *         category: { id: 2, name: "Akuntansi dan Keuangan" },
 *         children: [ /* ... * / ]
 *     }
 * ];
 *
 * // Menghasilkan array untuk select field dengan default ID terpilih:
 * const organized = organizeCategoriesWithChildren(categories, 12, true);
 * // Hasil: [
 * //   { value: null, label: "Pilih Kategori", selected: false },
 * //   { value: 12, label: "Akademik > Bahasa", selected: true },
 * //   { value: 13, label: "Akademik > Engineering", selected: false },
 * //   // ... kategori anak lainnya
 * // ]
 */
export const organizeCategoriesWithChildren = (categories, defaultSelectedId = null, addNull = false) => {
    let organizedCategories = [];

    if (addNull) {
        organizedCategories.push({
            value: null,
            label: 'Pilih Kategori',
            selected: false,
        });
    }

    categories.forEach(({ category, children }) => {
        children.forEach(child => {
            const isChildSelected = child.id === defaultSelectedId;

            organizedCategories.push({
                value: child.id,
                label: `${category.name} > ${child.name}`,
                selected: isChildSelected,
            });
        });
    });

    return organizedCategories;
};

/**
 * Memformat string tanggal dari API ke dalam format yang diinginkan.
 *
 * @param {string} dateString - String tanggal dalam format 'YYYY-MM-DDTHH:mm:ss'
 *                               (contoh: '2024-10-13T14:13:36').
 * @param {string} [format='DD MMMM YYYY HH:mm'] - Format output yang diinginkan menggunakan
 *                                                  sintaks Moment.js. Secara default,
 *                                                  akan mengembalikan dalam format
 *                                                  'DD MMMM YYYY HH:mm'.
 * @returns {string} - Mengembalikan tanggal yang diformat. Jika tanggal tidak valid,
 *                     akan mengembalikan pesan 'Tanggal tidak valid'.
 *
 * @example
 * // Menggunakan fungsi formatDate
 * const dateStr = '2024-10-13T14:13:36';
 * const formattedDate = formatDate(dateStr);
 * console.log(formattedDate); // Output: "13 Oktober 2024 14:13"
 */
export const formatDate = (dateString, format = 'DD MMMM YYYY HH:mm') => {
    const date = moment(dateString);

    if (!date.isValid()) {
        return 'Tanggal tidak valid';
    }

    moment.locale('id');

    // Return the formatted date
    return date.format(format);
};

/**
 * Memformat string angka menjadi format Rupiah (IDR).
 *
 * @param {string|number} amountString - String atau angka yang ingin diformat menjadi Rupiah.
 *                                       Input dapat berupa string angka atau angka numerik
 *                                       (contoh: '50000' atau 50000).
 * @returns {string} - Mengembalikan angka yang diformat dalam format Rupiah (IDR).
 *                     Jika input tidak valid, akan mengembalikan pesan 'Jumlah tidak valid'.
 *
 * @example
 * // Menggunakan fungsi formatRupiah
 * const amount = '50000';
 * const formattedRupiah = formatRupiah(amount);
 * console.log(formattedRupiah); // Output: "Rp50.000"
 *
 * @example
 * const invalidAmount = 'abc';
 * const formattedRupiah = formatRupiah(invalidAmount);
 * console.log(formattedRupiah); // Output: "Jumlah tidak valid"
 */
export const formatRupiah = (amountString) => {
    // Buat formatter langsung di dalam fungsi
    const rupiahFormatter = new Intl.NumberFormat('id-ID', {
        style: 'currency',
        currency: 'IDR',
        minimumFractionDigits: 0
    });

    const amount = parseFloat(amountString);

    // Pastikan nilai yang diformat valid
    if (isNaN(amount)) {
        return 'Jumlah tidak valid';
    }

    return rupiahFormatter.format(amount);
};

/**
 * Mengambil URL foto profil pengguna yang diautentikasi.
 *
 * @returns {string} - Mengembalikan URL foto profil pengguna. Jika pengguna tidak memiliki
 *                     foto profil, akan mengembalikan URL default gambar profil.
 *
 * @example
 * // Menggunakan fungsi getUserProfilePictureUrl
 * const profilePictureUrl = getUserProfilePictureUrl();
 * console.log(profilePictureUrl); // Output: "https://example.com/default-profile-picture.jpg"
 */
export const getUserProfilePictureUrl = () => {
    // Cek apakah pengguna memiliki foto profil yang diatur
    if (authenticatedUser.profile_picture_url) {
        return authenticatedUser.profile_picture_url;
    }

    // Jika foto profil kosong, kembalikan URL default foto profil
    return '/assets/img/misc/user-profile.png';
};

/**
 * Mendapatkan ID video YouTube dari URL yang diberikan.
 *
 * @param {string} url - URL YouTube yang ingin diambil ID videonya.
 *                       Dapat berupa URL YouTube lengkap (contoh: 'https://www.youtube.com/watch?v=xyz123abcde')
 *                       atau URL pendek YouTube (contoh: 'https://youtu.be/xyz123abcde').
 * @returns {string|null} - Mengembalikan ID video yang berhasil diekstrak jika URL YouTube valid.
 *                          Mengembalikan `null` jika URL tidak valid atau ID video tidak dapat diekstrak.
 *
 * @example
 * // Menggunakan fungsi getYouTubeVideoId
 * const url = 'https://www.youtube.com/watch?v=xyz123abcde';
 * const videoId = getYouTubeVideoId(url);
 * console.log(videoId); // Output: 'xyz123abcde'
 *
 * @example
 * const invalidUrl = 'https://www.example.com';
 * const videoId = getYouTubeVideoId(invalidUrl);
 * console.log(videoId); // Output: null
 */
export const getYouTubeVideoId = (url) => {
    const videoIdMatch = url.match(
        /(?:https?:\/\/)?(?:www\.)?(?:youtube\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|.*[?&]v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})/
    );

    return videoIdMatch ? videoIdMatch[1] : null;
};
