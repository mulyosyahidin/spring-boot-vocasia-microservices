import { atom } from "recoil";
import {AUTH_ACCESS_TOKEN, AUTH_REFRESH_TOKEN, AUTH_USER} from "../../../config/consts.js";

export const authAtom = atom({
    key: 'authAtom',
    default: {
        user: JSON.parse(localStorage.getItem(AUTH_USER)) || {},
        accessToken: localStorage.getItem(AUTH_ACCESS_TOKEN) || '',
        refreshToken: localStorage.getItem(AUTH_REFRESH_TOKEN) || '',
    },
    effects_UNSTABLE: [
        ({ onSet }) => {
            onSet((newValue) => {
                localStorage.setItem(AUTH_USER, JSON.stringify(newValue.user));
                localStorage.setItem(AUTH_ACCESS_TOKEN, newValue.accessToken);
                localStorage.setItem(AUTH_REFRESH_TOKEN, newValue.refreshToken);
            });
        },
    ],
});
