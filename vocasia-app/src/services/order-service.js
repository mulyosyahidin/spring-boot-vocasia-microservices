import {apiBaseUrl, AUTH_ACCESS_TOKEN, AUTH_USER} from "../config/consts.js";
import {axiosGet} from "./api.js";
import {USER_ORDER_GET_DATA, USER_ORDER_PLACE_NEW_ORDER} from "../config/endpoints.js";
import axios from "axios";

export const placeNewOrder = async (cart) => {
   try {
       const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);
       const getUserData = JSON.parse(localStorage.getItem(AUTH_USER));

       const endpoint = `${apiBaseUrl}${USER_ORDER_PLACE_NEW_ORDER}`;

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

       const orderData = {
           user_id: getUserData.id,
           customer: {
               id: getUserData.id,
               name: getUserData.name,
               email: getUserData.email,
           },
           items: orderItems,
       };

       const response = await axios.post(endpoint, orderData, {
           headers: {
               'Authorization': `Bearer ${accessToken}`,
               'Content-Type': 'application/json'
           }
       });

       return response.data.data;
   }
   catch (error) {
       throw error.response ? error.response.data : new Error('An error occurred');
   }
}

export const getOrderData = async (orderId) => {
    let endpoint = `${apiBaseUrl}${USER_ORDER_GET_DATA}`;
    endpoint = endpoint.replace(':orderId', orderId);

    const response = await axiosGet({url: endpoint});
    if (response.success) {
        return response.data;
    }
    else {
        throw new Error(response.message);
    }
}