import {ORDER_ADMIN_STUDENT_TRANSACTIONS} from "../../../../config/api-endpoint.js";
import {apiEndpoint} from "../../../../utils/new-utils.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAll = async (userId, currentPage) => {
    try {
        let endpoint = `${apiEndpoint(ORDER_ADMIN_STUDENT_TRANSACTIONS)}?page=${currentPage}`;
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-USER-ID': userId,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}