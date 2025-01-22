import {apiEndpoint} from "../../../utils/new-utils.js";
import {AUTHENTICATION_FORGOT_PASSWORD, AUTHENTICATION_FORGOT_PASSWORD_CREATE} from "../../../config/api-endpoint.js";
import axios from "axios";

export const requestResetPassword = async (data) => {
    try {
        const endpoint = apiEndpoint(AUTHENTICATION_FORGOT_PASSWORD);
        const response = await axios.post(endpoint, data);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const resetPassword = async (data) => {
    try {
        const endpoint = apiEndpoint(AUTHENTICATION_FORGOT_PASSWORD_CREATE);
        const response = await axios.post(endpoint, data);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}