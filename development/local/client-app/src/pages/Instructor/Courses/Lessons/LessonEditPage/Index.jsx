import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {Link, useParams} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import {findById as findCourseById} from "../../../../../services/new/course/instructor/course-service.js";
import {findById as findChapterById} from "../../../../../services/new/course/instructor/chapter-service.js";
import {findById as findLessonById} from "../../../../../services/new/course/instructor/lesson-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {InputField} from "../../../../../components/commons/Input/InputField.jsx";
import {SelectField} from "../../../../../components/commons/Input/SelectField.jsx";
import {TinyMCEField} from "../../../../../components/commons/Input/TinyMCEField.jsx";
import {updateLesson} from "../../../../../services/new/course/instructor/lesson-service.js";
import {InputFileField} from "../../../../../components/commons/Input/InputFileField.jsx";

const metaData = {
    title: 'Edit Pelajaran',
};

export const LessonEditPage = () => {
    const {courseId, chapterId, lessonId} = useParams();

    const [isLoading, setIsLoading] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);

    const [course, setCourse] = useState({});
    const [chapter, setChapter] = useState({});
    const [lesson, setLesson] = useState({});

    const [costs] = useState([
        {value: true, label: 'Ya'},
        {value: false, label: 'Tidak'},
    ]);
    const [needPreviousLesson] = useState([
        {value: true, label: 'Ya'},
        {value: false, label: 'Tidak'},
    ]);
    const [types, setTypes] = useState([
        {value: 'video', label: 'Video'},
        {value: 'text', label: 'Teks'},
    ]);
    const [attachmentTypes, setAttachmentTypes] = useState([
        {value: 'file', label: 'File'},
        {value: 'link', label: 'Tautan'},
    ]);
    const [hasAttachment, setHasAttachment] = useState(false);
    const contentTextRef = useRef(null);

    const [formData, setFormData] = useState({
        title: '',
        type: 'video',
        need_previous_lesson: false,
        is_free: false,
        content_video_duration: '',
        content_video_url: '',
        content_text: '',
        attachment_type: 'file',
        attachment_file_name: '',
        attachment_file: '',
        attachment_link: '',
        attachment_link_name: '',
        remove_attachment: false,
    });

    const [errors, setErrors] = useState({
        title: '',
        type: '',
        need_previous_lesson: '',
        is_free: '',
        content_video_duration: '',
        content_video_url: '',
        content_text: '',
        attachment_type: '',
        attachment_file_name: '',
        attachment_file: '',
        attachment_link: '',
        attachment_link_name: '',
        remove_attachment: '',
    });

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const [getCourseById, getChapterById, getLessonById] = await Promise.all([
                    findCourseById(courseId),
                    findChapterById(courseId, chapterId),
                    findLessonById(courseId, chapterId, lessonId)
                ]);

                if (getCourseById.success) {
                    setCourse(getCourseById.data.course);
                }

                if (getChapterById.success) {
                    setChapter(getChapterById.data.chapter);
                }

                if (getLessonById.success) {
                    setLesson(getLessonById.data.lesson);
                    setFormData({
                        title: getLessonById.data.lesson.title,
                        type: getLessonById.data.lesson.type,
                        need_previous_lesson: getLessonById.data.lesson.need_previous_lesson,
                        is_free: getLessonById.data.lesson.is_free,
                        content_video_duration: getLessonById.data.lesson.content_video_duration,
                        content_video_url: getLessonById.data.lesson.content_video_url,
                        content_text: getLessonById.data.lesson.content_text,
                        attachment_type: getLessonById.data.lesson.attachment_type,
                        attachment_file_name: getLessonById.data.lesson.attachment_file_name,
                        attachment_link: getLessonById.data.lesson.attachment_link,
                        attachment_link_name: getLessonById.data.lesson.attachment_link_name,
                    });

                    if (getLessonById.data.lesson.attachment_type === 'file') {
                        if (getLessonById.data.lesson.attachment_file_name) {
                            setHasAttachment(true);
                        }
                    } else if (getLessonById.data.lesson.attachment_type === 'link') {
                        if (getLessonById.data.lesson.attachment_link) {
                            setHasAttachment(true);
                        }
                    }
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
                        });
                }
            }
        };

        fetchInitialData();
    }, [courseId, chapterId, lessonId]);

    const handleChange = (e) => {
        if (e.target.type === 'file') {
            setFormData({
                ...formData,
                [e.target.name]: e.target.files[0],
            });
        }
        else if (e.target.type === 'checkbox') {
            setFormData({
                ...formData,
                [e.target.name]: e.target.checked,
            });
        }
        else {
            setFormData({
                ...formData,
                [e.target.name]: e.target.value,
            });
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);
        setErrors({
            title: '',
            type: '',
            need_previous_lesson: '',
            is_free: '',
            content_video_duration: '',
            content_video_url: '',
            content_text: '',
            attachment_type: '',
            attachment_file_name: '',
            attachment_file: '',
            attachment_link: '',
            attachment_link_name: '',
            remove_attachment: '',
        });

        try {
            const contentTextData = contentTextRef.current ? contentTextRef.current.getContent() : '';

            const finalFormData = new FormData();

            finalFormData.append('title', formData.title);
            finalFormData.append('type', formData.type);
            finalFormData.append('need_previous_lesson', formData.need_previous_lesson);
            finalFormData.append('is_free', formData.is_free);
            finalFormData.append('content_video_duration', formData.content_video_duration);
            finalFormData.append('content_video_url', formData.content_video_url);
            finalFormData.append('content_text', contentTextData);
            finalFormData.append('attachment_type', formData.attachment_type);
            finalFormData.append('attachment_file_name', formData.attachment_file_name);
            finalFormData.append('attachment_file', formData.attachment_file);
            finalFormData.append('attachment_link', formData.attachment_link);
            finalFormData.append('attachment_link_name', formData.attachment_link_name);
            finalFormData.append('remove_attachment', formData.remove_attachment ? 1 : 0);

            const doUpdateLesson = await updateLesson(courseId, chapterId, lessonId, finalFormData);
            if (doUpdateLesson.success) {
                await withReactContent(Swal).fire({
                    title: 'Berhasil!',
                    text: doUpdateLesson.message,
                    icon: 'success',
                }).then((isConfirmed) => {
                    if (isConfirmed) {
                        window.location.reload();
                    }
                });
            }
        } catch (error) {
            console.error(error);

            if (error.errors) {
                const getErrors = error.errors;
                const newErrors = {};

                Object.keys(getErrors).forEach((field) => {
                    newErrors[field] = getErrors[field][0];
                });

                setErrors(newErrors);
                setIsSubmitted(false);
            } else if (error.data) {
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
                    setErrors({general: error.message});
                }
            }

            setIsSubmitted(false);
        }
    };

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Edit Pelajaran</h1>
                            <div className="mt-10">Edit konten kursus</div>
                        </div>
                    </div>

                    <div className="row y-gap-60">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                                    <h2 className="text-17 lh-1 fw-500">{course.title}: {chapter.title}</h2>

                                    <div className={'d-flex justify-content-between gap-2'}>
                                        <Link to={`/instructor/courses/${courseId}/chapters/${chapterId}/lessons`}
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
                                            <form onSubmit={handleSubmit} className="contact-form row y-gap-30">
                                                <div className="col-12">
                                                    <InputField
                                                        label="Judul"
                                                        name="title"
                                                        placeholder="Judul Pelajaran"
                                                        value={formData.title}
                                                        onChange={handleChange}
                                                        error={errors.title}
                                                        isRequired={true}
                                                    />
                                                </div>

                                                <div className="row">
                                                    <div className="col-12 col-md-6">
                                                        <SelectField
                                                            label="Gratis?"
                                                            name="is_free"
                                                            options={costs}
                                                            value={formData.is_free}
                                                            onChange={handleChange}
                                                            error={errors.is_free}
                                                            isRequired={true}
                                                        />
                                                    </div>

                                                    <div className="col-12 col-md-6">
                                                        <SelectField
                                                            label="Butuh Pelajaran Sebelumnya?"
                                                            name="need_previous_lesson"
                                                            options={needPreviousLesson}
                                                            value={formData.need_previous_lesson}
                                                            onChange={handleChange}
                                                            error={errors.need_previous_lesson}
                                                            isRequired={true}
                                                        />
                                                    </div>
                                                </div>

                                                <div className="col-12">
                                                    <SelectField
                                                        label="Tipe Konten"
                                                        name="type"
                                                        options={types}
                                                        value={formData.type}
                                                        onChange={handleChange}
                                                        error={errors.type}
                                                        isRequired={true}
                                                    />
                                                </div>

                                                {
                                                    formData.type === 'video' && (
                                                        <div className="row">
                                                            <div className="col-12 col-md-6">
                                                                <InputField
                                                                    label="URL Video"
                                                                    name="content_video_url"
                                                                    placeholder="Masukkan URL Video"
                                                                    value={formData.content_video_url}
                                                                    onChange={handleChange}
                                                                    error={errors.content_video_url}
                                                                    isRequired={true}
                                                                />
                                                            </div>
                                                            <div className="col-12 col-md-6">
                                                                <InputField
                                                                    label="Durasi Video (detik)"
                                                                    name="content_video_duration"
                                                                    placeholder="Durasi Video (detik)"
                                                                    value={formData.content_video_duration}
                                                                    onChange={handleChange}
                                                                    error={errors.content_video_duration}
                                                                    isRequired={true}
                                                                />
                                                            </div>
                                                        </div>
                                                    )
                                                }

                                                {
                                                    formData.type === 'text' && (
                                                        <div className="row">
                                                            <div className="col-12">
                                                                <TinyMCEField
                                                                    onInit={(_evt, editor) => contentTextRef.current = editor}
                                                                    ref={contentTextRef}
                                                                    label="Konten Teks"
                                                                    name="content_text"
                                                                    placeholder="Konten Teks"
                                                                    defaultValue={formData.content_text}
                                                                    error={errors.content_text}
                                                                    isRequired={true}
                                                                />
                                                            </div>
                                                        </div>
                                                    )
                                                }

                                                <div className="col-12">
                                                    <SelectField
                                                        label="Jenis Lampiran"
                                                        name="attachment_type"
                                                        options={attachmentTypes}
                                                        value={formData.attachment_type}
                                                        onChange={handleChange}
                                                        error={errors.attachment_type}
                                                    />
                                                </div>

                                                {
                                                    hasAttachment && (
                                                        <div className="col-12">
                                                            <div className="row y-gap-10">
                                                                <div className="col-auto">
                                                                    <input type="checkbox"
                                                                           name="remove_attachment"
                                                                           id="remove_attachment"
                                                                           checked={formData.remove_attachment}
                                                                           onChange={handleChange}
                                                                    />
                                                                    <label htmlFor="remove_attachment" className="ml-5">Hapus
                                                                        lampiran</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    )
                                                }

                                                {
                                                    formData.attachment_type === 'file' && (
                                                        <div className="row">
                                                            <div className="col-12 col-md-6">
                                                                <InputFileField
                                                                    label="File Lampiran"
                                                                    name="attachment_file"
                                                                    placeholder="File lampiran"
                                                                    value={formData.attachment_file}
                                                                    onChange={handleChange}
                                                                    error={errors.attachment_file}
                                                                />
                                                            </div>

                                                            <div className="col-12 col-md-6">
                                                                <InputField
                                                                    label="Nama File Lampiran"
                                                                    name="attachment_file_name"
                                                                    placeholder="Nama file lampiran"
                                                                    value={formData.attachment_file_name}
                                                                    onChange={handleChange}
                                                                    error={errors.attachment_file_name}
                                                                />
                                                            </div>
                                                        </div>
                                                    )
                                                }

                                                {
                                                    formData.attachment_type === 'link' && (
                                                        <div className="row">
                                                            <div className="col-12 col-md-6">
                                                                <InputField
                                                                    label="Tautan"
                                                                    name="attachment_link"
                                                                    placeholder="Tautan"
                                                                    value={formData.attachment_link}
                                                                    onChange={handleChange}
                                                                    error={errors.attachment_link}
                                                                />
                                                            </div>
                                                            <div className="col-12 col-md-6">
                                                                <InputField
                                                                    label="Nama Tautan"
                                                                    name="attachment_link_name"
                                                                    placeholder="Nama tautan lampiran"
                                                                    value={formData.attachment_link_name}
                                                                    onChange={handleChange}
                                                                    error={errors.attachment_link_name}
                                                                />
                                                            </div>
                                                        </div>
                                                    )
                                                }

                                                <div className="row y-gap-20 justify-end pt-15">
                                                    <div className="col-auto">
                                                        <button className="button -md -purple-1 text-white"
                                                                disabled={isSubmitted}>
                                                            {
                                                                isSubmitted ? (
                                                                    'Menyimpan...'
                                                                ) : 'Simpan'
                                                            }
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        )
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </InstructorWrapper>
        </Wrapper>
    );
};
