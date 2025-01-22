import {Link, useParams} from "react-router-dom";
import {INSTRUCTOR} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {InstructorWrapper} from "../../../../../components/Instructors/InstructorWrapper/Index.jsx";
import React, {useState} from "react";
import {InputField} from "../../../../../components/commons/Input/InputField.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {createChapter} from "../../../../../services/new/course/instructor/chapter-service.js";

const metaData = {
    title: 'Tambah Bab Kursus',
};

export const ChapterCreatePage = () => {
    const {id} = useParams();

    const [isSubmitted, setIsSubmitted] = useState(false);

    const [errors, setErrors] = useState({});
    const [formData, setFormData] = useState({});

    const handleChange = (e) => {
        const {name, value} = e.target;

        setFormData((prevData) => ({...prevData, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);
        setErrors({});

        try {
            const doCreateChapter = await createChapter(id, formData);

            if (doCreateChapter.success) {
                await withReactContent(Swal).fire({
                    title: 'Berhasil!',
                    text: doCreateChapter.message,
                    icon: 'success',
                }).then((isConfirmed) => {
                    if (isConfirmed) {
                        setFormData({
                            title: '',
                            total_duration: ''
                        });
                    }
                });

                setIsSubmitted(false);
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
                            <h1 className="text-30 lh-12 fw-700">Tambah Bab Kursus</h1>
                        </div>
                    </div>

                    <div className="row y-gap-60">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Data Bab Kursus</h2>

                                    <Link to={`/instructor/courses/${id}/chapters`} className={'-md -white text-dark-1'}>
                                        <i className="fa fa-arrow-left mr-10 ml-10"/>
                                        Kembali
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

                                        <div className="row">
                                            <div className="col-12 col-md-6">
                                                <InputField
                                                    label="Judul"
                                                    name="title"
                                                    placeholder="Judul bab"
                                                    value={formData.title}
                                                    onChange={handleChange}
                                                    error={errors.title}
                                                    isRequired={true}
                                                />
                                            </div>

                                            <div className="col-12 col-md-6">
                                                <InputField
                                                    label="Total Durasi"
                                                    name="total_duration"
                                                    placeholder="Total durasi bab"
                                                    value={formData.total_duration}
                                                    onChange={handleChange}
                                                    error={errors.total_duration}
                                                    isRequired={true}
                                                />
                                            </div>
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
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </InstructorWrapper>

        </Wrapper>
    )
}