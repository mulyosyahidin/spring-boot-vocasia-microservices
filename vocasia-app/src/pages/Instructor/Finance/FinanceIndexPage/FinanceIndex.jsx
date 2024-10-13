import React, {useEffect, useState} from "react";
import {makeDateReadable, rupiahFormatter} from "../../../../utils/utils.js";
import {Link} from "react-router-dom";
import {getInstructorBalanceData} from "../../../../services/instructors/finance-service.js";

export const FinanceIndex = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [overview, setOverview] = useState({});
    const [histories, setHistories] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                setIsLoading(true);

                const getData = await getInstructorBalanceData();

                setOverview(getData.instructor_balance);
                setHistories(getData.histories);

            } catch (e) {
                console.error(e);
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, []);

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50">
                <div className="col-12">
                    <div>
                        <h1 className="text-30 lh-12 fw-700">Data Keuangan</h1>
                        <div className="mt-10">
                            Lihat riwayat pendapatan, dan lakukan penarikan.
                        </div>
                    </div>
                </div>
            </div>

            <div className="row y-gap-10 mb-10">
                <div className="col-xl-3 col-md-6">
                    <div
                        className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                        <div>
                            <div className="lh-1 fw-500">Saldo Saat Ini</div>
                            <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                {
                                    isLoading ? '...' : rupiahFormatter.format(overview.current_balance)
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
                                    isLoading ? '...' : rupiahFormatter.format(overview.total_income)
                                }
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-xl-3 col-md-6">
                    <div
                        className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                        <div>
                            <div className="lh-1 fw-500">Total Penarikan</div>
                            <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                {
                                    isLoading ? '...' : rupiahFormatter.format(overview.total_withdrawn)
                                }
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-xl-3 col-md-6">
                    <div
                        className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                        <div>
                            <div className="lh-1 fw-500">Total Penarikan Tertunda</div>
                            <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                {
                                    isLoading ? '...' : rupiahFormatter.format(overview.total_pending_withdrawal)
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
                            <h2 className="text-17 lh-1 fw-500">Data Keuangan Saya</h2>

                            <Link to={'/instructor/finances/withdrawal'} className={'-md -white text-dark-1'}>
                                <i className="fa fa-plus mr-10"/>
                                Withdrawal
                            </Link>
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
                                                <th scope="col">Jenis Transaksi</th>
                                                <th scope="col">Nominal</th>
                                                <th scope="col">Saldo Sebelumnya</th>
                                                <th scope="col">Saldo Baru</th>
                                                <th scope="col">Tanggal</th>
                                                <th scope="col"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            {
                                                histories.map((item, index) => (
                                                    <tr key={index}>
                                                        <td>{index + 1}</td>
                                                        <td>{item.transaction_type}</td>
                                                        <td>{rupiahFormatter.format(item.amount)}</td>
                                                        <td>{rupiahFormatter.format(item.previous_balance)}</td>
                                                        <td>{rupiahFormatter.format(item.new_balance)}</td>
                                                        <td>{makeDateReadable({date: item.transaction_date})}</td>
                                                        <td></td>
                                                    </tr>
                                                ))
                                            }

                                            {
                                                histories.length === 0 && (
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
    );
}