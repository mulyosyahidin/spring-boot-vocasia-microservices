import {FINANCE_INSTRUCTOR_BALANCE_DATA} from "../../../../config/api-endpoint.js";
import {apiEndpoint} from "../../../../utils/new-utils.js";
import axios from "axios";
import {accessToken, instructorAuthData} from "../../../../config/auth.js";

export const findBalanceData = async (currentPage = 1) => {
    try {
        const endpoint = `${apiEndpoint(FINANCE_INSTRUCTOR_BALANCE_DATA)}?page=${currentPage}`;
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