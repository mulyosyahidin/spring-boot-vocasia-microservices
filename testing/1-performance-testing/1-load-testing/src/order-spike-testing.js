import http from 'k6/http';
import { check, sleep, group } from 'k6';

const BASE_URL = 'http://localhost:14119';

const LOGIN_ENDPOINT = '/authentication/login';
const CREATE_ORDER_ENDPOINT = '/order/student/place-new-order';
const MIDTRANS_CALLBACK = '/payment/midtrans-callback-dummy';

function formatDate(date) {
    const jakartaOffset = 7 * 60;
    const localDate = new Date(date.getTime() + jakartaOffset * 60 * 1000);

    return localDate.toISOString().replace('T', ' ').split('.')[0];
}

const orderData = {
    items: [
        {
            course_instructor_id: 2,
            course_id: 4,
            course_title: "Riverpod Crash Course",
            course_slug: "riverpod-crash-course",
            course_featured_picture_url: "https://vocasia.s3.ap-southeast-1.amazonaws.com/Riverpod%20Crash%20Course.jpg",
            course_price: 99000,
            course_is_free: false,
            course_is_discount: false,
            course_discount: 0
        },
        {
            course_instructor_id: 1,
            course_id: 18,
            course_title: "TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA)",
            course_slug: "tutorial-spring-boot-dasar-bahasa-indonesia",
            course_featured_picture_url: "https://vocasia.s3.ap-southeast-1.amazonaws.com/1729093329452-TUTORIAL SPRING BOOT DASAR (BAHASA INDONESIA).jpg",
            course_price: 200000,
            course_is_free: false,
            course_is_discount: true,
            course_discount: 50000
        }
    ],
    customer: {
        id: 3,
        name: "Martin Mulyo Syahidin",
        email: "martinms.za@gmail.com"
    }
};

export function setup() {
    const startTime = new Date();
    console.log(`Start at: ${formatDate(startTime)}`);

    const loginPayload = JSON.stringify({
        email: 'martinms.za@gmail.com',
        password: 'password',
    });

    const loginParams = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const response = http.post(`${BASE_URL}${LOGIN_ENDPOINT}`, loginPayload, loginParams);

    check(response, {
        'login berhasil': (r) => r.status === 200,
    });

    const responseData = response.json().data;

    return response.status === 200 ? { token: responseData.token.access_token, user_id: responseData.user.id } : null;
}

export default function (data) {
    if (data.token == null) {
        console.error("Token tidak tersedia.");
        return;
    }

    group('Order Creation', () => {
        const orderPayload = JSON.stringify(orderData);
        const orderParams = {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${data.token}`,
                'X-USER-ID': data.user_id,
            },
        };

        const orderResponse = http.post(`${BASE_URL}${CREATE_ORDER_ENDPOINT}`, orderPayload, orderParams);

        check(orderResponse, {
            'order berhasil dibuat': (r) => r.status === 201,
        });

        const orderResponseData = orderResponse.json().data.order;
        const orderNumber = orderResponseData.order_number;

        const midtransCallbackPayload = JSON.stringify({ order_id: orderNumber });
        const midtransCallbackParams = {
            headers: {
                'Content-Type': 'application/json',
            },
        };

        const callbackResponse = http.post(`${BASE_URL}${MIDTRANS_CALLBACK}`, midtransCallbackPayload, midtransCallbackParams);

        check(callbackResponse, {
            'callback Midtrans berhasil': (r) => r.status === 200,
        });
    });

    sleep(1);
}

// Tentukan skenario pengujian
export const options = {
    stages: [
        { duration: '1m', target: 20 }, // Fase awal: 0 sampai 20 virtual users
        { duration: '3m', target: 500 }, // Fase spike: langsung naik ke 500 virtual users
        { duration: '6m', target: 50 }, // Fase reduksi: turun ke 50 virtual users
    ],
};

export function teardown() {
    const finishTime = new Date();
    console.log(`Finish at: ${formatDate(finishTime)}`);
}
