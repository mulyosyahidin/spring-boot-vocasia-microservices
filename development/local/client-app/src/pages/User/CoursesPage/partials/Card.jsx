import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";

export const Card = ({data}) => {
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
                <Link to={`/users/courses/${data.enrollment.id}`}>
                    <img className="rounded-8 w-1/1"
                         src={data.course.featured_picture_url ?? 'https://vocasia.s3.ap-southeast-1.amazonaws.com/no-media.png'}
                         alt="image"/>
                </Link>
            </div>

            <div className="pt-15">
                <Link to={`/users/courses/${data.enrollment.id}`}>
                    <h3 className="text-16 fw-500 lh-15 mt-10">{data.course.title}</h3>
                </Link>

                <div className="progress-bar mt-10">
                    <div className="progress-bar__bg bg-light-3"></div>
                    <div className="progress-bar__bar bg-purple-1" style={{width: `${data.enrollment.progress_percentage}%`}}
                    ></div>
                </div>

                <div className="d-flex y-gap-10 justify-between items-center mt-10">
                    <div className="text-dark-1 text-small">{data.enrollment.progress_percentage}%</div>
                </div>
            </div>
        </div>
    );
}