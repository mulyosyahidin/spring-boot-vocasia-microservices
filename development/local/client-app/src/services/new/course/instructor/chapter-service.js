import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    COURSE_INSTRUCTOR_COURSES_CHAPTERS,
    COURSE_INSTRUCTOR_COURSES_DELETE_BY_ID, COURSE_INSTRUCTOR_COURSES_FIND_BY_ID
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAll = async (courseId) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_CHAPTERS, {id: courseId});
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

export const createChapter = async (id, data) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_CHAPTERS, {id: id});
        const response = await axios.post(endpoint, data, {
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findById = async (courseId, chapterId) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_FIND_BY_ID, {id: courseId, chapterId: chapterId});
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


export const updateChapter = async (courseId, chapterId, data) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_FIND_BY_ID, {id: courseId, chapterId: chapterId});
        const response = await axios.put(endpoint, data, {
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const deleteById = async (courseId, chapterId) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_DELETE_BY_ID, {id: courseId, chapterId: chapterId});
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
