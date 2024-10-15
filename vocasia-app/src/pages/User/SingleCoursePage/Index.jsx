import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {STUDENT} from "../../../config/consts.js";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {Meta} from "../../../components/commons/Meta.jsx";
import {Header} from "./partials/Header.jsx";
import {VideoPlayer} from "./partials/VideoPlayer.jsx";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faChevronDown, faChevronUp} from "@fortawesome/free-solid-svg-icons";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {
    completeEnrollmentLesson,
    findEnrollmentDataById,
    isLessonCompleted, setLastAccesLesson,
    startLesson
} from "../../../services/new/enrollment/user/course-service.js";
import {findById, findCourseContents} from "../../../services/new/course/student/course-service.js";
import {formatDate} from "../../../utils/new-utils.js";

export const SingleCoursePage = () => {
    const {enrollmentId} = useParams();

    const [pageTitle, setPageTitle] = useState('');
    const [isLoading, setIsLoading] = useState(true);

    const [course, setCourse] = useState(null);
    const [enrollment, setEnrollment] = useState(null);

    const [activeItemId, setActiveItemId] = useState(0);
    const [lessonItems, setLessonItems] = useState([]);
    const [currentLesson, setCurrentLesson] = useState(null);
    const [completedLessons, setCompletedLessons] = useState({});
    const [totalLesson, setTotalLesson] = useState(0);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const findEnrollmentById = await findEnrollmentDataById(enrollmentId);

                if (findEnrollmentById.success) {
                    setEnrollment(findEnrollmentById.data.enrollment);

                    const findCourseById = await findById(findEnrollmentById.data.enrollment.course_id);
                    if (findCourseById.success) {
                        setCourse(findCourseById.data.course);
                        setPageTitle(findCourseById.data.course.title);
                        setTotalLesson(findCourseById.data.course.lesson_count);

                        const findCourseContentsById = await findCourseContents(findCourseById.data.course.id);
                        if (findCourseContentsById.success) {
                            setLessonItems(findCourseContentsById.data.chapters);
                            const lessonCompletionStatuses = {};
                            for (const chapter of findCourseContentsById.data.chapters) {
                                for (const lesson of chapter.lessons) {
                                    const checkIsComplete = await isLessonCompleted(enrollmentId, lesson.id);
                                    lessonCompletionStatuses[lesson.id] = checkIsComplete.success && checkIsComplete.data.is_complete;
                                }
                            }
                            setCompletedLessons(lessonCompletionStatuses);

                            const lastLessonId = findEnrollmentById.data.enrollment.last_lesson_id;

                            if (lastLessonId == null) {
                                // jika last lesson id null, artinya kursus ini baru pertama dibuka.
                                // jika begitu, buka dropdown chapter pertama dan set current lessonnya ke pelajaran pertama dari chapter tersebut

                                setActiveItemId(findCourseContentsById.data.chapters[0].chapter.id);
                                setCurrentLesson(findCourseContentsById.data.chapters[0].lessons[0]);

                                if (findCourseContentsById.data.chapters[0].lessons[0]) {
                                    await startLesson(enrollmentId, findCourseContentsById.data.chapters[0].lessons[0].id);
                                    await setLastAccesLesson(enrollmentId, findCourseContentsById.data.chapters[0].lessons[0].id);
                                }
                            }
                            else {
                                // jika last lesson id tidak null, artinya kursus ini sudah pernah dibuka sebelumnya
                                // jika begitu, cari chapter dan lesson yang sesuai dengan last lesson id tersebut

                                for (const chapter of findCourseContentsById.data.chapters) {
                                    for (const lesson of chapter.lessons) {
                                        if (lesson.id === lastLessonId) {
                                            setActiveItemId(chapter.chapter.id);
                                            setCurrentLesson(lesson);
                                            break;
                                        }
                                    }
                                }
                            }

                            setIsLoading(false);
                        }
                        else {
                            console.log('findCourseContentsById.success not success')
                        }
                    }
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
    }, [enrollmentId]);

    const completeLesson = async (lessonId) => {
        if (lessonId != currentLesson.id) {
            await withReactContent(Swal).fire({
                icon: 'error',
                title: 'Terjadi Kesalahan!',
                text: 'Anda hanya bisa menyelesaikan pelajaran yang sedang diputar!',
            });

            return;
        }

        try {
            let doCompleteLesson = await completeEnrollmentLesson(enrollmentId, lessonId, totalLesson);

            if (doCompleteLesson.success) {
                if (doCompleteLesson.data.is_course_completed == true) {
                    await withReactContent(Swal).fire({
                        icon: 'success',
                        title: 'Selamat!',
                        text: 'Selamat, Anda telah menyelesaikan kursus ini!',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                               window.location.reload();
                            }
                        });
                }
            }
        } catch (error) {
            console.error(error);

            if (error.data) {
                let message = error.message;

                if (error.data.error) {
                    message += `: (${error.data.error})`;
                }

                await withReactContent(Swal).fire({
                    icon: 'error',
                    title: 'Terjadi Kesalahan!',
                    text: message,
                })
                    .then((isConfirmed) => {
                        if (isConfirmed) {
                            window.location.reload();
                        }
                    });
            } else {
                if (error.message) {
                    await withReactContent(Swal).fire({
                        icon: 'error',
                        title: 'Terjadi Kesalahan!',
                        text: error.message,
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        });
                }
            }
        }
    }

    const handleLessonChange = async (lesson) => {
        setCurrentLesson(lesson);

        try {
            const [doSetLastLesson, doStartLesson] = await Promise.all([
                setLastAccesLesson(enrollmentId, lesson.id),
                startLesson(enrollmentId, lesson.id),
            ]);
        } catch (error) {
            console.error(error);

            if (error.data) {
                let message = error.message;

                if (error.data.error) {
                    message += `: (${error.data.error})`;
                }

                await withReactContent(Swal).fire({
                    icon: 'error',
                    title: 'Terjadi Kesalahan!',
                    text: message,
                })
                    .then((isConfirmed) => {
                        if (isConfirmed) {
                            window.location.reload();
                        }
                    });
            } else {
                if (error.message) {
                    await withReactContent(Swal).fire({
                        icon: 'error',
                        title: 'Terjadi Kesalahan!',
                        text: error.message,
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        });
                }
            }
        }
    }

    return (
        <Wrapper needAuth role={STUDENT}>
            <Meta meta={{
                title: pageTitle,
            }}/>

            <Header title={pageTitle}/>
            <PreLoader/>

            <div className="content-wrapper js-content-wrapper overflow-hidden">
                <section className="layout-pt-lg layout-pb-lg lg:pt-40 lg:order-1">
                    <div className="container">
                        <div className="row justify-start marginCustom">
                            <div className="col-xxl-8 col-xl-7 col-lg-8">
                                {
                                    !isLoading && (
                                        <VideoPlayer course={course} currentLesson={currentLesson}/>
                                    )
                                }
                            </div>
                        </div>
                    </div>
                </section>

                <aside className={`lesson-sidebar -type-2 lg:order-2`}>
                    <div className="px-30 sm:px-20">
                        {
                            (!isLoading && enrollment.completion_date != null) && (
                                <div
                                    className="d-flex items-center justify-between bg-success-1 pl-30 pr-20 py-30 rounded-8">
                                    <div className="text-success-2 lh-1 fw-500">
                                        Selamat, Anda telah menyelesaikan kursus ini!
                                    </div>
                                </div>
                            )
                        }

                        {
                            !isLoading && (
                                <>
                                    <div className="accordion -block-2 text-left js-accordion mt-30">
                                        {
                                            lessonItems.map((item, index) => {
                                                return (
                                                    <div
                                                        className={`accordion__item ${activeItemId === item.chapter.id ? "is-active" : ""}`}
                                                        key={index}
                                                    >
                                                        <div
                                                            onClick={() =>
                                                                setActiveItemId((prev) => (prev === item.chapter.id ? 0 : item.chapter.id))
                                                            }
                                                            className="accordion__button py-20 px-30 bg-light-4"
                                                        >
                                                            <div className="d-flex items-center">
                                                                <div className="accordion__icon">
                                                                    <div className="icon">
                                                                        <FontAwesomeIcon icon={faChevronDown}/>
                                                                    </div>
                                                                    <div className="icon">
                                                                        <FontAwesomeIcon icon={faChevronUp}/>
                                                                    </div>
                                                                </div>
                                                                <span className="text-17 fw-500 text-dark-1">
                                                            <small>Chapter {index + 1}</small>: {item.chapter.title}
                                                        </span>
                                                            </div>
                                                        </div>

                                                        <div
                                                            className="accordion__content"
                                                            style={activeItemId === item.chapter.id ? {maxHeight: "700px"} : {}}
                                                        >
                                                            <div className="accordion__content__inner px-30 py-30">
                                                                <div className="y-gap-30">
                                                                    <div className="mb-5">
                                                                        <small>{item.chapter.total_duration} total
                                                                            durasi
                                                                            | {item.chapter.lesson_count} pelajaran</small>
                                                                    </div>

                                                                    {
                                                                        item.lessons.map((lesson, index) => {
                                                                            return (
                                                                                <div
                                                                                    className={`${currentLesson.id === lesson.id ? 'bg-blue-light p-3 rounded-16' : ''}`}
                                                                                    key={index}>
                                                                                    <div className="d-flex">
                                                                                        <div
                                                                                            className="d-flex justify-center items-center size-30 rounded-full bg-purple-3 mr-10">
                                                                                            {
                                                                                                completedLessons[lesson.id] ? (
                                                                                                    <div
                                                                                                        className="icon-check text-9"
                                                                                                    ></div>
                                                                                                ) : (
                                                                                                    <input type={'checkbox'}
                                                                                                           onClick={() => completeLesson(lesson.id)}/>
                                                                                                )
                                                                                            }

                                                                                        </div>
                                                                                        <div className="">
                                                                                            <div
                                                                                                onClick={() => handleLessonChange(lesson)}
                                                                                                style={{cursor: "pointer"}}>{lesson.title}</div>
                                                                                            <div
                                                                                                className="d-flex x-gap-20 items-center pt-5">
                                                                                                        <span
                                                                                                            className="text-14 lh-1 text-purple-1 underline">
                                                                                                            {lesson.content_video_duration}
                                                                                                        </span>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            )
                                                                        })
                                                                    }
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                );
                                            })
                                        }
                                    </div>
                                </>
                            )
                        }
                    </div>
                </aside>

            </div>
        </Wrapper>
    );
}