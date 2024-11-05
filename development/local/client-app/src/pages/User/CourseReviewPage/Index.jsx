import {useParams} from "react-router-dom";
import {STUDENT} from "../../../config/consts.js";
import {Meta} from "../../../components/commons/Meta.jsx";
import {Header} from "../SingleCoursePage/partials/Header.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import React, {useEffect, useRef, useState} from "react";
import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {TinyMCEField} from "../../../components/commons/Input/TinyMCEField.jsx";
import {getMyReview, postCourseReview} from "../../../services/new/enrollment/user/review-service.js";
import {formatDate} from "../../../utils/new-utils.js";

const pageTitle = 'Course Review';

export const CourseReviewPage = () => {
    const {enrollmentId} = useParams();

    const [isLoading, setIsLoading] = useState(true);
    const [isSubmitted, setIsSubmitted] = useState(false);

    const [myReview, setMyReview] = useState({});
    const [hasReview, setHasReview] = useState(false);

    const [rating, setRating] = useState(2);
    const [errors, setErrors] = useState({
        review: '',
    });

    const reviewRef = useRef(null);

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                setIsLoading(false);

                const getMyCourseReview = await getMyReview(enrollmentId);
                if (getMyCourseReview.success) {
                    const review = getMyCourseReview.data.review;

                    if (review != null) {
                        setMyReview(review);
                        setHasReview(true);
                    }
                }
            } catch (error) {
                console.error(error);
                let msg = error.message || 'Error';
                if (error.data && error.data.error) {
                    msg += ' : ' + error.data.error;
                }

                await withReactContent(Swal).fire({
                    title: 'Terjadi Kesalahan!',
                    text: msg,
                    icon: 'error',
                }).then((isConfirmed) => {
                    if (isConfirmed) {
                        window.location.reload();
                    }
                });
            }
        }

        fetchInitialData();
    }, [enrollmentId]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const data = {
                rating: rating,
                review: reviewRef.current ? reviewRef.current.getContent() : '',
            }

            const doPostReview = await postCourseReview(enrollmentId, data);

            if (doPostReview.success) {
                await withReactContent(Swal).fire({
                    icon: 'success',
                    title: 'Berhasil!',
                    text: doPostReview.message,
                }).then((isConfirmed) => {
                    if (isConfirmed) {
                        window.location.reload();
                    }
                });
            }
        } catch (error) {
            console.error(error);

            if (error.errors) {
                const getErrors = error.errors;
                const newErrors = {};

                Object.keys(getErrors).forEach((field) => {
                    newErrors[field] = getErrors[field][0];
                });

                setErrors(newErrors);
                setIsSubmitted(false);
            } else if (error.data) {
                let message = error.message;

                if (error.data.error) {
                    message += `: (${error.data.error})`;
                }

                await withReactContent(Swal).fire({
                    icon: 'error',
                    title: 'Terjadi Kesalahan!',
                    text: message,
                })
                    .then((isConfirmed) => {
                        if (isConfirmed) {
                            window.location.reload();
                        }
                    });
            } else {
                if (error.message) {
                    setErrors({general: error.message});
                }
            }

            setIsSubmitted(false);
        } finally {
            setIsLoading(false);
        }

    }

    return (
        <Wrapper needAuth role={STUDENT}>
            <Meta meta={{title: pageTitle}}/>
            <Header title={pageTitle}/>
            <PreLoader/>

            <div className="content-wrapper js-content-wrapper overflow-hidden">
                <section className="layout-pt-lg layout-pb-lg lg:pt-40 lg:order-1">
                    <div className="container">
                        <div className="row marginCustom">
                            <div className="col-8 mx-auto">
                                <div className="respondForm pt-30">
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
                                        (!isLoading && hasReview) && (
                                            <>
                                                <h3 className="text-20 fw-500 mt-30">Penilaian Anda</h3>
                                                <small className="text-14">{formatDate(myReview.created_at)}</small>

                                                <div className="y-gap-10 pt-10">
                                                    <div className="col-12">
                                                        {[1, 2, 3, 4, 5].map((star) => (
                                                            <span
                                                                key={star}
                                                                className={`icon-star text-14 mx-1 ${myReview.rating >= star ? 'text-yellow-1' : 'text-gray-1'}`}
                                                            ></span>
                                                        ))}
                                                    </div>

                                                    <div className="col-12">
                                                        <div
                                                            dangerouslySetInnerHTML={{__html: myReview.review}}
                                                        />
                                                    </div>
                                                </div>
                                            </>
                                        )
                                    }

                                    {
                                        (!isLoading && !hasReview) && (
                                            <>
                                                <h3 className="text-20 fw-500 mt-30">Berikan Penilaian Anda</h3>

                                                <form
                                                    onSubmit={handleSubmit}
                                                    className="contact-form respondForm__form row y-gap-30 pt-30"
                                                >
                                                    <div className="col-12">
                                                        {[1, 2, 3, 4, 5].map((star) => (
                                                            <span
                                                                key={star}
                                                                className={`icon-star text-14 mx-1 ${rating >= star ? 'text-yellow-1' : 'text-gray-1'}`}
                                                                onClick={() => setRating(star)}
                                                                style={{cursor: 'pointer'}}
                                                            ></span>
                                                        ))}
                                                    </div>

                                                    <div className="col-12">
                                                        <div className="col-12">
                                                            <TinyMCEField
                                                                onInit={(_evt, editor) => reviewRef.current = editor}
                                                                error={errors.review}
                                                                height="400"
                                                            />
                                                        </div>
                                                    </div>
                                                    <div className="col-12">
                                                        <button
                                                            className="button -md -purple-1 text-white"
                                                            disabled={isSubmitted}
                                                        >
                                                            {isSubmitted ? 'Mengirim...' : 'Kirim'}
                                                        </button>
                                                    </div>
                                                </form>
                                            </>
                                        )
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </Wrapper>
    )
}
