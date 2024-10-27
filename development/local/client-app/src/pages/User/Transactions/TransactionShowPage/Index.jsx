import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {STUDENT} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {UserWrapper} from "../../../../components/Users/UserWrapper/Index.jsx";
import {Order} from "./partials/Order.jsx";
import {Items} from "./partials/Items.jsx";
import {Payment} from "./partials/Payment.jsx";
import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {findById} from "../../../../services/new/order/student/order-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

const metaData = {
    title: "Data Transaksi"
};
const buttons = [
    "Order",
    "Items",
    "Pembayaran"
];

export const TransactionShowPage = () => {
    const {orderId} = useParams();

    const [isLoading, setIsLoading] = useState(true);

    const [order, setOrder] = useState({});
    const [items, setItems] = useState([]);
    const [payment, setPayment] = useState({});

    const [activeTab, setActiveTab] = useState(1);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findOrderById = await findById(orderId);

                if (findOrderById.success) {
                    setOrder(findOrderById.data.order);
                    setItems(findOrderById.data.items);
                    setPayment(findOrderById.data.payment);
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
    }, [orderId]);

    return (
        <Wrapper needAuth role={STUDENT}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <UserWrapper>
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
            </UserWrapper>
        </Wrapper>
    );
}