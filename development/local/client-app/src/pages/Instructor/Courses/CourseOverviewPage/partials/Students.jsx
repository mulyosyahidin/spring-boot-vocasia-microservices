import React, {useEffect, useState} from "react";
import {findAllCourseStudents} from "../../../../../services/new/enrollment/instructor/course-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {formatDate} from "../../../../../utils/new-utils.js";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";

export const Students = ({activeTab, courseId}) => {
    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));

        setCurrentPage(newPage);
    }

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllStudents = await findAllCourseStudents(courseId, currentPage);

                if (findAllStudents.success) {
                    setData(findAllStudents.data.data);
                    setPagination(findAllStudents.data.pagination);
                }

                setIsLoading(false);
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
    }, [courseId, currentPage]);

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
                                <th scope="col">Nama</th>
                                <th scope="col">Tanggal Enroll</th>
                                <th scope="col">Progress</th>
                                <th scope="col">Selesai Pada</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                data.map((item, index) => (
                                    <tr key={item.enrollment.id}>
                                        <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                        <td>{item.user.name}</td>
                                        <td>{formatDate(item.enrollment.enrollment_date)}</td>
                                        <td>{item.enrollment.progress_percentage}%</td>
                                        <td>
                                            {
                                                item.enrollment.completion_date != null ? formatDate(item.enrollment.completion_date) : '-'
                                            }
                                        </td>
                                        <td></td>
                                    </tr>
                                ))
                            }
                            {
                                data.length === 0 && (
                                    <tr>
                                        <td colSpan={6}>
                                            <div className="text-center">
                                                <strong>Tidak ada data untuk ditampilkan</strong>
                                            </div>
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