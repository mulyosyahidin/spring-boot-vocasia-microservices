import {axiosDelete, axiosGet, axiosPost, axiosPut} from "../api.js";
import {
    COURSES_CHAPTERS_CREATE, COURSES_CHAPTERS_DELETE,
    COURSES_CHAPTERS_GETALL, COURSES_CHAPTERS_SHOW, COURSES_CHAPTERS_UPDATE,
    COURSES_CREATE_NEW_COURSE,
    COURSES_GET_DRAFT,
    COURSES_SHOW,
    COURSES_UPDATE,
    COURSES_UPLOAD_THUMBNAIL
} from "../../config/endpoints.js";
import {apiBaseUrl, INSTRUCTOR_AUTH_DATA} from "../../config/consts.js";

export const show = async (id) => {
    let showCourseEndpoint = `${apiBaseUrl}${COURSES_SHOW}`;
    showCourseEndpoint = showCourseEndpoint.replace(':id', id);

    const response = await axiosGet({url: showCourseEndpoint});

    return response;
}

export const store = async (formData) => {
    const storeEndpoint = `${apiBaseUrl}${COURSES_CREATE_NEW_COURSE}`;

    return await axiosPost({
        url: storeEndpoint,
        data: JSON.stringify(formData),
        headers: {'Content-Type': 'application/json'}
    });
}

export const update = async (id, formData) => {
    let storeEndpoint = `${apiBaseUrl}${COURSES_UPDATE}`;
    storeEndpoint = storeEndpoint.replace(':id', id);

    return await axiosPut({
        url: storeEndpoint,
        data: JSON.stringify(formData),
        headers: {'Content-Type': 'application/json'}
    });
}

export const uploadThumbnail = async ({id, formData}) => {
    let uploadThumbnailEndpoint = `${apiBaseUrl}${COURSES_UPLOAD_THUMBNAIL}`;
    uploadThumbnailEndpoint = uploadThumbnailEndpoint.replace(':id', id);

    return await axiosPut({url: uploadThumbnailEndpoint, data: formData});
};

export const getDataByStatus = async (status) => {
    let endpoint = '';

    if (status === 'draft') {
        endpoint = `${apiBaseUrl}${COURSES_GET_DRAFT}`;
    }

    const instructorData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));
    return await axiosGet({url: endpoint, headers: {'X-INSTRUCTOR-ID': instructorData.id}});
}

export const getAllChaptersByCourseId = async (courseId) => {
    let endpoint = `${apiBaseUrl}${COURSES_CHAPTERS_GETALL}`;
    endpoint = endpoint.replace(':courseId', courseId)

    return await axiosGet({url: endpoint});
}

export const addNewChapter = async (courseId, formData) => {
    let endpoint = `${apiBaseUrl}${COURSES_CHAPTERS_CREATE}`;
    endpoint = endpoint.replace(':courseId', courseId);

    return await axiosPost({
        url: endpoint,
        data: JSON.stringify(formData),
        headers: {'Content-Type': 'application/json'}
    });
}

export const editChapterData = async (courseId, chapterId, formData) => {
    let endpoint = `${apiBaseUrl}${COURSES_CHAPTERS_UPDATE}`;
    endpoint = endpoint.replace(':courseId', courseId);
    endpoint = endpoint.replace(':chapterId', chapterId);

    return await axiosPut({
        url: endpoint,
        data: JSON.stringify(formData),
        headers: {'Content-Type': 'application/json'}
    });
}

export const deleteChapterData = async (courseId, chapterId) => {
    let endpoint = `${apiBaseUrl}${COURSES_CHAPTERS_DELETE}`;
    endpoint = endpoint.replace(':courseId', courseId);
    endpoint = endpoint.replace(':chapterId', chapterId);

    return await axiosDelete({
        url: endpoint,
        data: JSON.stringify({isDeleted: true}),
        headers: {'Content-Type': 'application/json'}
    });
}

export const getChapterDataById = async (courseId, chapterId) => {
    let endpoint = `${apiBaseUrl}${COURSES_CHAPTERS_SHOW}`;
    endpoint = endpoint.replace(':chapterId', chapterId).replace(':courseId', courseId);

    return await axiosGet({url: endpoint});
}