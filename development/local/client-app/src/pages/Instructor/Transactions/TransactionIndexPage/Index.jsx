import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAll} from "../../../../services/new/finance/instructor/transaction-service.js";
import {Pagination} from "../../../../components/commons/Pagination.jsx";
import {formatRupiah} from "../../../../utils/new-utils.js";

const metaData = {
    title: 'Data Transaksi',
};

export const TransactionIndexPage = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getAllTransactions = await findAll(currentPage);

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
    }

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-12">
                            <div>
                                <h1 className="text-30 lh-12 fw-700">Data Transaksi</h1>
                                <div className="mt-10">
                                    Data transaksi penjualan kursus anda.
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="row y-gap-30">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div className="d-flex items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Transaksi Saya</h2>
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
                                                        <th scope="col">Order</th>
                                                        <th scope="col">User</th>
                                                        <th scope="col">Kursus</th>
                                                        <th scope="col">Harga</th>
                                                        <th scope="col">Pendapatan Saya</th>
                                                        <th scope="col"></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    {
                                                        data.map((item, index) => (
                                                            <tr key={index}>
                                                                <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                                                <td>#{item.order.order_number}</td>
                                                                <td>{item.user.name}</td>
                                                                <td>{item.course.title}</td>
                                                                <td>{formatRupiah(item.order_item.course_subtotal)}</td>
                                                                <td>{formatRupiah(item.income.total_instructor_income)}</td>
                                                                <td>
                                                                    <Link to={`/instructor/transactions/${item.income.id}`}>
                                                                        Detail
                                                                    </Link>
                                                                </td>
                                                            </tr>
                                                        ))
                                                    }

                                                    {
                                                        data.length === 0 && (
                                                            <tr>
                                                                <td colSpan={7} className={'text-center'}>
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
                                    (!isLoading && pagination.total_items > 10) && (
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
            </InstructorWrapper>
        </Wrapper>
    );
}