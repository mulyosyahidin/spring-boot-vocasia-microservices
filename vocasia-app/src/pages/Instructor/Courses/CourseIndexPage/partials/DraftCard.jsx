import React from "react";

import {Link} from "react-router-dom";
import {useState, useEffect} from "react";
import {generateAWSObjectUrl} from "../../../../../utils/utils.js";

export const DraftCard = ({data}) => {
    const [activeShare, setActiveShare] = useState(false);
    const [rating, setRating] = useState([]);

    useEffect(() => {
        for (let i = Math.floor(data.rating); i >= 1; i--) {
            setRating((pre) => [...pre, "star"]);
        }
    }, []);

    return (
        <div className="w-1/5 p-4">
            <div className="relative shadow-lg rounded-lg overflow-hidden">
                <img className="rounded-8 w-1/1" src={data.course.featured_picture_url ?? 'https://vocasia.s3.ap-southeast-1.amazonaws.com/no-media.png'} alt="image"/>

                <button
                    onClick={() => setActiveShare((pre) => !pre)}
                    className="absolute-button"
                    data-el-toggle=".js-more-1-toggle"
                >
                    <span className="d-flex items-center justify-center size-35 bg-white shadow-1 rounded-8">
                        <i className="icon-menu-vertical"></i>
                    </span>
                </button>

                <div
                    className={`toggle-element -dshb-more js-more-1-toggle ${
                        activeShare ? "-is-el-visible" : ""
                    } `}
                >
                    <div className="px-25 py-25 bg-white -dark-bg-dark-2 shadow-1 border-light rounded-8">
                        <Link to={`/instructor/courses/${data.course.id}/edit`} className="d-flex items-center">
                            <div className="icon-edit"></div>
                            <div className="text-17 lh-1 fw-500 ml-12">Edit</div>
                        </Link>

                        <Link to={`/instructor/courses/${data.course.id}/overview`} className="d-flex items-center mt-20">
                            <div className="icon icon-book"></div>
                            <div className="text-17 lh-1 fw-500 ml-12">Lihat</div>
                        </Link>
                    </div>
                </div>
            </div>

            <div className="pt-15">
                <div className="d-flex y-gap-10 justify-between items-center">
                    <div className="text-14 lh-1">{data.instructor.user.name}</div>

                    <div className="d-flex items-center">
                        <div className="text-14 lh-1 text-yellow-1 mr-10">
                            {data.course.status}
                        </div>
                    </div>
                </div>

                <h3 className="text-16 fw-500 lh-15 mt-10">{data.course.title}</h3>

            </div>
        </div>
    );
}
