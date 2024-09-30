import axios from "axios";
import {COURSES_CATEGORIES_GETALL} from "../../config/endpoints.js";
import {apiBaseUrl} from "../../config/consts.js";

export const getAllCategories = async () => {
    const getAllCategoryEndpoint = `${apiBaseUrl}${COURSES_CATEGORIES_GETALL}`;
    const response = await axios.get(getAllCategoryEndpoint);

    return response.data.data;
}