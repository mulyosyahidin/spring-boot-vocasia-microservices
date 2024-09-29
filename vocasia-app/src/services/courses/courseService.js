import axios from "axios";
import {_api} from "../../utils/utils.js";
import {axiosGet, axiosPost} from "../api.js";
import {COURSES_INDEX, COURSES_SHOW, COURSES_STORE, COURSES_UPLOAD_THUMBNAIL} from "../../config/endpoints.js";

export const show = async (id) => {
    const showCourseEndpoint = _api({endpoint: COURSES_SHOW, replaces: [id]});
    const response = await axiosGet({url: showCourseEndpoint});

    return response;
}

export const store = async (formData) => {
    const storeEndpoint = _api(COURSES_STORE);
    const response = await axiosPost({url: storeEndpoint, data: formData});

    return response;
}

export const uploadThumbnail = async ({ id, formData }) => {
    const uploadThumbnailEndpoint = _api({
        endpoint: COURSES_UPLOAD_THUMBNAIL,
        replaces: [id]
    });

    const response = await axiosPost({url:uploadThumbnailEndpoint, data: formData, headers: {'Content-Type': 'multipart/form-data'}});

    return response;
};

export const index = async () => {
    const indexEndpoint = _api({endpoint: COURSES_INDEX});
    const response = await axiosGet({url: indexEndpoint});

    return response;
}