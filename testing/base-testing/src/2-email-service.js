import http from 'k6/http';
import {check, sleep} from 'k6';
import {formatDate} from "./utils/utils.js";

const baseUrl = __ENV.BASE_URL || 'http://localhost:3000';
const endpoints = {
    'request-forgot-password': '/authentication/forgot-password/request',
};

export let options = {
    stages: [
        {duration: '2m', target: 50},
        { duration: '3m', target: 200 },
        { duration: '2m', target: 200 },
    ],
};

export function setup() {
    const startTime = new Date();

    console.log(`Base URL: ${baseUrl}`);
    console.log(`Start at: ${formatDate(startTime)}`);
}

export default function () {
    const email = 'martinms.za@gmail.com';

    http.post(`${baseUrl}${endpoints['request-forgot-password']}`, JSON.stringify({email: email}), {
        headers: {'Content-Type': 'application/json'},
    });

    sleep(1);
}

export function teardown() {
    const finishTime = new Date();

    console.log(`Finish at: ${formatDate(finishTime)}`);
}
