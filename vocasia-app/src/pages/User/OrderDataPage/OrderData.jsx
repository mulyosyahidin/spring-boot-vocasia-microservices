import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../states/contexts/AuthContext.jsx";
import {Link, useParams} from "react-router-dom";
import {getOrderData} from "../../../services/order-service.js";
import {Meta} from "../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {Header} from "../../../components/Header/Index.jsx";
import {makeDateReadable, rupiahFormatter} from "../../../utils/utils.js";

const metaData = {
    title: 'Order Data'
};
const dark = false;

export const OrderData = () => {
    const {orderId} = useParams();
    const {sweetAlert} = useContext(AuthContext);

    const [isLoading, setIsLoading] = useState(true);

    const [orderData, setOrderData] = useState({});
    const [paymentData, setPaymentData] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const order = await getOrderData(orderId);

                setOrderData(order.order);
                setPaymentData(order.payment);
            } catch (e) {
                console.log(e);
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();

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
    }, [orderId]);

    const handlePayButton = () => {
        window.snap.pay(paymentData.snap_token, {
            onSuccess: () => {
                console.log('success')
            },
            onPending: (result) => {
                console.log('pending transaction', result)
            },
            onError: (result) => {
                console.log('error transaction', result)
            },
            onClose: () => {
                console.log('customer close the popup window without the finishing the payment')
            },
        })
    }

    return (
        <>
            <Meta meta={metaData}/>
            <PreLoader/>
            <Header/>

            <div className="content-wrapper  js-content-wrapper ">

                <section className={`breadcrumbs ${dark ? "bg-dark-1" : ""} `}>
                    <div className="container">
                        <div className="row">
                            <div className="col-auto">
                                <div className="breadcrumbs__content">
                                    <div
                                        className={`breadcrumbs__item ${dark ? "text-dark-3" : ""} `}
                                    >
                                        <Link to="/">Beranda</Link>
                                    </div>

                                    <div
                                        className={`breadcrumbs__item ${dark ? "text-dark-3" : ""} `}
                                    >
                                        Data Order
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section className="page-header -type-1">
                    <div className="container">
                        <div className="page-header__content">
                            <div className="row justify-center text-center">
                                <div className="col-auto">
                                    <div>
                                        <h1 className="page-header__title">Data Order</h1>
                                    </div>

                                    <div>
                                        <p className="page-header__text">
                                            Order #{orderData.order_number}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section className="layout-pt-md layout-pb-lg">
                    <div className="container">
                        {
                            isLoading && (
                                <>
                                    Loading...
                                </>
                            )
                        }

                        {
                            !isLoading && (
                                <div className="row y-gap-50">
                                    <div className="col-lg-8">
                                        <div className="pt-30 pb-15 bg-white border-light rounded-8 bg-light-4">
                                            <h5 className="px-30 text-20 fw-500">Data Order</h5>

                                            <div className="px-30 mt-25">
                                                <div className="d-flex justify-between">
                                                    <div className="py-15 fw-500 text-dark-1">Order Number</div>
                                                    <div className="py-15 text-grey">{orderData.order_number}</div>
                                                </div>
                                                <div className="d-flex justify-between">
                                                    <div className="py-15 fw-500 text-dark-1">Jumlah Item</div>
                                                    <div className="py-15 text-grey">{orderData.total_items}</div>
                                                </div>
                                                <div className="d-flex justify-between">
                                                    <div className="py-15 fw-500 text-dark-1">Tanggal Order</div>
                                                    <div className="py-15 text-grey">{makeDateReadable(orderData.created_at)}</div>
                                                </div>
                                                <div className="d-flex justify-between">
                                                    <div className="py-15 fw-500 text-dark-1">Status</div>
                                                    <div className="py-15 text-grey">{orderData.payment_status}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-lg-4">
                                        <div className="">
                                            <div className="pt-30 pb-15 bg-white border-light rounded-8 bg-light-4">
                                                <h5 className="px-30 text-20 fw-500">Kursus</h5>

                                                <div className="d-flex justify-between px-30 mt-25">
                                                    <div className="py-15 fw-500 text-dark-1">Kursus</div>
                                                    <div className="py-15 fw-500 text-dark-1">Subtotal</div>
                                                </div>


                                                {orderData.orderItems.map((elm, i) => (
                                                    <div
                                                        key={i}
                                                        className={`d-flex justify-between ${
                                                            i === 0 ? "border-top-dark" : ""
                                                        }  px-30`}
                                                    >
                                                        <div className="py-15 text-grey">
                                                            <Link className="linkCustom"
                                                                  to={`/courses/${elm.course_slug}/${elm.course_id}`}>
                                                                {elm.course_title}
                                                            </Link>
                                                        </div>
                                                        <div className="py-15 text-grey">
                                                            {
                                                                elm.course_is_free ? 'Gratis' : (
                                                                    elm.course_is_discount ? rupiahFormatter.format(elm.course_price - elm.course_discount) : rupiahFormatter.format(elm.course_price)
                                                                )
                                                            }
                                                        </div>
                                                    </div>
                                                ))}

                                                <div className="d-flex justify-between border-top-dark px-30">
                                                    <div className="py-15 fw-500 text-dark-1">Total</div>
                                                    <div className="py-15 fw-500 text-dark-1">
                                                        {rupiahFormatter.format(orderData.total_price)}
                                                    </div>
                                                </div>
                                            </div>

                                            <div className="mt-30">
                                                <button onClick={() => handlePayButton()}
                                                      className="button -md -black col-12 -uppercase text-white">
                                                    Bayar
                                                </button>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            )
                        }
                    </div>
                </section>

            </div>
        </>
    );
}