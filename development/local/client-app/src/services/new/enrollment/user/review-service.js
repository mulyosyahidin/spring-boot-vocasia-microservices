import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    ENROLLMENT_STUDENT_COURSE_REVIEW_GET_MY_REVIEW,
    ENROLLMENT_STUDENT_COURSE_REVIEW_POST
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const postCourseReview = async (enrollmentId, data) => {
    try {
        const endpoint = apiEndpoint(ENROLLMENT_STUDENT_COURSE_REVIEW_POST, {id: enrollmentId});
        const response = await axios.post(endpoint, data, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const getMyReview = async (enrollmentId) => {
    try {
        const endpoint = apiEndpoint(ENROLLMENT_STUDENT_COURSE_REVIEW_GET_MY_REVIEW, {id: enrollmentId});
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
