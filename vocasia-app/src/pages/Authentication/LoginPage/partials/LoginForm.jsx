import React, {useContext, useState} from 'react';
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {Link} from "react-router-dom";
import {
    ADMIN,
    AUTH_ACCESS_TOKEN, AUTH_DATE,
    AUTH_REFRESH_TOKEN,
    AUTH_USER,
    INSTRUCTOR,
    INSTRUCTOR_AUTH_DATA, STUDENT
} from "../../../../config/consts.js";
import {useRecoilState} from "recoil";
import {authAtom} from "../../../../states/recoil/Atoms/Auth.jsx";
import {loginWithEmailAndPassword} from "../../../../services/new/authentication/login-service.js";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";

export const LoginForm = () => {
    const [authState, setAuthState] = useRecoilState(authAtom);
    const {sweetAlert} = useContext(AuthContext);

    const [formData, setFormData] = useState({
        email: '',
        password: '',
    });

    const [isLoading, setIsLoading] = useState(false);
    const [errors, setErrors] = useState({});
    const [successMessage, setSuccessMessage] = useState('');

    const handleChange = (e) => {
        const {name, value} = e.target;

        setFormData((prevData) => ({...prevData, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsLoading(true);
        setErrors({});
        setSuccessMessage('');

        try {
            const doLoginWithEmailAndPassword = await loginWithEmailAndPassword(formData.email, formData.password);

            if (doLoginWithEmailAndPassword.success) {
                setSuccessMessage(doLoginWithEmailAndPassword.message);

                const {user, token} = doLoginWithEmailAndPassword.data;
                const {access_token, refresh_token} = token;

                localStorage.setItem(AUTH_USER, JSON.stringify(user));
                localStorage.setItem(AUTH_ACCESS_TOKEN, access_token);
                localStorage.setItem(AUTH_REFRESH_TOKEN, refresh_token);
                localStorage.setItem(AUTH_DATE, new Date().getTime().toString());

                setAuthState({
                    user,
                    accessToken: access_token,
                    refreshToken: refresh_token,
                });

                setTimeout(() => {
                    if (user.role === INSTRUCTOR) {
                        let {instructor} = doLoginWithEmailAndPassword.data;
                        localStorage.setItem(INSTRUCTOR_AUTH_DATA, JSON.stringify(instructor));

                        window.location.href = '/instructor';
                    } else if (user.role === ADMIN) {
                        window.location.href = '/admin';
                    } else if (user.role === STUDENT) {
                        window.location.href = '/users';
                    }
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
            } else if (error.data) {
                let message = error.message;

                if (error.data.error) {
                    message += `: (${error.data.error})`;
                }

                sweetAlert.fire({
                    icon: 'error',
                    title: 'Terjadi Kesalahan!',
                    text: message,
                }).then((isConfirmed) => {
                    if (isConfirmed) {
                        window.location.reload();
                    }
                })
            } else {
                if (error.message) {
                    setErrors({general: error.message});
                }
            }
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="form-page__content lg:py-50">
            <div className="container">
                <div className="row justify-center items-center">
                    <div className="col-xl-6 col-lg-8">
                        <div className="px-50 py-50 md:px-25 md:py-25 bg-white shadow-1 rounded-16">
                            <h3 className="text-30 lh-13">Login</h3>
                            <p className="mt-10">
                                Belum punya akun?
                                <Link to={'/auth/register'} className="text-purple-1">Daftar disini</Link>
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
                                        className={`button -md -green-1 text-dark-1 fw-500 w-1/1 ${isLoading ? 'disabled' : ''}`}
                                        disabled={isLoading}
                                    >
                                        {isLoading ? 'Login...' : 'Login'}
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
