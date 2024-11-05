import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findInstructorBalanceDataById} from "../../../../../services/new/finance/admin/balance-service.js";
import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";

export const Finance = ({activeTab}) => {
    const {instructorId} = useParams();

    const [isLoading, setIsLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(1);
    const [overview, setOverview] = useState({});
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findInstructorBalanceData = await findInstructorBalanceDataById(instructorId, currentPage);

                if (findInstructorBalanceData.success) {
                    setOverview(findInstructorBalanceData.data.instructor_balance);
                    setData(findInstructorBalanceData.data.histories.data);
                    setPagination(findInstructorBalanceData.data.histories.pagination);
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
    }

    return (
        <>
            <div
                className={`tabs__pane -tab-item-1 ${activeTab === 5 ? "is-active" : ""} `}
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
                                        <div className="lh-1 fw-500">Saldo Saat Ini</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {
                                                isLoading ? '...' : formatRupiah(overview.current_balance)
                                            }
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
                                            {
                                                isLoading ? '...' : formatRupiah(overview.total_income)
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
                                                isLoading ? '...' : formatRupiah(overview.total_pending_withdrawal)
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
                                                isLoading ? '...' : formatRupiah(overview.total_withdrawn)
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
                        <div className={'table-responsive mt-10'}>
                            <table className="table table-bordered table-hover table-striped"
                                   style={{width: "100%"}}>
                                <thead>
                                <tr>
                                    <th scope="col" rowSpan={2} style={{border: "1px solid #555"}}>No</th>
                                    <th scope="col" rowSpan={2} style={{border: "1px solid #555"}}>Tanggal</th>
                                    <th scope="col" colSpan={2} className={'text-center'}
                                        style={{border: "1px solid #555"}}>Mutasi
                                    </th>
                                    <th scope="col" rowSpan={2} style={{border: "1px solid #555"}}
                                        className={'text-center'}>Saldo
                                    </th>
                                    <th scope="col" rowSpan={2} style={{border: "1px solid #555"}}>Keterangan</th>
                                </tr>
                                <tr>
                                    <th scope="col" className={'text-center'} style={{border: "1px solid #555"}}>Debet</th>
                                    <th scope="col" className={'text-center'} style={{border: "1px solid #555"}}>Kredit</th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    data.map((item, index) => (
                                        <tr key={index}>
                                            <td style={{border: "1px solid #555"}}>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                            <td style={{border: "1px solid #555"}}>{formatDate(item.transaction_date)}</td>
                                            <td style={{border: "1px solid #555"}} className={'text-center'}>
                                                {
                                                    item.transaction_type === 'withdrawal' &&
                                                    formatRupiah(item.amount)
                                                }
                                            </td>
                                            <td style={{border: "1px solid #555"}} className={'text-center'}>
                                                {
                                                    item.transaction_type === 'income' &&
                                                    formatRupiah(item.amount)
                                                }
                                            </td>
                                            <td style={{border: "1px solid #555"}} className={'text-center'}>{formatRupiah(item.new_balance)}</td>
                                            <td style={{border: "1px solid #555"}}>{item.transaction_type}</td>
                                        </tr>
                                    ))
                                }

                                {
                                    data.length === 0 && (
                                        <tr>
                                            <td colSpan={7} className={'text-center'}>
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
        </>
    )
}