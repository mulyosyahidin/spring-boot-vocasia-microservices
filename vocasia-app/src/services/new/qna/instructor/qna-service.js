import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    QNA_INSTRUCTOR_COURSES_QUESTIONS,
    QNA_INSTRUCTOR_COURSES_QUESTIONS_QNA,
    QNA_INSTRUCTOR_COURSES_QUESTIONS_QNA_STORE,
    QNA_STUDENT_QUESTIONS_ANSWERS_STORE
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken, authenticatedUser} from "../../../../config/auth.js";

export const findAllByCourseId = async (courseId, currentPage) => {
    try {
        let endpoint = `${apiEndpoint(QNA_INSTRUCTOR_COURSES_QUESTIONS, {courseId: courseId})}?page=${currentPage}`;
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

export const findAllCourseQna = async (courseId, qnaId, currentPage) => {
    try {
        let endpoint = `${apiEndpoint(QNA_INSTRUCTOR_COURSES_QUESTIONS_QNA, {courseId: courseId, qnaId: qnaId})}?page=${currentPage}`;
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

export const saveAnswer = async (courseId, qnaId, data) => {
    try {
        let endpoint = apiEndpoint(QNA_INSTRUCTOR_COURSES_QUESTIONS_QNA_STORE, {qnaId: qnaId, courseId: courseId});
        const response = await axios.post(endpoint, data, {
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
