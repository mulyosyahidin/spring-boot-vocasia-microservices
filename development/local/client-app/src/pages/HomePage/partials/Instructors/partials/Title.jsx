import {Link} from "react-router-dom";
import React from "react";

export const Title = () => {
    return (
        <div className="row y-gap-20 justify-between items-center">
            <div className="col-lg-6">
                <div className="sectionTitle ">
                    <h2 className="sectionTitle__title " data-aos="fade-left">
                        Belajar bersama instruktur-instruktur terbaik
                    </h2>

                    <p className="sectionTitle__text " data-aos="fade-left">
                        Beberapa dari mereka
                    </p>
                </div>
            </div>

            <div className="col-auto" data-aos="fade-left">
                <Link
                    to="/instructors-list-1"
                    className="button -icon -purple-3 text-purple-1"
                >
                    Lihat Semua
                    <i className="icon-arrow-top-right text-13 ml-10"></i>
                </Link>
            </div>
        </div>
    );
}