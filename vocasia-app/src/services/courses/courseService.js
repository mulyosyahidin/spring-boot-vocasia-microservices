import {endpoints} from "../../config/endpoints.js";
import axios from "axios";
import {_api} from "../../utils/utils.js";
import {axiosGet, axiosPost} from "../api.js";

export const show = async (id) => {
    const showCourseEndpoint = _api({endpoint: endpoints.courses.show, replaces: [id]});
    const response = await axiosGet({url: showCourseEndpoint});

    return response;
}

export const store = async (formData) => {
    const storeEndpoint = _api(endpoints.courses.store);
    const response = await axiosPost({url: storeEndpoint, data: formData});

    return response;
}

export const uploadThumbnail = async ({ id, formData }) => {
    const uploadThumbnailEndpoint = _api({
        endpoint: endpoints.courses.uploadThumbnail,
        replaces: [id]
    });

    const response = await axiosPost({url:uploadThumbnailEndpoint, data: formData, headers: {'Content-Type': 'multipart/form-data'}});

    return response;
};

export const index = async () => {
    const indexEndpoint = _api({endpoint: endpoints.courses.index});
    const response = await axiosGet({url: indexEndpoint});

    return response;
}