import axios from "axios";
import {
    COURSES_CATEGORIES_ADMIN_DELETE,
    COURSES_CATEGORIES_ADMIN_GETALL,
    COURSES_CATEGORIES_ADMIN_SHOW,
    COURSES_CATEGORIES_ADMIN_STORE, COURSES_CATEGORIES_ADMIN_UPDATE
} from "../../config/endpoints.js";
import {apiBaseUrl, AUTH_ACCESS_TOKEN} from "../../config/consts.js";

const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);

export const getAdminCategories = async () => {
    const getAllCategoryEndpoint = `${apiBaseUrl}${COURSES_CATEGORIES_ADMIN_GETALL}`;
    const response = await axios.get(getAllCategoryEndpoint, {
        headers: {
            'Authorization': `Bearer ${accessToken}`,
        },
    });

    return response.data.data.categories;
}

export const getCategoryById = async (id) => {
    let getCategoryEndpoint = `${apiBaseUrl}${COURSES_CATEGORIES_ADMIN_SHOW}`;
    getCategoryEndpoint = getCategoryEndpoint.replace(':categoryId', id);

    const response = await axios.get(getCategoryEndpoint, {
        headers: {
            'Authorization': `Bearer ${accessToken}`,
        },
    });

    return response.data.data.category;
}

export const createCategory = async (formData) => {
    const createCategoryEndpoint = `${apiBaseUrl}${COURSES_CATEGORIES_ADMIN_STORE}`;

    try {
        const response = await axios.post(createCategoryEndpoint, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                'Authorization': `Bearer ${accessToken}`,
            },
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const updateCategory = async (id, formData) => {
    let updateCategoryEndpoint = `${apiBaseUrl}${COURSES_CATEGORIES_ADMIN_UPDATE}`;
    updateCategoryEndpoint = updateCategoryEndpoint.replace(':categoryId', id);

    try {
        const response = await axios.put(updateCategoryEndpoint, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                'Authorization': `Bearer ${accessToken}`,
            },
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const deleteCategoryById = async (id) => {
    let deleteCategoryEndpoint = `${apiBaseUrl}${COURSES_CATEGORIES_ADMIN_DELETE}`;
    deleteCategoryEndpoint = deleteCategoryEndpoint.replace(':categoryId', id);

    try {
        const response = await axios.delete(deleteCategoryEndpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            },
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}