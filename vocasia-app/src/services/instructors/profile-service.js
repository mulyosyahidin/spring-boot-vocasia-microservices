import {axiosGet, axiosPut} from "../api.js";
import {apiBaseUrl, INSTRUCTOR_AUTH_DATA} from "../../config/consts.js";
import {INSTRUCTORS_GET_PROFILE, INSTRUCTORS_UPDATE_PROFILE} from "../../config/endpoints.js";

let getInstructorAuthData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));

export const getInstructorProfile = async () => {
    try {
        let endpoint = `${apiBaseUrl}${INSTRUCTORS_GET_PROFILE}`;
        endpoint = endpoint.replace(':instructorId', getInstructorAuthData.id);

        const response = await axiosGet({url: endpoint});
        return response.data.instructor;
    } catch (error) {
        throw error;
    }
}

export const updateInstructorProfile = async (formData) => {
    try {
        let endpoint = `${apiBaseUrl}${INSTRUCTORS_UPDATE_PROFILE}`;

        const response = await axiosPut({url: endpoint, data: formData, headers: {'X-INSTRUCTOR-ID': getInstructorAuthData.id}});
        return response.data;
    }
    catch (error) {
        throw error;
    }
}