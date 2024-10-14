import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {Data} from "./partials/Data.jsx";
import {Actions} from "./partials/Actions.jsx";

const buttons = [
    "Data",
    "Tindakan",
];

export const WithdrawalRequestShow = () => {
    const {id} = useParams();

    const [isLoading, setIsLoading] = useState(false);
    const [request, setRequest] = useState({});
    const [instructor, setInstructor] = useState({});
    const [process, setProcess] = useState({});
    const [activeTab, setActiveTab] = useState(1);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getData = await findWithdrawalRequestById(id);

                setRequest(getData.request);
                setInstructor(getData.instructor);
                if (getData.process) {
                    setProcess(getData.process);
                }
            }
            catch (error) {
                console.error(error);
            }
            finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, [id]);

    return (
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
                                            <Data activeTab={activeTab} isLoading={isLoading} request={request} instructor={instructor}/>
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
    );
}