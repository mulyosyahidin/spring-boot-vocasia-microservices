import {useEffect, useState} from "react";
import {getCourseIncomesData} from "../../../../../services/instructors/course-service.js";
import {makeDateReadable, rupiahFormatter} from "../../../../../utils/utils.js";
import {Link} from "react-router-dom";

export const Earnings = ({ activeTab, course, courseId }) => {
    const [isLoading, setIsLoading] = useState(true);
    const [incomes, setIncomes] = useState([]);
    const [overview, setOverview] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getIncomesData = await getCourseIncomesData(courseId);

                setIncomes(getIncomesData.incomes);
                setOverview(getIncomesData.overview);
            }
            catch (e) {
                console.error(e);
            }
            finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, []);

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab == 5 ? "is-active" : ""} `}
        >
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
                        <div className="row mb-10">
                            <div className="col-xl-3 col-md-6">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Transaksi</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {overview.total_transaction}
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col-xl-3 col-md-6">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Pendapatan</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {rupiahFormatter.format(overview.total_income)}
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col-xl-3 col-md-6">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Pembayaran User</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {rupiahFormatter.format(overview.total_user_payment)}
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col-xl-3 col-md-6">
                                <div
                                    className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                    <div>
                                        <div className="lh-1 fw-500">Total Potongan Platform</div>
                                        <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                            {rupiahFormatter.format(overview.total_platform_income)}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={'table-responsive'}>
                            <table className="table table-borderless table-hover table-striped"
                                   style={{width: "100%"}}>
                                <thead>
                                <tr>
                                    <th scope="col">No</th>
                                    <th scope="col">Order ID</th>
                                    <th scope="col">Tanggal</th>
                                    <th scope="col">Pendapatan</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    incomes.map((item, index) => (
                                        <tr key={item.id}>
                                            <td>{index + 1}</td>
                                            <td>#{item.order.order_number}</td>
                                            <td>{makeDateReadable({date: item.income.created_at})}</td>
                                            <td>{rupiahFormatter.format(item.income.total_instructor_income)}</td>
                                            <td>
                                            </td>
                                        </tr>
                                    ))
                                }
                                </tbody>
                            </table>
                        </div>
                    </>
                )
            }
        </div>
    )
}