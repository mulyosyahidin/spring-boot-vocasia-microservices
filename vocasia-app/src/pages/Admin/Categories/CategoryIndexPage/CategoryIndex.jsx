import React, {useContext, useEffect, useState} from "react";
import {deleteCategoryById, getAdminCategories} from "../../../../services/courses/admin-category-service.js";
import {Link} from "react-router-dom";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {Pagination} from "./partials/Pagination.jsx";

export const CategoryIndex = () => {
    const {sweetAlert} = useContext(AuthContext);

    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            const categories = await getAdminCategories();
            setCategories(categories);
        }

        fetchInitialData();
    }, []);

    const confirmDelete = async (id) => {
        const isConfirm = sweetAlert.fire({
            title: 'Apakah Anda yakin?',
            text: 'Kategori ini akan dihapus',
            icon: 'warning',
            buttons: true,
            dangerMode: true,
        }).then(async (isConfirm) => {
            if (isConfirm) {
                const deleteCategory = await deleteCategoryById(id);

                if (deleteCategory) {
                    sweetAlert.fire({
                        title: 'Berhasil',
                        text: 'Kategori berhasil dihapus',
                        icon: 'success',
                    });

                    setCategories(categories.filter(category => category.id !== id));
                }
            }
        });
    }

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Kelola Kategori Kursus</h1>
                    <div className="mt-10">Kategori untuk klasifikasi kursus yang dapat ditambahkan instruktur</div>
                </div>
            </div>

            <div className={`row y-gap-60`}>
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div
                            className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                            <h2 className="text-17 lh-1 fw-500">Kategori Kursus</h2>

                            <Link to={'/admin/categories/create'} className="button -sm -purple-1 text-white">
                                Tambah
                            </Link>
                        </div>

                        <div className="py-30 px-30">
                            <div className={'table-responsive'}>
                                <table className="table table-borderless table-hover table-striped"
                                       style={{width: "100%"}}>
                                    <thead>
                                    <tr>
                                        <th scope="col">No</th>
                                        <th scope="col">Nama Kategori</th>
                                        <th scope="col" className={'text-center'}>Jumlah Child</th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        categories.map((category, index) => (
                                            <tr key={category.id}>
                                                <td>{index + 1}</td>
                                                <td>{category.name}</td>
                                                <td className={'text-center'}>{category.children_count}</td>
                                                <td>
                                                    <div className="d-flex justify-content-end">
                                                        <Link to={`/admin/categories/${category.id}/edit`}
                                                              className="button -sm -purple-1 text-white">
                                                            Edit
                                                        </Link>
                                                        <a className={'button -sm -purple-1 text-white ml-5'} href={'#'}
                                                           onClick={() => confirmDelete(category.id)}>
                                                            Hapus
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                        ))
                                    }

                                    {
                                        categories.length === 0 && (
                                            <tr>
                                                <td colSpan={3} className={'text-center'}>
                                                    Belum ada kategori
                                                </td>
                                            </tr>
                                        )
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}