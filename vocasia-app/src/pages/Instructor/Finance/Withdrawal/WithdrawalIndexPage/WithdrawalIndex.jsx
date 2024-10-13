import React, {useEffect, useState} from "react";
import {makeDateReadable, rupiahFormatter} from "../../../../../utils/utils.js";
import {Link} from "react-router-dom";
import {getInstructorWithdrawalHistories} from "../../../../../services/instructors/finance-service.js";

export const WithdrawalIndex = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [histories, setHistories] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                setIsLoading(true);

                const getData = await getInstructorWithdrawalHistories();
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
            <div className="row pb-50 mb-10">
                <div className="col-12">
                    <div>
                        <h1 className="text-30 lh-12 fw-700">Riwayat Withdrawal</h1>
                        <div className="mt-10">
                            Lihat riwayat penarikan saya.
                        </div>
                    </div>
                </div>
            </div>

            <div className="row y-gap-30">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                            <h2 className="text-17 lh-1 fw-500">Riwayat Withdrawal Saya</h2>

                            <div>
                                <Link to={'/instructor/finances/withdrawal/request'} className={'-md -white text-dark-1'}>
                                    <i className="fa fa-plus mr-10 ml-10"/>
                                    Request
                                </Link>
                                <Link to={'/instructor/finances'} className={'-md -white text-dark-1'}>
                                    <i className="fa fa-arrow-left mr-10 ml-10"/>
                                    Kembali
                                </Link>
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
                                                <th scope="col">Nominal</th>
                                                <th scope="col">Tanggal Request</th>
                                                <th scope="col">Rekening</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Diproses Pada</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            {
                                                histories.map((item, index) => (
                                                    <tr key={index}>
                                                        <td>{index + 1}</td>
                                                        <td>{rupiahFormatter.format(item.amount)}</td>
                                                        <td>{makeDateReadable(item.requested_at)}</td>
                                                        <td>
                                                            {item.bank_account_number}
                                                            <br/>
                                                            {item.bank_name} a.n {item.bank_account_name}
                                                        </td>
                                                        <td>{item.status}</td>
                                                        <td>
                                                            {
                                                                item.processed_at ? makeDateReadable(item.processed_at) : '-'
                                                            }
                                                        </td>
                                                    </tr>
                                                ))
                                            }

                                            {
                                                histories.length === 0 && (
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
                    </div>
                </div>
            </div>

        </div>
    );
}