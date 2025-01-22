import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {Link, useParams} from "react-router-dom";
import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";
import {findAll} from "../../../../../services/new/order/admin/student-transaction-service.js";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";

export const Transactions = ({activeTab}) => {
    const {userId} = useParams();

    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findStudentTransactionById = await findAll(userId, currentPage);

                if (findStudentTransactionById.success) {
                    setData(findStudentTransactionById.data.data);
                    setPagination(findStudentTransactionById.data.pagination);

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
    }, [userId]);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));

        setCurrentPage(newPage);
    }

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 2 ? "is-active" : ""} `}
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
                    <div className={'table-responsive'}>
                        <table className="table table-borderless table-hover table-striped"
                               style={{width: "100%"}}>
                            <thead>
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Order</th>
                                <th scope="col" className={'text-center'}>Jumlah Item</th>
                                <th scope="col">Total Harga</th>
                                <th scope="col">Status Pembayaran</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                data.map((item, index) => (
                                    <tr key={index}>
                                        <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                        <td>#{item.order_number}</td>
                                        <td className={'text-center'}>{item.total_items}</td>
                                        <td>{formatRupiah(item.total_price)}</td>
                                        <td>{item.payment_status}</td>
                                        <td>
                                            <Link to={`/admin/transactions/${item.id}`}>
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