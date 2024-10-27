import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";
import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {Link} from "react-router-dom";
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {SelectField} from "../../../../components/commons/Input/SelectField.jsx";
import {InputFileField} from "../../../../components/commons/Input/InputFileField.jsx";
import {findAllOnlyParents, saveCategory} from "../../../../services/new/course/admin/category-service.js";
import {organizeCategories} from "../../../../utils/new-utils.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

const metaData = {
    title: 'Tambah Kategori Kursus',
};

export const CreateCategoryPage = () => {
    const {sweetAlert} = useContext(AuthContext);

    const [isLoading, setIsLoading] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);

    const [categories, setCategories] = useState([]);

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
            setIsLoading(true);

            try {
                const findOnlyParentCategories = await findAllOnlyParents();

                if (findOnlyParentCategories.success) {
                    setCategories(organizeCategories(findOnlyParentCategories.data.data));

                    setIsLoading(false);
                }
            } catch (error) {
                console.error(error);

                if (error.data) {
                    let message = error.message;

                    if (error.data.error) {
                        message += `: (${error.data.error})`;
                    }

                    await withReactContent(Swal).fire({
                        title: 'Terjadi Kesalahan!',
                        text: message,
                        icon: 'error',
                    }).then((isConfirmed) => {
                        if (isConfirmed) {
                            window.location.reload();
                        }
                    });
                } else {
                    if (error.message) {
                        setErrors({
                            general: error.message
                        });
                    }
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

        setErrors({
            general: '',
            name: '',
            icon: '',
            parent_id: '',
        });

        try {
            const formDataToSend = new FormData();

            formDataToSend.append('name', formData.name);
            formDataToSend.append('icon', formData.icon);
            formDataToSend.append('parent_id', formData.parent_id);

            const doSaveCategory = await saveCategory(formDataToSend);

            if (doSaveCategory.success) {
                if (doSaveCategory.data.category.type === 'parent') {
                    const newCategory = {
                        value: doSaveCategory.data.category.id,
                        label: doSaveCategory.data.category.name,
                    };

                    setCategories((prevCategories) => [
                        ...prevCategories,
                        newCategory,
                    ]);
                }

                setFormData({
                    name: '',
                    icon: '',
                    parent_id: '',
                });

                setErrors({
                    general: '',
                    name: '',
                    icon: '',
                    parent_id: '',
                });

                await withReactContent(Swal).fire({
                    title: 'Berhasil',
                    text: doSaveCategory.message,
                    icon: 'success',
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
            } else if (error.data) {
                let message = error.message;

                if (error.data.error) {
                    message += `: (${error.data.error})`;
                }

                await withReactContent(Swal).fire({
                    title: 'Terjadi Kesalahan!',
                    text: message,
                    icon: 'error',
                }).then((isConfirmed) => {
                    if (isConfirmed) {
                        window.location.reload();
                    }
                });
            } else {
                if (error.message) {
                    setErrors({
                        general: error.message
                    });
                }
            }
        } finally {
            setIsSubmitted(false);
        }
    }

    return (
        <Wrapper needAuth={true} role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Tambah Kategori Baru</h1>
                        </div>
                    </div>

                    <div className="row y-gap-60">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                                    <h2 className="text-17 lh-1 fw-500">Informasi Kategori</h2>

                                    <div className="col-auto">
                                        <Link to={'/admin/categories'} className="-md -white text-dark-1">
                                            <i className="fa fa-arrow-left mr-10"/>
                                            Kembali
                                        </Link>
                                    </div>
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
            </AdminWrapper>
        </Wrapper>
    );
}