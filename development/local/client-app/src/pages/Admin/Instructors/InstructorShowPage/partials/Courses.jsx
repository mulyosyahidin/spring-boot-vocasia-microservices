import React, {useEffect, useState} from "react";
import {Link, useParams} from "react-router-dom";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";
import {findAllByInstructorId} from "../../../../../services/new/course/admin/course-service.js";
import {formatRupiah} from "../../../../../utils/new-utils.js";

export const Courses = ({activeTab}) => {
    const {instructorId} = useParams();

    const [isLoading, setIsLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllInstructorCourses = await findAllByInstructorId(instructorId, currentPage);

                if (findAllInstructorCourses.success) {
                    setData(findAllInstructorCourses.data.data);
                    setPagination(findAllInstructorCourses.data.pagination);
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
            className={`tabs__pane -tab-item-1 ${activeTab === 2 ? "is-active" : ""} `}
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
                    <>
                        <div className={'table-responsive'}>
                            <table className="table table-borderless table-hover table-striped"
                                   style={{width: "100%"}}>
                                <thead>
                                <tr>
                                    <th scope="col">No</th>
                                    <th scope="col">Judul</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Harga</th>
                                    <th scope="col">Gratis / Diskon</th>
                                    <th scope="col" className={'text-center'}>Chapter / Lesson</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    data.map((item, index) => (
                                        <tr key={`${item.course.id}-${index}`}>
                                            <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                            <td>{item.course.title}</td>
                                            <td>{item.course.status}</td>
                                            <td>{formatRupiah(item.course.price)}</td>
                                            <td>
                                                {item.course.is_free ? 'Ya' : 'Tidak'}/{item.course.is_discount ? formatRupiah(item.course.discount) : 'Tidak'}
                                            </td>
                                            <td className={'text-center'}>{item.course.chapter_count}/{item.course.lesson_count}</td>
                                            <td>
                                                <Link to={`/admin/courses/${item.course.id}`}>
                                                    Detail
                                                </Link>
                                            </td>
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

                        {
                            (!isLoading && pagination.total_items > 10) && (
                                <div className="row justify-center pt-30 pb-30">
                                    <div className="col-auto">
                                        <Pagination pagination={pagination} onPageChange={handlePageChange}/>
                                    </div>
                                </div>
                            )
                        }
                    </>
                )
            }
        </div>
    )
}