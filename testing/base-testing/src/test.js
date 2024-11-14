import http from 'k6/http';
import { check, sleep } from 'k6';

const baseUrl = __ENV.BASE_URL || 'http://localhost:3000';
const authData = [
  { email: 'admin@local.test', password: 'password' },
  { email: 'martinms.za@gmail.com', password: 'password' },
  { email: 'i1@local.test', password: 'password' },
];

export let options = {
  stages: [
    { duration: '3m', target: 50 }, // Low load: Ramp up to 50 users over 3 minutes
  ],
};

export default function () {
  const user = authData[Math.floor(Math.random() * authData.length)];

  const response = http.post(`${baseUrl}/auth/login`, JSON.stringify(user), {
    headers: { 'Content-Type': 'application/json' },
  });

  check(response, { 'status was 200': (r) => r.status === 200 });

  sleep(180); // Tidur selama 1 detik setelah login
}
