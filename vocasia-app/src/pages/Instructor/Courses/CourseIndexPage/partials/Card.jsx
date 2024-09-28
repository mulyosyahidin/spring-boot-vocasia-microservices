import React from "react";

import {Link} from "react-router-dom";
import {useState, useEffect} from "react";
import {generateAWSObjectUrl} from "../../../../../utils/utils.js";

export const Card = ({data}) => {
    const [activeShare, setActiveShare] = useState(false);
    const [rating, setRating] = useState([]);

    useEffect(() => {
        for (let i = Math.floor(data.rating); i >= 1; i--) {
            setRating((pre) => [...pre, "star"]);
        }
    }, []);

    return (
        <div className="w-full sm:w-1/2 md:w-1/3 lg:w-1/3 xl:w-1/3">
            <div className="relative">
                <img className="rounded-8 w-1/1" src={generateAWSObjectUrl(data.picture)} alt="image"/>

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
                        <a href="#" className="d-flex items-center">
                            <div className="icon-edit"></div>
                            <div className="text-17 lh-1 fw-500 ml-12">Edit</div>
                        </a>

                        <a href="#" className="d-flex items-center mt-20">
                            <div className="icon-link"></div>
                            <div className="text-17 lh-1 fw-500 ml-12">Lihat</div>
                        </a>
                    </div>
                </div>
            </div>

            <div className="pt-15">
                <div className="d-flex y-gap-10 justify-between items-center">
                    <div className="text-14 lh-1">{data.instructor.user.name}</div>

                    <div className="d-flex items-center">
                        <div className="text-14 lh-1 text-yellow-1 mr-10">
                            {data.status}
                        </div>
                    </div>
                </div>

                <h3 className="text-16 fw-500 lh-15 mt-10">{data.title}</h3>

                <div className="progress-bar mt-10">
                    <div className="progress-bar__bg bg-light-3"></div>
                    <div className="progress-bar__bar bg-purple-1 w-1/5"></div>
                </div>

                <div className="d-flex y-gap-10 justify-between items-center mt-10">
                    {/*<div className="text-dark-1">% {data.completed} Completed</div>*/}
                    <div>25%</div>
                </div>
            </div>
        </div>
    );
}
