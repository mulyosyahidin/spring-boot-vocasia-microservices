import React, {useEffect, useState} from "react";
import {findAllByInstructorId} from "../../../../../services/new/finance/admin/withdrawal-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {Link, useParams} from "react-router-dom";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";
import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";

export const Withdrawal = ({activeTab}) => {
    const {instructorId} = useParams();
    const [isLoading, setIsLoading] = useState(false);
    const [status, setStatus] = useState('all');
    const [overview, setOverview] = useState({});
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});
    const [currentPage, setCurrentPage] = useState(1);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllWithdrawalRequest = await findAllByInstructorId(instructorId, currentPage);

                if (findAllWithdrawalRequest.success) {
                    setOverview(findAllWithdrawalRequest.data.overview);
                    setData(findAllWithdrawalRequest.data.requests.data);
                    setPagination(findAllWithdrawalRequest.data.requests.pagination);
                }

                setIsLoading(false);
            }
            catch (error) {
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
    }, [status, currentPage]);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));

        setCurrentPage(newPage);
    };

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 6 ? "is-active" : ""} `}
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
                    <div className="row y-gap-10 mb-10">
                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Jumlah Withdrawal Tertunda</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {
                                            isLoading ? '...' : overview.total_pending_request
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Withdrawal Tertunda</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {
                                            isLoading ? '...' : formatRupiah(overview.total_pending_amount)
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Jumlah Withdrawal</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {
                                            isLoading ? '...' : overview.total_paid_request
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Withdrawal</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {
                                            isLoading ? '...' : formatRupiah(overview.total_paid_amount)
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
                                <th scope="col">Nominal</th>
                                <th scope="col">Status</th>
                                <th scope="col">Tanggal Request</th>
                                <th scope="col">Diproses Pada</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                data.map((item, index) => (
                                    <tr key={index}>
                                        <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                        <td>{formatRupiah(item.request.amount)}</td>
                                        <td>{item.request.status}</td>
                                        <td>{formatDate(item.request.requested_at)}</td>
                                        <td>
                                            {
                                                item.request.processed_at ? formatDate(item.request.processed_at) : '-'
                                            }
                                        </td>
                                        <td>
                                            <Link
                                                to={`/admin/withdrawal/${item.request.id}`}>
                                                Detail
                                            </Link>
                                        </td>
                                    </tr>
                                ))
                            }

                            {
                                data.length === 0 && (
                                    <tr>
                                        <td colSpan={6} className={'text-center'}>
                                            Tidak ada data untuk ditampilkan.
                                        </td>
                                    </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
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
    )
}