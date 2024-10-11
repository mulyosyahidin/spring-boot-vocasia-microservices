import axios from "axios";
import {AUTH_ACCESS_TOKEN} from "../config/consts.js";

export const axiosGet = async ({url, headers = {}, includeAuthentication = true}) => {
    const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);

    const config = includeAuthentication ? {
        headers: {
            ...headers,
            ...(accessToken ? {Authorization: `Bearer ${accessToken}`} : {}),
        }
    } : {
        headers: {
            ...headers,
        }
    };

    const response = await axios.get(url, config);

    return response.data;
}

export const axiosPost = async ({url, data, headers = {}}) => {
    const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);

    const config = {
        method: 'POST',
        headers: {
            ...headers,
            ...(accessToken ? {Authorization: `Bearer ${accessToken}`} : {}),
        },
        body: data,
    };

    try {
        const response = await fetch(url, config);

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(`Error ${response.status}: ${errorData.message || 'Something went wrong'}`);
        }

        const result = await response.json();
        return result;
    } catch (error) {
        console.error('Error during fetch POST:', error.message);
        throw error;
    }
};

export const axiosPut = async ({url, data, headers = {}}) => {
    const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);

    const config = {
        method: 'PUT',
        headers: {
            ...headers,
            ...(accessToken ? {Authorization: `Bearer ${accessToken}`} : {}),
        },
        body: data,
    };

    try {
        const response = await fetch(url, config);

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(`Error ${response.status}: ${errorData.message || 'Something went wrong'}`);
        }

        const result = await response.json();
        return result;
    } catch (error) {
        console.error('Error during fetch PUT:', error.message);
        throw error; // Anda bisa mengembalikan error ini atau melempar kembali
    }
}

// axiosDelete
export const axiosDelete = async ({url, headers = {}}) => {
    const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);

    const config = {
        method: 'DELETE',
        headers: {
            ...headers,
            ...(accessToken ? {Authorization: `Bearer ${accessToken}`} : {}),
        },
    };

    try {
        const response = await fetch(url, config);

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(`Error ${response.status}: ${errorData.message || 'Something went wrong'}`);
        }

        const result = await response.json();
        return result;
    } catch (error) {
        console.error('Error during fetch DELETE:', error.message);
        throw error;
    }
}