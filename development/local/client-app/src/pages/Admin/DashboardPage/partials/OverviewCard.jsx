import {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {formatRupiah} from "../../../../utils/new-utils.js";
import {
    getAuthenticationOverview,
    getCourseOverview,
    getInstructorOverview, getOrderOverview
} from "../../../../services/new/admin-overview.js";

export const OverviewCard = () => {
    const [isLoading, setIsLoading] = useState(false);

    const [overview, setOverview] = useState({
        totalCourses: 0,
        totalInstructors: 0,
        totalStudents: 0,
        totalSales: 0
    });

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const [courseOverview, instructorOverview, authenticationOverview, orderOverview] = await Promise.all([
                    getCourseOverview(), getInstructorOverview(), getAuthenticationOverview(), getOrderOverview(),
                ]);

                if (courseOverview.success && instructorOverview.success && authenticationOverview.success && orderOverview.success) {
                    setIsLoading(false);

                    setOverview({
                        totalCourses: courseOverview.data.total_courses,
                        totalInstructors: instructorOverview.data.total_instructors,
                        totalStudents: authenticationOverview.data.total_students,
                        totalSales: orderOverview.data.total_success_orders,
                    });
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
                        });
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
                                    <div className="lh-1 fw-500">Total Kursus</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {overview.totalCourses}
                                    </div>
                                </div>

                                <i className={`text-40 icon-play-button text-purple-1`}></i>
                            </div>
                        </div>

                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Instruktur</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {overview.totalInstructors}
                                    </div>
                                </div>

                                <i className={`text-40 icon-person-2 text-purple-1`}></i>
                            </div>
                        </div>

                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Siswa</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {overview.totalStudents}
                                    </div>
                                </div>

                                <i className={`text-40 icon-person-3 text-purple-1`}></i>
                            </div>
                        </div>

                        <div className="col-xl-3 col-md-6">
                            <div
                                className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                                <div>
                                    <div className="lh-1 fw-500">Total Penjualan</div>
                                    <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                        {formatRupiah(overview.totalSales)}
                                    </div>
                                </div>

                                <i className={`text-40 icon-coupon text-purple-1`}></i>
                            </div>
                        </div>
                    </>
                )
            }
        </div>
    )
}