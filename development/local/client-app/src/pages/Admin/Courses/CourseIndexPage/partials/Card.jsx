import React from "react";

import {Link} from "react-router-dom";
import {useState, useEffect} from "react";

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
                <Link to={`/admin/courses/${data.course.id}/overview`}>
                    <img className="rounded-8 w-1/1"
                         src={data.course.featured_picture_url ?? 'https://vocasia.s3.ap-southeast-1.amazonaws.com/no-media.png'}
                         alt="image"/>
                </Link>
            </div>

            <div className="pt-15">
                <div className="d-flex y-gap-10 justify-between items-center">
                    <span>{data.instructor.user.name}</span>

                    <div className="d-flex items-center">
                        <div className="text-14 lh-1 text-yellow-1 mr-10">
                            {data.course.status}
                        </div>
                    </div>
                </div>

                <h3 className="text-16 fw-500 lh-15 mt-10">
                    <Link to={`/admin/courses/${data.course.id}/overview`}>
                        {data.course.title}
                    </Link>
                </h3>

            </div>
        </div>
    );
}
