import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {Link} from "react-router-dom";
import {Card} from "./partials/Card.jsx";
import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {Pagination} from "../../../../components/commons/Pagination.jsx";
import {findAll} from "../../../../services/new/course/instructor/course-service.js";

const metaData = {
    title: 'Kursus Saya',
};

const courseStatuses = [
    {value: 'all', label: 'Semua'},
    {value: 'published', label: 'Diterbitkan'},
    {value: 'draft', label: 'Draft'},
];

export const CourseIndexPage = () => {
    const [activeTab, setActiveTab] = useState('all');
    const [isLoading, setIsLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const getAllCourses = await findAll(currentPage, activeTab);

                if (getAllCourses.success) {
                    setData(getAllCourses.data.data);
                    setPagination(getAllCourses.data.pagination);

                    setIsLoading(false);
                }
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
    }, [activeTab, currentPage]);

    const handlePageChange = (newPage) => {
        setPagination((prev) => ({
            ...prev,
            current_page: newPage
        }));

        setCurrentPage(newPage);
    }

    const handleTabChange = (tabName) => {
        setCurrentPage(1);
        setActiveTab(tabName);
    }

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-12 d-flex justify-content-between">
                            <div>
                                <h1 className="text-30 lh-12 fw-700">Kursus Saya</h1>
                                <div className="mt-10">
                                    Tambah, edit atau lihat data-data kursus
                                </div>
                            </div>

                            <div>
                                <Link to={'/instructor/courses/create'}
                                      className={'button text-13 -sm -light-7 -dark-button-dark-2 text-purple-1'}>
                                    Buat Kursus Baru
                                </Link>
                            </div>
                        </div>
                    </div>

                    <div className="row y-gap-30">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div className="tabs -active-purple-2 js-tabs">
                                    <div
                                        className="tabs__controls d-flex items-center pt-20 px-30 border-bottom-light js-tabs-controls">
                                        {
                                            courseStatuses.map((status, index) => (
                                                <button
                                                    className={`text-light-1 lh-12 tabs__button js-tabs-button ${index > 0 ? 'ml-30' : ''} ${
                                                        activeTab === status.value ? "is-active" : ""
                                                    } `}
                                                    data-tab-target={`.-tab-item-${status.value}`}
                                                    type="button"
                                                    key={status.value}
                                                    onClick={() => handleTabChange(status.value)}
                                                >
                                                    {status.label}
                                                </button>
                                            ))
                                        }
                                    </div>

                                    <div className="tabs__content py-30 px-30 js-tabs-content">
                                        <div className="tabs__pane -tab-item-1 is-active">

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
                                                        <div className="row y-gap-30 pt-30">
                                                            {data.map((data, i) => (
                                                                <Card data={data} tabName={activeTab} key={data.id || i}/>
                                                            ))}

                                                            {
                                                                data.length === 0 && (
                                                                    <div className="col-12 text-center">
                                                                        <div className="text-16 text-light-1">
                                                                            Belum ada kursus yang tersedia
                                                                        </div>
                                                                    </div>
                                                                )
                                                            }
                                                        </div>
                                                    </>
                                                )
                                            }
                                        </div>

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
                        </div>
                    </div>
                </div>
            </InstructorWrapper>
        </Wrapper>
    );
}