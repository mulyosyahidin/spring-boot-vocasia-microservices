import {useEffect, useState} from "react";
import {getAllEnrollments} from "../../../services/users/enrollments.js";
import {Link} from "react-router-dom";
import {courseStatuses} from "../../Instructor/Courses/CourseIndexPage/data.js";
import {DraftCard} from "../../Instructor/Courses/CourseIndexPage/partials/DraftCard.jsx";
import {Card} from "./partials/Card.jsx";

export const Courses = () => {
    const [isLoading, setIsLoading] = useState(true);

    const [searchQuery, setSearchQuery] = useState('');
    const [pageItems, setPageItems] = useState([]);
    const [filteredItems, setFilteredItems] = useState([]);

    const handleSearch = (e) => {
        setSearchQuery(e.target.value);

        if (e.target.value.length > 0) {
            const filteredData = pageItems.filter((item) => {
                return item.course.title.toLowerCase().includes(e.target.value.toLowerCase());
            });

            setFilteredItems(filteredData);
        } else {
            setFilteredItems(pageItems);
        }
    }

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getEnrollments = await getAllEnrollments();

                if (getEnrollments) {
                    setPageItems(getEnrollments);
                    setFilteredItems(getEnrollments);
                }
            } catch (error) {
                console.error('Error fetching initial data:', error);
            } finally {
                setIsLoading(false);
            }
        };

        fetchInitialData();
    }, []);

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-12 d-flex justify-content-between">
                    <div>
                        <h1 className="text-30 lh-12 fw-700">Kursus Saya</h1>
                        <div className="mt-10">
                            Jangan lupa selesaikan kursus kamu ya!
                        </div>
                    </div>
                </div>
            </div>

            <div className="row y-gap-30">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div className="tabs -active-purple-2 js-tabs">
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
                                            <Card data={data} key={data.id || i}/>
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
                    </div>
                </div>
            </div>
        </div>
    )
}