import {
    QNA_STUDENT_IS_I_ASK_THIS_LESSON, QNA_STUDENT_QUESTIONS_ANSWERS_GET_ALL, QNA_STUDENT_QUESTIONS_ANSWERS_STORE,
    QNA_STUDENT_QUESTIONS_GET_ALL,
    QNA_STUDENT_QUESTIONS_STORE
} from "../../../../config/api-endpoint.js";
import {apiEndpoint} from "../../../../utils/new-utils.js";
import axios from "axios";
import {accessToken, authenticatedUser} from "../../../../config/auth.js";

export const findAllByCourseIdAndLessonId = async (courseId, lessonId, currentPage) => {
    try {
        let endpoint = `${apiEndpoint(QNA_STUDENT_QUESTIONS_GET_ALL, {courseId: courseId, lessonId: lessonId})}?page=${currentPage}`;
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

export const isStudentAskThisLesson = async (lessonId) => {
    try {
        let endpoint = apiEndpoint(QNA_STUDENT_IS_I_ASK_THIS_LESSON, {lessonId: lessonId});
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

export const saveQuestion = async (courseId, lessonId, data) => {
    try {
        let endpoint = apiEndpoint(QNA_STUDENT_QUESTIONS_STORE, {courseId: courseId, lessonId: lessonId});
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

export const findAllQnaAnswers = async (lessonId, qnaId, currentPage) => {
    try {
        let endpoint = `${apiEndpoint(QNA_STUDENT_QUESTIONS_ANSWERS_GET_ALL, {lessonId: lessonId, qnaId: qnaId})}?page=${currentPage}`;
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

export const saveAnswer = async (lessonId, qnaId, data) => {
    try {
        let endpoint = apiEndpoint(QNA_STUDENT_QUESTIONS_ANSWERS_STORE, {qnaId: qnaId, lessonId: lessonId});
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
