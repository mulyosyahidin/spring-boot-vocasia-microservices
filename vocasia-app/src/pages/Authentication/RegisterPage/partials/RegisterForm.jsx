import React, {useState} from 'react';
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {Link, useNavigate} from "react-router-dom";
import {
    ADMIN,
    AUTH_ACCESS_TOKEN,
    AUTH_REFRESH_TOKEN,
    AUTH_USER,
    INSTRUCTOR,
    INSTRUCTOR_AUTH_DATA
} from "../../../../config/consts.js";
import {useRecoilState} from "recoil";
import {authAtom} from "../../../../states/recoil/Atoms/Auth.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {register} from "../../../../services/new/authentication/register-service.js";

export const RegisterForm = () => {
    const [authState, setAuthState] = useRecoilState(authAtom);

    const [formData, setFormData] = useState({
        email: '',
        password: '',
        name: '',
        username: '',
    });

    const [isSubmitted, setIsSubmitted] = useState(false);
    const [errors, setErrors] = useState({});
    const [successMessage, setSuccessMessage] = useState('');

    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);
        setErrors({});
        setSuccessMessage('');

        try {
            const doRegister = await register(formData);

            if (doRegister.success) {
                let {user, token} = doRegister.data;
                let {access_token, refresh_token} = token;

                localStorage.setItem(AUTH_USER, JSON.stringify(user));
                localStorage.setItem(AUTH_ACCESS_TOKEN, access_token);
                localStorage.setItem(AUTH_REFRESH_TOKEN, refresh_token);

                setAuthState({
                    user,
                    accessToken: access_token,
                    refreshToken: refresh_token,
                });

                setTimeout(() => {
                    window.location.href = '/users';
                }, 3000);
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
            setIsSubmitted(false);
        }
    };

    return (
        <div className="form-page__content lg:py-50">
            <div className="container">
                <div className="row justify-center items-center">
                    <div className="col-xl-6 col-lg-8">
                        <div className="px-50 py-50 md:px-25 md:py-25 bg-white shadow-1 rounded-16">
                            <h3 className="text-30 lh-13">Daftar</h3>
                            <p className="mt-10">
                                Sudah punya akun?
                                <Link to={'/auth/login'} className="text-purple-1">Login disini</Link>
                            </p>

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
                                        label="Nama"
                                        type="text"
                                        name="name"
                                        value={formData.name}
                                        onChange={handleChange}
                                        error={errors.name}
                                        placeholder="Nama..."
                                    />
                                </div>

                                <div className="col-12">
                                    <InputField
                                        label="Username"
                                        type="text"
                                        name="username"
                                        value={formData.username}
                                        onChange={handleChange}
                                        error={errors.username}
                                        placeholder="Username..."
                                    />
                                </div>

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
                                    <InputField
                                        label="Password"
                                        type="password"
                                        name="password"
                                        value={formData.password}
                                        onChange={handleChange}
                                        error={errors.password}
                                        placeholder="Password..."
                                    />
                                </div>
                                <div className="col-12">
                                    <button
                                        type="submit"
                                        className={`button -md -green-1 text-dark-1 fw-500 w-1/1 ${isSubmitted ? 'disabled' : ''}`}
                                        disabled={isSubmitted}
                                    >
                                        {isSubmitted ? 'Login...' : 'Login'}
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
};
