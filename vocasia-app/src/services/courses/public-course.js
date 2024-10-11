import {axiosGet} from "../api.js";
import {apiBaseUrl} from "../../config/consts.js";
import {
    PUBLIC_COURSES_GET_CATEGORIES,
    PUBLIC_COURSES_GET_EDITOR_CHOICES,
    PUBLIC_COURSES_SHOW_CHAPTERS,
    PUBLIC_COURSES_SHOW_OVERVIEW
} from "../../config/endpoints.js";

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