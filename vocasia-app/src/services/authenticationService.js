import axios from 'axios';
import {AUTH_LOGIN, AUTH_REGISTER} from "../config/endpoints.js";
import {apiBaseUrl, AUTH_ACCESS_TOKEN} from "../config/consts.js";

export const login = async (loginData) => {
    const loginEndpoint = `${apiBaseUrl}${AUTH_LOGIN}`
    const response = await axios.post(loginEndpoint, loginData);

    return response.data;
};

export const register = async (registerData) => {
    const registerEndpoint = `${apiBaseUrl}${AUTH_REGISTER}`
    const response = await axios.post(registerEndpoint, registerData);

    return response.data;
}