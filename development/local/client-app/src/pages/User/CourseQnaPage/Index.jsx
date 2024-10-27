import {Link, useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import {STUDENT} from "../../../config/consts.js";
import {Meta} from "../../../components/commons/Meta.jsx";
import {Header} from "../SingleCoursePage/partials/Header.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {QnA} from "../SingleCoursePage/partials/QnA.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAllQnaAnswers, saveAnswer} from "../../../services/new/qna/student/qna-service.js";
import {formatDate} from "../../../utils/new-utils.js";
import {TinyMCEField} from "../../../components/commons/Input/TinyMCEField.jsx";

export const CourseQnaPage = () => {
    const {enrollmentId, lessonId, questionId} = useParams();

    const [isLoading, setIsLoading] = useState(true)
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [qna, setQna] = useState([]);
    const [pagination, setPagination] = useState({});

    const [pageTitle, setPageTitle] = useState('QnA');

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getAllQnaAnswers = await findAllQnaAnswers(lessonId, questionId, currentPage);

                if (getAllQnaAnswers.success) {
                    setData(getAllQnaAnswers.data.answers);
                    setQna(getAllQnaAnswers.data.qna);
                    setPagination(getAllQnaAnswers.data.pagination);
                    setPageTitle(getAllQnaAnswers.data.qna.title);
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
    }, [enrollmentId, lessonId, questionId]);

    const [formData, setFormData] = useState({
        answer: '',
    });
    const [errors, setErrors] = useState({
        answer: '',
    });

    const [isSubmitted, setIsSubmitted] = useState(false);
    const answerRef = useRef(null);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);
        setErrors({});

        try {
            const questionContent = answerRef.current ? answerRef.current.getContent() : '';

            const data = {
                ...formData,
                answer: questionContent,
            };

            const doAnswer= await saveAnswer(lessonId,questionId, data);

            if (doAnswer.success) {
                await withReactContent(Swal).fire({
                    icon: 'success',
                    title: 'Berhasil!',
                    text: doAnswer.message,
                })
                    .then((isConfirmed) => {
                        if (isConfirmed) {
                            window.location.reload();
                        }
                    });
            }
        }
        catch (error) {
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
        <>
            <Wrapper needAuth role={STUDENT}>
                <Meta meta={{
                    title: pageTitle,
                }}/>

                <Header title={pageTitle}/>
                <PreLoader/>

                <div className="content-wrapper js-content-wrapper overflow-hidden">
                    <section className="layout-pt-lg layout-pb-lg lg:pt-40 lg:order-1">
                        <div className="container">
                            <div className="row justify-start marginCustom">
                                <div className="col-xxl-8 col-xl-7 col-lg-8">
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
                                                <h2 className="text-20 fw-500">Pertanyaan Saya</h2>
                                                <ul className="comments__list mt-10">
                                                    <li key={1} className="comments__item">
                                                        <div className="comments__item-inner md:direction-column">
                                                            <div className="comments__img mr-20">
                                                                <div
                                                                    className="bg-image rounded-full js-lazy"
                                                                    style={{
                                                                        backgroundImage: `url(https://gravatar.com/avatar/000000000000000000000000000000000000000000000000000000)`
                                                                    }}
                                                                ></div>
                                                            </div>

                                                            <div className="comments__body md:mt-15">
                                                                <div className="comments__header">
                                                                    <h4 className="text-17 fw-500 lh-15">
                                                            <span className="text-13 text-light-1 fw-400">
                                                                {formatDate(qna.created_at)}
                                                            </span>
                                                                    </h4>
                                                                </div>

                                                                <h5 className="text-15 fw-500 mt-5">
                                                                    {qna.title}
                                                                </h5>
                                                                <div className="comments__text mt-10">
                                                                    <div
                                                                        dangerouslySetInnerHTML={{__html: qna.question}}
                                                                    />
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </>
                                        )
                                    }

                                    {
                                        !isLoading && (
                                            <>
                                                <div className="mt-60 lg:mt-40">
                                                    <div className="blogPost -comments">
                                                        <div className="blogPost__content">
                                                            <h2 className="text-20 fw-500">Jawaban</h2>

                                                            {
                                                                data.length > 0 && (
                                                                    <ul className="comments__list mt-30">
                                                                        {data.map((item, i) => (
                                                                            <li key={i} className="comments__item">
                                                                                <div
                                                                                    className="comments__item-inner md:direction-column">
                                                                                    <div className="comments__img mr-20">
                                                                                        <div
                                                                                            className="bg-image rounded-full js-lazy"
                                                                                            style={{
                                                                                                backgroundImage: `url(https://gravatar.com/avatar/000000000000000000000000000000000000000000000000000000)`
                                                                                            }}
                                                                                        ></div>
                                                                                    </div>

                                                                                    <div
                                                                                        className="comments__body md:mt-15">
                                                                                        <div className="comments__header">
                                                                                            <h4 className="text-17 fw-500 lh-15">
                                                                                                {item.user.name}
                                                                                                <span
                                                                                                    className="text-13 text-light-1 fw-400">
                                                                {formatDate(item.qna.created_at)}
                                                            </span>
                                                                                            </h4>

                                                                                            <div className="stars"></div>
                                                                                        </div>

                                                                                        <h5 className="text-15 fw-500 mt-15">
                                                                                            <Link
                                                                                                to={`/users/courses/${enrollmentId}/qna/${lessonId}/${item.qna.id}`}>
                                                                                                {item.qna.title}
                                                                                            </Link>
                                                                                        </h5>
                                                                                        <div
                                                                                            className="comments__text">
                                                                                            <div
                                                                                                dangerouslySetInnerHTML={{__html: item.qna.answer}}
                                                                                            />
                                                                                        </div>

                                                                                    </div>
                                                                                </div>
                                                                            </li>
                                                                        ))}
                                                                    </ul>
                                                                )
                                                            }

                                                            {
                                                                data.length === 0 && (
                                                                    <div className="text-left mt-10">
                                                                        <span className="text-14 fw-400">Belum ada jawaban. Tunggulah instruktur atau siswa lain menanggapi pertanyaan Anda.</span>
                                                                    </div>
                                                                )
                                                            }
                                                        </div>
                                                    </div>

                                                    <div className="respondForm pt-30 mt-30">
                                                        <h3 className="text-20 fw-500">Tambahkan Tanggapan</h3>

                                                        <form
                                                            onSubmit={handleSubmit}
                                                            className="contact-form respondForm__form row y-gap-30 pt-30"
                                                        >
                                                            <div className="col-12">
                                                                <div className="col-12">
                                                                    <TinyMCEField
                                                                        onInit={(_evt, editor) => answerRef.current = editor}
                                                                        label="Tanggapan Anda"
                                                                        error={errors.answer}
                                                                        height="300"
                                                                    />
                                                                </div>
                                                            </div>
                                                            <div className="col-12">
                                                                <button
                                                                    className="button -md -purple-1 text-white"
                                                                    disabled={isSubmitted}
                                                                >
                                                                    {isSubmitted ? 'Mengirim Tanggapan' : 'Kirim Tanggapan'}
                                                                </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </>
                                        )
                                    }
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </Wrapper>
        </>
    );
}