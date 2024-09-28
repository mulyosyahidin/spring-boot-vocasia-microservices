import React, { useState } from "react";

export const SearchToggle = ({ allClasses, color }) => {
    const [activeSearch, setActiveSearch] = useState(false);
    return (
        <>
            <div className={allClasses ? allClasses : ""}>
                <button
                    onClick={() => setActiveSearch((pre) => !pre)}
                    className={`d-flex items-center ${color ? color : "text-white"} `}
                    data-el-toggle=".js-search-toggle"
                >
                    <i className="text-20 icon icon-search"></i>
                </button>

                <div
                    className={`toggle-element js-search-toggle ${
                        activeSearch ? "-is-el-visible" : ""
                    }`}
                >
                    <div
                        className="header-search pt-90 bg-white shadow-4 "
                        style={{ height: "200px" }}
                    >
                        <div className="container">
                            <div className="header-search__field">
                                <div className="icon icon-search text-dark-1"></div>
                                <input
                                    required
                                    type="text"
                                    className="col-12 text-18 lh-12 text-dark-1 fw-500"
                                    placeholder="Ingin belajar apa?"
                                />

                                <button
                                    onClick={() => setActiveSearch(false)}
                                    className="d-flex items-center justify-center size-40 rounded-full bg-purple-3"
                                    data-el-toggle=".js-search-toggle"
                                >
                                    <img src="/assets/img/menus/close.svg" alt="icon" />
                                </button>
                            </div>
                        </div>
                    </div>
                    <div
                        className="header-search__bg"
                        data-el-toggle=".js-search-toggle"
                    ></div>
                </div>
            </div>
        </>
    );
};