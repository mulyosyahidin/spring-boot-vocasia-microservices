import React, { useEffect, useState } from "react";
import { useLocation, Link } from "react-router-dom";
import {MobileFooter} from "../MobileFooter/Index.jsx";

// Definisi menuList
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
        links: [
            { href: "/event-list-1", label: "Event List 1" },
            { href: "/event-list-2", label: "Event List 2" },
            { href: "/events/1", label: "Event Single" },
            { href: "/event-cart", label: "Event Cart" },
            { href: "/event-checkout", label: "Event Checkout" },
        ],
    }
];

export default function MobileMenu({ setActiveMobileMenu, activeMobileMenu }) {
    const [showMenu, setShowMenu] = useState(false);
    const [menuNesting, setMenuNesting] = useState([]);
    const [menuItem, setMenuItem] = useState("");
    const [submenu, setSubmenu] = useState("");
    const { pathname } = useLocation();

    useEffect(() => {
        menuList.forEach((elm) => {
            if (elm.href && elm.href === pathname) {
                setMenuItem(elm.title);
            } else if (elm.links) {
                elm.links.forEach((subElm) => {
                    if (subElm.href === pathname) {
                        setMenuItem(elm.title);
                        setSubmenu(subElm.label);
                    }
                });
            }
        });
    }, [pathname]);

    useEffect(() => {
        setShowMenu(true);
    }, []);

    return (
        <div className={`header-menu js-mobile-menu-toggle ${activeMobileMenu ? "-is-el-visible" : ""}`}>
            <div className="header-menu__content">
                <div className="mobile-bg js-mobile-bg"></div>

                <div className="d-none xl:d-flex items-center px-20 py-20 border-bottom-light">
                    <Link to="/login" className={`text-dark-1 ${pathname === "/login" ? "activeMenu" : "inActiveMenu"}`}>
                        Log in
                    </Link>
                    <Link to="/signup" className={`text-dark-1 ml-30 ${pathname === "/signup" ? "activeMenu" : "inActiveMenu"}`}>
                        Sign Up
                    </Link>
                </div>

                {showMenu && activeMobileMenu && (
                    <div className="mobileMenu text-dark-1">
                        {menuList.map((elm, i) => (
                            <div key={i} className="submenuOne">
                                <div className="title" onClick={() => setMenuNesting(pre => pre[0] === elm.title ? [] : [elm.title])}>
                                    <span className={elm.title === menuItem ? "activeMenu" : "inActiveMenu"}>{elm.title}</span>
                                    {elm.links && <i className={`icon-chevron-right text-13 ml-10 ${menuNesting[0] === elm.title ? "active" : ""}`}></i>}
                                </div>
                                {elm.links && (
                                    <ul className={`subnav ${menuNesting[0] === elm.title ? "active" : ""}`}>
                                        <li className="menu__backButton js-nav-list-back">
                                            <Link to="#"><i className="icon-chevron-left text-13 mr-10"></i>{elm.title}</Link>
                                        </li>
                                        {elm.links.map((subItem, subIndex) => (
                                            <li key={subIndex} className={pathname === subItem.href ? "activeMenu" : "inActiveMenu"}>
                                                <Link to={subItem.href}>{subItem.label}</Link>
                                            </li>
                                        ))}
                                    </ul>
                                )}
                            </div>
                        ))}
                    </div>
                )}

                <MobileFooter />

            </div>

            <div className="header-menu-close" onClick={() => setActiveMobileMenu(false)} data-el-toggle=".js-mobile-menu-toggle">
                <div className="size-40 d-flex items-center justify-center rounded-full bg-white">
                    <div className="icon-close text-dark-1 text-16"></div>
                </div>
            </div>

            <div className="header-menu-bg" onClick={() => setActiveMobileMenu(false)}></div>
        </div>
    );
}
