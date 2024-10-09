import {useContext, useState} from "react";
import {Link} from "react-router-dom";
import {CategoryButton} from "./partials/CategoryButton.jsx";
import {SearchToggle} from "./partials/SearchToggle.jsx";
import {AuthContext} from "../../states/contexts/AuthContext.jsx";
import Menu from "../commons/Menu.jsx";
import MobileMenu from "../commons/MobileMenu.jsx";
import {ADMIN, INSTRUCTOR} from "../../config/consts.js";
import {CartToggle} from "../commons/CartToggle/Index.jsx";

export const Header = () => {
    const [activeMobileMenu, setActiveMobileMenu] = useState(false);
    const {isLoggedIn, user} = useContext(AuthContext);

    return (
        <>
            <header className="header -type-1 ">
                <div className="header__container">
                    <div className="row justify-between items-center">
                        <div className="col-auto">
                            <div className="header-left">
                                <div className="header__logo ">
                                    <Link to={'/'}>
                                        <img src="/logo-white.svg" alt="logo"/>
                                    </Link>
                                </div>

                                {/*<CategoryButton*/}
                                {/*    allClasses={*/}
                                {/*        "header__explore text-green-1 ml-60 xl:ml-30 xl:d-none"*/}
                                {/*    }*/}
                                {/*/>*/}
                            </div>
                        </div>

                        <Menu allClasses={"menu__nav text-white -is-active"}/>

                        <MobileMenu
                            setActiveMobileMenu={setActiveMobileMenu}
                            activeMobileMenu={activeMobileMenu}
                        />

                        <div className="col-auto">
                            <div className="header-right d-flex items-center">
                                <div className="header-right__icons text-white d-flex items-center">
                                    <SearchToggle/>

                                    <CartToggle
                                        parentClassess={"relative ml-30 xl:ml-20"}
                                        allClasses={"d-flex items-center text-white"}
                                    />

                                    <div className="d-none xl:d-block ml-20">
                                        <button
                                            onClick={() => setActiveMobileMenu(true)}
                                            className="text-white items-center"
                                            data-el-toggle=".js-mobile-menu-toggle"
                                        >
                                            <i className="text-11 icon icon-mobile-menu"></i>
                                        </button>
                                    </div>
                                </div>

                                <div className="header-right__buttons d-flex items-center ml-30 md:d-none">
                                    {
                                        isLoggedIn ? (
                                            <>
                                                {
                                                    user.role === INSTRUCTOR ? (
                                                        <Link
                                                            to={'/instructor'}
                                                            className="button -sm -white text-dark-1 ml-30"
                                                        >
                                                            Dashboard
                                                        </Link>
                                                    ) : (
                                                        user.role === ADMIN ? (
                                                            <Link
                                                                to={'/admin'}
                                                                className="button -sm -white text-dark-1 ml-30"
                                                            >
                                                                Dashboard
                                                            </Link>
                                                        ) : (
                                                            <Link
                                                                to={'/users'}
                                                                className="button -sm -white text-dark-1 ml-30"
                                                            >
                                                                Dashboard
                                                            </Link>
                                                        )
                                                    )
                                                }
                                            </>
                                        ) : (
                                            <>
                                                <Link to={'/auth/login'} className="button -underline text-white">
                                                    Masuk
                                                </Link>
                                                <Link
                                                    to={'/auth/register'}
                                                    className="button -sm -white text-dark-1 ml-30"
                                                >
                                                    Daftar
                                                </Link>
                                            </>
                                        )
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </>
    );
}