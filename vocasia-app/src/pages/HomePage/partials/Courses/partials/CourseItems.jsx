import React, {useEffect, useState} from "react";
import {CourseCard} from "../../../../../components/commons/CourseCard.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findEditorChoices} from "../../../../../services/new/course/public/course-service.js";

export const CourseItems = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getEditorChoiceCourses = await findEditorChoices();

                if (getEditorChoiceCourses.success) {
                    setCourses(getEditorChoiceCourses.data.courses);

                    setIsLoading(false);
                }
            } catch (error) {
                console.error(error);

                if (error.message) {
                    let msg = error.message;
                    if (error.data.error) {
                        msg += ' : ' + error.data.error;
                    }

                    await withReactContent(Swal).fire({
                        title: 'Terjadi Kesalahan!',
                        text: msg,
                        icon: 'error',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        })
                }
            }
        }

        fetchInitialData();
    }, []);

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
