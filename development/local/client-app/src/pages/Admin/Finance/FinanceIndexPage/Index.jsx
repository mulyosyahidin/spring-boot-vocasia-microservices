import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findBalanceData} from "../../../../services/new/finance/admin/balance-service.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {formatDate, formatRupiah} from "../../../../utils/new-utils.js";
import {Link} from "react-router-dom";
import {Pagination} from "../../../../components/commons/Pagination.jsx";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";

const metaData = {
    title: 'Data Keuangan',
};

export const FinanceIndexPage = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [overview, setOverview] = useState({});
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const findMyBalanceData = await findBalanceData(currentPage);

                if (findMyBalanceData.success) {
                    setOverview(findMyBalanceData.data.platform_balance);
                    setPagination(findMyBalanceData.data.histories.pagination);
                    setData(findMyBalanceData.data.histories.data);

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
    };

    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50">
                        <div className="col-12">
                            <div>
                                <h1 className="text-30 lh-12 fw-700">Data Keuangan</h1>
                                <div className="mt-10">
                                    Lihat mutasi keuangan platform.
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="row y-gap-10 mb-10">
                        <div className="col-xl-6 col-md-6">
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
                        <div className="col-xl-6 col-md-6">
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
                    </div>

                    <div className="row y-gap-30">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Data Keuangan Platform</h2>
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
                                                <table className="table table-bordered table-hover table-striped"
                                                       style={{width: "100%"}}>
                                                    <thead>
                                                    <tr>
                                                        <th scope="col" rowSpan={2} style={{border: "1px solid #555"}}>No</th>
                                                        <th scope="col" rowSpan={2} style={{border: "1px solid #555"}}>Tanggal</th>
                                                        <th scope="col" colSpan={2} className={'text-center'} style={{border: "1px solid #555"}}>Mutasi
                                                        </th>
                                                        <th scope="col" rowSpan={2} style={{border: "1px solid #555"}} className={'text-center'}>Saldo</th>
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
                                                                        item.transaction_type === 'fee' &&
                                                                        formatRupiah(item.amount)
                                                                    }
                                                                </td>
                                                                <td style={{border: "1px solid #555"}} className={'text-center'}>{formatRupiah(item.new_balance)}</td>
                                                                <td style={{border: "1px solid #555"}}>
                                                                    {item.transaction_type}
                                                                    {
                                                                        item.reference_type == 'order' && <Link className={'ml-5'} to={`/admin/transactions/${item.reference_id}`}><i class={'fa fa-external-link'}></i></Link>
                                                                    }
                                                                </td>
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
            </AdminWrapper>
        </Wrapper>
    );

}