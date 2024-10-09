import React, {useState} from 'react';
import {submitLoginForm} from "../actions/LoginAction.jsx";
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {Link, useNavigate} from "react-router-dom";
import {
    ADMIN,
    AUTH_ACCESS_TOKEN, AUTH_DATE,
    AUTH_REFRESH_TOKEN,
    AUTH_USER,
    INSTRUCTOR,
    INSTRUCTOR_AUTH_DATA
} from "../../../../config/consts.js";
import {useRecoilState} from "recoil";
import {authAtom} from "../../../../states/recoil/Atoms/Auth.jsx";

export const LoginForm = () => {
    const [authState, setAuthState] = useRecoilState(authAtom);

    const [formData, setFormData] = useState({
        email: '',
        password: '',
    });

    const [loading, setLoading] = useState(false);
    const [errors, setErrors] = useState({});
    const [successMessage, setSuccessMessage] = useState('');

    const navigate = useNavigate();

    const handleChange = (e) => {
        const {name, value} = e.target;

        setFormData((prevData) => ({...prevData, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setLoading(true);
        setErrors({});
        setSuccessMessage('');


        const result = await submitLoginForm(formData, setLoading, setErrors, setSuccessMessage);

        if (result) {
            let {user, token} = result;
            let {access_token, refresh_token} = token;

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
                    let {instructor} = result;
                    localStorage.setItem(INSTRUCTOR_AUTH_DATA, JSON.stringify(instructor));

                    navigate('/instructor');
                }
                else if (user.role === ADMIN) {
                    navigate('/admin');
                }
            }, 5000);
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
                                        className={`button -md -green-1 text-dark-1 fw-500 w-1/1 ${loading ? 'disabled' : ''}`}
                                        disabled={loading}
                                    >
                                        {loading ? 'Login...' : 'Login'}
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
