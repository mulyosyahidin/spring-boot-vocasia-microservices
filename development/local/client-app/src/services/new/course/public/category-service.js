import {apiEndpoint} from "../../../../utils/new-utils.js";
import {CATALOG_PUBLIC_CATEGORIES} from "../../../../config/api-endpoint.js";
import axios from "axios";

export const findAll = async (currentPage) => {
    try {
        const endpoint = apiEndpoint(CATALOG_PUBLIC_CATEGORIES);
        const response = await axios.get(endpoint);

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}