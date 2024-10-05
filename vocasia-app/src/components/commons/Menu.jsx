import React, { useEffect, useState } from "react";
import { useLocation, Link } from "react-router-dom";
import {MobileFooter} from "../MobileFooter/Index.jsx";

const menuList = [
    {
        title: "Beranda",
        href: "/"
    },
    {
        title: "Workshop",
        href: "/workshop"
    },
    {
        title: "Bootcamp",
        href: "/bootcamp"
    },
];

export default function Menu({ allClasses, headerPosition }) {
    const [menuItem, setMenuItem] = useState("");
    const { pathname } = useLocation();

    useEffect(() => {
        menuList.forEach((elm) => {
            if (elm.href && elm.href === pathname) {
                setMenuItem(elm.title);
            } else if (elm.links) {
                elm.links.forEach((subElm) => {
                    if (subElm.href === pathname) {
                        setMenuItem(elm.title);
                    }
                });
            }
        });
    }, [pathname]);

    return (
        <div className={`header-menu js-mobile-menu-toggle ${headerPosition || ""}`}>
            <div className="header-menu__content">
                <div className="mobile-bg js-mobile-bg"></div>

                <div className="d-none xl:d-flex items-center px-20 py-20 border-bottom-light">
                    <Link to="/login" className="text-dark-1">Log in</Link>
                    <Link to="/signup" className="text-dark-1 ml-30">Sign Up</Link>
                </div>

                <div className="menu js-navList">
                    <ul className={allClasses || ""}>
                        {menuList.map((item, index) => (
                            <li key={index} className={item.links ? "menu-item-has-children" : ""}>
                                <Link
                                    data-barba
                                    to={item.href || "#"}
                                    className={menuItem === item.title ? "activeMenu" : ""}
                                >
                                    {item.title} {item.links && <i className="icon-chevron-right text-13 ml-10"></i>}
                                </Link>
                                {item.links && (
                                    <ul className="subnav">
                                        <li className="menu__backButton js-nav-list-back">
                                            <Link to="#">
                                                <i className="icon-chevron-left text-13 mr-10"></i> {item.title}
                                            </Link>
                                        </li>
                                        {item.links.map((subItem, subIndex) => (
                                            <li
                                                key={subIndex}
                                                className={pathname === subItem.href ? "activeMenu" : "inActiveMenu"}
                                            >
                                                <Link data-barba to={subItem.href}>
                                                    {subItem.label}
                                                </Link>
                                            </li>
                                        ))}
                                    </ul>
                                )}
                            </li>
                        ))}
                    </ul>
                </div>

                <MobileFooter />
            </div>

            <div className="header-menu-close" data-el-toggle=".js-mobile-menu-toggle">
                <div className="size-40 d-flex items-center justify-center rounded-full bg-white">
                    <div className="icon-close text-dark-1 text-16"></div>
                </div>
            </div>

            <div className="header-menu-bg"></div>
        </div>
    );
}
