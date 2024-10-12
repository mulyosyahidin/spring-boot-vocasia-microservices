import {apiBaseUrl, INSTRUCTOR_AUTH_DATA} from "../../config/consts.js";
import {axiosGet} from "../api.js";
import {INSTRUCTORS_STUDENTS_GET_ALL, INSTRUCTORS_STUDENTS_GET_BY_ID} from "../../config/endpoints.js";

const instructorAuthData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));

export const getAllMyStudents = async () => {
    try {
        let endpoint = `${apiBaseUrl}${INSTRUCTORS_STUDENTS_GET_ALL}`;

        const response = await axiosGet({
            url: endpoint,
            headers: {
                'X-INSTRUCTOR-ID': instructorAuthData.id,
            }
        });

        return response.data;
    } catch (error) {
        throw error;
    }
}

export const getStudentByInstructorStudentId = async (instructorStudentId) => {
    try {
        let endpoint = `${apiBaseUrl}${INSTRUCTORS_STUDENTS_GET_BY_ID}`;
        endpoint = endpoint.replace(':studentId', instructorStudentId);

        const response = await axiosGet({
            url: endpoint
        });
        return response.data;
    } catch (error) {
        throw error;
    }
}