import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../../../components/Admin/AdminWrapper/Index.jsx";
import React, {useEffect, useState} from "react";
import {findAll} from "../../../../../services/new/finance/admin/withdrawal-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";
import {Link} from "react-router-dom";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";

const metaData = {
    title: 'Riwayat Withdrawal',
};

export const WithdrawalRequestIndexPage = () => {
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
                const findAllWithdrawalRequest = await findAll(currentPage, status);

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
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50">
                        <div className="col-12">
                            <div>
                                <h1 className="text-30 lh-12 fw-700">Data Withdrawal</h1>
                                <div className="mt-10">
                                    Lihat data withdrawal instruktur
                                </div>
                            </div>
                        </div>
                    </div>

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

                    <div className="row y-gap-30">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Semua Data</h2>

                                    <div className="">
                                        <div
                                            id="dd3button"
                                            onClick={() => {
                                                document
                                                    .getElementById("dd3button")
                                                    .classList.toggle("-is-dd-active");
                                                document
                                                    .getElementById("dd3content")
                                                    .classList.toggle("-is-el-visible");
                                            }}
                                            className="dropdown js-dropdown js-category-active"
                                        >
                                            <div
                                                className="dropdown__button d-flex items-center text-14 bg-white -dark-bg-dark-1 border-light rounded-8 px-20 py-10 text-14 lh-12"
                                                data-el-toggle=".js-category-toggle"
                                                data-el-toggle-active=".js-category-active"
                                            >
                                                <span className="js-dropdown-title">Lihat Data</span>
                                                <i className="icon text-9 ml-40 icon-chevron-down"></i>
                                            </div>

                                            <div
                                                id="dd3content"
                                                className="toggle-element -dropdown -dark-bg-dark-2 -dark-border-white-10 js-click-dropdown js-category-toggle"
                                            >
                                                <div className="text-14 y-gap-15 js-dropdown-list">
                                                    <div>
                                                        <a href="#" className="d-block js-dropdown-link"
                                                           onClick={() => setStatus('all')}>
                                                            Semua
                                                        </a>
                                                    </div>

                                                    <div>
                                                        <a href="#" className="d-block js-dropdown-link"
                                                           onClick={() => setStatus('pending')}>
                                                            Pending
                                                        </a>
                                                    </div>

                                                    <div>
                                                        <a href="#" className="d-block js-dropdown-link"
                                                           onClick={() => setStatus('paid')}>
                                                            Berhasil
                                                        </a>
                                                    </div>

                                                    <div>
                                                        <a href="#" className="d-block js-dropdown-link"
                                                           onClick={() => setStatus('rejected')}>
                                                            Ditolak
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
                                                        <th scope="col">Nama</th>
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
                                                                <td>{item.instructor.user.name}</td>
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
            </AdminWrapper>
        </Wrapper>
    );
}