import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {AUTH_USER, INSTRUCTOR, INSTRUCTOR_AUTH_DATA} from "../../../config/consts.js";
import {Meta} from "../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../components/Instructors/InstructorWrapper/Index.jsx";
import React, {useEffect, useRef, useState} from "react";
import {InputField} from "../../../components/commons/Input/InputField.jsx";
import {InputFileField} from "../../../components/commons/Input/InputFileField.jsx";
import {TinyMCEField} from "../../../components/commons/Input/TinyMCEField.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findMyProfile, updateMyProfile} from "../../../services/new/instructor/instructor/profile-service.js";

const metaData = {
    title: 'Profil Saya',
};

export const ProfilePage = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [isSubmitted, setIsSubmitted] = useState(false);

    const [instructor, setInstructor] = useState({});

    const [formData, setFormData] = useState({
        name: '',
        profile_picture: '',
        email: '',
        password: '',
        phone_number: '',
        summary: '',
    });
    const [errors, setErrors] = useState({})

    const summaryEditorRef = useRef(null);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getProfile = await findMyProfile();

                if (getProfile.success) {
                    setTimeout(() => {
                        summaryEditorRef?.current?.setContent(getProfile.data.instructor.summary);
                    }, 1000);

                    setInstructor(getProfile.data.instructor);
                    setFormData({
                        name: getProfile.data.user.name,
                        email: getProfile.data.user.email,
                        phone_number: getProfile.data.instructor.phone_number,
                    });
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

    const handleChange = (e) => {
        if (e.target.type === 'file') {
            setFormData({
                ...formData,
                [e.target.name]: e.target.files[0],
            });
        } else {
            setFormData({
                ...formData,
                [e.target.name]: e.target.value,
            });
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);

        try {
            const formDataToSend = new FormData();

            formDataToSend.append('name', formData.name);
            formDataToSend.append('email', formData.email);
            if (formData.password) {
                formDataToSend.append('password', formData.password);
            }

            formDataToSend.append('phone_number', formData.phone_number);
            formDataToSend.append('profile_picture', formData.profile_picture);
            formDataToSend.append('summary', summaryEditorRef.current.getContent());

            const doUpdateProfile = await updateMyProfile(formDataToSend);

            if (doUpdateProfile.success) {
                await withReactContent(Swal).fire({
                    icon: 'success',
                    title: 'Berhasil!',
                    text: doUpdateProfile.message,
                });

                localStorage.removeItem(AUTH_USER);
                localStorage.removeItem(INSTRUCTOR_AUTH_DATA);

                localStorage.setItem(AUTH_USER, JSON.stringify(doUpdateProfile.data.user));
                localStorage.setItem(INSTRUCTOR_AUTH_DATA, JSON.stringify(doUpdateProfile.data.instructor));

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

            setIsSubmitted(false);
        } finally {
            setIsLoading(false);
        }
    }

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Profil Saya</h1>
                        </div>
                    </div>

                    <div className="row y-gap-60">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div className="d-flex items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Data Diri Saya</h2>
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
                                                        label="Nama"
                                                        name="name"
                                                        placeholder="Nama Lengkap"
                                                        value={formData.name}
                                                        onChange={handleChange}
                                                        error={errors.name}
                                                        isRequired={true}
                                                    />
                                                </div>

                                                <div className="col-12">
                                                    <InputFileField
                                                        label="Foto Profil"
                                                        name="profile_picture"
                                                        placeholder="Foto Profil"
                                                        onChange={handleChange}
                                                        error={errors.profile_picture}
                                                    />
                                                </div>

                                                <div className="row">
                                                    <div className="col-12 col-md-6">
                                                        <InputField
                                                            type={'email'}
                                                            label="Email"
                                                            name="email"
                                                            placeholder="Email"
                                                            value={formData.email}
                                                            onChange={handleChange}
                                                            error={errors.email}
                                                            isRequired={true}
                                                        />
                                                    </div>
                                                    <div className="col-12 col-md-6">
                                                        <InputField
                                                            type={'password'}
                                                            label="Password"
                                                            name="password"
                                                            placeholder="Password akun"
                                                            onChange={handleChange}
                                                            error={errors.password}
                                                        />
                                                    </div>
                                                </div>

                                                <div className="col-12">
                                                    <InputField
                                                        label="No. HP"
                                                        name="phone_number"
                                                        placeholder="No. HP"
                                                        value={formData.phone_number}
                                                        onChange={handleChange}
                                                        error={errors.phone_number}
                                                    />
                                                </div>

                                                <div className="col-12">
                                                    <TinyMCEField
                                                        onInit={(_evt, editor) => summaryEditorRef.current = editor}
                                                        label="Tentang Saya"
                                                        error={errors.summary}
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
    )
}