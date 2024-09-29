import axios from 'axios';
import {AUTH_LOGIN} from "../config/endpoints.js";
import { _api } from "../utils/utils.js";
import {apiBaseUrl} from "../config/consts.js";

export const login = async (loginData) => {
    const loginEndpoint = `${apiBaseUrl}${AUTH_LOGIN}`
    const response = await axios.post(loginEndpoint, loginData);

    return response.data;
};
