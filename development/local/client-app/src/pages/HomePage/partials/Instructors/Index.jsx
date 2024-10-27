import React from "react";

import {Title} from "./partials/Title.jsx";
import {JoinUs} from "./partials/JoinUs.jsx";
import {InstructorItems} from "./partials/InstructorItems.jsx";

export const Instructors = ({backgroundColor}) => {
    return (
        <section
            className={`layout-pt-lg layout-pb-lg ${
                backgroundColor ? backgroundColor : ""
            }`}
        >
            <div className="container">
                <Title/>
                <InstructorItems/>
                <JoinUs/>
            </div>
        </section>
    );
}
