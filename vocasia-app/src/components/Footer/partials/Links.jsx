import React from "react";
import {Link} from "react-router-dom";
import {footerLinks} from "../data.js";

export const Links = ({allClasses}) => {
    return (
        <>
            {footerLinks.map((elm, i) => (
                <div key={i} className="col-xl-2 col-lg-4 col-md-6">
                    <div className={`${allClasses ? allClasses : ""}`}>{elm.title}</div>
                    <div className="d-flex y-gap-10 flex-column">
                        {elm.links.map((itm, index) => (
                            <Link key={index} to={itm.href}>
                                {itm.label}
                            </Link>
                        ))}
                    </div>
                </div>
            ))}
        </>
    );
}
