import {
    COURSE_INSTRUCTOR_COURSES,
    COURSE_INSTRUCTOR_COURSES_GET_ALL,
    COURSE_INSTRUCTOR_COURSES_GET_BY_ID,
    COURSE_INSTRUCTOR_COURSES_GET_DRAFT,
    COURSE_INSTRUCTOR_COURSES_GET_PUBLISHED, COURSE_INSTRUCTOR_COURSES_PUBLISH,
    COURSE_INSTRUCTOR_COURSES_THUMBNAIL
} from "../../../../config/api-endpoint.js";
import {apiEndpoint} from "../../../../utils/new-utils.js";
import axios from "axios";
import {accessToken, instructorAuthData} from "../../../../config/auth.js";

export const findAll = async (currentPage, status) => {
    try {
        let endpoint;
        if (status === 'published') {
            endpoint = `${apiEndpoint(COURSE_INSTRUCTOR_COURSES_GET_PUBLISHED)}?page=${currentPage}`;
        } else if (status === 'draft') {
            endpoint = `${apiEndpoint(COURSE_INSTRUCTOR_COURSES_GET_DRAFT)}?page=${currentPage}`;
        } else {
            endpoint = `${apiEndpoint(COURSE_INSTRUCTOR_COURSES_GET_ALL)}?page=${currentPage}`;
        }

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

export const createCourse = async (data) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES);
        const response = await axios.post(endpoint, data, {
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
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_GET_BY_ID, {id: id});
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

export const updateThumbnail = async (id, formData) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_THUMBNAIL, {id: id});
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

export const updateCourse = async (id, data) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_GET_BY_ID, {id: id});
        const response = await axios.put(endpoint, data, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const publishById = async (id) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_PUBLISH, {id: id});
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
