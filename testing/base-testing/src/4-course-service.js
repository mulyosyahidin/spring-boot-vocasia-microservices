import http from 'k6/http';
import {check, sleep} from 'k6';
import {formatDate} from "./utils/utils.js";

const baseUrl = __ENV.BASE_URL || 'http://localhost:3000';
const authData = {
    email: 'i1@local.test',
    password: 'password'
};

const endpoints = {
    'login': '/authentication/login',
    'create-course': '/course/instructor/courses',
};

export let options = {
    stages: [
        {duration: '5m', target: 50},
        { duration: '5m', target: 200 },
        { duration: '7m', target: 200 },
    ],
};

export function setup() {
    const startTime = new Date();
    console.log(`Base URL: ${baseUrl}`);
    console.log(`Start at: ${formatDate(startTime)}`);

    // Perform login and retrieve token
    const loginRes = http.post(`${baseUrl}${endpoints.login}`, JSON.stringify(authData), {
        headers: {'Content-Type': 'application/json'},
    });

    check(loginRes, {
        'login successful': (res) => res.status === 200 && res.json('success') === true,
    });

    const token = loginRes.json('data.token.access_token');
    return {token};
}

export default function (data) {
    const token = data.token;

    const timeInMs = new Date().getTime();

    // Add a new course
    const courseData = {
        category_id: 4,
        title: 'K6 Performance Testing ' + timeInMs,
        short_description: 'K6 Performance Testing ' + timeInMs,
        description: 'K6 Performance Testing ' + timeInMs,
        level: 'all',
        language: 'id',
        price: 100000,
        total_duration: '1j 10m 10d',
        discount: 0,
    };

    const courseRes = http.post(`${baseUrl}${endpoints['create-course']}`, JSON.stringify(courseData), {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
            'X-INSTRUCTOR-ID': 2,
        },
    });

    check(courseRes, {
        'course creation successful': (res) => res.status === 200 || res.status === 201,
    });

    sleep(1);
}

export function teardown() {
    const finishTime = new Date();
    console.log(`Finish at: ${formatDate(finishTime)}`);
}
