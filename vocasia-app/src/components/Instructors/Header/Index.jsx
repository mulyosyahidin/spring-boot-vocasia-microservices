import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {getUserProfilePictureUrl} from "../../../utils/new-utils.js";

export const Header = () => {
    const [isfullScreen, setIsfullScreen] = useState(false);
    const [isOnNotification, setIsOnNotification] = useState(false);
    const [isOnProfile, setIsOnProfile] = useState(false);

    const [documentElement, setDocumentElement] = useState();
    const handleFullScreenToggle = () => {
        setIsfullScreen((pre) => !pre);
        if (!isfullScreen) {
            openFullscreen();
        } else {
            closeFullscreen();
        }
    };

    useEffect(() => {
        setDocumentElement(document.documentElement);
    }, []);
    const openFullscreen = () => {
        if (documentElement?.requestFullscreen) {
            documentElement?.requestFullscreen();
        } else if (documentElement?.webkitRequestFullscreen) {
            /* Safari */
            documentElement?.webkitRequestFullscreen();
        } else if (documentElement?.msRequestFullscreen) {
            /* IE11 */
            documentElement?.msRequestFullscreen();
        }
    };

    const handleDarkmode = () => {
        if (document) {
            document.getElementsByTagName("html")[0].classList.toggle("-dark-mode");
        }
    };

    const closeFullscreen = () => {
        if (document?.exitFullscreen) {
            document?.exitFullscreen();
        } else if (document?.webkitExitFullscreen) {
            /* Safari */
            document?.webkitExitFullscreen();
        } else if (document?.msExitFullscreen) {
            /* IE11 */
            document?.msExitFullscreen();
        }
    };

    const handleResize = () => {
    };
    useEffect(() => {
        if (window.innerWidth < 990) {
            document
                .getElementById("dashboardOpenClose")
                .classList.add("-is-sidebar-hidden");
        }
        const handleResize = () => {
            if (window.innerWidth < 990) {
                document
                    .getElementById("dashboardOpenClose")
                    .classList.add("-is-sidebar-hidden");
            }
        };

        // Add event listener to window resize event
        window.addEventListener("resize", handleResize);

        // Clean up the event listener when component unmounts
        return () => {
            window.removeEventListener("resize", handleResize);
        };
    }, []);

    return (
        <>
            <header className="header -dashboard -dark-bg-dark-1 js-header">
                <div className="header__container py-20 px-30">
                    <div className="row justify-between items-center">
                        <div className="col-auto">
                            <div className="d-flex items-center">
                                <div className="header__explore text-dark-1">
                                    <button
                                        onClick={() => {
                                            document
                                                .getElementById("dashboardOpenClose")
                                                .classList.toggle("-is-sidebar-hidden");
                                        }}
                                        className="d-flex items-center js-dashboard-home-9-sidebar-toggle"
                                    >
                                        <i className="icon -dark-text-white icon-explore"></i>
                                    </button>
                                </div>

                                <div className="header__logo ml-30 md:ml-20">
                                    <Link data-barba to="/">
                                        <img
                                            className="-light-d-none"
                                            src="/logo-white.svg"
                                            alt="logo"
                                        />
                                        <img
                                            className="-dark-d-none"
                                            src="/logo-dark.svg"
                                            alt="logo"
                                        />
                                    </Link>
                                </div>
                            </div>
                        </div>

                        <div className="col-auto">
                            <div className="d-flex items-center">
                                <div className="d-flex items-center sm:d-none">
                                    <div className="relative">
                                        <button
                                            onClick={handleDarkmode}
                                            className="js-darkmode-toggle text-light-1 d-flex items-center justify-center size-50 rounded-16 -hover-dshb-header-light"
                                        >
                                            <i className="text-24 icon icon-night"></i>
                                        </button>
                                    </div>

                                    <div className="relative">
                                        <button
                                            onClick={() => handleFullScreenToggle()}
                                            className="d-flex text-light-1 items-center justify-center size-50 rounded-16 -hover-dshb-header-light"
                                        >
                                            <i className="text-24 icon icon-maximize"></i>
                                        </button>
                                    </div>

                                </div>

                                <div
                                    className="relative d-flex items-center ml-10"
                                    onClick={() => setIsOnProfile((pre) => !pre)}
                                >
                                    <a href="#" data-el-toggle=".js-profile-toggle">
                                        <img
                                            className="size-50" style={{borderRadius: '50%'}}
                                            src={getUserProfilePictureUrl()}
                                            alt="image"
                                        />
                                    </a>

                                    <div
                                        className={`toggle-element js-profile-toggle ${
                                            isOnProfile ? "-is-el-visible" : ""
                                        } -`}
                                    >
                                        <div
                                            className="toggle-bottom -profile bg-white shadow-4 border-light rounded-8 mt-10">
                                            <div className="px-30 py-30">
                                                <div className="sidebar -dashboard">
                                                    <div
                                                        key={'dp-1'}
                                                        className={`sidebar__item`}
                                                    >
                                                        <Link
                                                            to={'/instructor/profile'}
                                                            className="d-flex items-center text-17 lh-1 fw-500 "
                                                        >
                                                            <i className={'fa fa-user'}></i>
                                                            <span className="ml-15">Profile</span>
                                                        </Link>
                                                    </div>

                                                    <div
                                                        key={'dp-2'}
                                                        className={`sidebar__item`}
                                                    >
                                                        <Link
                                                            to={'/logout'}
                                                            className="d-flex items-center text-17 lh-1 fw-500 "
                                                        >
                                                            <i className={'fa fa-sign-out'}></i>
                                                            <span className="ml-15">Keluar</span>
                                                        </Link>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </>
    );
}