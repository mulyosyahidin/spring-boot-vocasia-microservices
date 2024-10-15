import {apiEndpoint} from "../../../../utils/new-utils.js";
import {
    ORDER_STUDENT_ORDERS,
    ORDER_STUDENT_ORDERS_GET_BY_ID,
    ORDER_STUDENT_PLACE_NEW_ORDER
} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken, authenticatedUser} from "../../../../config/auth.js";

export const placeNewOrder = async (cart) => {
    try {
        const orderItems = [];

        for (const item of cart) {
            orderItems.push({
                course_id: item.id,
                course_instructor_id: item.instructor_id,
                course_title: item.title,
                course_slug: item.slug,
                course_featured_picture_url: item.featured_picture_url,
                course_price: item.price,
                course_is_free: item.is_free,
                course_is_discount: item.is_discount,
                course_discount: item.discount,
            });
        }

        const data = {
            customer: {
                id: authenticatedUser.id,
                name: authenticatedUser.name,
                email: authenticatedUser.email,
            },
            items: orderItems,
        };

        let endpoint = apiEndpoint(ORDER_STUDENT_PLACE_NEW_ORDER);
        const response = await axios.post(endpoint, data, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-USER-ID': authenticatedUser.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findById = async (id) => {
    try {
        let endpoint = apiEndpoint(ORDER_STUDENT_ORDERS_GET_BY_ID, {id: id});
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const findAll = async (currentPage) => {
    try {
        let endpoint = `${apiEndpoint(ORDER_STUDENT_ORDERS)}?page=${currentPage}`;
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-USER-ID': authenticatedUser.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}