import {apiBaseUrl, AUTH_USER} from "../../config/consts.js";
import {
    PUBLIC_COURSES_SHOW_CHAPTERS,
    USER_ENROLLMENT_GET_ALL,
    USER_ENROLLMENT_GET_BY_ID
} from "../../config/endpoints.js";
import {axiosGet} from "../api.js";

const getUserData = JSON.parse(localStorage.getItem(AUTH_USER));

export const getAllEnrollments = async () => {
    let endpoint = `${apiBaseUrl}${USER_ENROLLMENT_GET_ALL}`;

    console.log(getUserData)

    const response = await axiosGet({
        url: endpoint,
        headers: {
            'X-USER-ID': getUserData.id,
        }
    });

    if (response.success) {
        return response.data.enrollments;
    } else {
        throw new Error(response.message);
    }
}

export const getEnrollmentDataById = async (enrollmentId) => {
    let endpoint = `${apiBaseUrl}${USER_ENROLLMENT_GET_BY_ID}`;
    endpoint = endpoint.replace(':enrollmentId', enrollmentId);

    const response = await axiosGet({
        url: endpoint,
        headers: {
            'X-USER-ID': getUserData.id,
        }
    });

    if (response.success) {
        return response.data;
    } else {
        throw new Error(response.message);
    }
}

export const getCourseLessonDataByEnrollment = async (slug, courseId) => {
    let endpoint = `${apiBaseUrl}${PUBLIC_COURSES_SHOW_CHAPTERS}`;
    endpoint = endpoint.replace(':slug', slug);
    endpoint = endpoint.replace(':courseId', courseId);

    const response = await axiosGet({
        url: endpoint,
        includeAuthentication: false,
    });

    if (response.success) {
        return response.data.chapters;
    } else {
        throw new Error(response.message);
    }
}
