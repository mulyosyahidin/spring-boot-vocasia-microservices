import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {UserWrapper} from "../../../components/Users/UserWrapper/Index.jsx";
import {STUDENT} from "../../../config/consts.js";
import {Card} from "./partials/Card.jsx";
import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAllMyEnrollments} from "../../../services/new/enrollment/user/course-service.js";
import {formatRupiah} from "../../../utils/new-utils.js";
import {Link} from "react-router-dom";
import {Pagination} from "../../../components/commons/Pagination.jsx";

const metaData = {
    title: "Kursus Saya"
}

export const CoursesPage = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getAllTransactions = await findAllMyEnrollments(currentPage);

                if (getAllTransactions.success) {
                    setData(getAllTransactions.data.data);
                    setPagination(getAllTransactions.data.pagination);
                }

                setIsLoading(false);
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
    };

    return (
        <Wrapper needAuth role={STUDENT}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <UserWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-12 d-flex justify-content-between">
                            <div>
                                <h1 className="text-30 lh-12 fw-700">Kursus Saya</h1>
                                <div className="mt-10">
                                    Jangan lupa selesaikan kursus kamu ya!
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="row y-gap-30">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div className="d-flex items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Kursus Saya</h2>
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
                                                    {
                                                        data.map((data, i) => (
                                                            <Card data={data} key={data.id || i}/>
                                                        ))
                                                    }
                                                </div>

                                                {
                                                    data.length === 0 && (
                                                        <div className="col-12 text-center">
                                                            <div className="text-16 text-light-1">
                                                                Belum ada kursus yang tersedia
                                                            </div>
                                                        </div>
                                                    )
                                                }
                                            </>
                                        )
                                    }
                                </div>

                                {
                                    (!isLoading && data.length > 10) && (
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
            </UserWrapper>
        </Wrapper>
    );
}