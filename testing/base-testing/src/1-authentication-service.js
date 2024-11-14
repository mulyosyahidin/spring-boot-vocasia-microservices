import http from 'k6/http';
import {check, sleep} from 'k6';
import {formatDate} from "./utils/utils.js";

const baseUrl = __ENV.BASE_URL || 'http://localhost:3000';
const authData = [
    {email: 'admin@local.test', password: 'password'},
    {email: 'martinms.za@gmail.com', password: 'wrong-password'},
    {email: 'i1@local.test', password: 'password'},
];
const endpoints = {
    'login': '/authentication/login',
    'request-forgot-password': '/authentication/forgot-password/request',
    'create-new-password': '/authentication/forgot-password/create-password',
};

export let options = {
    stages: [
        {duration: '3m', target: 50},
        { duration: '5m', target: 200 },
        { duration: '7m', target: 200 },
    ],
};

export function setup() {
    const startTime = new Date();

    console.log(`Base URL: ${baseUrl}`);
    console.log(`Start at: ${formatDate(startTime)}`);
}

export default function () {
    const user = authData[Math.floor(Math.random() * authData.length)];

    const response = http.post(`${baseUrl}${endpoints.login}`, JSON.stringify(user), {
        headers: {'Content-Type': 'application/json'},
    });

    if (response.status === 400) {
        http.post(`${baseUrl}${endpoints['request-forgot-password']}`, JSON.stringify({email: user.email}), {
            headers: {'Content-Type': 'application/json'},
        });
    }

    sleep(1);
}

export function teardown() {
    const finishTime = new Date();

    console.log(`Finish at: ${formatDate(finishTime)}`);
}
