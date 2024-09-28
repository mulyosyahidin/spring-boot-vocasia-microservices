import React, { useEffect, useState } from "react";
import {CourseCard} from "../../../../../components/commons/CourseCard.jsx";
import {coursesData} from "../data.js";

export const CourseItems = ({ category }) => {
    const [filtered, setFiltered] = useState([]);

    useEffect(() => {
        if (category === "Semua Kategori") {
            setFiltered(coursesData);
        } else {
            const filteredData = coursesData.filter(
                (course) => course.category === category
            );
            setFiltered(filteredData);
        }
    }, [category]);

    return (
        <div
            className="pt-60 m-auto row y-gap-30 container pl-0 pr-0"
            data-aos="fade-right"
            data-aos-offset="80"
            data-aos-duration={800}
        >
            {filtered.slice(0, 8).map((course, index) => (
                <CourseCard
                    key={index}
                    data={course}
                    data-aos="fade-right"
                    data-aos-duration={(index + 1) * 300}
                />
            ))}
        </div>
    );
};
