import {Link} from "react-router-dom";
import React, {useContext} from "react";
import {AuthContext} from "../../../../../states/contexts/AuthContext.jsx";
import {INSTRUCTOR} from "../../../../../config/consts.js";

export const JoinUs = () => {
    const {isLoggedIn, user} = useContext(AuthContext);

    return isLoggedIn ? (
        user.role == INSTRUCTOR ? (
            <div className="row justify-center text-center pt-60 lg:pt-40">
                <div className="col-auto">
                    <p className="lh-1">
                       Buka dasbor dan tambahkan kursus Anda
                        <Link className="text-purple-1 underline" to={'/instructor/courses'}>
                            Mulai sekarang
                        </Link>
                    </p>
                </div>
            </div>
        ) : (<></>)
    ) : (
        <div className="row justify-center text-center pt-60 lg:pt-40">
            <div className="col-auto">
                <p className="lh-1">
                    Ingin berbagi ilmu dan mendapatkan penghasilan tambahan?
                    <Link className="text-purple-1 underline" to={'/become-instructor'}>
                        Bergabung bersama kami
                    </Link>
                </p>
            </div>
        </div>
    )
}