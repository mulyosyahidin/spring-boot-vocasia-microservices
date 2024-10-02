import React, {useContext, useState} from "react";
import {publishCourseById} from "../../_actions/CourseAction.jsx";
import {AuthContext} from "../../../../../states/contexts/AuthContext.jsx";

export const Settings = ({activeTab, courseId, course, setCourse}) => {
    const {sweetAlert} = useContext(AuthContext);

    const [loading, setLoading] = useState(false);

    const confirmPublish = async () => {
        setLoading(true);

        const result = await sweetAlert.fire({
            title: 'Terbitkan Kursus?',
            text: "Apakah Anda yakin ingin menerbitkan kursus ini?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Ya, terbitkan!',
            cancelButtonText: 'Batal'
        });

        if (result.isConfirmed) {
            try {
                let publishCourse = await publishCourseById(courseId);

                if (publishCourse) {
                    setCourse({
                        ...course,
                        status: publishCourse.status,
                    });

                    sweetAlert.fire(
                        'Terbit!',
                        'Kursus berhasil diterbitkan.',
                        'success'
                    );
                }
            } catch (error) {
                console.error('Error publishing course:', error);

                sweetAlert.fire(
                    'Gagal!',
                    'Terjadi kesalahan saat menerbitkan kursus.',
                    'error'
                );
            } finally {
                setLoading(false);
            }
        }
        else {
            setLoading(false);
        }
    }

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab == 6 ? "is-active" : ""} `}
        >
            {
                course.status === 'draft' && (
                    <div>
                        <h4 className="text-18 lh-1 fw-500">
                            Terbitkan Kursus
                        </h4>
                        <p className="mt-15 mb-15">
                            Dengan menerbitkan kursus, konten Anda dapat diakses oleh seluruh user Vocasia. Pastikan
                            Anda
                            telah melengkapi konten kursus Anda dengan baik.
                        </p>

                        <button className="button -sm -purple-1 text-white" disabled={loading}
                                onClick={() => confirmPublish()}>
                            {
                                loading ? 'Menerbitkan kursus...' : 'Terbitkan'
                            }
                        </button>
                    </div>
                )
            }

            {
                course.status === 'published' && (
                    <>
                        Kursus sudah diterbitkan.
                    </>
                )
            }
        </div>
    )
}