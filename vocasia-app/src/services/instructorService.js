import axios from 'axios';
import { endpoints } from "../config/endpoints.js";
import { _api } from "../utils/utils.js";

export const registerInstructor = async (instructorData) => {
    const registerEndpoint = _api(endpoints.instructors.register);
    const response = await axios.post(registerEndpoint, instructorData);

    return response.data;
};
