import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    COURSE_ADMIN_CATEGORIES, COURSE_ADMIN_CATEGORIES_GET_BY_ID,
    COURSE_ADMIN_CATEGORIES_ONLY_PARENTS,
    COURSE_ADMIN_CATEGORIES_STORE
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAll = async (currentPage) => {
    try {
        const endpoint = `${apiEndpoint(COURSE_ADMIN_CATEGORIES)}?page=${currentPage}`;
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

export const findAllOnlyParents = async () => {
    try {
        const endpoint = apiEndpoint(COURSE_ADMIN_CATEGORIES_ONLY_PARENTS);
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

export const saveCategory = async (formData) => {
    try {
        const endpoint = apiEndpoint(COURSE_ADMIN_CATEGORIES_STORE);
        const response = await axios.post(endpoint, formData, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'Content-Type': 'multipart/form-data',
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findById = async (id) => {
    try {
        const endpoint = apiEndpoint(COURSE_ADMIN_CATEGORIES_GET_BY_ID, {id: id});
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

export const updateCategory = async (id, formData) => {
    try {
        const endpoint = apiEndpoint(COURSE_ADMIN_CATEGORIES_GET_BY_ID, {id: id});
        const response = await axios.put(endpoint, formData, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'Content-Type': 'multipart/form-data',
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const deleteById = async (id) => {
    try {
        const endpoint = apiEndpoint(COURSE_ADMIN_CATEGORIES_GET_BY_ID, {id: id});
        const response = await axios.delete(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}