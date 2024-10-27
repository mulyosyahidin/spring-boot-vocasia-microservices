import {apiEndpoint} from "../../../../utils/new-utils.js";
import {ORDER_ADMIN_TRANSACTIONS, ORDER_ADMIN_TRANSACTIONS_FIND_BY_ID} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken} from "../../../../config/auth.js";

export const findAll = async (currentPage) => {
    try {
        let endpoint = `${apiEndpoint(ORDER_ADMIN_TRANSACTIONS)}?page=${currentPage}`;
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findById = async (id) => {
    try {
        let endpoint = apiEndpoint(ORDER_ADMIN_TRANSACTIONS_FIND_BY_ID, {id: id});
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}
