import {apiBaseUrl, INSTRUCTOR_AUTH_DATA} from "../../config/consts.js";
import {INSTRUCTORS_TRANSACTIONS_GET_ALL, INSTRUCTORS_TRANSACTIONS_GET_BY_INCOME_ID} from "../../config/endpoints.js";
import {axiosGet} from "../api.js";

let getInstructorAuthData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));

export const getAllSaleTransactions = async () => {
    try {
        let endpoint = `${apiBaseUrl}${INSTRUCTORS_TRANSACTIONS_GET_ALL}`;

        const response = await axiosGet({
            url: endpoint,
            headers: {
                'X-INSTRUCTOR-ID': getInstructorAuthData.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error;
    }
}

export const getSaleTransactionById = async (id) => {
    try {
        let endpoint = `${apiBaseUrl}${INSTRUCTORS_TRANSACTIONS_GET_BY_INCOME_ID}`;
        endpoint = endpoint.replace(':id', id);

        const response = await axiosGet({
            url: endpoint
        });

        return response.data;
    } catch (error) {
        throw error;
    }
}