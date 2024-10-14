import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {Link, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import ModalVideoComponent from "../../../../../components/commons/ModalVideo.jsx";
import {findById as findCourseById} from "../../../../../services/new/course/instructor/course-service.js";
import {deleteById, findAll} from "../../../../../services/new/course/instructor/lesson-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {getYouTubeVideoId} from "../../../../../utils/new-utils.js";
import {findById as findChapterById} from "../../../../../services/new/course/instructor/chapter-service.js";

const metaData = {
    title: 'Kelola Pelajaran',
};

export const LessonIndexPage = () => {
    const {courseId, chapterId} = useParams();

    const [isLoading, setIsLoading] = useState(false);
    const [currentOpenItem, setCurrentOpenItem] = useState('');

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedVideoId, setSelectedVideoId] = useState('');

    const [course, setCourse] = useState({});
    const [chapter, setChapter] = useState({});
    const [lessons, setLessons] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const [getCourseById, getChapterById, findLessons] = await Promise.all([
                    findCourseById(courseId),
                    findChapterById(courseId, chapterId),
                    findAll(courseId, chapterId)
                ]);

                if (getCourseById.success) {
                    setCourse(getCourseById.data.course);
                }

                if (getChapterById.success) {
                    setChapter(getChapterById.data.chapter);
                }

                if (findLessons.success) {
                    setLessons(findLessons.data.lessons);
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
    }, []);

    const handleOpenModal = (videoId) => {
        setSelectedVideoId(videoId);
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    const confirmDelete = async (lessonId) => {
        await withReactContent(Swal).fire({
            title: 'Apakah Anda yakin?',
            text: 'Yakin ingin menghapus pelajaran? Tindakan ini tidak dapat dibatalkan',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Ya, hapus!',
            cancelButtonText: 'Batal',
        })
            .then(async (result) => {
                if (result.isConfirmed) {
                    try {
                        const doDeleteLesson = await deleteById(courseId, chapterId, lessonId);

                        if (doDeleteLesson.success) {
                            await withReactContent(Swal).fire({
                                title: 'Berhasil!',
                                text: doDeleteLesson.message,
                                icon: 'success',
                            })
                                .then((isConfirmed) => {
                                    if (isConfirmed) {
                                        setLessons((pre) => pre.filter((lesson) => lesson.id !== lessonId));
                                    }
                                })
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
            });
    }

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
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
                                    className="d-flex flex-wrap  items-center py-20 px-30 border-bottom-light justify-content-between">
                                    <h2 className="text-17 lh-1 fw-500">
                                        {
                                            isLoading ? '...' : (
                                                <>
                                                    {course.title}: {chapter.title}
                                                </>
                                            )
                                        }
                                    </h2>

                                    <div className={'d-flex justify-content-between'}>
                                        <Link
                                            to={`/instructor/courses/${courseId}/chapters/${chapterId}/lessons/create`}
                                            className={`button -sm -dark-2 text-white mr-10`}>
                                            Tambah Pelajaran
                                        </Link>

                                        <Link to={`/instructor/courses/${courseId}/chapters`}
                                              className={`button -sm -dark-2 text-white`}>
                                            Kembali
                                        </Link>
                                    </div>
                                </div>

                                <div className="py-30 px-30">
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
                                            <div className="accordion -block-2 text-left js-accordion">
                                                {
                                                    lessons.map((lesson, index) => (
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
                                                                {lesson.title}
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
                                                                    <div className="d-flex justify-content-between">
                                                                        <div>
                                                                            <span
                                                                                className="badge badge-small mr-5 px-2 text-white bg-purple-1"
                                                                                style={{fontSize: '12px'}}>{lesson.type}
                                                                            </span>
                                                                            {lesson.type === 'video' && (
                                                                                <span
                                                                                    className="badge badge-small px-2 text-white bg-purple-1"
                                                                                    style={{fontSize: '12px'}}>
                                                                                        {lesson.content_video_duration}
                                                                                </span>
                                                                            )}
                                                                        </div>

                                                                        <div>
                                                                            <Link to={`/instructor/courses/${courseId}/chapters/${chapterId}/lessons/${lesson.id}/edit`} className="icon icon-edit mr-5"></Link>
                                                                            <a href="#" className="icon icon-bin" onClick={() => confirmDelete(lesson.id)}></a>
                                                                        </div>
                                                                    </div>

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
                                        )
                                    }
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
            </InstructorWrapper>
        </Wrapper>
    );
}