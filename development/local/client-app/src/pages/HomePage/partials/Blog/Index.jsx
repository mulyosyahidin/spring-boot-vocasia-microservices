import React from "react";

import {Link} from "react-router-dom";
import {Title} from "./partials/Title.jsx";
import {blogs, events} from "./data.js";
import {ArticleCard} from "../../../../components/commons/ArticleCard.jsx";
import {EventCard} from "../../../../components/commons/EventCard.jsx";

export const Blog = () => {
    return (
        <section className="layout-pt-lg layout-pb-lg">
            <div className="container">
                <div className="row y-gap-20 justify-between items-center">
                    <Title />

                    <div
                        className="col-auto"
                        data-aos="fade-left"
                        data-aos-duration={700}
                    >
                        <Link
                            to="/blog-list-1"
                            className="button -icon -purple-3 text-purple-1"
                        >
                            Lihat Semua
                            <i className="icon-arrow-top-right text-13 ml-10"></i>
                        </Link>
                    </div>
                </div>

                <div className="row y-gap-30 pt-50">
                    {blogs.slice(0, 2).map((elm, i) => (
                        <div
                            key={i}
                            className="col-lg-4 col-md-6"
                            data-aos="fade-left"
                            data-aos-duration={(i + 1) * 400}
                        >
                            <ArticleCard elm={elm} />
                        </div>
                    ))}

                    <div className="col-lg-4">
                        <div
                            className="row y-gap-30"
                            data-aos="fade-left"
                            data-aos-duration={700}
                        >
                            {events.slice(0, 3).map((elm, i) => (
                                <div key={i} className="col-lg-12 col-md-6">
                                   <EventCard elm={elm} />
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}
