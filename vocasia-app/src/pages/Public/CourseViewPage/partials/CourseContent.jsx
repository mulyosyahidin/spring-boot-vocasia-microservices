import {faChevronDown, faChevronUp} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

import React, {useEffect, useState} from "react";
import {lessonItems} from "../data/about-course.js";
import ModalVideoComponent from "../../../../components/commons/ModalVideo.jsx";
import {getCourseContents} from "../../../../services/courses/public-course.js";

export default function CourseContent({chapters, course}) {
    const [activeItemId, setActiveItemId] = useState(0);
    const [isOpen, setIsOpen] = useState(false);

    const [loading, setLoading] = useState(false);

    return (
        <>
            <div id="course-content" className="pt-60 lg:pt-40">
                <h2 className="text-20 fw-500">Konten</h2>

                <div className="d-flex justify-between items-center mt-30">
                    <div className="">{course.chapter_count} bab • {course.lesson_count} pelajaran</div>
                </div>

                <div className="mt-10">
                    {
                        loading && (
                            <>
                                Memuat konten kursus...
                            </>
                        )
                    }

                    {
                        !loading && (
                            <div className="accordion -block-2 text-left js-accordion">
                                {chapters.map((item, i) => (
                                    <div
                                        key={i}
                                        className={`accordion__item ${
                                            activeItemId === item.chapter.id ? "is-active" : ""
                                        } `}
                                    >
                                        <div
                                            onClick={() =>
                                                setActiveItemId((pre) => (pre === item.chapter.id ? 0 : item.chapter.id))
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
                                                    {item.chapter.title}
                                                </span>
                                            </div>

                                            <div>
                                                {item.chapter.lesson_count} pelajaran • {item.chapter.total_duration} durasi
                                            </div>
                                        </div>

                                        <div
                                            className="accordion__content"
                                            style={activeItemId === item.chapter.id ? {maxHeight: "700px"} : {}}
                                        >
                                            <div className="accordion__content__inner px-30 py-30">
                                                <div className="y-gap-20">
                                                    {item.lessons.map((lesson, index) => (
                                                        <div key={index} className="d-flex justify-between">
                                                            <div className="d-flex items-center">
                                                                <div
                                                                    className="d-flex justify-center items-center size-30 rounded-full bg-purple-3 mr-10">
                                                                    {
                                                                        lesson.type === 'video' ? (
                                                                            <div className="icon-play text-9"></div>
                                                                        ) : (<div className="icon-book text-9"></div>)
                                                                    }
                                                                </div>
                                                                <div>{lesson.title}</div>
                                                            </div>

                                                            {
                                                                lesson.type === 'video' && (
                                                                    <div className="d-flex x-gap-20 items-center">
                                                                        <span className="text-14 lh-1 text-purple-1 underline">
                                                                            {lesson.content_video_duration}
                                                                        </span>
                                                                    </div>
                                                                )
                                                            }
                                                        </div>
                                                    ))}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        )
                    }
                </div>
            </div>
            <ModalVideoComponent
                isOpen={isOpen}
                setIsOpen={setIsOpen}
                videoId={"LlCwHnp3kL4"}
            />
        </>
    );
}
