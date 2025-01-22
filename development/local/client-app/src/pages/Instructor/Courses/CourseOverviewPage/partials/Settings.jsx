import React, {useContext, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {publishById} from "../../../../../services/new/course/instructor/course-service.js";
import {Link} from "react-router-dom";

export const Settings = ({activeTab, course, courseId}) => {
    const [isLoading, setIsLoading] = useState(false);

    const confirmPublish = async () => {
        setIsLoading(true);

        await withReactContent(Swal).fire({
            icon: 'warning',
            title: 'Terbitkan Kursus?',
            text: 'Apakah Anda yakin ingin menerbitkan kursus ini?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Ya, terbitkan!',
            cancelButtonText: 'Batal'
        })
            .then(async (isConfirmed) => {
                if (isConfirmed) {
                   try {
                       const doPublishCourse = await publishById(courseId);

                      if (doPublishCourse.success) {
                          await withReactContent(Swal).fire({
                              title: 'Berhasil!',
                              text: doPublishCourse.message,
                              icon: 'success',
                          })
                              .then((isConfirmed) => {
                                  if (isConfirmed) {
                                      window.location.reload();
                                  }
                              })
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
            });
    }

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 6 ? "is-active" : ""} `}
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

                        <button className="button -sm -purple-1 text-white" disabled={isLoading}
                                onClick={() => confirmPublish()}>
                            {
                                isLoading ? 'Menerbitkan kursus...' : 'Terbitkan'
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

            <div className="mt-10">
                <Link to={`/instructor/courses/${courseId}/edit`} className="-sm -purple-1 black">
                    <i className={`icon-edit`}/> EDIT KURSUS
                </Link>
            </div>
        </div>
    )
}