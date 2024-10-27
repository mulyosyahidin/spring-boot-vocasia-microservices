import {Link} from "react-router-dom";
import React from "react";

export const Menu = () => {
    return (
        <div className="py-30 border-top-light-15">
            <div className="row justify-between items-center y-gap-20">
                <div className="col-auto">
                    <div className="d-flex items-center h-100 text-white">
                        © {new Date().getFullYear()} Vocasia. Semua hak dilindungi.
                    </div>
                </div>

                <div className="col-auto">
                    <div className="d-flex x-gap-20 y-gap-20 items-center flex-wrap">
                        <div>
                            <Link
                                to="#"
                                className="button px-30 h-50 -dark-6 rounded-200 text-white"
                            >
                                <i className="icon-worldwide text-20 mr-15"></i>
                                <span className="text-15">English</span>
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}