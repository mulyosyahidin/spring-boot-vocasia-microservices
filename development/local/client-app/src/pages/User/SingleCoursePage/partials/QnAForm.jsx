import React, {useEffect, useRef, useState} from "react";
import {isStudentAskThisLesson, saveQuestion} from "../../../../services/new/qna/student/qna-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {InputField} from "../../../../components/commons/Input/InputField.jsx";
import {TinyMCEField} from "../../../../components/commons/Input/TinyMCEField.jsx";
import {Link, useNavigate, useParams} from "react-router-dom";
import {formatDate} from "../../../../utils/new-utils.js";

export const QnAForm = ({courseId, lessonId}) => {
    const {enrollmentId} = useParams();

    const [isLoading, setIsLoading] = useState(false)
    const [isSubmitted, setIsSubmitted] = useState(false);

    const [isIAskThisLesson, setIsIAskThisLesson] = useState(false);
    const [myQnas, setMyQnas] = useState({});

    const [formData, setFormData] = useState({
        title: '',
        question: '',
    });
    const [errors, setErrors] = useState({
        title: '',
        question: '',
    });

    const questionRef = useRef(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findIsIAskThisLesson = await isStudentAskThisLesson(lessonId);

                if (findIsIAskThisLesson.success) {
                    setIsIAskThisLesson(findIsIAskThisLesson.data.is_i_answer_this_lesson)

                    if (findIsIAskThisLesson.data.is_i_answer_this_lesson) {
                        setMyQnas(findIsIAskThisLesson.data.questions)
                    }
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
    }, [courseId, lessonId]);

    const handleChange = (e) => {
        const {name, value} = e.target;

        setFormData((prevData) => ({...prevData, [name]: value}));
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);
        setErrors({});

        try {
            const questionContent = questionRef.current ? questionRef.current.getContent() : '';

            const data = {
                ...formData,
                question: questionContent,
            };

            const doAsk = await saveQuestion(courseId, lessonId, data);

            if (doAsk.success) {
                await withReactContent(Swal).fire({
                    icon: 'success',
                    title: 'Berhasil!',
                    text: doAsk.message,
                })
                    .then((isConfirmed) => {
                        if (isConfirmed) {
                            navigate(`/users/courses/${enrollmentId}/qna/${lessonId}/${doAsk.data.question.id}`);
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
                    <div className="respondForm pt-30">
                        <h3 className="text-20 fw-500 mt-30">Berikan Sebuah Pertanyaan</h3>

                        {
                            isIAskThisLesson && (
                                <>
                                    <h5 className="text-15 fw-400 mt-20">Pertanyaan Saya</h5>
                                    <ul className="comments__list mt-30">
                                       {
                                           myQnas.map((myQna, index) => {
                                               return (
                                                   <li key={index} className="comments__item">
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
                                                                {formatDate(myQna.created_at)}
                                                            </span>
                                                                   </h4>
                                                               </div>

                                                               <h5 className="text-15 fw-500 mt-5">
                                                                   <Link
                                                                       to={`/users/courses/${enrollmentId}/qna/${lessonId}/${myQna.id}`}>
                                                                       {myQna.title}
                                                                   </Link>
                                                               </h5>
                                                               <div className="comments__text mt-10">
                                                                   <div
                                                                       dangerouslySetInnerHTML={{__html: myQna.question}}
                                                                   />
                                                               </div>

                                                           </div>
                                                       </div>
                                                   </li>
                                               )
                                           })
                                       }
                                    </ul>
                                </>
                            )
                        }

                        <form
                            onSubmit={handleSubmit}
                            className="contact-form respondForm__form row y-gap-30 pt-30"
                        >
                            <div className="col-12">
                                <InputField
                                    label="Judul Pertanyaan"
                                    name="title"
                                    placeholder="Judul pertanyaan yang ingin Anda ajukan"
                                    value={formData.title}
                                    onChange={handleChange}
                                    error={errors.title}
                                    isRequired={true}
                                />
                            </div>
                            <div className="col-12">
                                <div className="col-12">
                                    <TinyMCEField
                                        onInit={(_evt, editor) => questionRef.current = editor}
                                        label="Pertanyaan Anda"
                                        error={errors.question}
                                        height="400"
                                    />
                                </div>
                            </div>
                            <div className="col-12">
                                <button
                                    className="button -md -purple-1 text-white"
                                    disabled={isSubmitted}
                                >
                                    {isSubmitted ? 'Mengirim Pertanyaan' : 'Kirim Pertanyaan'}
                                </button>
                            </div>
                        </form>
                    </div>
                )
            }
        </>
    )
}