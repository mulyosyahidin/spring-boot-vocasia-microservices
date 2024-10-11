import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {useParams} from "react-router-dom";
import {findCourseById} from "../_actions/CourseAction.jsx";
import {Information} from "./partials/Information.jsx";
import {Contents} from "./partials/Contents.jsx";
import {Students} from "./partials/Students.jsx";
import {QnA} from "./partials/QnA.jsx";
import {Earnings} from "./partials/Earnings.jsx";
import {Settings} from "./partials/Settings.jsx";

const buttons = [
    "Informasi",
    "Konten",
    "Siswa",
    "Tanya Jawab",
    "Penghasilan",
    "Pengaturan",
];

export const CourseOverview = () => {
    const {sweetAlert} = useContext(AuthContext);
    const {courseId} = useParams();
    const [activeTab, setActiveTab] = useState(1);
    const [isLoading, setIsLoading] = useState(true);

    const [course, setCourse] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getCourse = await findCourseById(courseId);
                setCourse(getCourse.course);
            } catch (error) {
                console.error('Error fetching initial data:', error);
            }
            finally {
                setIsLoading(false);
            }
        };

        fetchInitialData();
    }, [courseId]);

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">{course.title}</h1>
                    <div className="mt-10">Data Kursus</div>
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
                                            activeTab == i + 1 ? "is-active" : ""
                                        } `}
                                        type="button"
                                    >
                                        {elm}
                                    </button>
                                ))}
                            </div>

                            <div className="tabs__content py-30 px-30 js-tabs-content">
                                <Information activeTab={activeTab} course={course} isLoading={isLoading}/>
                                <Contents activeTab={activeTab} course={course} courseId={courseId} isLoading={isLoading}/>
                                <Students activeTab={activeTab} course={course} courseId={courseId} />
                                <QnA activeTab={activeTab} course={course}/>
                                <Earnings activeTab={activeTab} course={course}/>
                                <Settings activeTab={activeTab} course={course} courseId={courseId} setCourse={setCourse}/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    );
}