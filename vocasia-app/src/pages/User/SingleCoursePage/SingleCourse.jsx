import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {Meta} from "../../../components/commons/Meta.jsx";
import {Header} from "./partials/Header.jsx";
import {VideoPlayer} from "./partials/VideoPlayer.jsx";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faChevronDown, faChevronUp} from "@fortawesome/free-solid-svg-icons";

export const SingleCourse = () => {
    const {enrollmentId} = useParams();

    const [pageTitle, setPageTitle] = useState('');
    const [isLoading, setIsLoading] = useState(true);

    const [course, setCourse] = useState(null);
    const [enrollment, setEnrollment] = useState(null);

    const [activeItemId, setActiveItemId] = useState(0);
    const [lessonItems, setLessonItems] = useState([]);
    const [currentLesson, setCurrentLesson] = useState(null);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getEnrollmentData = await getEnrollmentDataById(enrollmentId);

                setPageTitle(getEnrollmentData.course.title);

                setEnrollment(getEnrollmentData.enrollment);
                setCourse(getEnrollmentData.course);

                const getCourseLessonData = await getCourseLessonDataByEnrollment(getEnrollmentData.course.slug, getEnrollmentData.course.id);
                setLessonItems(getCourseLessonData);

                if (getCourseLessonData.length > 0) {
                    setActiveItemId(getCourseLessonData[0].id);
                    setCurrentLesson(getCourseLessonData[0].lessons[0]);
                }
            } catch (error) {
                console.error('Error fetching initial data:', error);
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, [enrollmentId]);

    return (
        <>
            {
                isLoading ? (
                    <div>Loading...</div>
                ) : (
                    <>
                        <Meta meta={{
                            title: pageTitle,
                        }}/>

                        <Header title={pageTitle}/>

                        <div className="content-wrapper js-content-wrapper overflow-hidden">
                            <section className="layout-pt-lg layout-pb-lg lg:pt-40 lg:order-1">
                                <div className="container">
                                    <div className="row justify-start marginCustom">
                                        <div className="col-xxl-8 col-xl-7 col-lg-8">
                                            <VideoPlayer course={course} currentLesson={currentLesson}/>
                                        </div>
                                    </div>
                                </div>
                            </section>

                            <aside className={`lesson-sidebar -type-2 lg:order-2`}>
                                <div className="px-30 sm:px-20">
                                    <div className="accordion -block-2 text-left js-accordion mt-30">
                                        {lessonItems.map((item, index) => (
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
                                                            <small>Chapter {index+1}</small>: {item.chapter.title}
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
                                                                <small>{item.chapter.total_duration} total durasi | {item.chapter.lesson_count} pelajaran</small>
                                                            </div>

                                                            {item.lessons.map((lesson, index) => (
                                                                <div className={`${currentLesson.id === lesson.id ? 'bg-blue-light p-3 rounded-16' : ''}`} key={index}>
                                                                    <div className="d-flex">
                                                                        <div
                                                                            className="d-flex justify-center items-center size-30 rounded-full bg-purple-3 mr-10">
                                                                            <div className="icon-play text-9" style={{cursor: 'pointer'}}
                                                                                 onClick={() => setCurrentLesson(lesson)}
                                                                            ></div>
                                                                        </div>
                                                                        <div className="">
                                                                            <div onClick={() => setCurrentLesson(lesson)} style={{cursor: "pointer"}}>{lesson.title}</div>
                                                                            <div className="d-flex x-gap-20 items-center pt-5">
                                                                                <span className="text-14 lh-1 text-purple-1 underline">
                                                                                    {lesson.content_video_duration}
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            ))}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        ))}
                                    </div>
                                </div>
                            </aside>

                        </div>
                    </>
                )
            }
        </>
    )
}
