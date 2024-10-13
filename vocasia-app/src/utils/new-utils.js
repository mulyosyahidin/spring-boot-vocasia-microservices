import {apiBaseUrl} from "../config/consts.js";

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
