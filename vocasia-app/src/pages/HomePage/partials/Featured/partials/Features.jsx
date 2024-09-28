import {featureOne} from "../data.js";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheck} from "@fortawesome/free-solid-svg-icons";
import React from "react";

export const Features = () => {
    return (
        <div className="y-gap-20 pt-30">
            {featureOne.map((elm, i) => (
                <div
                    key={i}
                    className="d-flex items-center"
                    data-aos="fade-up"
                >
                    <div className="about-content-list__icon">
                                            <span className="text-white"
                                                  style={{fontSize: "10px", fontWeight: "300"}} aria-hidden="true">
                                                <FontAwesomeIcon icon={faCheck}/>
                                            </span>
                    </div>
                    <div className="about-content-list__title">{elm.title}</div>
                </div>
            ))}
        </div>
    );
}