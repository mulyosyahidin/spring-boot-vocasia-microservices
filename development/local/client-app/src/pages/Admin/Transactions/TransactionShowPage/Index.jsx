import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findById} from "../../../../services/new/order/admin/transaction-service.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../config/consts.js";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {Transaction} from "./partials/Transaction.jsx";
import {Course} from "./partials/Course.jsx";
import {Student} from "./partials/Student.jsx";
import {Payment} from "./partials/Payment.jsx";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";
import {InstructorIncome} from "./partials/InstructorIncome.jsx";
import {PlatformIncome} from "./partials/PlatformIncome.jsx";

export const TransactionShowPage = () => {
    const {id} = useParams();

    const [courses, setCourses] = useState([]);

    const [activeTab, setActiveTab] = useState(1);
    const [isLoading, setIsLoading] = useState(true);
    const [transaction, setTransaction] = useState({
        payment: {},
        items: [],
        user: {},
        order: {},
        platform_incomes: [],
        instructor_incomes: [],
    });
    const [buttons, setButtons] = useState([]);

    const [metaData, setMetaData] = useState({
        title: 'Loading...',
    });

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findTransactionById = await findById(id);

                if (findTransactionById.success) {
                    const {
                        payment,
                        items,
                        user,
                        order,
                        instructor_incomes,
                        platform_incomes
                    } = findTransactionById.data;

                    setMetaData({
                        title: `Order #${order.order_number}`,
                    });

                    setTransaction({
                        payment: payment,
                        items: items,
                        user: user,
                        order: order,
                        platform_incomes: platform_incomes,
                        instructor_incomes: instructor_incomes,
                    });

                    if (order.payment_status == 'success') {
                        setButtons(
                            [
                                "Transaksi",
                                "Kursus",
                                "Siswa",
                                "Pembayaran",
                                "Pendapatan Instruktur",
                                "Pendapatan Platform",
                            ]
                        );
                    } else {
                        setButtons(
                            [
                                "Transaksi",
                                "Kursus",
                                "Siswa",
                                "Pembayaran",
                            ]
                        );
                    }

                    setIsLoading(false);
                }
            } catch (error) {
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
    }, [id]);

    return (
        <Wrapper needAuth role={ADMIN}>
            <PreLoader/>
            <Meta meta={metaData}/>

            <AdminWrapper>
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
                                                    <Transaction activeTab={activeTab} transaction={transaction}/>
                                                    <Course activeTab={activeTab} transaction={transaction}/>
                                                    <Student activeTab={activeTab} transaction={transaction}/>
                                                    <Payment activeTab={activeTab} transaction={transaction}/>
                                                    <InstructorIncome activeTab={activeTab} transaction={transaction}/>
                                                    <PlatformIncome activeTab={activeTab} transaction={transaction}/>
                                                </>
                                            )
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </AdminWrapper>
        </Wrapper>
    );
}