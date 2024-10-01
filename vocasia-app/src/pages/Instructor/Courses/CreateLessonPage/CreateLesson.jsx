import {Link, useParams} from "react-router-dom";
import React, {useContext, useEffect, useRef, useState} from "react";
import {findCourseById, getChapterById} from "../_actions/CourseAction.jsx";
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {SelectField} from "../../../../components/commons/Input/SelectField.jsx";
import {TinyMCEField} from "../../../../components/commons/Input/TinyMCEField.jsx";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {storeLesson} from "../_actions/LessonAction.jsx";

export const CreateLesson = () => {
    const {sweetAlert} = useContext(AuthContext);

    const {courseId, chapterId} = useParams();

    const [loading, setLoading] = useState(false);

    const [course, setCourse] = useState({});
    const [chapter, setChapter] = useState({});

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
    const contentTextRef = useRef(null);

    const [formData, setFormData] = useState({
        title: '',
        type: 'video',
        need_previous_lesson: false,
        is_free: false,
        content_video_duration: '',
        content_video_url: '',
        content_text: '',
    });

    const [errors, setErrors] = useState({
        title: '',
        type: '',
        need_previous_lesson: '',
        is_free: '',
        content_video_duration: '',
        content_video_url: '',
        content_text: '',
    });

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getCourse = await findCourseById(courseId);
                setCourse(getCourse.course);

                const getChapter = await getChapterById(courseId, chapterId);
                setChapter(getChapter.chapter);
            } catch (e) {
                console.error(e);
            }
        };

        fetchInitialData();
    }, [courseId, chapterId]);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setLoading(true);
        setErrors({});

        const contentTextData = contentTextRef.current ? contentTextRef.current.getContent() : '';

        const finalFormData = {
            ...formData,
            content_text: contentTextData,
        };

        const lesson = await storeLesson(courseId, chapterId, finalFormData, setLoading, setErrors);
        if (lesson) {
            setFormData({
                title: '',
                type: 'video',
                need_previous_lesson: false,
                is_free: false,
                content_video_duration: '',
                content_video_url: '',
                content_text: '',
            });
            setErrors({});
            contentTextRef.current && contentTextRef.current.setContent('');

            sweetAlert.fire({
                icon: 'success',
                title: 'Berhasil',
                text: 'Berhasil menambah pelajaran baru',

            });
        }
    }

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Tambah Pelajaran</h1>
                    <div className="mt-10">Tambah konten kursus</div>
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
                                            label="Perlu Pelajaran Sebelumnya?"
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
                                        label="Jenis Konten"
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
                                            <div className={'col-12 col-md-6'}>
                                                <InputField
                                                    label="URL Video"
                                                    name="content_video_url"
                                                    placeholder="URL Video"
                                                    value={formData.content_video_url}
                                                    onChange={handleChange}
                                                    error={errors.content_video_url}
                                                    isRequired={false}
                                                />
                                            </div>
                                            <div className={'col-12 col-md-6'}>
                                                <InputField
                                                    label="Durasi Video"
                                                    name="content_video_duration"
                                                    placeholder="Durasi Video"
                                                    value={formData.content_video_duration}
                                                    onChange={handleChange}
                                                    error={errors.content_video_duration}
                                                    isRequired={false}
                                                />
                                            </div>
                                        </div>
                                    )
                                }

                                {
                                    formData.type === 'text' && (
                                        <div className="col-12">
                                            <TinyMCEField
                                                onInit={(_evt, editor) => contentTextRef.current = editor}
                                                label="Isi Konten"
                                                error={errors.content_text}
                                            />
                                        </div>
                                    )
                                }

                                <div className="row y-gap-20 justify-end pt-15">
                                    <div className="col-auto">
                                        <button className="button -md -purple-1 text-white" disabled={loading}>
                                            {
                                                loading ? (
                                                    'Menyimpan...'
                                                ) : 'Simpan'
                                            }
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}