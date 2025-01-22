import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";
import {Link, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {SelectField} from "../../../../components/commons/Input/SelectField.jsx";
import {InputFileField} from "../../../../components/commons/Input/InputFileField.jsx";
import {findAllOnlyParents, findById, updateCategory} from "../../../../services/new/course/admin/category-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {organizeCategories} from "../../../../utils/new-utils.js";

const metaData = {
    title: 'Edit Kategori Kursus',
};

export const EditCategoryPage = () => {
    const {categoryId} = useParams();

    const [isLoading, setIsLoading] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);

    const [category, setCategory] = useState({});
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
                const findCategoryById = await findById(categoryId);

                if (findOnlyParentCategories.success) {
                    setCategories(organizeCategories(findOnlyParentCategories.data.data));
                    setCategory(findCategoryById.data.category);

                    setFormData({
                        name: findCategoryById.data.category.name,
                        icon: '',
                        parent_id: findCategoryById.data.category.parent_id || '',
                    });

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
    }, [categoryId]);

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

        setIsSubmitted(true);

        try {
            const formDataToSend = new FormData();

            formDataToSend.append('name', formData.name);
            if (formData.icon) {
                formDataToSend.append('icon', formData.icon);
            }
            formDataToSend.append('parent_id', formData.parent_id);

            const updatedCategory = await updateCategory(categoryId, formDataToSend);

            if (updatedCategory.success) {
                await withReactContent(Swal).fire({
                    title: 'Berhasil',
                    text: updatedCategory.message,
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
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Perbarui Kategori Kursus</h1>
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
                                                        isRequired={true}
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