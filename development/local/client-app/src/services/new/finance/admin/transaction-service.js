import {apiEndpoint} from "../../../../utils/new-utils.js";
import {FINANCE_ADMIN_TRANSACTIONS} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAllByInstructorId = async (instructorId, currentPage) => {
    try {
        const endpoint = `${apiEndpoint(FINANCE_ADMIN_TRANSACTIONS)}?page=${currentPage}`;
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-INSTRUCTOR-ID': instructorId
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}