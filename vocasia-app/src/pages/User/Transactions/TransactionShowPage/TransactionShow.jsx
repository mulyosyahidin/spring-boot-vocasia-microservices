import {useParams} from "react-router-dom";
import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {findMyOrderDataById} from "../../../../services/users/order-service.js";
import {Order} from "./partials/Order.jsx";
import {Items} from "./partials/Items.jsx";
import {Payment} from "./partials/Payment.jsx";

const buttons = [
    "Order",
    "Items",
    "Pembayaran"
];

export const TransactionShow = () => {
    const {sweetAlert} = useContext(AuthContext);
    const {orderId} = useParams();

    const [isLoading, setIsLoading] = useState(false);

    const [order, setOrder] = useState({});
    const [items, setItems] = useState([]);
    const [payment, setPayment] = useState({});

    const [activeTab, setActiveTab] = useState(1);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getData = await findMyOrderDataById(orderId);

                setOrder(getData.order);
                setItems(getData.items);
                setPayment(getData.payment);
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
    }, [orderId]);

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Data Transaksi</h1>
                </div>
            </div>

            <div className="row y-gap-30">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div className="tabs -active-purple-2 js-tabs pt-0">
                            <div
                                className="tabs__controls d-flex  x-gap-30 y-gap-20 flex-wrap items-center pt-20 px-30 border-bottom-light js-tabs-controls">
                                {buttons.map((elm, i) => (
                                    <button
                                        key={i}
                                        onClick={() => setActiveTab(i + 1)}
                                        className={`tabs__button text-light-1 js-tabs-button ${
                                            activeTab === i + 1 ? "is-active" : ""
                                        } `}
                                        type="button"
                                    >
                                        {elm}
                                    </button>
                                ))}
                            </div>

                            <div className="tabs__content py-30 px-30 js-tabs-content">
                                {
                                    isLoading && (
                                        <div className="text-center">
                                            <div className="spinner-border text-primary" role="status">
                                                <span className="visually-hidden">Loading...</span>
                                            </div>
                                        </div>
                                    )
                                }

                                {
                                    !isLoading && (
                                        <>
                                            <Order activeTab={activeTab} order={order} isLoading={isLoading}/>
                                            <Items activeTab={activeTab} items={items} isLoading={isLoading}/>
                                            <Payment activeTab={activeTab} payment={payment} isLoading={isLoading}/>
                                        </>
                                    )
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    );
}