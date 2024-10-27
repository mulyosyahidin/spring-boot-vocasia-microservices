import {AUTH_ACCESS_TOKEN, AUTH_USER, INSTRUCTOR_AUTH_DATA} from "./consts.js";

export const accessToken = localStorage.getItem(AUTH_ACCESS_TOKEN);
export const instructorAuthData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));
export const authenticatedUser = JSON.parse(localStorage.getItem(AUTH_USER));
