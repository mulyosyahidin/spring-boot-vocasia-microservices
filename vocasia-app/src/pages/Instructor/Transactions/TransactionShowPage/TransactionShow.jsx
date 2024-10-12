import {Meta} from "../../../../components/commons/Meta.jsx";
import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {getSaleTransactionById} from "../../../../services/instructors/transaction-service.js";
import {Transaction} from "./partials/Transaction.jsx";
import {Course} from "./partials/Course.jsx";
import {Student} from "./partials/Student.jsx";
import {Income} from "./partials/Income.jsx";

const buttons = [
    "Transaksi",
    "Kursus",
    "Siswa",
    "Pendapatan",
];

export const TransactionShow = () => {
    const {incomeId} = useParams();

    const [activeTab, setActiveTab] = useState(1);
    const [isLoading, setIsLoading] = useState(true);
    const [transaction, setTransaction] = useState({});
    const [metaData, setMetaData] = useState({
        title: 'Loading...',
    });

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getData = await getSaleTransactionById(incomeId);

                setMetaData({
                    title: `Order #${getData.order.order_number}`,
                });

                setTransaction(getData);
            } catch (e) {
                console.log(e);
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, []);

    return (
        <>
            <Meta meta={metaData}/>

            <div className="dashboard__content bg-light-4">
                <div className="row pb-50 mb-10">
                    <div className="col-auto">
                        <h1 className="text-30 lh-12 fw-700">Data Transaksi</h1>
                    </div>
                </div>

                <div className="row y-gap-30">
                    <div className="col-12">
                        <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
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
                                            <Transaction activeTab={activeTab} transaction={transaction} />
                                            <Course activeTab={activeTab} transaction={transaction} />
                                            <Student activeTab={activeTab} transaction={transaction} />
                                            <Income activeTab={activeTab} transaction={transaction} />
                                        </div>
                                    </div>
                                )
                            }
                        </div>
                    </div>
                </div>
            </div>

        </>
    )
        ;
}