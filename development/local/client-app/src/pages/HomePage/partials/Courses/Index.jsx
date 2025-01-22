import React, {useState} from "react";
import {Title} from "./partials/Title.jsx";
import {CategorySelector} from "./partials/CategorySelector.jsx";
import {CourseItems} from "./partials/CourseItems.jsx";

export const Courses = () => {
    const [category, setCategory] = useState("Semua Kategori");

    return (
        <section className="layout-pt-lg layout-pb-lg">
            <Title/>
            <CourseItems/>
        </section>
    );
}
