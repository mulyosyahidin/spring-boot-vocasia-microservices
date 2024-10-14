import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import React, {useEffect, useRef, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {SelectField} from "../../../../components/commons/Input/SelectField.jsx";
import {TinyMCEField} from "../../../../components/commons/Input/TinyMCEField.jsx";
import {findAll} from "../../../../services/new/course/instructor/category-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {organizeCategoriesWithChildren} from "../../../../utils/new-utils.js";
import {createCourse} from "../../../../services/new/course/instructor/course-service.js";

const metaData = {
    title: 'Buat Kursus Baru',
};

export const CreateCoursePage = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);

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
    const [errors, setErrors] = useState({});

    const navigate = useNavigate();
    const shortDescriptionEditorRef = useRef(null);
    const descriptionEditorRef = useRef(null);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getAllCategories = await findAll();

                if (getAllCategories.success) {
                    setCategories(organizeCategoriesWithChildren(getAllCategories.data.data));

                    const levels = {
                        'all': 'Semua',
                        'beginner': 'Pemula',
                        'intermediate': 'Menengah',
                        'expert': 'Expert',
                        'advanced': 'Lanjutan',
                    };
                    const languages = {
                        'id': 'Bahasa Indonesia',
                        'en': 'English',
                    };

                    setLevels(Object.entries(levels).map(([key, value]) => ({value: key, label: value})));
                    setLanguages(Object.entries(languages).map(([key, value]) => ({value: key, label: value})));

                    setIsLoading(false);
                }
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
        };

        fetchInitialData();
    }, []);

    const handleChange = (e) => {
        const {name, value} = e.target;

        setFormData((prevData) => ({...prevData, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);
        setErrors({});

        try {
            const shortDescriptionContent = shortDescriptionEditorRef.current ? shortDescriptionEditorRef.current.getContent() : '';
            const descriptionContent = descriptionEditorRef.current ? descriptionEditorRef.current.getContent() : '';

            const data = {
                ...formData,
                short_description: shortDescriptionContent,
                description: descriptionContent,
            };

            const doCreateCourse = await createCourse(data);

            if (doCreateCourse.success) {
                await withReactContent(Swal).fire({
                    title: 'Berhasil!',
                    text: doCreateCourse.message,
                    icon: 'success',
                })
                    .then(() => {
                        navigate(`/instructor/courses/${doCreateCourse.data.course.id}/update-thumbnail`);
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
                            <h1 className="text-30 lh-12 fw-700">Buat Kursus Baru</h1>
                            <div className="mt-10">Tambahkan kursus kamu secara mandiri.</div>
                        </div>
                    </div>

                    <div className="row y-gap-60">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Informasi Kursus</h2>

                                    <Link to={'/instructor/courses'} className={'-md -white text-dark-1'}>
                                        <i className="fa fa-arrow-left mr-10 ml-10"/>
                                        Kembali
                                    </Link>
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
                                                        error={errors.category_id}
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
}