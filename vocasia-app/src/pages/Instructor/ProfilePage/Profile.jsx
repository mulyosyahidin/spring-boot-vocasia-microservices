import React, {useContext, useEffect, useRef, useState} from "react";
import {InputField} from "../../../components/commons/Input/InputField.jsx";
import {TinyMCEField} from "../../../components/commons/Input/TinyMCEField.jsx";
import {getInstructorProfile, updateInstructorProfile} from "../../../services/instructors/profile-service.js";
import {InputFileField} from "../../../components/commons/Input/InputFileField.jsx";
import {AuthContext} from "../../../states/contexts/AuthContext.jsx";
import {AUTH_USER, INSTRUCTOR_AUTH_DATA} from "../../../config/consts.js";

export const Profile = () => {
    const {sweetAlert} = useContext(AuthContext);

    const [isLoading, setIsLoading] = useState(true);
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
            try {
                const getProfile = await getInstructorProfile();

                setTimeout(() => {
                    summaryEditorRef?.current?.setContent(getProfile.summary);
                }, 1000);

                setInstructor(getProfile);
                setFormData({
                    name: getProfile.user.name,
                    email: getProfile.user.email,
                    phone_number: getProfile.phone_number,
                });
            }
            catch (e) {
                console.error(e);
            }
            finally {
                setIsLoading(false);
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

        const formDataToSend = new FormData();

        formDataToSend.append('name', formData.name);
        formDataToSend.append('email', formData.email);
        formDataToSend.append('password', formData.password);
        formDataToSend.append('phone_number', formData.phone_number);
        formDataToSend.append('profile_picture', formData.profile_picture);
        formDataToSend.append('summary', summaryEditorRef.current.getContent());

        console.log(formDataToSend);

        try {
            const updatedInstructor = await updateInstructorProfile(formDataToSend);

            if(updatedInstructor) {
                setErrors({});

                localStorage.setItem(AUTH_USER, JSON.stringify(updatedInstructor.instructor.user));
                localStorage.setItem(INSTRUCTOR_AUTH_DATA, JSON.stringify(updatedInstructor));

                sweetAlert.fire({
                    title: 'Berhasil',
                    text: 'Data profil berhasil diperbarui',
                    icon: 'success',
                });
            }
        }
        catch (e) {
            console.error(e);
        }
    }

    return (
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
                                    <div className="text-center">
                                       Loading...
                                    </div>
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
                                                <button className="button -md -purple-1 text-white" disabled={isLoading}>
                                                    {
                                                        isLoading ? (
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
    )
}