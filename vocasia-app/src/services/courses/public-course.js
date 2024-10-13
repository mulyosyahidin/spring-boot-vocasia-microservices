import {axiosGet} from "../api.js";
import {apiBaseUrl, AUTH_ACCESS_TOKEN, AUTH_USER} from "../../config/consts.js";
import {
    ENROLLMENT_COURSES_IS_USER_ENROLLED,
    FINANCE_ADMIN_WITHDRAWAL_REQUEST_PROCESS,
    PUBLIC_COURSES_GET_CATEGORIES,
    PUBLIC_COURSES_GET_EDITOR_CHOICES,
    PUBLIC_COURSES_SHOW_CHAPTERS,
    PUBLIC_COURSES_SHOW_OVERVIEW
} from "../../config/endpoints.js";
import axios from "axios";

export const getSliderCategories = async () => {
    let endpoint = `${apiBaseUrl}${PUBLIC_COURSES_GET_CATEGORIES}`;

    let response = await axiosGet({url: endpoint, includeAuthentication: false});

    if (response.success) {
        return response.data.categories;
    } else {
        return [];
    }
}

export const getEditorChoiceCourses = async () => {
    let endpoint = `${apiBaseUrl}${PUBLIC_COURSES_GET_EDITOR_CHOICES}`;

    let response = await axiosGet({url: endpoint, includeAuthentication: false});
    console.log(response);

    if (response.success) {
        return response.data.courses;
    } else {
        return [];
    }
}

export const getOverview = async (slug, id) => {
    let endpoint = `${apiBaseUrl}${PUBLIC_COURSES_SHOW_OVERVIEW}`;
    endpoint = endpoint.replace(':slug', slug);
    endpoint = endpoint.replace(':courseId', id);

    let response = await axiosGet({url: endpoint, includeAuthentication: false});

    if (response.success) {
        return response.data;
    } else {
        return {};
    }
}

export const getCourseContents = async (slug, id) => {
    let endpoint = `${apiBaseUrl}${PUBLIC_COURSES_SHOW_CHAPTERS}`;
    endpoint = endpoint.replace(':slug', slug);
    endpoint = endpoint.replace(':courseId', id);

    let response = await axiosGet({url: endpoint, includeAuthentication: false});

    if (response.success) {
        return response.data.chapters;
    } else {
        return [];
    }
}

export const isUserEnrollThisCourse = async (courseId) => {
    try {
        const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);
        const getUserData = JSON.parse(localStorage.getItem(AUTH_USER));

        let endpoint = `${apiBaseUrl}${ENROLLMENT_COURSES_IS_USER_ENROLLED}`;
        endpoint = endpoint.replace(':courseId', courseId);

        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-USER-ID': getUserData.id,
            },
        });

        return response.data;
    }
    catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}