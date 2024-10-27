import React, {useEffect, useRef, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {
    findAllByCourseIdAndLessonId,
} from "../../../../services/new/qna/student/qna-service.js";
import {QnAForm} from "./QnAForm.jsx";
import {Pagination} from "../../../../components/commons/Pagination.jsx";
import {formatDate} from "../../../../utils/new-utils.js";
import {Link, useParams} from "react-router-dom";

export const QnA = ({course, currentLesson}) => {
    const {enrollmentId} = useParams();

    const [isLoading, setIsLoading] = useState(true)
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({})

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getAllQnas = await findAllByCourseIdAndLessonId(course.id, currentLesson.id, currentPage);

                if (getAllQnas.success) {
                    setData(getAllQnas.data.questions);
                    setPagination(getAllQnas.data.pagination);
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
    }, [currentLesson.id, currentPage]);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));

        setCurrentPage(newPage);
    }

    return (
        <div className="mt-60 lg:mt-40">
            <div className="blogPost -comments">
                <div className="blogPost__content">
                    <h2 className="text-20 fw-500">Tanya Jawab dalam Pelajaran Ini</h2>

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
                        (!isLoading && data.length === 0) && (
                            <div className={'mt-20'}>
                                <p>Belum ada pertanyaan yang diajukan.</p>
                            </div>
                        )
                    }

                    {
                        !isLoading && (
                            <>
                                <ul className="comments__list mt-30">
                                    {data.map((item, i) => (
                                        <li key={i} className="comments__item">
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
                                                            {item.user.name}
                                                            <span className="text-13 text-light-1 fw-400">
                                                                {formatDate(item.qna.created_at)}
                                                            </span>
                                                        </h4>

                                                        <div className="stars"></div>
                                                    </div>

                                                    <h5 className="text-15 fw-500 mt-15">
                                                        <Link to={`/users/courses/${enrollmentId}/qna/${currentLesson.id}/${item.qna.id}`}>
                                                            {item.qna.title}
                                                        </Link>
                                                    </h5>
                                                    <div className="comments__text mt-10">
                                                        <p>
                                                            <Link to={`/users/courses/${enrollmentId}/qna/${currentLesson.id}/${item.qna.id}`}>
                                                                {item.qna.short_question}
                                                            </Link>
                                                        </p>
                                                    </div>

                                                </div>
                                            </div>
                                        </li>
                                    ))}
                                </ul>

                                {
                                    (data.length > 10) && (
                                        <div className="row justify-center pt-30 pb-30">
                                            <div className="col-auto">
                                                <Pagination pagination={pagination} onPageChange={handlePageChange}/>
                                            </div>
                                        </div>
                                    )
                                }
                            </>
                        )
                    }

                    {
                        !isLoading && <QnAForm courseId={course.id} lessonId={currentLesson.id}/>
                    }
                </div>
            </div>
        </div>
    )

}