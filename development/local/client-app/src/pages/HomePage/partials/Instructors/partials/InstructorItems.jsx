import React from "react";
import {teamMembers} from "../data.js";
import {InstructorCard} from "../../../../../components/commons/InstructorCard.jsx";

export const InstructorItems = () => {
    return (
        <div className="row y-gap-30 pt-50">
            {teamMembers.slice(0, 4).map((elm, i) => (
                <div
                    key={i}
                    className="col-lg-3 col-sm-6"
                    data-aos="fade-left"
                    data-aos-duration={(i + 1) * 500}
                >
                    <InstructorCard instructor={elm}/>
                </div>
            ))}
        </div>
    );
}