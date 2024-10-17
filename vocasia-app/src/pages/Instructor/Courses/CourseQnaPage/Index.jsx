import React, {useEffect, useRef, useState} from "react";
import {findAllByCourseId, findAllCourseQna, saveAnswer} from "../../../../services/new/qna/instructor/qna-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {Link, useParams} from "react-router-dom";
import {INSTRUCTOR} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {formatDate} from "../../../../utils/new-utils.js";
import {Pagination} from "../../../../components/commons/Pagination.jsx";
import {TinyMCEField} from "../../../../components/commons/Input/TinyMCEField.jsx";

export const CourseQnaPage = () => {
    const {courseId, lessonId, qnaId} = useParams();

    const [pageTitle, setPageTitle] = useState('Tanya Jawab');
    const [isLoading, setIsLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [question, setQuestion] = useState({});
    const [lesson, setLesson] = useState({});
    const [user, setUser] = useState({});
    const [pagination, setPagination] = useState({});

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));

        setCurrentPage(newPage);
    }

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const findCourseQuestions = await findAllCourseQna(courseId, qnaId, currentPage);

                if (findCourseQuestions.success) {
                    const {question, lesson, answers, user} = findCourseQuestions.data;

                    setData(answers.data);
                    setQuestion(question);
                    setLesson(lesson);
                    setUser(user);
                    setPagination(answers.pagination);

                    setPageTitle(question.title);
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
    }, [courseId, currentPage]);

    const [isSubmitted, setIsSubmitted] = useState(false);
    const answerRef = useRef(null);
    const [formData, setFormData] = useState({
        answer: '',
    });
    const [errors, setErrors] = useState({});

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);

        try {
            const questionContent = answerRef.current ? answerRef.current.getContent() : '';

            const data = {
                ...formData,
                answer: questionContent,
            };

            const doPostAnswer = await saveAnswer(courseId, qnaId, data);

            if (doPostAnswer.success) {
                await withReactContent(Swal).fire({
                    icon: 'success',
                    title: 'Berhasil!',
                    text: doPostAnswer.message,
                })
                    .then((isConfirmed) => {
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
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={{
                title: pageTitle,
            }}/>
            <PreLoader/>

            <InstructorWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Tanya Jawab</h1>
                            <div className="mt-10">Bantu siswa memecahkan permasalahan mereka</div>
                        </div>
                    </div>

                    <div className="row y-gap-60">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Tanya Jawab</h2>

                                    <Link to={`/instructor/courses/${courseId}/overview`}
                                          className={'-md -white text-dark-1'}>
                                        <i className="fa fa-arrow-left mr-10 ml-10"/>
                                        Kembali
                                    </Link>
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
                                            <>
                                                <div className="d-flex pb-30">
                                                    <div className="mr-20">
                                                        <img className={'avatar -md'}
                                                             style={{borderRadius: "50%"}}
                                                             src={'https://gravatar.com/avatar/'} alt="image"/>
                                                    </div>

                                                    <div className="comments__body md:mt-15">
                                                        <div className="comments__header">
                                                            <h4 className="text-17 fw-500 lh-15">
                                                                {question.title}
                                                            </h4>
                                                        </div>

                                                        <h5 className="text-15 fw-500 mt-5">
                                                            {user.name}
                                                            <span className="text-13 text-light-1 fw-400 ml-5">
                                                                    {formatDate(question.created_at)}
                                                                </span></h5>
                                                        <div className="comments__text mt-10">
                                                            <div
                                                                dangerouslySetInnerHTML={{__html: question.question}}
                                                            />
                                                        </div>
                                                    </div>
                                                </div>
                                            </>
                                        )
                                    }

                                    {
                                        !isLoading && data.length > 0 && (
                                            <>
                                                <h5 className="text-17 fw-500 lh-15 mb-10">Tanggapan</h5>

                                                {
                                                    data.map((item, i) => (
                                                        <div
                                                            className={`d-flex ${
                                                                i !== 0 ? "border-top-light" : ""
                                                            } pt-10 pb-30`}
                                                        >
                                                            <div className="mr-20">
                                                                <img className={'avatar -md'}
                                                                     style={{borderRadius: "50%"}}
                                                                     src={'https://gravatar.com/avatar/000000000000000000000000000000000000000000000000000000'}
                                                                     alt="image"/>
                                                            </div>

                                                            <div className="comments__body md:mt-15">
                                                                <div className="comments__header">
                                                                    <h4 className="text-17 fw-500 lh-15">
                                                                        {item.answer.is_instructor ? <u>{item.user.name}</u> : item.user.name}
                                                                        <span
                                                                            className="text-13 text-light-1 fw-400 ml-5">
                                                                            {formatDate(item.answer.created_at)}
                                                                        </span>
                                                                    </h4>
                                                                </div>

                                                                <div className="comments__text mt-10">
                                                                    <div
                                                                        dangerouslySetInnerHTML={{__html: item.answer.answer}}
                                                                    />
                                                                </div>
                                                            </div>
                                                        </div>
                                                    ))
                                                }
                                            </>
                                        )
                                    }

                                    {
                                        !isLoading && data.length === 0 && (
                                            <div className="text-center">
                                                <h4 className="text-17 fw-500 lh-15">Belum ada tanggapan untuk pertanyaan
                                                    ini.</h4>
                                            </div>
                                        )
                                    }

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

                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Berikan Tanggapan</h2>
                                </div>

                                <div className="py-30 px-30">
                                    <form onSubmit={handleSubmit} className="contact-form row y-gap-30">
                                        <div className="col-12">
                                            <TinyMCEField
                                                onInit={(_evt, editor) => answerRef.current = editor}
                                                label="Tanggapan"
                                                height="400"
                                                error={errors.answer}
                                            />
                                        </div>

                                        <div className="row y-gap-20 justify-end pt-15">
                                            <div className="col-auto">
                                                <button className="button -md -purple-1 text-white"
                                                        disabled={isSubmitted}>
                                                    {
                                                        isSubmitted ? (
                                                            'Mengirim...'
                                                        ) : 'Kirim'
                                                    }
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </InstructorWrapper>
        </Wrapper>
    )
}