import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    ENROLLMENT_STUDENT_COURSES,
    ENROLLMENT_STUDENT_COURSES_GET_BY_ID, ENROLLMENT_STUDENT_PROGRESS_COMPLETE_LESSON,
    ENROLLMENT_STUDENT_PROGRESS_IS_LESSON_COMPLETE, ENROLLMENT_STUDENT_PROGRESS_SET_LAST_LESSON_ID,
    ENROLLMENT_STUDENT_PROGRESS_START_LESSON,
    ENROLLMENT_USER_COURSES_IS_USER_ENROLLED
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken, authenticatedUser} from "../../../../config/auth.js";

export const findIsUserHasEnrollThisCourse = async (id) => {
    try {
        const endpoint = apiEndpoint(ENROLLMENT_USER_COURSES_IS_USER_ENROLLED, {id: id});
        const response = await axios.get(endpoint, {
            headers: {
                'X-USER-ID': authenticatedUser.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findAllMyEnrollments = async (currentPage) => {
    try {
        const endpoint = `${apiEndpoint(ENROLLMENT_STUDENT_COURSES)}?page=${currentPage}`;
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-USER-ID': authenticatedUser.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findEnrollmentDataById = async (id) => {
    try {
        const endpoint = apiEndpoint(ENROLLMENT_STUDENT_COURSES_GET_BY_ID, {id: id});
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-USER-ID': authenticatedUser.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const startLesson = async (enrollmentId, lessonId) => {
    try {
        const endpoint = apiEndpoint(ENROLLMENT_STUDENT_PROGRESS_START_LESSON, {enrollmentId: enrollmentId, lessonId: lessonId});
        const response = await axios.post(endpoint, {},{
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const completeEnrollmentLesson = async (enrollmentId, lessonId, totalLesson) => {
    try {
        const endpoint = apiEndpoint(ENROLLMENT_STUDENT_PROGRESS_COMPLETE_LESSON, {enrollmentId: enrollmentId, lessonId: lessonId});
        const response = await axios.post(endpoint, {
            total_lesson: totalLesson,
        },{
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const isLessonCompleted = async (enrollmentId, lessonId) => {
    try {
        const endpoint = apiEndpoint(ENROLLMENT_STUDENT_PROGRESS_IS_LESSON_COMPLETE, {enrollmentId: enrollmentId, lessonId: lessonId});
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

export const setLastAccesLesson = async (enrollmentId, lessonId) => {
    try {
        const endpoint = apiEndpoint(ENROLLMENT_STUDENT_PROGRESS_SET_LAST_LESSON_ID, {enrollmentId: enrollmentId});
        const response = await axios.post(endpoint, {
            lesson_id: lessonId
        },{
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}