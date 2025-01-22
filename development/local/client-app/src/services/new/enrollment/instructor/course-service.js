import {ENROLLMENT_INSTRUCTOR_COURSES_STUDENTS} from "../../../../config/api-endpoint.js";
import {apiEndpoint} from "../../../../utils/new-utils.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAllCourseStudents = async (courseId, currentPage) => {
    try {
        const endpoint = `${apiEndpoint(ENROLLMENT_INSTRUCTOR_COURSES_STUDENTS, {id: courseId})}?page=${currentPage}`;
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