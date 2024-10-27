import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAll} from "../../../../services/new/instructor/instructor/student-service.js";
import {Pagination} from "../../../../components/commons/Pagination.jsx";

const metaData = {
    title: 'Siswa Saya',
};

export const StudentIndexPage = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllMyStudents = await findAll(currentPage);

                if (findAllMyStudents.success) {
                    setData(findAllMyStudents.data.data);
                    setPagination(findAllMyStudents.data.pagination);

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
    }, [currentPage]);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));
        setCurrentPage(newPage);
    };

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-12">
                            <div>
                                <h1 className="text-30 lh-12 fw-700">Siswa Saya</h1>
                                <div className="mt-10">
                                    Lihat daftar siswa yang mengikuti kursus Anda
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="row y-gap-30">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div className="d-flex items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Siswa Saya</h2>
                                </div>
                                <div className="py-30 px-30">
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
                                                            <tr key={item.instructor_student.id}>
                                                                <td>{(currentPage - 1) * pagination.per_page + (index + 1)}</td>
                                                                <td>{item.user.name}</td>
                                                                <td>{item.user.email}</td>
                                                                <td className={'text-center'}>{item.course_count}</td>
                                                                <td>
                                                                    <Link
                                                                        to={`/instructor/students/${item.instructor_student.id}`}>
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
                                </div>

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
                        </div>
                    </div>

                </div>
            </InstructorWrapper>
        </Wrapper>
    );
}