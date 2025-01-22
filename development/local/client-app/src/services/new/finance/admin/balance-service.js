import {apiEndpoint} from "../../../../utils/new-utils.js";
import {FINANCE_ADMIN_BALANCE_DATA, FINANCE_ADMIN_INSTRUCTOR_BALANCE_DATA} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findBalanceData = async (currentPage) => {
    try {
        const endpoint = `${apiEndpoint(FINANCE_ADMIN_BALANCE_DATA)}?page=${currentPage}`;
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

export const findInstructorBalanceDataById = async (instructorId, currentPage) => {
    try {
        const endpoint = `${apiEndpoint(FINANCE_ADMIN_INSTRUCTOR_BALANCE_DATA)}?page=${currentPage}`;
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-INSTRUCTOR-ID': instructorId,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}