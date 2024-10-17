import React, {useState} from "react";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../config/consts.js";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {Data} from "./partials/Data.jsx";
import {Transactions} from "./partials/Transactions.jsx";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";
import {Courses} from "./partials/Courses.jsx";

const buttons = [
    "Data",
    "Transaksi",
    "Kursus",
];

export const StudentShowPage = () => {
    const [activeTab, setActiveTab] = useState(1);

    return (
        <Wrapper needAuth role={ADMIN}>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Data Siswa</h1>
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
                                        <Transactions activeTab={activeTab}/>
                                        <Courses activeTab={activeTab}/>
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