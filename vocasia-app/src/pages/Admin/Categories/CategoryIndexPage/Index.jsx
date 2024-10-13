import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";
import {Link} from "react-router-dom";
import React, {useContext, useEffect, useState} from "react";
import {deleteById, findAll} from "../../../../services/new/course/admin/category-service.js";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {Pagination} from "../../../../components/commons/Pagination.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

const metaData = {
    title: 'Kategori Kursus',
};

export const CategoryIndexPage = () => {
    const {sweetAlert} = useContext(AuthContext);

    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllCategories = await findAll(currentPage);

                if (findAllCategories.success) {
                    setData(findAllCategories.data.data);
                    setPagination(findAllCategories.data.pagination);
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
                        await withReactContent(Swal).fire({
                            title: 'Terjadi Kesalahan!',
                            text: error.message,
                            icon: 'error',
                        }).then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        });
                    }
                }
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, [currentPage]);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));
        setCurrentPage(newPage);
    };

    const confirmDelete = async (id) => {
        const isConfirm = await withReactContent(Swal).fire({
            title: 'Apakah Anda yakin?',
            text: 'Menghapus kategori tidak akan menghapus kursus di dalamnya. Tindakan ini tidak dapat dibatalkan.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Ya, hapus!',
            cancelButtonText: 'Batal',
        });

        if (isConfirm.isConfirmed) {
            try {
                const deleteCategoryById = await deleteById(id);

                if (deleteCategoryById.success) {
                    await withReactContent(Swal).fire({
                        title: 'Berhasil',
                        text: deleteCategoryById.message,
                        icon: 'success',
                    });

                    setData(data.filter(item => item.category.id !== id));
                } else {
                    throw new Error(deleteCategoryById.message);
                }
            } catch (error) {
                await withReactContent(Swal).fire({
                    title: 'Terjadi Kesalahan!',
                    text: error.message || 'Kategori gagal dihapus',
                    icon: 'error',
                });
            }
        }
    };

    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Kelola Kategori Kursus</h1>
                            <div className="mt-10">Kategori untuk klasifikasi kursus yang dapat ditambahkan instruktur
                            </div>
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
                                            <div className={'table-responsive'}>
                                                <table className="table table-borderless table-hover table-striped"
                                                       style={{width: "100%"}}>
                                                    <thead>
                                                    <tr>
                                                        <th scope="col">No</th>
                                                        <th scope="col">Nama Kategori</th>
                                                        <th scope="col">Tipe</th>
                                                        <th scope="col" className={'text-center'}>Jumlah Child</th>
                                                        <th scope="col">Nama Induk</th>
                                                        <th scope="col"></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    {
                                                        data.map((item, index) => (
                                                            <tr key={item.category.id}>
                                                                <td>
                                                                    {
                                                                        item.category.icon == null ? (currentPage - 1) * pagination.per_page + (index + 1)
                                                                            : (
                                                                                <img src={item.category.icon_url}
                                                                                     alt={item.category.name}
                                                                                     className={'rounded-16'}
                                                                                     style={{width: '20px', height: '20px'}}/>
                                                                            )
                                                                    }
                                                                </td>
                                                                <td>{item.category.name}</td>
                                                                <td>{item.category.parent_id == null ? 'Induk' : 'Child'}</td>
                                                                <td className={'text-center'}>
                                                                    {item.category.type === 'parent' ? item.children.length : '-'}
                                                                </td>
                                                                <td>{item.category.type === 'child' ? item.parent.name : '-'}</td>
                                                                <td>
                                                                    <div className="d-flex justify-content-end">
                                                                        <Link
                                                                            to={`/admin/categories/${item.category.id}/edit`}
                                                                            className="button -sm -purple-1 text-white">
                                                                            Edit
                                                                        </Link>
                                                                        <a className={'button -sm -purple-1 text-white ml-5'}
                                                                           href={'#'}
                                                                           onClick={() => confirmDelete(item.category.id)}>
                                                                            Hapus
                                                                        </a>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        ))
                                                    }

                                                    {
                                                        data.length === 0 && (
                                                            <tr>
                                                                <td colSpan={6} className={'text-center'}>
                                                                    Tidak ada data untuk ditampilkan
                                                                </td>
                                                            </tr>
                                                        )
                                                    }
                                                    </tbody>
                                                </table>
                                            </div>
                                        )
                                    }
                                </div>

                                {
                                    !isLoading && (
                                        <div className="row justify-center pt-30 pb-30">
                                            <div className="col-auto">
                                                <Pagination pagination={pagination} onPageChange={handlePageChange}/>
                                            </div>
                                        </div>
                                    )
                                }
                            </div>
                        </div>
                    </div>

                </div>
            </AdminWrapper>
        </Wrapper>
    );
}