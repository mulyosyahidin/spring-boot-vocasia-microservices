import React from "react";
import {getPercentOf, makeDateReadable, rupiahFormatter} from "../../../../../utils/utils.js";

export const Information = ({activeTab, course}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab == 1 ? "is-active" : ""} `}
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
                                            <small>{`${rupiahFormatter.format(course.price)}`}</small>
                                        </strike>

                                        {`${rupiahFormatter.format(course.price - course.discount)}`} (Diskon {`${getPercentOf(course.discount, course.price)}%`})
                                    </>
                                ) : (
                                    <>
                                        {`${rupiahFormatter.format(course.price)}`}
                                    </>
                                )
                            )
                        }
                    </p>

                    <h4 className="text-18 lh-1 fw-500" style={{marginTop: '15px'}}>
                        Ditambahkan Pada
                    </h4>
                    <p className="mt-2">
                        {`${makeDateReadable(course.created_at)}`}
                    </p>
                </div>
            </div>
        </div>
    )
}