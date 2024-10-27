import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    AUTHENTICATION_ADMIN_STUDENTS,
    AUTHENTICATION_ADMIN_STUDENTS_GET_BY_ID
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAll = async (currentPage) => {
    try {
        const endpoint = apiEndpoint(AUTHENTICATION_ADMIN_STUDENTS);
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`
            },
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findById = async (id) => {
    try {
        const endpoint = apiEndpoint(AUTHENTICATION_ADMIN_STUDENTS_GET_BY_ID, {userId: id});
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`
            },
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}