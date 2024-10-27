import {apiEndpoint} from "../../../../utils/new-utils.js";
import {INSTRUCTOR_ADMIN_INSTRUCTORS, INSTRUCTOR_ADMIN_INSTRUCTORS_GET_BY_ID} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAll = async (currentPage) => {
    try {
        const endpoint = `${apiEndpoint(INSTRUCTOR_ADMIN_INSTRUCTORS)}?page=${currentPage}`;
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

export const findById = async (instructorId) => {
    try {
        const endpoint = apiEndpoint(INSTRUCTOR_ADMIN_INSTRUCTORS_GET_BY_ID, {instructorId: instructorId});
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