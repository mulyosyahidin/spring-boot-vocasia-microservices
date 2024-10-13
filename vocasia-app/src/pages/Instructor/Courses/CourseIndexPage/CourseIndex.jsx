import {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {courseStatuses} from "./data.js";
import {Pagination} from "./partials/Pagination.jsx";
import {DraftCard} from "./partials/DraftCard.jsx";
import {Link} from "react-router-dom";
import {findCourseById, getAllCourses} from "../_actions/CourseAction.jsx";
import {generateAWSObjectUrl} from "../../../../utils/utils.js";

export const CourseIndex = () => {
    const {user} = useContext(AuthContext);

    const [currentStatus, setCurrentStatus] = useState("Semua");
    const [activeTab, setActiveTab] = useState('all');
    const [isLoading, setIsLoading] = useState(true);

    const [pageItems, setPageItems] = useState([]);
    const [filteredItems, setFilteredItems] = useState([]); // state untuk hasil filter

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getCourses = await getAllCourses(activeTab);

                if (getCourses) {
                    setPageItems(getCourses);
                    setFilteredItems(getCourses);
                }
            } catch (error) {
                console.error('Error fetching initial data:', error);
            } finally {
                setIsLoading(false);
            }
        };

        fetchInitialData();
    }, [activeTab]);

    const [searchQuery, setSearchQuery] = useState('');

    const handleSearch = (e) => {
        const query = e.target.value;
        setSearchQuery(query);

        if (query.length > 0) {
            const searchResults = pageItems.filter((item) => {
                return (
                    item.course.title.toLowerCase().includes(query.toLowerCase()) ||
                    item.course.status.toLowerCase().includes(query.toLowerCase())
                );
            });

            setFilteredItems(searchResults);
        } else {
            setFilteredItems(pageItems);
        }

    };

    return (
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
                        {
                            isLoading && (
                                <div className="text-center pt-50 pb-50">
                                    <div className="spinner-border text-light-1" role="status">
                                        <span className="visually-hidden">Loading...</span>
                                    </div>
                                </div>
                            )
                        }
                        {
                            !isLoading && (
                                <div className="tabs -active-purple-2 js-tabs">
                                    <div
                                        className="tabs__controls d-flex items-center pt-20 px-30 border-bottom-light js-tabs-controls">
                                        {
                                            courseStatuses.map((status, index) => (
                                                <button
                                                    className={`text-light-1 lh-12 tabs__button js-tabs-button ${index > 0 ? 'ml-30' : ''} ${
                                                        activeTab == status.value ? "is-active" : ""
                                                    } `}
                                                    data-tab-target={`.-tab-item-${status.value}`}
                                                    type="button"
                                                    key={status.value}
                                                    onClick={() => setActiveTab(status.value)}
                                                >
                                                    {status.label}
                                                </button>
                                            ))
                                        }
                                    </div>

                                    <div className="tabs__content py-30 px-30 js-tabs-content">
                                        <div className="tabs__pane -tab-item-1 is-active">
                                            <div className="row y-gap-10 justify-between">
                                                <div className="col-12">
                                                    <form
                                                        className="search-field border-light rounded-8 h-50"
                                                    >
                                                        <input
                                                            required
                                                            className="bg-white -dark-bg-dark-2 pr-50"
                                                            type="text"
                                                            value={searchQuery}
                                                            onChange={handleSearch}
                                                            placeholder="Cari..."
                                                        />
                                                        <button className="" type="submit">
                                                            <i className="icon-search text-light-1 text-20"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>

                                            <div className="row y-gap-30 pt-30">
                                                {filteredItems.map((data, i) => (
                                                    <DraftCard data={data} key={data.id || i}/>
                                                ))}

                                                {
                                                    filteredItems.length === 0 && (
                                                        <div className="col-12 text-center">
                                                            <div className="text-16 text-light-1">
                                                                Belum ada kursus yang tersedia
                                                            </div>
                                                        </div>
                                                    )
                                                }
                                            </div>

                                        </div>

                                    </div>
                                </div>
                            )
                        }
                    </div>
                </div>
            </div>
        </div>
    );
}
