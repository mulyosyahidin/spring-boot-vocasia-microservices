import http from 'k6/http';
import { sleep } from 'k6';

// Konfigurasi pengujian
export const options = {
  vus: 1, // Virtual User (jumlah pengguna virtual yang mengirim permintaan)
  iterations: 20, // Jumlah permintaan total
  duration: '20s', // Durasi pengujian (20 detik)
};

const url = 'http://localhost:14119/instructor/instructor/profile';
const params = {
  headers: {
    'X-INSTRUCTOR-ID': 2,
    'Authorization': 'Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJYZWp0UnpwTkxKMUpOUVNCSkJVUS1DeHFOY0VIVENkQmcya1lYLW9zRUtRIn0.eyJleHAiOjE3MzAxNDM5NTgsImlhdCI6MTczMDEwNzk1OCwianRpIjoiMTI5NjU1ODctNjg3OS00YTNlLTljZDktZWYwY2I3YWQ4Mzg2IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo3MDgwL3JlYWxtcy9Wb2Nhc2lhIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjQzZjU0YTdkLTg0MWYtNDFiNi04M2Y4LTBhMWNhZmE2YWNkOSIsInR5cCI6IkJlYXJlciIsImF6cCI6InZvY2FzaWEtYXV0aCIsInNpZCI6IjkwNzM3NGRlLWFmN2UtNGJmNi1iNmY4LWNhN2Q5ZDQwNmZjZCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy12b2Nhc2lhIiwiaW5zdHJ1Y3RvciIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJKZXNzeSBNYW5kYXNhcmkiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJqZXNzeSIsImdpdmVuX25hbWUiOiJKZXNzeSIsImZhbWlseV9uYW1lIjoiTWFuZGFzYXJpIiwiZW1haWwiOiJqZXNzeS5tYW5kYXNhcmlAZ21haWwuY29tIn0.lM-2zS8IPFe9qIrc4TmWcj01T87U26aL5I5hNf01e0rv-RY3-uvKy8byIgSycWL6oZvVfIoIVllzDnNN1dnjuxfiga7pDvrq9k42Boa-AOsWmYLMJjl3lMyoRIdqF0cGB_GaHHNdCQ9LCiFxgfMdGi0GVriktTrd0nkDualGnJr9ZJ0fshTTb_rUcj8CeMngWTWxaf0DNe6BHS0ELy6JzWBjG6_iydguk_5zuCXyCqgq6_2rcJq89LPlOpEpeMrG-kx-3J3v84VVqMxoSLj1yiPTGWugHy7bpYzxh8qHi_xzj69Ol8XpVF2LCs6ZVAmocNvxKJFMcQgCaRucRmP_rA', // Ganti 'YOUR_TOKEN_HERE' dengan token sebenarnya
  },
};

export default function () {
  const res = http.get(url, params);
  console.log(`Status: ${res.status}, Iteration: ${__ITER}`);

  // Jika permintaan gagal, mencetak pesan error
  if (res.status !== 200) {
    console.error(`Request failed with status ${res.status} on iteration ${__ITER}`);
  }

  // Menunggu 1 detik sebelum melakukan permintaan berikutnya
  sleep(1);
}
