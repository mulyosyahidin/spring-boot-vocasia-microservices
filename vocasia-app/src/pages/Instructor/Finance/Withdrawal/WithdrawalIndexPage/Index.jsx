import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../../components/Instructors/InstructorWrapper/Index.jsx";
import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {findAll} from "../../../../../services/new/finance/instructor/withdrawal-service.js";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";

const metaData = {
    title: 'Riwayat Withdrawal',
};

export const WithdrawalIndexPage = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getAllMyWithdrawals = await findAll(currentPage);

                if (getAllMyWithdrawals.success) {
                    setData(getAllMyWithdrawals.data.data);
                    setPagination(getAllMyWithdrawals.data.pagination);

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
    }, []);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));
        setCurrentPage(newPage);
    };

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-12">
                            <div>
                                <h1 className="text-30 lh-12 fw-700">Withdrawal</h1>
                                <div className="mt-10">
                                    Lihat data withdrawal saya.
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="row y-gap-30">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Withdrawal Saya</h2>

                                    <div>
                                        <Link to={'/instructor/finances'} className={'-md -white text-dark-1'}>
                                            <i className="fa fa-arrow-left mr-10"/>
                                            Kembali
                                        </Link>
                                        <Link to={'/instructor/finances/withdrawal/request'}
                                              className={'-md -white text-dark-1'}>
                                            <i className="fa fa-plus mr-10 mr-10 ml-10"/>
                                            Request
                                        </Link>
                                    </div>
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
                                                        <th scope="col">Nominal</th>
                                                        <th scope="col">Tanggal Request</th>
                                                        <th scope="col">Rekening</th>
                                                        <th scope="col">Status</th>
                                                        <th scope="col">Diproses Pada</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    {
                                                        data.map((item, index) => (
                                                            <tr key={index}>
                                                                <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                                                <td>{formatRupiah(item.amount)}</td>
                                                                <td>{formatDate(item.requested_at)}</td>
                                                                <td>
                                                                    {item.bank_account_number}
                                                                    <br/>
                                                                    {item.bank_name} a.n {item.bank_account_name}
                                                                </td>
                                                                <td>{item.status}</td>
                                                                <td>
                                                                    {
                                                                        item.processed_at ? formatDate(item.processed_at) : '-'
                                                                    }
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
            </InstructorWrapper>
        </Wrapper>
    );
}