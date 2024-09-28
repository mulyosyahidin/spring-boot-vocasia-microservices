import React from "react";
import Socials from "../commons/Socials.jsx";
import {Links} from "./partials/Links.jsx";
import {Menu} from "./partials/Menu.jsx";

export const Footer = () => {
    return (
        <footer className="footer -type-1 bg-dark-1 -green-links">
            <div className="container">
                <div className="footer-header">
                    <div className="row y-gap-20 justify-between items-center">
                        <div className="col-auto">
                            <div className="footer-header__logo">
                                <img src="/logo-white.svg" alt="logo"/>
                            </div>
                        </div>
                        <div className="col-auto">
                            <div className="footer-header-socials">
                                <div className="footer-header-socials__title text-white">
                                    Ikuti kami
                                </div>
                                <div className="footer-header-socials__list">
                                    <Socials/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="footer-columns">
                    <div className="row y-gap-30">
                        <Links
                            allClasses={"text-17 fw-500 text-white uppercase mb-25"}
                        />
                    </div>
                </div>

                <Menu/>
            </div>
        </footer>
    );
}
