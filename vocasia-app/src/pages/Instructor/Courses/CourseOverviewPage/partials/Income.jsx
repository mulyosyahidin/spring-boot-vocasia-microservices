import React, {useEffect, useState} from "react";
import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAll} from "../../../../../services/new/finance/instructor/income-service.js";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";

export const Income = ({activeTab, courseId}) => {
    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [overview, setOverview] = useState({});
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllIncomes = await findAll(courseId, currentPage);

                if (findAllIncomes.success) {
                    setOverview(findAllIncomes.data.overview);
                    setData(findAllIncomes.data.incomes.data);
                    setPagination(findAllIncomes.data.incomes.pagination);
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
    }, [courseId, currentPage]);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));

        setCurrentPage(newPage);
    };

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 4 ? "is-active" : ""} `}
        >
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
                        <div className="row mb-10">
                            <div className="col-xl-3 col-md-6">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Transaksi</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {overview.total_transaction}
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col-xl-3 col-md-6">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Pendapatan</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {formatRupiah(overview.total_income)}
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col-xl-3 col-md-6">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Pembayaran User</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {formatRupiah(overview.total_user_payment)}
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col-xl-3 col-md-6">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Potongan Platform</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {formatRupiah(overview.total_platform_income)}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={'table-responsive'}>
                            <table className="table table-borderless table-hover table-striped"
                                   style={{width: "100%"}}>
                                <thead>
                                <tr>
                                    <th scope="col">No</th>
                                    <th scope="col">Order ID</th>
                                    <th scope="col">Tanggal</th>
                                    <th scope="col">Pendapatan</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    data.map((item, index) => (
                                        <tr key={item.id}>
                                            <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                            <td>#{item.order.order_number}</td>
                                            <td>{formatDate(item.income.created_at)}</td>
                                            <td>{formatRupiah(item.income.total_instructor_income)}</td>
                                            <td>
                                            </td>
                                        </tr>
                                    ))
                                }
                                {
                                    data.length === 0 && (
                                        <tr>
                                            <td colSpan={5}>
                                                <div className="text-center">
                                                    <strong>Tidak ada data untuk ditampilkan</strong>
                                                </div>
                                            </td>
                                        </tr>
                                    )
                                }
                                </tbody>
                            </table>
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
                    </>
                )
            }
        </div>
    )
}