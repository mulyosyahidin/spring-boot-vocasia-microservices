import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {SelectField} from "../../../../components/commons/Input/SelectField.jsx";
import {InputFileField} from "../../../../components/commons/Input/InputFileField.jsx";
import {createCategory, getAdminCategories} from "../../../../services/courses/admin-category-service.js";
import {Link} from "react-router-dom";
import {organizeCategories} from "../../../../utils/courses.js";

export const CreateCategory = () => {
    const {sweetAlert} = useContext(AuthContext);

    const [categories, setCategories] = useState([]);

    const [loading, setLoading] = useState(false);
    const [formData, setFormData] = useState({
        name: '',
        icon: '',
        parent_id: '',
    });
    const [errors, setErrors] = useState({
        general: '',
        name: '',
        icon: '',
        parent_id: '',
    });

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const categories = await getAdminCategories();

                setCategories(organizeCategories({categories, addNull: true}));
            } catch (e) {
                console.error(e);
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
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setLoading(true);

        const formDataToSend = new FormData();
        formDataToSend.append('name', formData.name);
        formDataToSend.append('icon', formData.icon);
        formDataToSend.append('parent_id', formData.parent_id);

        try {
            const category = await createCategory(formDataToSend);

            if (category) {
                setFormData({
                    name: '',
                    icon: '',
                    parent_id: '',
                });

                const categories = await getAdminCategories();
                setCategories(organizeCategories({categories, addNull: true}));

                sweetAlert.fire({
                    title: 'Berhasil',
                    text: 'Kategori kursus berhasil ditambahkan',
                    icon: 'success',
                });
            }
        } catch (error) {
            sweetAlert.fire({
                title: 'Gagal',
                text: error.message,
                icon: 'error',
            });
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Tambah Kategori Kursus Baru</h1>
                </div>
            </div>

            <div className="row y-gap-60">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                            <h2 className="text-17 lh-1 fw-500">Informasi Kursus</h2>

                            <div className="col-auto">
                                <Link to={'/admin/categories'} className="-md -white text-dark-1">
                                    <i className="fa fa-arrow-left mr-10"/>
                                    Kembali
                                </Link>
                            </div>
                        </div>

                        <div className="py-30 px-30">
                            <form className="contact-form row y-gap-30" onSubmit={handleSubmit}>
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
                                        label="Nama Kategori"
                                        name="name"
                                        placeholder="Nama kategori"
                                        value={formData.name}
                                        onChange={handleChange}
                                        error={errors.name}
                                        isRequired={false}
                                    />
                                </div>

                                <div className="col-12">
                                    <SelectField
                                        label="Kategori Induk"
                                        name="parent_id"
                                        options={categories}
                                        value={formData.parent_id}
                                        onChange={handleChange}
                                        error={errors.category}
                                        placeholder="Pilih Kategori Induk"
                                        isRequired={false}
                                    />
                                </div>

                                <div className="col-12">
                                    <InputFileField
                                        label="Icon Kategori"
                                        name="icon"
                                        placeholder="Icon kategori"
                                        onChange={handleChange}
                                        error={errors.icon}
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
}