import {calculatePercentage, formatDate, formatRupiah} from "../../../../../utils/new-utils.js";
import React from "react";

export const Information = ({course, activeTab}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 1 ? "is-active" : ""} `}
        >
            <div className="py-30 px-30">
                {
                    course.featured_picture != null && (
                        <div>
                            <img src={course.featured_picture_url} alt={course.title} className="w-100 rounded-lg"/>
                        </div>
                    )
                }

                <div>
                    <h4 className="text-18 lh-1 fw-500">
                        {course.title}
                    </h4>
                    <p className="mt-15">
                        <div
                            dangerouslySetInnerHTML={{__html: course.short_description}}
                        />
                    </p>
                </div>

                <div>
                    <h5 className="text-18 lh-1 fw-500" style={{marginTop: '30px'}}>
                        Deskripsi
                    </h5>
                    <p className="mt-15">
                        <div
                            dangerouslySetInnerHTML={{__html: course.description}}
                        />
                    </p>
                </div>

                <div style={{marginTop: '30px'}}>
                    <h4 className="text-18 lh-1 fw-500">
                        Total Durasi
                    </h4>
                    <p className="mt-2">
                        {course.total_duration}
                    </p>

                    <h4 className="text-18 lh-1 fw-500" style={{marginTop: '15px'}}>
                        Level
                    </h4>
                    <p className="mt-2">
                        {course.level}
                    </p>

                    <h4 className="text-18 lh-1 fw-500" style={{marginTop: '15px'}}>
                        Status
                    </h4>
                    <p className="mt-2">
                        {course.status}
                    </p>

                    <h4 className="text-18 lh-1 fw-500" style={{marginTop: '15px'}}>
                        Bahasa
                    </h4>
                    <p className="mt-2">
                        {course.language}
                    </p>

                    <h4 className="text-18 lh-1 fw-500" style={{marginTop: '15px'}}>
                        Harga
                    </h4>
                    <p className="mt-2">
                        {
                            course.is_free ? (
                                <>Gratis</>
                            ) : (
                                course.is_discount ? (
                                    <>
                                        <strike>
                                            <small>{`${formatRupiah(course.price)}`}</small>
                                        </strike>

                                        {`${formatRupiah(course.price - course.discount)}`} (Diskon {`${calculatePercentage(course.price, course.discount)}%`})
                                    </>
                                ) : (
                                    <>
                                        {`${formatRupiah(course.price)}`}
                                    </>
                                )
                            )
                        }
                    </p>

                    <h4 className="text-18 lh-1 fw-500" style={{marginTop: '15px'}}>
                        Ditambahkan Pada
                    </h4>
                    <p className="mt-2">
                        {`${formatDate(course.created_at)}`}
                    </p>
                </div>
            </div>
        </div>
    )
}