import {Link, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAllByInstructorId} from "../../../../../services/new/instructor/admin/student-service.js";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";

export const Students = ({activeTab}) => {
    const {instructorId} = useParams();

    const [isLoading, setIsLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllInstructorStudents = await findAllByInstructorId(instructorId, currentPage);

                if (findAllInstructorStudents.success) {
                    setData(findAllInstructorStudents.data.data);
                    setPagination(findAllInstructorStudents.data.pagination);
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
    }, [instructorId, currentPage]);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));

        setCurrentPage(newPage);
    };

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
                                <th scope="col">Email</th>
                                <th scope="col" className={'text-center'}>Jumlah Kursus</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                data.map((item, index) => (
                                    <tr key={`${item.instructor_student.id}-${index}`}>
                                        <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                        <td>{item.user.name}</td>
                                        <td>{item.user.email}</td>
                                        <td className={'text-center'}>{item.course_count}</td>
                                        <td>
                                            <Link
                                                to={`/admin/instructors/${instructorId}/students/${item.instructor_student.id}`}>
                                                Detail
                                            </Link>
                                        </td>
                                    </tr>
                                ))
                            }

                            {
                                data.length === 0 && (
                                    <tr>
                                        <td colSpan={5} className={'text-center'}>
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
                (!isLoading && data.length > 10) && (
                    <div className="row justify-center pt-30 pb-30">
                        <div className="col-auto">
                            <Pagination pagination={pagination} onPageChange={handlePageChange}/>
                        </div>
                    </div>
                )
            }
        </div>
    );
}