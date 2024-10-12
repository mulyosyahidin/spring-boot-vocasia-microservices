import React, {useEffect, useState} from "react";
import {getAllMyStudents} from "../../../../services/instructors/student-service.js";
import {Link} from "react-router-dom";

export const StudentIndex = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [students, setStudents] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                setIsLoading(true);

                const getData = await getAllMyStudents();
                setStudents(getData.students);
            } catch (e) {
                console.error(e);
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, []);

    return (
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
                                        <div className="d-flex align-items-center">
                                            <strong role="status">Loading...</strong>
                                            <div className="spinner-border ms-auto" aria-hidden="true"></div>
                                        </div>
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
                                                students.map((item, index) => (
                                                    <tr key={item.instructor_student.id}>
                                                        <td>{index + 1}</td>
                                                        <td>{item.user.name}</td>
                                                        <td>{item.user.email}</td>
                                                        <td className={'text-center'}>{item.course_count}</td>
                                                        <td>
                                                            <Link to={`/instructor/students/${item.instructor_student.id}`}>
                                                                Detail
                                                            </Link>
                                                        </td>
                                                    </tr>
                                                ))
                                            }
                                            </tbody>
                                        </table>
                                    </div>
                                )
                            }
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )

}