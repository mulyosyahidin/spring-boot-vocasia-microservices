import React from "react";

import {Link} from "react-router-dom";
import {useState, useEffect} from "react";
import {getPercentage, rupiahFormatter} from "../../utils/utils.js";

export const CourseCard = ({data, index}) => {
    const [rating, setRating] = useState([]);
    useEffect(() => {
        for (let i = Math.round(data.rating); i >= 1; i--) {
            setRating((pre) => [...pre, "star"]);
        }
    }, []);

    return (
        <div className="col-lg-3 col-md-6">
            <div>
                <div className="coursesCard -type-1">
                    <div className="relative">
                        <div className="coursesCard__image overflow-hidden rounded-8">
                            <img
                                style={{height: "100%", width: "100%"}}
                                className="w-1/1"
                                src={data.course.featured_picture_url}
                                alt="image"
                            />
                            <div className="coursesCard__image_overlay rounded-8"></div>
                        </div>
                        <div className="d-flex justify-between py-10 px-10 absolute-full-center z-3"></div>
                    </div>

                    <div className="h-100 pt-15">
                        {/*<div className="d-flex items-center">*/}
                        {/*    <div className="text-14 lh-1 text-yellow-1 mr-10">*/}
                        {/*        {data.course.rating}*/}
                        {/*    </div>*/}
                        {/*    <div className="d-flex x-gap-5 items-center">*/}
                        {/*        {rating.map((itm, i) => (*/}
                        {/*            <div key={i} className="icon-star text-9 text-yellow-1"></div>*/}
                        {/*        ))}*/}
                        {/*    </div>*/}
                        {/*    <div className="text-13 lh-1 ml-10">({data.course.rating_count})</div>*/}
                        {/*</div>*/}

                        <div className="text-17 lh-15 fw-500 text-dark-1 mt-10" style={{minHeight: "60px"}}>
                            <Link className="linkCustom" to={`/courses/${data.course.slug}/${data.course.id}`}>
                                {data.course.title}
                            </Link>
                        </div>

                        <div className="d-flex x-gap-10 items-center pt-10">
                            <div className="d-flex items-center">
                                <div className="mr-8">
                                    <img src="assets/img/coursesCards/icons/1.svg" alt="icon"/>
                                </div>
                                <div className="text-14 lh-1">{data.course.lesson_count} pelajaran</div>
                            </div>

                            <div className="d-flex items-center">
                                <div className="mr-8">
                                    <img src="assets/img/coursesCards/icons/2.svg" alt="icon"/>
                                </div>
                                <div className="text-14 lh-1">{data.course.total_duration}</div>
                            </div>

                            <div className="d-flex items-center">
                                <div className="mr-8">
                                    <img src="assets/img/coursesCards/icons/3.svg" alt="icon"/>
                                </div>
                                <div className="text-14 lh-1">{data.course.level}</div>
                            </div>
                        </div>

                        <div className="coursesCard-footer">
                            <div className="coursesCard-footer__author">
                                <img src="https://gravatar.com/avatar/000000000000000000000000000000000000000000000000000000" alt="image"/>
                                <div>{data.instructor.user.name}</div>
                            </div>

                            <div className="coursesCard-footer__price">
                                {data.course.is_free ? (
                                    <>
                                        <div></div>
                                        <div>Free</div>
                                    </>
                                ) : (
                                    data.course.is_discount ? (
                                        <>
                                            <div>{`${rupiahFormatter.format(data.course.price)}`}</div>
                                            <div>{`${rupiahFormatter.format(data.course.price - data.course.discount)}`}</div>
                                        </>
                                    ) : (
                                        <>
                                            <div></div>
                                            <div>{`${rupiahFormatter.format(data.course.price)}`}</div>
                                        </>
                                    )
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
