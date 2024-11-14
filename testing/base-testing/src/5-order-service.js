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
    'place-new-order': '/order/student/place-new-order',
    'midtrans-callback': '/payment/midtrans-callback-dummy'
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

    const orderData = {
        items: [
            {
                course_instructor_id: 1,
                course_id: 18,
                course_title: 'TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)',
                course_slug: 'tutorial-spring-boot-dasar-bahasa-indonesia',
                course_featured_picture_url: 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1729093329452-TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA).jpg',
                course_price: 200000,
                course_is_free: false,
                course_is_discount: true,
                course_discount: 50000
            },
            {
                course_instructor_id: 2,
                course_id: 27,
                course_title: 'Ternak bebek peking 100 ekor analisa dan keuntungan',
                course_slug: 'ternak-bebek-peking-100-ekor-analisa-dan-keuntungan',
                course_featured_picture_url: 'https://vocasia.s3.ap-southeast-1.amazonaws.com/1730742655475-ternak-bebek-petelur.jpg',
                course_price: 150000,
                course_is_free: false,
                course_is_discount: false,
                course_discount: 0
            }
        ],
        customer: {
            id: 3,
            name: "Martin Mulyo Syahidin",
            email: "martinms.za@gmail.com"
        }
    }

    group('Order Creation', () => {
        const orderPayload = JSON.stringify(orderData);

        const orderResponse = http.post(`${baseUrl}${endpoints['place-new-order']}`, orderPayload, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
                'X-USER-ID': 3,
            }
        });

        check(orderResponse, {
            'order berhasil dibuat': (r) => r.status === 201,
        });

        const orderResponseData = orderResponse.json().data.order;
        const orderNumber = orderResponseData.order_number;

        const midtransCallbackPayload = JSON.stringify({order_id: orderNumber});
        const midtransCallbackParams = {
            headers: {
                'Content-Type': 'application/json',
            },
        };

        const callbackResponse = http.post(`${baseUrl}${endpoints['midtrans-callback']}`, midtransCallbackPayload, midtransCallbackParams);

        check(callbackResponse, {
            'callback Midtrans berhasil': (r) => r.status === 200,
        });
    });
}

export function teardown() {
    const finishTime = new Date();
    console.log(`Finish at: ${formatDate(finishTime)}`);
}