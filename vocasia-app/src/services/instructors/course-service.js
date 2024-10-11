import {apiBaseUrl, INSTRUCTOR_AUTH_DATA} from "../../config/consts.js";
import {INSTRUCTORS_COURSE_GET_STUDENTS} from "../../config/endpoints.js";
import {axiosGet} from "../api.js";

let getInstructorAuthData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));

export const getCourseStudents = async (courseId) => {
    try {
        let endpoint = `${apiBaseUrl}${INSTRUCTORS_COURSE_GET_STUDENTS}`;
        endpoint = endpoint.replace(':courseId', courseId);

        const response = await axiosGet({url: endpoint});
        return response.data;
    }
    catch (error) {
        throw error;
    }
}