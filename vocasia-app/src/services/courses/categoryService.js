import {_api} from "../../utils/utils.js";
import {endpoints} from "../../config/endpoints.js";
import axios from "axios";

export const getAllCategories = async () => {
    const getAllCategoryEndpoint = _api({endpoint: endpoints.courses.categories.getAll});
    const response = await axios.get(getAllCategoryEndpoint);

    const categories = response.data.data;

    return categories;
}