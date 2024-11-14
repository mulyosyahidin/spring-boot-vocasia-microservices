import http from 'k6/http';
import {check, group, sleep} from 'k6';
import {formatDate} from "./utils/utils.js";

const baseUrl = __ENV.BASE_URL || 'http://localhost:3000';;

const endpoints = {
    'register': '/instructor/register',
}

export let options = {
    stages: [
        {duration: '5m', target: 50},
        {duration: '5m', target: 200},
        {duration: '7m', target: 200},
    ],
};

export function setup() {
    const startTime = new Date();
    console.log(`Base URL: ${baseUrl}`);
    console.log(`Start at: ${formatDate(startTime)}`);
}

export default function () {
    const timeInMs = new Date().getTime();

    const password = 'password';
    const name = 'User ' + timeInMs;
    const phoneNumber = '0812'+ timeInMs;
    const email = 'user'+ timeInMs + '@mail.com';
    const username = 'user'+ timeInMs;

    const payload = {
        name: name,
        username: username,
        email: email,
        password: password,
        phone_number: phoneNumber,
    };

    const res = http.post(`${baseUrl}${endpoints.register}`, JSON.stringify(payload), {
        headers: {'Content-Type': 'application/json'},
    });

    check(res, {
        'register successful': (res) => res.status === 201,
    });

    sleep(1);
}

export function teardown() {
    const finishTime = new Date();
    console.log(`Finish at: ${formatDate(finishTime)}`);
}