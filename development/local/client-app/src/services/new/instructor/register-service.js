import {apiEndpoint} from "../../../utils/new-utils.js";
import {INSTRUCTOR_REGISTER} from "../../../config/api-endpoint.js";
import axios from "axios";

export const registerInstructor = async (data) => {
    try {
        const endpoint = apiEndpoint(INSTRUCTOR_REGISTER);
        const response = await axios.post(endpoint, data);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}