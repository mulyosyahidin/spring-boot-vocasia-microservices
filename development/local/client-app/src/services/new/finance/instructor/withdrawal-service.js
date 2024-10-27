import {
    FINANCE_INSTRUCTOR_WITHDRAWAL,
    FINANCE_INSTRUCTOR_WITHDRAWAL_STORE
} from "../../../../config/api-endpoint.js";
import {apiEndpoint} from "../../../../utils/new-utils.js";
import axios from "axios";
import {accessToken, instructorAuthData} from "../../../../config/auth.js";

export const findAll = async(currentPage) => {
    try {
        const endpoint = `${apiEndpoint(FINANCE_INSTRUCTOR_WITHDRAWAL)}?page=${currentPage}`;
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-INSTRUCTOR-ID': instructorAuthData.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const saveWithdrawal =async (data) => {
    try {
        const endpoint = apiEndpoint(FINANCE_INSTRUCTOR_WITHDRAWAL_STORE);
        const response = await axios.post(endpoint, data, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-INSTRUCTOR-ID': instructorAuthData.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}