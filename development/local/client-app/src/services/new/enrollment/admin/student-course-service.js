import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    ENROLLMENT_ADMIN_STUDENT_COURSES,
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAllByUserId = async (userId, currentPage) => {
    try {
        const endpoint = `${apiEndpoint(ENROLLMENT_ADMIN_STUDENT_COURSES)}?page=${currentPage}`;
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