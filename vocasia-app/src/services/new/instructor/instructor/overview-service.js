import {apiEndpoint} from "../../../../utils/new-utils.js";
import {INSTRUCTOR_OVERVIEW} from "../../../../config/api-endpoint.js";
import axios from "axios";
import {accessToken, instructorAuthData} from "../../../../config/auth.js";

export const getOverview = async () => {
    try {
        let endpoint = apiEndpoint(INSTRUCTOR_OVERVIEW);
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