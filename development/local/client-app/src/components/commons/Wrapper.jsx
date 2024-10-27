import React from 'react';
import { useRecoilValue } from 'recoil';
import {UnauthorizedPage} from "../../pages/Errors/UnauthorizedPage/Index.jsx";
import {authStatusSelector} from "../../states/recoil/Selectors/AuthStatusSelector.jsx";
import {AuthContext} from "../../states/contexts/AuthContext.jsx";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

export const Wrapper = ({ children, needAuth = false, role = undefined }) => {
    const { isLoggedIn, user } = useRecoilValue(authStatusSelector);
    const sweetAlert = withReactContent(Swal);

    if (needAuth && (!user || (role && user.role !== role))) {
        return <UnauthorizedPage />;
    }

    return (
        <AuthContext.Provider value={{ isLoggedIn, user, sweetAlert }}>
            {children}
        </AuthContext.Provider>
    );
};
