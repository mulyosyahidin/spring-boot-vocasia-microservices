import http from 'k6/http';
import {check, sleep} from 'k6';
import {formatDate} from "./utils/utils.js";

export let options = {
    stages: [
        {duration: '1m', target: 50},
        { duration: '2m', target: 200 },
        { duration: '1m', target: 200 },
    ],
};

export function setup() {
    const startTime = new Date();

    console.log(`Start at: ${formatDate(startTime)}`);
}

export default function () {

    const response = http.get('http://35.186.159.9:9000/');


    sleep(1);
}

export function teardown() {
    const finishTime = new Date();

    console.log(`Finish at: ${formatDate(finishTime)}`);
}