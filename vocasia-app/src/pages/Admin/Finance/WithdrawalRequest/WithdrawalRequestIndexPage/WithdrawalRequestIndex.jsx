import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";

export const WithdrawalRequestIndex = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [status, setStatus] = useState('all');
    const [overview, setOverview] = useState({});
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getData = await getAllWithdrawalRequests(status);

                setOverview(getData.overview);
                setData(getData.requests);
            } catch (e) {
                console.error(e);
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, [status]);

    return (
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
                                    isLoading ? '...' : rupiahFormatter.format(overview.total_pending_amount)
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
                                    isLoading ? '...' : rupiahFormatter.format(overview.total_paid_amount)
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row y-gap-30">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
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
                                                <a href="#" className="d-block js-dropdown-link" onClick={() => setStatus('all')}>
                                                    Semua
                                                </a>
                                            </div>

                                            <div>
                                                <a href="#" className="d-block js-dropdown-link" onClick={() => setStatus('pending')}>
                                                    Pending
                                                </a>
                                            </div>

                                            <div>
                                                <a href="#" className="d-block js-dropdown-link" onClick={() => setStatus('paid')}>
                                                    Berhasil
                                                </a>
                                            </div>

                                            <div>
                                                <a href="#" className="d-block js-dropdown-link" onClick={() => setStatus('rejected')}>
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
                                        <div className="d-flex align-items-center">
                                            <strong role="status">Loading...</strong>
                                            <div className="spinner-border ms-auto" aria-hidden="true"></div>
                                        </div>
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
                                                        <td>{index + 1}</td>
                                                        <td>{item.instructor.user.name}</td>
                                                        <td>{rupiahFormatter.format(item.request.amount)}</td>
                                                        <td>{item.request.status}</td>
                                                        <td>{makeDateReadable(item.request.requested_at)}</td>
                                                        <td>
                                                            {
                                                                item.request.processed_at ? makeDateReadable(item.request.processed_at) : '-'
                                                            }
                                                        </td>
                                                        <td>
                                                            <Link to={`/admin/finances/withdrawal/${item.request.id}`}>
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
                    </div>
                </div>
            </div>

        </div>
    )
}