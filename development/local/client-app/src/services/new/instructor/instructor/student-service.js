import axios from "axios";
import {apiEndpoint} from "../../../../utils/new-utils.js";
import {INSTRUCTOR_INSTRUCTOR_STUDENTS, INSTRUCTOR_INSTRUCTOR_STUDENTS_GET_BY_ID} from "../../../../config/api-endpoint.js";
import {accessToken, instructorAuthData} from "../../../../config/auth.js";

export const findAll = async (currentPage) => {
    try {
        let endpoint = `${apiEndpoint(INSTRUCTOR_INSTRUCTOR_STUDENTS)}?page=${currentPage}`;
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

export const findById = async (id) => {
    try {
        let endpoint = apiEndpoint(INSTRUCTOR_INSTRUCTOR_STUDENTS_GET_BY_ID, {id: id});
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