import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    FINANCE_ADMIN_WITHDRAWAL_REQUEST,
    FINANCE_ADMIN_WITHDRAWAL_REQUEST_GET_BY_ID, FINANCE_ADMIN_WITHDRAWAL_REQUEST_PROCESS_BY_ID,
    FINANCE_INSTRUCTOR_BALANCE_DATA
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken, instructorAuthData} from "../../../../config/auth.js";

export const findAll = async (currentPage = 1, status = 'all') => {
    try {
        const endpoint = `${apiEndpoint(FINANCE_ADMIN_WITHDRAWAL_REQUEST)}?page=${currentPage}&status=${status}`;
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findById = async (id) => {
    try {
        const endpoint = apiEndpoint(FINANCE_ADMIN_WITHDRAWAL_REQUEST_GET_BY_ID, {id: id});
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const processById = async (id, data) => {
    try {
        const endpoint = apiEndpoint(FINANCE_ADMIN_WITHDRAWAL_REQUEST_PROCESS_BY_ID, {id: id});
        const response = await axios.post(endpoint, data, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'Content-Type': 'multipart/form-data',
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}