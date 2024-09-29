import {_api} from "../../utils/utils.js";
import axios from "axios";
import {COURSES_CATEGORIES_GETALL} from "../../config/endpoints.js";

export const getAllCategories = async () => {
    const getAllCategoryEndpoint = _api({endpoint: COURSES_CATEGORIES_GETALL});
    const response = await axios.get(getAllCategoryEndpoint);

    const categories = response.data.data;

    return categories;
}