import React, {useState} from "react";
import {InputField} from "../../../components/commons/Input/InputField.jsx";
import {TextAreaField} from "../../../components/commons/Input/TextAreaField.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {registerInstructor} from "../../../services/new/instructor/register-service.js";
import {useNavigate} from "react-router-dom";

export const Join = () => {
    const [showForm, setShowForm] = useState(false);
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        username: '',
        phone_number: '',
        password: '',
        summary: ''
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

        try {
            const doRegister = await registerInstructor(formData);

            if (doRegister.success) {
                await withReactContent(Swal).fire({
                    icon: 'success',
                    title: 'Berhasil!',
                    text: 'Berhasil melakukan pendaftaran. Silahkan login ke akun Anda.',
                })
                    .then((isConfirmed) => {
                        if (isConfirmed) {
                           navigate('/auth/login');
                        }
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
                setLoading(false);
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
        } finally {
            setLoading(false);
        }
    };

    return (
        <section className="layout-pt-lg layout-pb-md bg-light-4">
            <div className="container">
                <div className="row y-gap-50 justify-between items-center">
                    <div className="col-lg-7 pr-60">
                        <img src="/assets/img/become-ins/1.png" alt="image"/>
                    </div>

                    <div className="col-lg-5">
                        {showForm ? (
                            <form className="contact-form row y-gap-30 pt-60 lg:pt-40" onSubmit={handleSubmit}>
                                <h3 className="text-24 fw-500">Menjadi Instruktur</h3>
                                <p className="mt-2">
                                    {successMessage ? (
                                        <div className="text-green-1">{successMessage}</div>
                                    ) : errors.general ? (
                                        <div className="text-red-1">{errors.general}</div>
                                    ) : (
                                        'Daftar dengan email dan password Anda. Jelaskan sedikit tentang diri Anda dan kelas yang ingin Anda buat.'
                                    )}
                                </p>

                                <InputField
                                    label="Nama"
                                    type="text"
                                    name="name"
                                    value={formData.name}
                                    onChange={handleChange}
                                    error={errors.name}
                                    placeholder="Nama..."
                                />
                                <InputField
                                    label="No. HP"
                                    type="text"
                                    name="phone_number"
                                    value={formData.phone_number}
                                    onChange={handleChange}
                                    error={errors.phone_number}
                                    placeholder="No. HP"
                                />
                                <InputField
                                    label="Email"
                                    type="email"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                    error={errors.email}
                                    placeholder="Email..."
                                />
                                <div className="col-md-6">
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
                                <div className="col-md-6">
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

                                <TextAreaField
                                    label="Tentang Saya"
                                    name="summary"
                                    value={formData.summary}
                                    onChange={handleChange}
                                    error={errors.summary}
                                    placeholder="Ceritakan sedikit tentang diri Anda..."
                                />

                                <div className="col-12">
                                    <button
                                        type="submit"
                                        className={`button -md -purple-1 text-white ${loading ? 'disabled' : ''}`}
                                        disabled={loading}
                                    >
                                        {loading ? 'Mengirim pendaftaran...' : 'Daftar'}
                                    </button>
                                </div>
                            </form>
                        ) : (
                            <>
                                <h2 className="text-45 lh-15">
                                    Daftarlah <span className="text-purple-1">sekarang</span>
                                </h2>
                                <p className="text-dark-1 mt-25">
                                    Jadi, tunggu apa lagi? Ayo daftar sekarang dan mulai membuat kelas Anda.
                                </p>
                                <div className="d-inline-block mt-30">
                                    <button className="button -md -dark-1 text-white" onClick={() => setShowForm(true)}>
                                        Daftar
                                    </button>
                                </div>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </section>
    );
};

export default Join;
