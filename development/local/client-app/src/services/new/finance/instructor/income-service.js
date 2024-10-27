import {FINANCE_INSTRUCTOR_COURSES_INCOME} from "../../../../config/api-endpoint.js";
import {apiEndpoint} from "../../../../utils/new-utils.js";
import axios from "axios";
import {accessToken, instructorAuthData} from "../../../../config/auth.js";

export const findAll = async (courseId, currentPage) => {
    try {
        const endpoint = `${apiEndpoint(FINANCE_INSTRUCTOR_COURSES_INCOME, {id: courseId})}?page=${currentPage}`;
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