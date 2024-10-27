import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    FINANCE_INSTRUCTOR_TRANSACTIONS,
    FINANCE_INSTRUCTOR_TRANSACTIONS_GET_BY_ID
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken, instructorAuthData} from "../../../../config/auth.js";

export const findAll = async (currentPage) => {
    try {
        const endpoint = `${apiEndpoint(FINANCE_INSTRUCTOR_TRANSACTIONS)}?page=${currentPage}`;
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

export const findById = async (id) => {
    try {
        const endpoint = apiEndpoint(FINANCE_INSTRUCTOR_TRANSACTIONS_GET_BY_ID, {id: id});
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}