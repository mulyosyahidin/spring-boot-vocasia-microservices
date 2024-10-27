import React, {useEffect} from 'react';
import {useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";

import {authAtom} from "../../states/recoil/Atoms/Auth.jsx";
import {AUTH_ACCESS_TOKEN, AUTH_REFRESH_TOKEN, AUTH_USER, INSTRUCTOR_AUTH_DATA} from "../../config/consts.js";

export const Logout = () => {
    const [authState, setAuthState] = useRecoilState(authAtom);
    const navigate = useNavigate();

    useEffect(() => {
        localStorage.removeItem(AUTH_USER);
        localStorage.removeItem(AUTH_ACCESS_TOKEN);
        localStorage.removeItem(AUTH_REFRESH_TOKEN);
        localStorage.removeItem(INSTRUCTOR_AUTH_DATA);

        setAuthState({
            user: null,
            accessToken: null,
            refreshToken: null,
        });

        navigate('/');
    }, [navigate, setAuthState]);

    return null;
};
