import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {Data} from "./partials/Data.jsx";
import {getStudentByInstructorStudentId} from "../../../../services/instructors/student-service.js";
import {Courses} from "./partials/Courses.jsx";

const buttons = [
    "Data",
    "Kursus",
];

export const ShowStudent = () => {
    const {studentId} = useParams();
    const [activeTab, setActiveTab] = useState(1);

    const [isLoading, setIsLoading] = useState(true);

    const [student, setStudent] = useState({});
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getData = await getStudentByInstructorStudentId(studentId);

                setStudent(getData.user);
                setCourses(getData.courses);
            } catch (e) {
                console.error(e);
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, []);

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Data Siswa</h1>
                    <div className="mt-10">Data siswa saya</div>
                </div>
            </div>

            <div className="row y-gap-30">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div className="tabs -active-purple-2 js-tabs pt-0">
                            <div
                                className="tabs__controls d-flex  x-gap-30 y-gap-20 flex-wrap items-center pt-20 px-30 border-bottom-light js-tabs-controls">
                                {buttons.map((elm, i) => (
                                    <button
                                        key={i}
                                        onClick={() => setActiveTab(i + 1)}
                                        className={`tabs__button text-light-1 js-tabs-button ${
                                            activeTab === i + 1 ? "is-active" : ""
                                        } `}
                                        type="button"
                                    >
                                        {elm}
                                    </button>
                                ))}
                            </div>

                            <div className="tabs__content py-30 px-30 js-tabs-content">
                                {
                                    isLoading && (
                                        <div className="text-center">
                                            <div className="spinner-border text-primary" role="status">
                                                <span className="visually-hidden">Loading...</span>
                                            </div>
                                        </div>
                                    )
                                }

                                {
                                    !isLoading && (
                                        <>
                                            <Data activeTab={activeTab} student={student}/>
                                            <Courses activeTab={activeTab} courses={courses}/>
                                        </>
                                    )
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    );
}