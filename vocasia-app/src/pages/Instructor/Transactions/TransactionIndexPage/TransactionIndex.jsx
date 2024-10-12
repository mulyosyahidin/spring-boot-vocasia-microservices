import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {getAllSaleTransactions} from "../../../../services/instructors/transaction-service.js";
import {rupiahFormatter} from "../../../../utils/utils.js";

export const TransactionIndex = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getData = await getAllSaleTransactions();

                setTransactions(getData.incomes);
            }
            catch (e) {
                console.error(e);
            }
            finally {
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
                                                transactions.map((item, index) => (
                                                    <tr key={index}>
                                                        <td>{index + 1}</td>
                                                        <td>#{item.order.order_number}</td>
                                                        <td>{item.user.name}</td>
                                                        <td>{item.course.title}</td>
                                                        <td>{rupiahFormatter.format(item.order_item.course_subtotal)}</td>
                                                        <td>{rupiahFormatter.format(item.income.total_instructor_income)}</td>
                                                        <td>
                                                            <Link to={`/instructor/transactions/${item.income.id}`}>
                                                                Detail
                                                            </Link>
                                                        </td>
                                                    </tr>
                                                ))
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