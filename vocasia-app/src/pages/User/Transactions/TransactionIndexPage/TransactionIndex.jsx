import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {Link} from "react-router-dom";

export const TransactionIndex = () => {
    const {sweetAlert} = useContext(AuthContext);

    const [isLoading, setIsLoading] = useState(false);
    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getData = await getAllMyOrders();

                setTransactions(getData.orders);
            }
            catch (error) {
                console.error(error);

                if (error.message) {
                    let msg = error.message;
                    if (error.data.error) {
                        msg += ' : ' + error.data.error;
                    }

                    sweetAlert.fire({
                        icon: 'error',
                        title: 'Terjadi Kesalahan!',
                        text: msg,
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        })
                }
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
                            Lihat data transaksi saya
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
                                                <th scope="col">Harga</th>
                                                <th scope="col">Status Pembayaran</th>
                                                <th scope="col">Tanggal</th>
                                                <th scope="col"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            {
                                                transactions.map((item, index) => {
                                                    console.log(item)
                                                    return (
                                                        <tr key={item.id}>
                                                            <td>{index + 1}</td>
                                                            <td>#{item.order_number}</td>
                                                            <td>{rupiahFormatter.format(item.total_price)}</td>
                                                            <td>{item.payment_status}</td>
                                                            <td>{makeDateReadable({date: item.created_at})}</td>
                                                            <td>
                                                                <Link to={`/users/transactions/${item.id}`}>
                                                                    Detail
                                                                </Link>
                                                            </td>
                                                        </tr>
                                                    )
                                                })
                                            }
                                            {
                                                transactions.length === 0 && (
                                                    <tr>
                                                        <td colSpan={5} className={'text-center'}>
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
                    </div>
                </div>
            </div>

        </div>
    );
}