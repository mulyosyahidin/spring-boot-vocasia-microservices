import {apiEndpoint} from "../../../../utils/new-utils.js";
import {COURSE_ADMIN_INSTRUCTOR_COURSES} from "../../../../config/api-endpoint.js";
import {accessToken} from "../../../../config/auth.js";
import axios from "axios";

export const findAllByInstructorId = async (instructorId, currentPage = 1) => {
    try {
        const endpoint = `${apiEndpoint(COURSE_ADMIN_INSTRUCTOR_COURSES)}?page=${currentPage}`;
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