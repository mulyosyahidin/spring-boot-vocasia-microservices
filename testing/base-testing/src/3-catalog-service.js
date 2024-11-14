import http from 'k6/http';
import {check, sleep} from 'k6';
import {formatDate} from "./utils/utils.js";

const baseUrl = __ENV.BASE_URL || 'http://localhost:3000';
const endpoints = {
    'latest': '/catalog/public/courses/latests',
};

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
    const response = http.get(`${baseUrl}${endpoints.latest}`, {
        headers: {'Content-Type': 'application/json'},
    });

    check(response, {
        'is status 200': (r) => r.status === 200,
    });

    sleep(1);
}

export function teardown() {
    const finishTime = new Date();

    console.log(`Finish at: ${formatDate(finishTime)}`);
}
