import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {Information} from "./partials/Information.jsx";
import {Contents} from "./partials/Contents.jsx";
import {Students} from "./partials/Students.jsx";
import {Income} from "./partials/Income.jsx";
import {Settings} from "./partials/Settings.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findById} from "../../../../services/new/course/instructor/course-service.js";
import {Qna} from "./partials/Qna.jsx";

const metaData = {
    title: 'Ringkasan Kursus',
};
const buttons = [
    "Informasi",
    "Konten",
    "Siswa",
    "Tanya Jawab",
    "Penghasilan",
    "Pengaturan",
];

export const CourseOverviewPage = () => {
    const {courseId} = useParams();
    const [activeTab, setActiveTab] = useState(1);
    const [isLoading, setIsLoading] = useState(true);

    const [course, setCourse] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findCourseById = await findById(courseId);

                if (findCourseById.success) {
                    const course = findCourseById.data.course;
                    setCourse(course);
                }

                setIsLoading(false);
            }
            catch (error) {
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
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
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
                                                <>
                                                <span>
                                                    <i className={'fa fa-spinner fa-spin mr-5'}></i>
                                                    <strong role="status">Loading...</strong>
                                                </span>
                                                </>
                                            )
                                        }

                                        {
                                            !isLoading && (
                                                <>
                                                    <Information activeTab={activeTab} course={course} isLoading={isLoading}/>
                                                    <Contents activeTab={activeTab} courseId={courseId}/>
                                                    <Students activeTab={activeTab} courseId={courseId}/>
                                                    <Qna activeTab={activeTab} courseId={courseId} />
                                                    <Income activeTab={activeTab} courseId={courseId}/>
                                                    <Settings activeTab={activeTab} courseId={courseId} course={course}/>
                                                </>
                                            )
                                        }

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </InstructorWrapper>
        </Wrapper>
    );
}