import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    INSTRUCTOR_ADMIN_INSTRUCTORS,
    INSTRUCTOR_ADMIN_INSTRUCTORS_GET_BY_ID, INSTRUCTOR_ADMIN_SUBMISSIONS_APPROVE,
    INSTRUCTOR_ADMIN_SUBMISSIONS_GET_ALL, INSTRUCTOR_ADMIN_SUBMISSIONS_REJECT
} from "../../../../config/api-endpoint.js";
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

export const findAllSubmissions = async (currentPage) => {
    try {
        const endpoint = `${apiEndpoint(INSTRUCTOR_ADMIN_SUBMISSIONS_GET_ALL)}?page=${currentPage}`;
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

export const approveSubmission = async (id) => {
    try {
        const endpoint = apiEndpoint(INSTRUCTOR_ADMIN_SUBMISSIONS_APPROVE, {id: id});
        const response = await axios.post(endpoint, {}, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const rejectSubmission = async (id) => {
    try {
        const endpoint = apiEndpoint(INSTRUCTOR_ADMIN_SUBMISSIONS_REJECT, {id: id});
        const response = await axios.post(endpoint, {}, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}
