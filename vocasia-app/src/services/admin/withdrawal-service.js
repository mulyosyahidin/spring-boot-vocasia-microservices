import {apiBaseUrl, AUTH_ACCESS_TOKEN} from "../../config/consts.js";
import {
    FINANCE_ADMIN_WITHDRAWAL_REQUEST,
    FINANCE_ADMIN_WITHDRAWAL_REQUEST_FIND_BY_ID, FINANCE_ADMIN_WITHDRAWAL_REQUEST_PROCESS,
    INSTRUCTOR_FINANCE_GET_DATA
} from "../../config/endpoints.js";
import {axiosGet} from "../api.js";
import axios from "axios";

export const getAllWithdrawalRequests = async (status) => {
    try {
        let endpoint = `${apiBaseUrl}${FINANCE_ADMIN_WITHDRAWAL_REQUEST}?status=${status}`;

        const response = await axiosGet({
            url: endpoint,
        });

        return response.data;
    } catch (error) {
        throw error;
    }
}

export const findWithdrawalRequestById = async (id) => {
    try {
        let endpoint = `${apiBaseUrl}${FINANCE_ADMIN_WITHDRAWAL_REQUEST_FIND_BY_ID}`;
        endpoint = endpoint.replace(":id", id);

        const response = await axiosGet({
            url: endpoint,
        });

        return response.data;
    } catch (error) {
        throw error;
    }
}

export const processWithdrawalRequest = async (id, formData) => {
    try {
        const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);

        let endpoint = `${apiBaseUrl}${FINANCE_ADMIN_WITHDRAWAL_REQUEST_PROCESS}`;
        endpoint = endpoint.replace(":id", id);

        const response = await axios.post(endpoint, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                'Authorization': `Bearer ${accessToken}`,
            },
        });

        return response.data;
    }
    catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}