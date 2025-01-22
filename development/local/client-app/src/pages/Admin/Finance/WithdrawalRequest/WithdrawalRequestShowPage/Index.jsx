import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../../../components/Admin/AdminWrapper/Index.jsx";
import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {Data} from "./partials/Data.jsx";
import {Actions} from "./partials/Actions.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findById} from "../../../../../services/new/finance/admin/withdrawal-service.js";

const metaData = {
    title: 'Data Withdrawal',
};

const buttons = [
    "Data",
    "Tindakan",
];

export const WithdrawalRequestShowPage = () => {
    const {id} = useParams();

    const [isLoading, setIsLoading] = useState(true);
    const [request, setRequest] = useState({});
    const [instructor, setInstructor] = useState({});
    const [process, setProcess] = useState({});
    const [activeTab, setActiveTab] = useState(1);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const findWithdrawalRequestById = await findById(id);

                if (findWithdrawalRequestById.success) {
                    setRequest(findWithdrawalRequestById.data.request);
                    setInstructor(findWithdrawalRequestById.data.instructor);
                    if (findWithdrawalRequestById.data.process) {
                        setProcess(findWithdrawalRequestById.data.process);
                    }

                    setIsLoading(false);
                }
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
    }, [id]);

    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Data Withdrawal</h1>
                            <div className="mt-10">Data permintaan withdrawal</div>
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
                                                    <Data activeTab={activeTab} isLoading={isLoading} request={request}
                                                          instructor={instructor}/>
                                                    <Actions activeTab={activeTab} request={request} process={process}/>
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