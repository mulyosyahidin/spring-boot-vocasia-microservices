import React, {useEffect, useState} from "react";
import {getChaptersByCourseId} from "../../_actions/CourseAction.jsx";
import ModalVideoComponent from "../../../../../components/commons/ModalVideo.jsx";
import {getYouTubeVideoId} from "../../../../../utils/utils.js";
import Modal from "react-bootstrap/Modal";

export const Contents = ({ activeTab,courseId, course, isLoading }) => {
    const [isChaptersLoaded, setIsChaptersLoaded] = useState(false);
    const [chapters, setChapters] = useState([]);
    const [currentOpenItem, setCurrentOpenItem] = useState();

    const [selectedVideoId, setSelectedVideoId] = useState('');
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    const handleOpenModal = (videoId) => {
        setSelectedVideoId(videoId);
        setIsModalOpen(true);
    };

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getAllChapters = await getChaptersByCourseId(courseId);
                setChapters(getAllChapters);
            } catch (error) {
                console.error('Error fetching initial data:', error);
            }
            finally {
                setIsChaptersLoaded(true);
            }
        };

        fetchInitialData();
    }, [course.id]);

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 2 ? "is-active" : ""} `}
        >
            {
                !isLoading && isChaptersLoaded && (
                    <>
                        <div className="accordion -block-2 text-left js-accordion">
                            {
                                chapters.map((chapter, index) => (
                                    <div
                                        key={index}
                                        className={`accordion__item -dark-bg-dark-1 mt-10 ${
                                            currentOpenItem === `${index}` ? "is-active" : ""
                                        } `}
                                    >
                                        <div
                                            className="accordion__button py-20 px-30 bg-light-4"
                                            onClick={() =>
                                                setCurrentOpenItem((pre) =>
                                                    pre === `${index}` ? "" : `${index}`,
                                                )
                                            }
                                        >
                                            <div className="d-flex items-center">
                                                <div className="icon icon-drag mr-10"></div>
                                                <span className="text-16 lh-14 fw-500 text-dark-1">
                                                    {chapter.chapter.title}
                                                </span>
                                            </div>

                                            <div className="d-flex x-gap-10 items-center">
                                                <div className="accordion__icon mr-0">
                                                    <div
                                                        className="d-flex items-center justify-center icon icon-chevron-down"></div>
                                                    <div
                                                        className="d-flex items-center justify-center icon icon-chevron-up"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div
                                            className="accordion__content"
                                            style={
                                                currentOpenItem === `${index}`
                                                    ? {maxHeight: "none"}
                                                    : {}
                                            }
                                        >
                                            <div className="accordion__content__inner px-30 py-30">
                                                {
                                                    chapter.lessons.map((lesson, index) => (
                                                        <div key={index}
                                                             className="d-flex x-gap-10 y-gap-10 mb-5 align-items-center">
                                                            {
                                                                lesson.type === 'video' && (
                                                                    <div className="icon icon-play cursor-pointer" style={{cursor: 'pointer'}}
                                                                         onClick={() => handleOpenModal(getYouTubeVideoId(lesson.content_video_url))}></div>
                                                                )
                                                            }

                                                            {
                                                                lesson.type === 'text' && (
                                                                    <div className="icon icon-book"></div>
                                                                )
                                                            }

                                                            <div
                                                                className="text-16 lh-14 fw-500 text-dark-1">
                                                                {lesson.title}

                                                                {
                                                                    lesson.type === 'video' && (
                                                                        <span
                                                                            className="text-14 lh-14 fw-400 text-dark-2 ml-5">
                                                                ({lesson.content_video_duration})
                                                            </span>
                                                                    )
                                                                }
                                                            </div>
                                                        </div>
                                                    ))
                                                }
                                            </div>
                                        </div>
                                    </div>
                                ))
                            }
                        </div>

                        <ModalVideoComponent
                            videoId={selectedVideoId}
                            isOpen={isModalOpen}
                            setIsOpen={setIsModalOpen}
                            onClose={handleCloseModal}
                        />
                    </>
                )
            }

            {
                isLoading || !isChaptersLoaded && (
                    <div className="text-center">
                        <div className="spinner spinner-primary mt-50"></div>
                    </div>
                )
            }
        </div>
    )
}