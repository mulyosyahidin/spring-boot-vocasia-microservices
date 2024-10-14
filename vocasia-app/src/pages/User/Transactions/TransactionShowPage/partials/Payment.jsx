import React, {useContext, useEffect} from "react";
import {AuthContext} from "../../../../../states/contexts/AuthContext.jsx";

export const Payment = ({activeTab, payment, isLoading}) => {
    const {sweetAlert} = useContext(AuthContext);

    useEffect(() => {
        const snapSrcUrl = 'https://app.sandbox.midtrans.com/snap/snap.js'
        const myMidtransClientKey = 'SB-Mid-client-4KbJJQfBaYS9Lplt';
        const script = document.createElement('script')
        script.src = snapSrcUrl
        script.setAttribute('data-client-key', myMidtransClientKey)
        script.async = true

        document.body.appendChild(script)

        return () => {
            document.body.removeChild(script)
        }
    }, []);

    const handlePayButton = () => {
        window.snap.pay(payment.snap_token, {
            onSuccess: () => {
                window.location.reload();
            },
            onPending: (result) => {
                console.log('pending transaction', result)
            },
            onError: (result) => {
                console.log('error transaction', result)
            },
            onClose: () => {
                // oke
            },
        })
    }

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 3 ? "is-active" : ""} `}
        >
            {
                (!isLoading && payment.payment_status === 'success') && (
                    <>
                        <div
                            className="d-flex items-center justify-between bg-success-1 pl-30 pr-20 py-30 rounded-8">
                            <div className="text-success-2 lh-1 fw-500">
                                Order sudah dibayar
                            </div>
                        </div>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '50px'}}>
                            Total Harga
                        </h4>
                        <p className="mt-2">
                            {rupiahFormatter.format(payment.total_price)}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Biaya Layanan
                        </h4>
                        <p className="mt-2">
                            {rupiahFormatter.format(payment.additional_fee)}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Total Bayar
                        </h4>
                        <p className="mt-2">
                            {rupiahFormatter.format(payment.total_payment)}
                        </p>
                    </>
                )
            }

            {
                (!isLoading && payment.payment_status === 'PENDING') && (
                    <>
                        <div
                            className="d-flex items-center justify-between bg-warning-1 pl-30 pr-20 py-30 rounded-8">
                            <div className="text-warning-2 lh-1 fw-500">
                                Silahkan lakukan pembayaran sebelum {makeDateReadable({date: payment.payment_expire_at})}
                            </div>
                        </div>

                        <button onClick={() => handlePayButton()}
                                className="button -md -black col-12 -uppercase text-white mt-20">
                            Bayar
                        </button>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '50px'}}>
                            Total Harga
                        </h4>
                        <p className="mt-2">
                            {rupiahFormatter.format(payment.total_price)}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Biaya Layanan
                        </h4>
                        <p className="mt-2">
                            {rupiahFormatter.format(payment.additional_fee)}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Total Bayar
                        </h4>
                        <p className="mt-2">
                            {rupiahFormatter.format(payment.total_payment)}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Batas Waktu Pembayaran
                        </h4>
                        <p className="mt-2">
                            {makeDateReadable({date: payment.payment_expire_at})}
                        </p>

                    </>
                )
            }
        </div>
    );
}