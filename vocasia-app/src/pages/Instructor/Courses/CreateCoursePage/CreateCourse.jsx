import React, {useContext, useEffect, useRef, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {getAllCategories} from '../../../../services/courses/categoryService.js';
import {InputField} from '../../../../components/commons/Input/InputField.jsx';
import {SelectField} from '../../../../components/commons/Input/SelectField.jsx';
import {TinyMCEField} from '../../../../components/commons/Input/TinyMCEField.jsx';
import {organizeCategories} from '../../../../utils/courses.js';
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {createCourse} from "../_actions/CourseAction.jsx";
import {apiBaseUrl, AUTH_USER, INSTRUCTOR_AUTH_DATA} from "../../../../config/consts.js";
import {COURSES_CREATE_NEW_COURSE} from "../../../../config/endpoints.js";

export const CreateCourse = () => {
    const {sweetAlert} = useContext(AuthContext);

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

                setCategories(organizeCategories(categoriesData.categories));

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

        const course = await createCourse(finalFormData, setLoading, setErrors);

        if (course) {
            let courseId = course.course.id;

            sweetAlert.fire({
                icon: 'success',
                title: 'Berhasil',
                text: 'Berhasil menambahkan kursus baru. Silahkan lengkapi data lainnya.',

            }).then(() => {
                navigate(`/instructor/courses/${courseId}/update-thumbnail`);
            })

        }
    };

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Buat Kursus Baru</h1>
                    <div className="mt-10">Tambahkan kursus kamu secara mandiri.</div>
                </div>
            </div>

            <div className="row y-gap-60">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div className="d-flex items-center py-20 px-30 border-bottom-light">
                            <h2 className="text-17 lh-1 fw-500">Informasi Kursus</h2>
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
};
