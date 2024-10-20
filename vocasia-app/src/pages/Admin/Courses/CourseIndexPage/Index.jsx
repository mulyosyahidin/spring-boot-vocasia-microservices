import React, {useEffect, useState} from "react";
import {ADMIN} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {Link} from "react-router-dom";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAll} from "../../../../services/new/course/admin/course-service.js";
import {Pagination} from "../../../../components/commons/Pagination.jsx";
import {Card} from "./partials/Card.jsx";

const metaData = {
    title: 'Data Kursus',
}

export const CourseIndexPage = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getAllCourses = await findAll(currentPage);

                if (getAllCourses.success) {
                    setData(getAllCourses.data.data);
                    setPagination(getAllCourses.data.pagination);

                    setIsLoading(false);
                }
            } catch (error) {
                console.error(error);

                if (error.message) {
                    let msg = error.message;
                    if (error.data.error) {
                        msg += ' : ' + error.data.error;
                    }

                    await withReactContent(Swal).fire({
                        title: 'Terjadi Kesalahan!',
                        text: msg,
                        icon: 'error',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        })
                }
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
    }

    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-12 d-flex justify-content-between">
                            <div>
                                <h1 className="text-30 lh-12 fw-700">Data Kursus</h1>
                                <div className="mt-10">
                                    Kelola data kursus yang dimiliki instruktur
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="row y-gap-30">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Data Kursus</h2>
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
                                            <>
                                                <div className="row y-gap-30 pt-30">
                                                    {data.map((data, i) => (
                                                        <Card data={data}
                                                              key={data.id || i}/>
                                                    ))}

                                                    {
                                                        data.length === 0 && (
                                                            <div className="col-12 text-center">
                                                                <div className="text-16 text-light-1">
                                                                    Belum ada kursus yang tersedia
                                                                </div>
                                                            </div>
                                                        )
                                                    }
                                                </div>
                                            </>
                                        )
                                    }

                                    {
                                        (!isLoading && data.length > 10) && (
                                            <div className="row justify-center pt-30 pb-30">
                                                <div className="col-auto">
                                                    <Pagination pagination={pagination}
                                                                onPageChange={handlePageChange}/>
                                                </div>
                                            </div>
                                        )
                                    }
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </AdminWrapper>
        </Wrapper>
    )
}