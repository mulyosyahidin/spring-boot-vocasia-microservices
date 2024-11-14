import {apiEndpoint} from "../../utils/new-utils.js";
import {
    AUTHENTICATION_ADMIN_OVERVIEW,
    COURSE_ADMIN_OVERVIEW,
    INSTRUCTOR_ADMIN_OVERVIEW, ORDER_ADMIN_OVERVIEW
} from "../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../config/auth.js";

export const getCourseOverview = async () => {
    try {
        let endpoint = apiEndpoint(COURSE_ADMIN_OVERVIEW);
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

export const getInstructorOverview = async () => {
    try {
        let endpoint = apiEndpoint(INSTRUCTOR_ADMIN_OVERVIEW);
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

export const getAuthenticationOverview = async () => {
    try {
        let endpoint = apiEndpoint(AUTHENTICATION_ADMIN_OVERVIEW);
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

export const getOrderOverview = async () => {
    try {
        let endpoint = apiEndpoint(ORDER_ADMIN_OVERVIEW);
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

