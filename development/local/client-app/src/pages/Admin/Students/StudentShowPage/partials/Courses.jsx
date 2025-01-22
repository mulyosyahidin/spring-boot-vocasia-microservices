import {Link, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";
import {findAllByUserId} from "../../../../../services/new/enrollment/admin/student-course-service.js";

export const Courses = ({activeTab}) => {
    const {userId} = useParams();

    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findStudentTransactionById = await findAllByUserId(userId, currentPage);

                if (findStudentTransactionById.success) {
                    setData(findStudentTransactionById.data.data);
                    setPagination(findStudentTransactionById.data.pagination);

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
    }, [userId]);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));

        setCurrentPage(newPage);
    }

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 3 ? "is-active" : ""} `}
        >
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
                    <div className={'table-responsive'}>
                        <table className="table table-borderless table-hover table-striped"
                               style={{width: "100%"}}>
                            <thead>
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Judul</th>
                                <th scope="col">Tanggal Enrollment</th>
                                <th scope="col">Progress</th>
                                <th scope="col">Status</th>
                                <th scope="col">Selesai Pada</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                data.map((item, index) => (
                                    <tr key={index}>
                                        <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                        <td>{item.course.title}</td>
                                        <td>{formatDate(item.enrollment.enrollment_date)}</td>
                                        <td>{item.enrollment.progress_percentage}%</td>
                                        <td>{item.enrollment.status}</td>
                                        <td>{item.enrollment.completion_date == null ? '-' : formatDate(item.enrollment.completion_date)}</td>
                                        <td></td>
                                    </tr>
                                ))
                            }

                            {
                                data.length === 0 && (
                                    <tr>
                                        <td colSpan={7} className={'text-center'}>
                                            Tidak ada data untuk ditampilkan
                                        </td>
                                    </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                )
            }

            {
                (!isLoading && pagination.total_items > 10) && (
                    <div className="row justify-center pt-30 pb-30">
                        <div className="col-auto">
                            <Pagination pagination={pagination} onPageChange={handlePageChange}/>
                        </div>
                    </div>
                )
            }
        </div>
    )
}