import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    CATALOG_PUBLIC_COURSES_LATESTS,
    COURSE_PUBLIC_COURSES_EDITOR_CHOICES,
    COURSE_PUBLIC_COURSES_GET_BY_SLUG_ID, COURSE_PUBLIC_COURSES_GET_CONTENTS_BY_SLUG_ID
} from "../../../../config/api-endpoint.js";
import axios from "axios";

export const findEditorChoices = async (currentPage) => {
    try {
        const endpoint = apiEndpoint(COURSE_PUBLIC_COURSES_EDITOR_CHOICES);
        const response = await axios.get(endpoint);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findLatestCourses = async (currentPage) => {
    try {
        const endpoint = apiEndpoint(CATALOG_PUBLIC_COURSES_LATESTS);
        const response = await axios.get(endpoint);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findByIdAndSlug = async (slug, id) => {
    try {
        const endpoint = apiEndpoint(COURSE_PUBLIC_COURSES_GET_BY_SLUG_ID, {slug: slug, id: id});
        const response = await axios.get(endpoint);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findContentsByIdAndSlug = async (slug, id) => {
    try {
        const endpoint = apiEndpoint(COURSE_PUBLIC_COURSES_GET_CONTENTS_BY_SLUG_ID, {slug: slug, id: id});
        const response = await axios.get(endpoint);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}
