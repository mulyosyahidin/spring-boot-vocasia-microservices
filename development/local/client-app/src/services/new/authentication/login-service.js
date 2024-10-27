import {apiEndpoint} from "../../../utils/new-utils.js";
import {AUTHENTICATION_LOGIN} from "../../../config/api-endpoint.js";
import axios from "axios";

export const loginWithEmailAndPassword = async (email, password) => {
    try {
        const endpoint = apiEndpoint(AUTHENTICATION_LOGIN);
        const data = {
            email,
            password,
        };

        const response = await axios.post(endpoint, data);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}
