import React, {useEffect, useState} from "react";
import {getCourseStudents} from "../../../../../services/instructors/course-service.js";
import {makeDateReadable} from "../../../../../utils/utils.js";

export const Students = ({activeTab, course, courseId}) => {
    const [isLoading, setIsLoading] = useState(true);
    const [students, setStudents] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getEnrollmentsData = await getCourseStudents(courseId);

                setStudents(getEnrollmentsData.enrollments);
            } catch (error) {
                console.error(error);
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, []);

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 3 ? "is-active" : ""} `}
        >
            {
                isLoading && (
                    <div className="loading">Loading...</div>
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
                                students.map((item, index) => (
                                    <tr key={item.enrollment.id}>
                                        <td>{index + 1}</td>
                                        <td>{item.user.name}</td>
                                        <td>{makeDateReadable(item.enrollment.enrollment_date)}</td>
                                        <td>{item.enrollment.progress_percentage}%</td>
                                        <td>
                                            {
                                                item.completion_date != null ? makeDateReadable(item.completion_date) : '-'
                                            }
                                        </td>
                                        <td></td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </table>
                    </div>
                )
            }
        </div>
    )
}