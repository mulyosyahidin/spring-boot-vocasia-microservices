import {Link} from "react-router-dom";
import {content, info} from "../data.jsx";
import React, {useContext} from "react";
import {AuthContext} from "../../../../../states/contexts/AuthContext.jsx";
import {INSTRUCTOR} from "../../../../../config/consts.js";

const {title, text_underline, info_hero, starts} = content;

export const Content = () => {
    const {isLoggedIn, user} = useContext(AuthContext);

    return (
        <div className="col-xl-6 col-lg-6 col-sm-10">
            <div
                className="masthead__content"
                data-aos="fade-up"
                data-aos-delay="500"
            >
                <h1 className="masthead__title">
                    {title}{" "}
                    <span className="text-green-1 underline">
                                        {text_underline}
                                    </span>
                </h1>
                <p
                    data-aos="fade-up"
                    data-aos-duration="100"
                    className="masthead__text"
                >
                    {info_hero}
                </p>
                <div
                    data-aos="fade-up"
                    data-aos-duration="200"
                    className="masthead__buttons row x-gap-10 y-gap-10"
                >
                    <div className="col-12 col-sm-auto">
                        {
                            isLoggedIn ? (
                                user.role == INSTRUCTOR ? (
                                    <Link
                                        data-barba
                                        to={'/instructor/courses'}
                                        className="button -md -purple-1 text-white"
                                    >
                                        Kelola Kursus
                                    </Link>
                                ) : (<> </>)
                            ) : (
                                <Link
                                    data-barba
                                    to={'/auth/register'}
                                    className="button -md -purple-1 text-white"
                                >
                                    Daftar Sekarang
                                </Link>
                            )
                        }
                    </div>
                    <div className="col-12 col-sm-auto">
                        <Link
                            data-barba
                            to={'/courses'}
                            className="button -md -outline-green-1 text-green-1"
                        >
                            Cari Kursus
                        </Link>
                    </div>
                </div>
                <div
                    data-aos="fade-up"
                    data-aos-duration="300"
                    className="masthead-info row y-gap-15 sm:d-none"
                >
                    {info.map((item, i) => (
                        <div
                            key={i}
                            className="masthead-info__item d-flex items-center text-white"
                        >
                            <div className="masthead-info__icon mr-10">
                                <img src={item.icon} alt="icon"/>
                            </div>
                            <div className="masthead-info__title lh-1">
                                {item.text}
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}