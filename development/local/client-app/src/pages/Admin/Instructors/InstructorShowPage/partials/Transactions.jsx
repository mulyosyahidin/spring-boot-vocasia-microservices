import {Link, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";
import {findAllByInstructorId} from "../../../../../services/new/finance/admin/transaction-service.js";
import {formatRupiah} from "../../../../../utils/new-utils.js";

export const Transactions = ({activeTab}) => {
    const {instructorId} = useParams();

    const [isLoading, setIsLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [overview, setOverview] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllInstructorTransactions = await findAllByInstructorId(instructorId, currentPage);

                if (findAllInstructorTransactions.success) {
                    setOverview(findAllInstructorTransactions.data.overview);
                    setData(findAllInstructorTransactions.data.data);
                    setPagination(findAllInstructorTransactions.data.pagination);
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
    }, [instructorId, currentPage]);

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
                        <div className="row y-gap-10 mb-10">
                            <div className="col-4">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Transaksi</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {
                                                isLoading ? '...' : overview.total_transactions
                                            }
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-4">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Pendapatan</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {
                                                isLoading ? '...' : formatRupiah(overview.total_income)
                                            }
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-4">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Fee Platform</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {
                                                isLoading ? '...' : formatRupiah(overview.total_platform_fee)
                                            }
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
                                    <th scope="col">Order</th>
                                    <th scope="col">Siswa</th>
                                    <th scope="col">Kursus</th>
                                    <th scope="col">Harga</th>
                                    <th scope="col">Pendapatan Instruktur</th>
                                    <th scope="col">Fee Platform</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    data.map((item, index) => (
                                        <tr key={`${item.order.id}-${index}`}>
                                            <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                            <td>#{item.order.order_number}</td>
                                            <td>{item.user.name}</td>
                                            <td>{item.course.title}</td>
                                            <td>{formatRupiah(item.order_item.course_subtotal)}</td>
                                            <td>{formatRupiah(item.income.total_instructor_income)}</td>
                                            <td>{formatRupiah(item.income.total_platform_fee)}</td>
                                            <td>
                                                <Link
                                                    to={`/admin/transactions/${item.order.id}`}>
                                                    Detail
                                                </Link>
                                            </td>
                                        </tr>
                                    ))
                                }

                                {
                                    data.length === 0 && (
                                        <tr>
                                            <td colSpan={8} className={'text-center'}>
                                                Tidak ada data untuk ditampilkan
                                            </td>
                                        </tr>
                                    )
                                }
                                </tbody>
                            </table>
                        </div>
                    </>
                )
            }

            {
                (!isLoading && pagination.total_items > 10) && (
                    <div className="row justify-center pt-30 pb-30">
                        <div className="col-auto">
                            <Pagination pagination={pagination} onPageChange={handlePageChange}/>
                        </div>
                    </div>
                )
            }
        </div>
    );
}