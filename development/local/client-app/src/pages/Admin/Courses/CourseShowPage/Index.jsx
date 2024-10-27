import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findById} from "../../../../services/new/course/admin/course-service.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {Information} from "./partials/Information.jsx";
import {Contents} from "./partials/Contents.jsx";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";

const buttons = [
    "Informasi",
    "Konten",
    // "Siswa",
    // "Tanya Jawab",
    // "Penghasilan",
    // "Pengaturan",
]

export const CourseShowPage = () => {
    const {courseId} = useParams();

    const [pageTitle, setPageTitle] = useState('Ringkasan Kursus');
    const [activeTab, setActiveTab] = useState(1);

    const [isLoading, setIsLoading] = useState(true);
    const [course, setCourse] = useState({});
    const [chapters, setChapters] = useState([]);
    const [instructor, setInstructor] = useState({});
    const [category, setCategory] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const findCourseById = await findById(courseId);

                if (findCourseById.success) {
                    const {instructor, course, category, chapters, students} = findCourseById.data;

                    setPageTitle(course.title);

                    setInstructor(instructor);
                    setCourse(course);
                    setCategory(category);
                    setChapters(chapters);
                }

                setIsLoading(false);
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
    }, [courseId]);

    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={{
                title: pageTitle,
            }}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">{isLoading ? '...' : course.title}</h1>
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
                                                    <Information course={course} activeTab={activeTab}/>
                                                    <Contents chapters={chapters} activeTab={activeTab}/>
                                                </>
                                            )
                                        }

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </AdminWrapper>
        </Wrapper>
    )
}