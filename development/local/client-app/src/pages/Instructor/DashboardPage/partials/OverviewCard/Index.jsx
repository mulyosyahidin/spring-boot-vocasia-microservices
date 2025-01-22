import {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {getOverview} from "../../../../../services/new/instructor/instructor/overview-service.js";
import {formatRupiah} from "../../../../../utils/new-utils.js";

export const OverviewCard = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [overview, setOverview] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getInstructorOverview = await getOverview();

                if (getInstructorOverview.success) {
                    setOverview(getInstructorOverview.data.overview);
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
    }, []);

    return (
        <div className="row y-gap-30">
            {
                isLoading && (
                    <div className="col-12">
                         <span>
                             <i className={'fa fa-spinner fa-spin mr-5'}></i>
                             <strong role="status">Loading...</strong>
                         </span>
                    </div>
                )
            }

            {
                !isLoading && (
                    <>
                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Pendapatan</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {formatRupiah(overview.finance.total_income)}
                                    </div>
                                </div>

                                <i className={`text-40 icon-coupon text-purple-1`}></i>
                            </div>
                        </div>

                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Withdrawal</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {formatRupiah(overview.finance.total_withdrawn)}
                                    </div>
                                </div>

                                <i className={`text-40 icon-coupon text-purple-1`}></i>
                            </div>
                        </div>

                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Kursus</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {overview.course.total}
                                    </div>
                                </div>

                                <i className={`text-40 icon-play-button text-purple-1`}></i>
                            </div>
                        </div>

                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Siswa</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {overview.total_student}
                                    </div>
                                </div>

                                <i className={`text-40 icon-graduate-cap text-purple-1`}></i>
                            </div>
                        </div>
                    </>
                )
            }
        </div>
    );
}