import axios from 'axios';
import { endpoints } from "../config/endpoints.js";
import { _api } from "../utils/utils.js";

export const login = async (loginData) => {
    const loginEndpoint = _api({endpoint: endpoints.auth.login});
    const response = await axios.post(loginEndpoint, loginData);

    return response.data;
};
