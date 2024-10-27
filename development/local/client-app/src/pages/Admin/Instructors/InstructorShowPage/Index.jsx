import {useParams} from "react-router-dom";
import React, {useState} from "react";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../config/consts.js";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";
import {Transaction} from "../../Transactions/TransactionShowPage/partials/Transaction.jsx";
import {Course} from "../../Transactions/TransactionShowPage/partials/Course.jsx";
import {Student} from "../../Transactions/TransactionShowPage/partials/Student.jsx";
import {Payment} from "../../Transactions/TransactionShowPage/partials/Payment.jsx";
import {InstructorIncome} from "../../Transactions/TransactionShowPage/partials/InstructorIncome.jsx";
import {PlatformIncome} from "../../Transactions/TransactionShowPage/partials/PlatformIncome.jsx";
import {Data} from "./partials/Data.jsx";
import {Courses} from "./partials/Courses.jsx";
import {Students} from "./partials/Students.jsx";
import {Transactions} from "./partials/Transactions.jsx";
import {Finance} from "./partials/Finance.jsx";
import {Withdrawal} from "./partials/Withdrawal.jsx";

const buttons = [
    "Data",
    "Kursus",
    "Siswa",
    "Transaksi",
    "Keuangan",
    "Withdrawal",
];

const metaData = {
    title: 'Data Instruktur'
};

export const InstructorShowPage = () => {
    const [activeTab, setActiveTab] = useState(1);

    return (
        <Wrapper needAuth role={ADMIN}>
            <PreLoader/>
            <Meta meta={metaData}/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Data Instruktur</h1>
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
                                        <Data activeTab={activeTab}/>
                                        <Courses activeTab={activeTab}/>
                                        <Students activeTab={activeTab}/>
                                        <Transactions activeTab={activeTab}/>
                                        <Finance activeTab={activeTab}/>
                                        <Withdrawal activeTab={activeTab}/>
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