import http from 'k6/http';
import { check, sleep } from 'k6';

// Fungsi untuk memformat tanggal dan waktu ke zona waktu Asia/Jakarta
function formatDate(date) {
  const jakartaOffset = 7 * 60; // Offset untuk Asia/Jakarta (UTC+7)
  const localDate = new Date(date.getTime() + jakartaOffset * 60 * 1000);
  return localDate.toISOString().replace('T', ' ').split('.')[0];
}

export let options = {
  stages: [
    { duration: '3m', target: 50 },   // Warm-Up (Low Load)
    { duration: '5m', target: 200 },  // Ramp-Up (Increasing Load)
    { duration: '7m', target: 200 },  // Steady-State (Stable Load)
  ],
};

// Mencatat waktu mulai saat tes dimulai
export function setup() {
  const startTime = new Date();
  console.log(`Start at: ${formatDate(startTime)}`);
}

// Fungsi utama yang dieksekusi selama tes
export default function () {
  // Ganti URL dengan endpoint yang ingin diuji
  const res = http.get('http://localhost:14119/catalog/public/courses/latests');
  
  // Memastikan response status 200 untuk setiap request
  check(res, { 'status was 200': (r) => r.status === 200 });
  
  // Tidur sejenak agar tidak terlalu cepat
  sleep(1);
}

// Mencatat waktu selesai saat tes berakhir
export function teardown() {
  const finishTime = new Date();
  console.log(`Finish at: ${formatDate(finishTime)}`);
}
