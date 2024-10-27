import React, {useContext} from "react";
import {Link} from "react-router-dom";
import {AuthContext} from "../../../states/contexts/AuthContext.jsx";
import {ADMIN, INSTRUCTOR} from "../../../config/consts.js";

export const Join = () => {
    const {isLoggedIn, user} = useContext(AuthContext);

    return (
        <section className="layout-pt-md layout-pb-md bg-purple-1">
            <div className="container">
                <div className="row y-gap-20 justify-between items-center">
                    <div className="col-xl-4 col-lg-5">
                        <h2 className="text-30 lh-15 text-white">
                            Ingin jadi
                            <span className="text-green-1">instruktur</span> kami?
                        </h2>
                    </div>

                    <div className="col-auto">
                        {
                            isLoggedIn ? (
                                user.role === INSTRUCTOR ? (
                                    <Link to={'/instructor'} className="button -md -green-1 text-dark-1">
                                        Buka Dasbor
                                    </Link>
                                ) : user.role === ADMIN ? (
                                    <Link to={'/admin'} className="button -md -green-1 text-dark-1">
                                        Buka Dasbor
                                    </Link>
                                ) : (
                                    <Link to="#" className="button -md -green-1 text-dark-1">
                                        Ayo Bergabung Sekarang!
                                    </Link>
                                )
                            ) : (
                                <Link to={'/become-instructor'} className="button -md -green-1 text-dark-1">
                                    Ayo Bergabung Sekarang!
                                </Link>
                            )
                        }
                    </div>
                </div>
            </div>
        </section>
    );
}
