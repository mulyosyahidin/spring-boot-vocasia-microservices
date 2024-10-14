import React, {useEffect, useState} from "react";
import {CourseCard} from "../../../../../components/commons/CourseCard.jsx";

export const CourseItems = ({category}) => {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getCourses = await getEditorChoiceCourses();
                setCourses(getCourses);
            }
            catch (e) {
                console.error(e);
            }
        }

        fetchInitialData();
    }, [category]);

    return (
        <div
            className="pt-60 m-auto row y-gap-30 container pl-0 pr-0"
            data-aos="fade-right"
            data-aos-offset="80"
            data-aos-duration={800}
        >
            {courses.slice(0, 8).map((course, index) => (
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
