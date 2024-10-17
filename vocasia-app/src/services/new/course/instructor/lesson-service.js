import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS,
    COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_CREATE,
    COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_DELETE,
    COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_FIND_BY_ID, COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_UPDATE,
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAll = async (courseId, chapterId) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS, {
            courseId: courseId,
            chapterId: chapterId
        });
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

export const createLesson = async (courseId, chapterId, data) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_CREATE, {
            courseId: courseId,
            chapterId: chapterId
        });
        const response = await axios.post(endpoint, data, {
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

export const findById = async (courseId, chapterId, lessonId) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_FIND_BY_ID, {
            courseId: courseId,
            chapterId: chapterId,
            lessonId: lessonId
        });
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

export const deleteById = async (courseId, chapterId, lessonId) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_DELETE, {
            courseId: courseId,
            chapterId: chapterId,
            lessonId: lessonId
        });
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

export const updateLesson = async (courseId, chapterId, lessonId, data) => {
    try {
        const endpoint = apiEndpoint(COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_UPDATE, {
            courseId: courseId,
            chapterId: chapterId,
            lessonId: lessonId
        });
        const response = await axios.put(endpoint, data, {
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