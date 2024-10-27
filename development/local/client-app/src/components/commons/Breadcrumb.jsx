import {Link} from "react-router-dom";
import React from "react";

export const Breadcrumb = ({links = [], dark}) => {
    return (
        <section className={`breadcrumbs ${dark ? "bg-dark-1" : ""}`}>
            <div className="container">
                <div className="row">
                    <div className="col-auto">
                        <div className="breadcrumbs__content">
                            <div className={`breadcrumbs__item ${dark ? "text-dark-3" : ""}`}>
                                <Link to="/">Beranda</Link>
                            </div>

                            {links.map((link, index) => {
                                const isLastItem = index === links.length - 1;
                                return (
                                    <div key={index} className={`breadcrumbs__item ${dark ? "text-dark-3" : ""}`}>
                                        {isLastItem ? (
                                            <small>{link.label}</small>
                                        ) : (
                                            <Link to={link.href}>{link.label}</Link>
                                        )}
                                    </div>
                                );
                            })}
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};
