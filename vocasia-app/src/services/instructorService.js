import axios from 'axios';
import {INSTRUCTOR_REGISTER} from "../config/endpoints.js";
import {apiBaseUrl} from "../config/consts.js";

export const registerInstructor = async (instructorData) => {
    const registerEndpoint = `${apiBaseUrl}${INSTRUCTOR_REGISTER}`;
    const response = await axios.post(registerEndpoint, instructorData);

    return response.data;
};
