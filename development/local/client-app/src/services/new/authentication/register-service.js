import {apiEndpoint} from "../../../utils/new-utils.js";
import {AUTHENTICATION_REGISTER} from "../../../config/api-endpoint.js";
import axios from "axios";

export const register = async (data) => {
    try {
        const endpoint = apiEndpoint(AUTHENTICATION_REGISTER);
        const response = await axios.post(endpoint, data);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}
