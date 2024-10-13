import {ORDER_GET_DATA, ORDER_MY_ORDERS} from "../../config/endpoints.js";
import {apiBaseUrl, AUTH_ACCESS_TOKEN, AUTH_USER} from "../../config/consts.js";
import axios from "axios";

export const getAllMyOrders = async () => {
    try {
        const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);
        const getUserData = JSON.parse(localStorage.getItem(AUTH_USER));

        let endpoint = `${apiBaseUrl}${ORDER_MY_ORDERS}`;

        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-USER-ID': getUserData.id,
            },
        });

        return response.data.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findMyOrderDataById = async (orderId) => {
    try {
        const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);
        const getUserData = JSON.parse(localStorage.getItem(AUTH_USER));

        let endpoint = `${apiBaseUrl}${ORDER_GET_DATA}`;
        endpoint = endpoint.replace(':orderId', orderId);

        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-USER-ID': getUserData.id,
            },
        });

        return response.data.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}