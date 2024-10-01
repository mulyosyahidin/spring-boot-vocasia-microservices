import {apiBaseUrl} from "../../config/consts.js";
import {COURSES_CHAPTERS_LESSONS_INDEX, COURSES_CHAPTERS_LESSONS_STORE} from "../../config/endpoints.js";
import {axiosGet, axiosPost} from "../api.js";

export const getLessonsDataByChapterId = async (courseId, chapterId) => {
    let endpoint = `${apiBaseUrl}${COURSES_CHAPTERS_LESSONS_INDEX}`;

    endpoint = endpoint.replace(':courseId', courseId);
    endpoint = endpoint.replace(':chapterId', chapterId);

    console.log(`[3] getLessonsDataByChapterId: ${endpoint} started...`);
    let response = await axiosGet({ url: endpoint });
    console.log(`[3] getLessonsDataByChapterId: ${endpoint} response: `, response);

    return response;
}

export const store = async (courseId, chapterId, formData) => {
    let endpoint = `${apiBaseUrl}${COURSES_CHAPTERS_LESSONS_STORE}`;

    endpoint = endpoint.replace(':courseId', courseId);
    endpoint = endpoint.replace(':chapterId', chapterId);

    return await axiosPost({
        url: endpoint,
        data: JSON.stringify(formData),
        headers: {'Content-Type': 'application/json'}
    });
}