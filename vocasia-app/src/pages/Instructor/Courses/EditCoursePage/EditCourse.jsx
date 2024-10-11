import React, {useContext, useEffect, useRef, useState} from "react";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {Link, useNavigate, useParams} from "react-router-dom";
import {getAllCategories} from "../../../../services/courses/categoryService.js";
import {organizeCategories} from "../../../../utils/courses.js";
import {INSTRUCTOR_AUTH_DATA} from "../../../../config/consts.js";
import {createCourse, findCourseById, updateCourse} from "../_actions/CourseAction.jsx";
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {SelectField} from "../../../../components/commons/Input/SelectField.jsx";
import {TinyMCEField} from "../../../../components/commons/Input/TinyMCEField.jsx";

export const EditCourse = () => {
    const {sweetAlert} = useContext(AuthContext);
    const {id} = useParams();

    const [course, setCourse] = useState();
    const [instructor, setInstructor] = useState();
    const [category, setCategory] = useState();

    const [categories, setCategories] = useState([]);
    const [levels, setLevels] = useState([]);
    const [languages, setLanguages] = useState([]);

    const [formData, setFormData] = useState({
        title: '',
        category_id: '',
        short_description: '',
        price: '',
        discount: '0',
        level: 'all',
        language: 'id',
        total_duration: '',
    });
    const [loading, setLoading] = useState(false);
    const [errors, setErrors] = useState({});

    const navigate = useNavigate();

    const shortDescriptionEditorRef = useRef(null);
    const descriptionEditorRef = useRef(null);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const categoriesData = await getAllCategories();

                const levels = {
                    'all': 'Semua',
                    'beginner': 'Pemula',
                    'intermediate': 'Menengah',
                    'expert': 'Expert',
                    'advanced': 'Lanjutan',
                };
                setLevels(Object.entries(levels).map(([key, value]) => ({value: key, label: value})));

                const languages = {
                    'id': 'Bahasa Indonesia',
                    'en': 'English',
                };
                setLanguages(Object.entries(languages).map(([key, value]) => ({value: key, label: value})));

                const getCourseData = await findCourseById(id);

                setCourse(getCourseData.course);
                setInstructor(getCourseData.instructor);
                setCategory(getCourseData.category);

                setFormData({
                    title: getCourseData.course.title,
                    category_id: getCourseData.course.category_id,
                    short_description: getCourseData.course.short_description,
                    price: getCourseData.course.price,
                    discount: getCourseData.course.discount,
                    level: getCourseData.course.level,
                    language: getCourseData.course.language,
                    total_duration: getCourseData.course.total_duration,
                });

                setCategories(organizeCategories(categoriesData, getCourseData.category.id));

                shortDescriptionEditorRef.current.setContent(getCourseData.course.short_description);
                descriptionEditorRef.current.setContent(getCourseData.course.description);
            } catch (error) {
                console.error('Error fetching initial data:', error);
            }
        };

        fetchInitialData();
    }, []);

    const handleChange = (e) => {
        const {name, value} = e.target;

        setFormData((prevData) => ({...prevData, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setLoading(true);
        setErrors({});

        const instructorData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));

        const shortDescriptionContent = shortDescriptionEditorRef.current ? shortDescriptionEditorRef.current.getContent() : '';
        const descriptionContent = descriptionEditorRef.current ? descriptionEditorRef.current.getContent() : '';

        const isFree = formData.price === '0';
        const instructorId = instructorData.id;

        const finalFormData = {
            ...formData,
            short_description: shortDescriptionContent,
            description: descriptionContent,
            is_free: isFree,
            instructor_id: instructorId,
        };

        const course = await updateCourse(id, finalFormData, setLoading, setErrors);

        if (course) {
            let courseId = course.course.id;

            sweetAlert.fire({
                icon: 'success',
                title: 'Berhasil',
                text: 'Berhasil memperbarui data kursus',
            });

        }
    };

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Edit Kursus</h1>
                    <div className="mt-10">Perbarui data kursus kamu</div>
                </div>
            </div>

            <div className="row y-gap-60">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                            <h2 className="text-17 lh-1 fw-500">Informasi Kursus</h2>

                            <Link to={`/instructor/courses/${id}/update-thumbnail`} className="button -sm -dark-2 text-white">
                                Thumbnail <i className={'fa fa-arrow-right ml-5'}/>
                            </Link>
                        </div>

                        <div className="py-30 px-30">
                            <form onSubmit={handleSubmit} className="contact-form row y-gap-30">
                                {
                                    errors.general && (
                                        <div className="col-12">
                                            <div className="text-red-1">
                                                <b>Error:</b> {errors.general}
                                            </div>
                                        </div>
                                    )
                                }

                                <div className="col-12">
                                    <InputField
                                        label="Judul"
                                        name="title"
                                        placeholder="Judul kursus"
                                        value={formData.title}
                                        onChange={handleChange}
                                        error={errors.title}
                                        isRequired={true}
                                    />
                                </div>

                                <div className="col-12">
                                    <SelectField
                                        label="Kategori"
                                        name="category_id"
                                        options={categories}
                                        value={formData.category_id}
                                        onChange={handleChange}
                                        error={errors.category}
                                        placeholder="Pilih kategori"
                                        selectedId={course ? category.id : null}
                                        isRequired={true}
                                    />
                                </div>

                                <div className="row">
                                    <div className="col-12 col-md-6">
                                        <InputField
                                            label="Harga"
                                            name="price"
                                            placeholder="Harga kursus"
                                            value={formData.price}
                                            onChange={handleChange}
                                            error={errors.price}
                                            hint='Masukkan "0" jika kursus ini gratis'
                                            isRequired={true}
                                        />
                                    </div>
                                    <div className="col-12 col-md-6">
                                        <InputField
                                            label="Diskon"
                                            name="discount"
                                            placeholder="Diskon harga"
                                            value={formData.discount}
                                            onChange={handleChange}
                                            error={errors.discount}
                                            hint='Masukkan "0" jika tidak ada diskon'
                                        />
                                    </div>
                                </div>

                                <div className="row">
                                    <div className="col-12 col-md-6">
                                        <SelectField
                                            label="Level"
                                            name="level"
                                            options={levels}
                                            value={formData.level}
                                            onChange={handleChange}
                                            error={errors.level}
                                            placeholder="Pilih level"
                                            isRequired={true}
                                        />
                                    </div>
                                    <div className="col-12 col-md-6">
                                        <SelectField
                                            label="Bahasa"
                                            name="language"
                                            options={languages}
                                            value={formData.language}
                                            onChange={handleChange}
                                            error={errors.language}
                                            placeholder={"Pilih bahasa"}
                                            isRequired={true}
                                        />
                                    </div>
                                </div>

                                <div className="col-12">
                                    <InputField
                                        label="Total Durasi"
                                        name="total_duration"
                                        placeholder="Total durasi kursus"
                                        value={formData.total_duration}
                                        onChange={handleChange}
                                        error={errors.total_duration}
                                    />
                                </div>

                                <div className="col-12">
                                    <TinyMCEField
                                        onInit={(_evt, editor) => shortDescriptionEditorRef.current = editor}
                                        label="Deskripsi Singkat"
                                        error={errors.short_description}
                                    />
                                </div>

                                <div className="col-12">
                                    <TinyMCEField
                                        onInit={(_evt, editor) => descriptionEditorRef.current = editor}
                                        label="Deskripsi"
                                        height="400"
                                        error={errors.description}
                                    />
                                </div>

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