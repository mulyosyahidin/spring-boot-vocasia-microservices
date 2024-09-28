import axios from "axios";
import {AUTH_ACCESS_TOKEN, AUTH_REFRESH_TOKEN} from "../config/consts.js";

export const axiosGet = async ({url, headers = {}}) => {
    console.log(`GET ${url}`);

    const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);
    const refreshToken = localStorage.getItem(AUTH_REFRESH_TOKEN);

    const config = accessToken ? {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        }
    } : {};

    const response = await axios.get(url, config);

    return response.data;
}

export const axiosPost = async ({url, data, headers = {}}) => {
    console.log(`PUT ${url}`);

    const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);
    const refreshToken = localStorage.getItem(AUTH_REFRESH_TOKEN);

    const config = accessToken ? {
        headers: {
            Authorization: `Bearer ${accessToken}`,
            'Content-Type': 'multipart/form-data'
        }
    } : {};

    const response = await axios.post(url, data, config);

    return response.data;
};
