import {Link, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {findCourseById, getChapterById} from "../_actions/CourseAction.jsx";
import {getChapterLessons} from "../_actions/LessonAction.jsx";
import ModalVideoComponent from "../../../../components/commons/ModalVideo.jsx";
import {getYouTubeVideoId} from "../../../../utils/utils.js";

export const ManageLessons = () => {
    const {courseId, chapterId} = useParams();

    const [currentOpenItem, setCurrentOpenItem] = useState('');

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedVideoId, setSelectedVideoId] = useState('');

    const [course, setCourse] = useState({});
    const [chapter, setChapter] = useState({});
    const [lessons, setLessons] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getCourse = await findCourseById(courseId);
                setCourse(getCourse.course);

                const getChapter = await getChapterById(courseId, chapterId);
                setChapter(getChapter.chapter);

                console.log('[1] getting lessonss...');
                const getLessons = await getChapterLessons(courseId, chapterId);
                setLessons(getLessons.lessons);
                console.log('[1]  lessons: ', getLessons);
                // console.log(getLessons);
            } catch (e) {
                console.error(e);
            }
        };

        fetchInitialData();
    }, [courseId, chapterId]);

    const handleOpenModal = (videoId) => {
        setSelectedVideoId(videoId);
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Kelola Pelajaran</h1>
                    <div className="mt-10">Kelola konten kursus</div>
                </div>
            </div>

            <div className="row y-gap-60">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div
                            className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                            <h2 className="text-17 lh-1 fw-500">{course.title}: {chapter.title}</h2>

                            <div className={'d-flex justify-content-between'}>
                                <Link to={`/instructor/courses/${courseId}/chapters/${chapterId}/lessons/create`}
                                      className={`button -sm -dark-2 text-white`}>
                                    Tambah Pelajaran
                                </Link>

                                <Link to={`/instructor/courses/${courseId}/chapters`}
                                      className={`button -sm -dark-2 text-white`}>
                                    Kembali
                                </Link>
                            </div>
                        </div>

                        <div className="py-30 px-30">
                            <div className={'row pt-30'}>
                                <div className="col-12">
                                    <div className="accordion -block-2 text-left js-accordion">
                                        {
                                            lessons.map((lesson, index) => (
                                                <div
                                                    key={index}
                                                    className={`accordion__item -dark-bg-dark-1 mt-10 ${
                                                        currentOpenItem == `${index}` ? "is-active" : ""
                                                    } `}
                                                >
                                                    <div
                                                        className="accordion__button py-20 px-30 bg-light-4"
                                                        onClick={() =>
                                                            setCurrentOpenItem((pre) =>
                                                                pre == `${index}` ? "" : `${index}`,
                                                            )
                                                        }
                                                    >
                                                        <div className="d-flex items-center">
                                                            <div className="icon icon-drag mr-10"></div>
                                                            <span className="text-16 lh-14 fw-500 text-dark-1">
                                                                {lesson.title}
                                                            </span>
                                                        </div>

                                                        <div className="d-flex x-gap-10 items-center">
                                                            <a href="#" className="icon icon-edit mr-5"></a>
                                                            <a href="#" className="icon icon-bin"></a>
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
                                                            currentOpenItem == `${index}`
                                                                ? {maxHeight: "none"}
                                                                : {}
                                                        }
                                                    >
                                                        <div className="accordion__content__inner px-30 py-30">
                                                            <span
                                                                className="badge badge-small mr-5 px-2 text-white bg-purple-1"
                                                                style={{fontSize: '12px'}}>
                                                                {lesson.type}
                                                            </span>
                                                            {lesson.type === 'video' && (
                                                                <span
                                                                    className="badge badge-small px-2 text-white bg-purple-1"
                                                                    style={{fontSize: '12px'}}>
                                                                    {lesson.content_video_duration}
                                                                </span>
                                                            )}

                                                            {lesson.type === 'video' && (
                                                                <button
                                                                    className="button -sm -dark-2 text-white mt-10"
                                                                    onClick={() => handleOpenModal(getYouTubeVideoId(lesson.content_video_url))}>
                                                                    <span className="icon-play text-18"></span>
                                                                </button>
                                                            )}

                                                            {
                                                                lesson.type === 'text' && (
                                                                    <div className={'mt-10'}>
                                                                        <div
                                                                            dangerouslySetInnerHTML={{__html: lesson.content_text}}
                                                                        />
                                                                    </div>
                                                                )
                                                            }
                                                        </div>

                                                    </div>
                                                </div>
                                            ))
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <ModalVideoComponent
                videoId={selectedVideoId}
                isOpen={isModalOpen}
                setIsOpen={setIsModalOpen}
                onClose={handleCloseModal}
            />
        </div>
    );
}