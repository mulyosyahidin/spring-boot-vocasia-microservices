import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 1,
  iterations: 20,
  duration: '20s',
};

const url = 'http://localhost:14119/authentication/login';
const loginData = {
  email: 'i1@local.test',
  password: 'password', // Ganti dengan password yang sesuai
};

export default function () {
  // Mengirimkan permintaan login
  const response = http.post(url, JSON.stringify(loginData), {
    headers: { 'Content-Type': 'application/json' },
  });

  // Memeriksa status respons
  if (response.status === 200) {
    const responseData = JSON.parse(response.body);

    console.log(`${responseData.message} - ${responseData.data['instructor']['id']}`);
  } else {
    console.log(`Login gagal: ${response.status} - ${response.body}`);
  }

  // Tidur selama 1 detik antara setiap permintaan
  sleep(1);
}
