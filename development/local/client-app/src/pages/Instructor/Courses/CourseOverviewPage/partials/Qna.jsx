import {Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAllByCourseId} from "../../../../../services/new/qna/instructor/qna-service.js";
import {formatDate} from "../../../../../utils/new-utils.js";
import {Pagination} from "../../../../../components/commons/Pagination.jsx";

export const Qna = ({activeTab, courseId}) => {
    const [isLoading, setIsLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
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
                const findCourseQuestions = await findAllByCourseId(courseId, currentPage);

                if (findCourseQuestions.success) {
                    setData(findCourseQuestions.data.data);
                    setPagination(findCourseQuestions.data.pagination);
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

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 4 ? "is-active" : ""} `}
        >
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
                        {
                            data.map((item, i) => (
                                <div key={i} className="md:direction-column pb-25">
                                    <Link to={`/instructor/courses/${courseId}/qna/${item.lesson.id}/${item.qna.id}`}>
                                        <div
                                            className={`d-flex ${
                                                i !== 0 ? "border-top-light" : ""
                                            }  pt-30`}
                                        >
                                            <div className="mr-20">
                                                <img className={'avatar -md'} style={{borderRadius: "50%"}}
                                                     src={'https://gravatar.com/avatar/000000000000000000000000000000000000000000000000000000'}
                                                     alt="image"/>
                                            </div>

                                            <div className="comments__body md:mt-15">
                                                <div className="comments__header">
                                                    <h4 className="text-17 fw-500 lh-15">
                                                        {item.user.name}
                                                        <span className="text-13 text-light-1 fw-400 ml-5">
                                                        {formatDate(item.qna.created_at)}
                                                    </span>
                                                    </h4>
                                                </div>

                                                <h5 className="text-15 fw-500 mt-5">{item.qna.title}</h5>
                                                <div className="comments__text mt-10">
                                                    <p>{item.lesson.title}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </Link>
                                </div>
                            ))
                        }
                    </>
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
    )
}