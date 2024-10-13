import {apiBaseUrl, AUTH_ACCESS_TOKEN, INSTRUCTOR_AUTH_DATA} from "../../config/consts.js";
import {axiosGet, axiosPost} from "../api.js";
import {
    INSTRUCTOR_FINANCE_GET_DATA,
    INSTRUCTOR_FINANCE_WITHDRAWAL_GET_ALL,
    INSTRUCTOR_FINANCE_WITHDRAWAL_SEND_REQUEST
} from "../../config/endpoints.js";
import axios from "axios";

let getInstructorAuthData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));

export const getInstructorBalanceData = async () => {
    try {
        let endpoint = `${apiBaseUrl}${INSTRUCTOR_FINANCE_GET_DATA}`;

        const response = await axiosGet({
            url: endpoint,
            headers: {
                'X-INSTRUCTOR-ID': getInstructorAuthData.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error;
    }
}

export const getInstructorWithdrawalHistories = async () => {
    try {
        let endpoint = `${apiBaseUrl}${INSTRUCTOR_FINANCE_WITHDRAWAL_GET_ALL}`;

        const response = await axiosGet({
            url: endpoint,
            headers: {
                'X-INSTRUCTOR-ID': getInstructorAuthData.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error;
    }
}

export const sendWithdrawalRequest = async (formData) => {
    try {
        const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);
        let endpoint = `${apiBaseUrl}${INSTRUCTOR_FINANCE_WITHDRAWAL_SEND_REQUEST}`;

        const response = await axios.post(endpoint, formData, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'Content-Type': 'application/json',
                'X-INSTRUCTOR-ID': getInstructorAuthData.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error;
    }
}