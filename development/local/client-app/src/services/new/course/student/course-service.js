import {apiEndpoint} from "../../../../utils/new-utils.js";
import {COURSE_STUDENT_COURSE_CONTENTS, COURSE_STUDENT_GET_BY_ID} from "../../../../config/api-endpoint.js";
import axios from "axios";

export const findById = async (id) => {
    try {
        const endpoint = apiEndpoint(COURSE_STUDENT_GET_BY_ID, {id: id});
        const response = await axios.get(endpoint);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findCourseContents = async (id) => {
    try {
        const endpoint = apiEndpoint(COURSE_STUDENT_COURSE_CONTENTS, {id: id});
        const response = await axios.get(endpoint);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}