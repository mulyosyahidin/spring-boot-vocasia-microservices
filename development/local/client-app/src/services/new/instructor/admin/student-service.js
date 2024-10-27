import {apiEndpoint} from "../../../../utils/new-utils.js";
import {INSTRUCTOR_ADMIN_STUDENTS, INSTRUCTOR_ADMIN_STUDENTS_GET_BY_ID} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAllByInstructorId = async (instructorId, currentPage) => {
    try {
        let endpoint = `${apiEndpoint(INSTRUCTOR_ADMIN_STUDENTS)}?page=${currentPage}`;
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

export const findByInstructorId = async (instructorStudentId) => {
    try {
        let endpoint = apiEndpoint(INSTRUCTOR_ADMIN_STUDENTS_GET_BY_ID, {id: instructorStudentId});
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