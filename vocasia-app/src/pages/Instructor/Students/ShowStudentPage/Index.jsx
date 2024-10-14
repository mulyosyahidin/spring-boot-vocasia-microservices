import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../config/consts.js";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {Data} from "./partials/Data.jsx";
import {Courses} from "./partials/Courses.jsx";
import {findAll, findById} from "../../../../services/new/instructor/instructor/student-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

const buttons = [
    "Data",
    "Kursus",
];

export const ShowStudentPage = () => {
    const {id} = useParams();
    const [activeTab, setActiveTab] = useState(1);

    const [isLoading, setIsLoading] = useState(true);
    const [student, setStudent] = useState({});
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findStudentById = await findById(id);

                if (findStudentById.success) {
                    setStudent(findStudentById.data.user);
                    setCourses(findStudentById.data.courses);

                    setIsLoading(false);
                }
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
    }, [id]);

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <PreLoader/>

            <InstructorWrapper>
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
            </InstructorWrapper>
        </Wrapper>
    );
}