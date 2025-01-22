import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {AuthenticationHeader} from "../../../components/AuthenticationHeader/Index.jsx";
import {SideImage} from "../LoginPage/partials/SideImage.jsx";
import {LoginForm} from "../LoginPage/partials/LoginForm.jsx";
import React, {useState} from "react";
import {Link} from "react-router-dom";
import {InputField} from "../../../components/commons/Input/InputField.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {requestResetPassword} from "../../../services/new/authentication/forgot-password.js";

const metaData = {
    title: 'Lupa Password?'
}

export const ForgotPassword = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);

    const [successMessage, setSuccessMessage] = useState(null);
    const [formData, setFormData] = useState({
        email: '',
    });
    const [errors, setErrors] = useState({
        email: '',
        general: '',
    });

    const handleChange = (e) => {
        const {name, value} = e.target;

        setFormData((prevData) => ({...prevData, [name]: value}));
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsLoading(true);
        setErrors({});
        setSuccessMessage('');

        try {
            const doRequestForgetPassword = await requestResetPassword(formData);

            if (doRequestForgetPassword.success) {
                setSuccessMessage(doRequestForgetPassword.message);
                setFormData({
                    email: '',
                });

                setIsSubmitted(false);
                setIsLoading(false);
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
                    setErrors({general: error.message,});
                }
            }

            setIsSubmitted(false);
        }
    }

    return (
        <Wrapper>
            <Meta meta={metaData}/>

            <PreLoader/>
            <AuthenticationHeader/>

            <div className="content-wrapper js-content-wrapper overflow-hidden">
                <section className="form-page js-mouse-move-container">
                    <SideImage/>
                    <div className="form-page__content lg:py-50">
                        <div className="container">
                            <div className="row justify-center items-center">
                                <div className="col-xl-6 col-lg-8">
                                    <div className="px-50 py-50 md:px-25 md:py-25 bg-white shadow-1 rounded-16">
                                        <h3 className="text-30 lh-13">Lupa Password?</h3>
                                        <div className="mt-2">
                                            {successMessage ? (
                                                <div className="text-green-1 mt-20">{successMessage}</div>
                                            ) : errors.general ? (
                                                <div className="text-red-1 mt-20">{errors.general}</div>
                                            ) : null}
                                        </div>

                                        <form className="contact-form respondForm__form row y-gap-20 pt-30"
                                              onSubmit={handleSubmit}>
                                            <div className="col-12">
                                                <InputField
                                                    label="Email"
                                                    type="email"
                                                    name="email"
                                                    value={formData.email}
                                                    onChange={handleChange}
                                                    error={errors.email}
                                                    placeholder="Email..."
                                                />
                                            </div>

                                            <div className="col-12">
                                                <button
                                                    type="submit"
                                                    className={`button -md -green-1 text-dark-1 fw-500 w-1/1 ${isLoading ? 'disabled' : ''}`}
                                                    disabled={isLoading}
                                                >
                                                    {isLoading ? 'Mengirim...' : 'Kirim'}
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </Wrapper>
    );
}