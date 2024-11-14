import http from 'k6/http';
import {check, group, sleep} from 'k6';
import {formatDate} from "./utils/utils.js";

const baseUrl = __ENV.BASE_URL || 'http://localhost:3000';
const authData = {
    email: 'martinms.za@gmail.com',
    password: 'password'
};

const endpoints = {
    'login': '/authentication/login',
    'ask-question': '/qna/student/questions/5/15',
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

    const questionData = {
        title: "Question title " + timeInMs,
        question: "Question content " + timeInMs
    }

    const questionRes = http.post(`${baseUrl}${endpoints['ask-question']}`, JSON.stringify(questionData), {
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
            'X-USER-ID': 3,
        },
    });

    check(questionRes, {
        'question created': (res) => res.status === 201,
    });

    sleep(1);
}

export function teardown() {
    const finishTime = new Date();
    console.log(`Finish at: ${formatDate(finishTime)}`);
}