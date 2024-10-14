import {apiEndpoint} from "../../../../utils/new-utils.js";
import {INSTRUCTOR_INSTRUCTOR_PROFILE} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken, instructorAuthData} from "../../../../config/auth.js";

export const findMyProfile = async () => {
    try {
        let endpoint = apiEndpoint(INSTRUCTOR_INSTRUCTOR_PROFILE);
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'X-INSTRUCTOR-ID': instructorAuthData.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}

export const updateMyProfile = async (data) => {
    try {
        let endpoint = apiEndpoint(INSTRUCTOR_INSTRUCTOR_PROFILE);
        const response = await axios.put(endpoint,data, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
                'Content-Type': 'application/form-data',
                'X-INSTRUCTOR-ID': instructorAuthData.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error.response ? error.response.data : new Error('An error occurred');
    }
}