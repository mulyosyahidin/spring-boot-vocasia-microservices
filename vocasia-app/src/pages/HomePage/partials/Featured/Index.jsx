import React from "react";

import {Link} from "react-router-dom";
import {Title} from "./partials/Title.jsx";
import {Features} from "./partials/Features.jsx";
import {SideImage} from "./partials/SideImage.jsx";

export const Featured = () => {
    return (
        <section className="layout-pt-lg layout-pb-lg bg-beige-1">
            <div className="container">
                <div className="row y-gap-30 justify-between items-center">
                    <div className="col-xl-5 col-lg-6 col-md-10 order-2 order-lg-1">
                        <div className="about-content">
                            <Title/>

                            <Features/>

                            <div className="d-inline-block mt-30">
                                <Link to={'/auth/register'} className="button -md -dark-1 text-white">
                                    Bergabung, Gratis!
                                </Link>
                            </div>
                        </div>
                    </div>

                    <SideImage/>
                </div>
            </div>
        </section>
    );
}
