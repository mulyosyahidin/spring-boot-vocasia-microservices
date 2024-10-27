import React, {useEffect, useState} from "react";
import ModalVideoComponent from "../../../../../components/commons/ModalVideo.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAll} from "../../../../../services/new/course/instructor/chapter-service.js";
import {getYouTubeVideoId} from "../../../../../utils/new-utils.js";

export const Contents = ({activeTab, courseId}) => {
    const [isLoading, setIsLoading] = useState(false);
    const [data, setData] = useState([]);

    const [currentOpenItem, setCurrentOpenItem] = useState('');
    const [selectedVideoId, setSelectedVideoId] = useState('');
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    const handleOpenModal = (videoId) => {
        setSelectedVideoId(videoId);
        setIsModalOpen(true);
    }

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllCourseChapters = await findAll(courseId);

                if (findAllCourseChapters.success) {
                    setData(findAllCourseChapters.data.data);
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
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 2 ? "is-active" : ""} `}
        >
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
                        <div className="accordion -block-2 text-left js-accordion">
                            {
                                data.map((chapter, index) => (
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

        </div>
    )
}