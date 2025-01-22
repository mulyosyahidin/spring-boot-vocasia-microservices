import {Link} from "react-router-dom";
import React from "react";

export const EventCard = ({elm}) => {
    const {imgSrc, title, date, category, id} = elm;

    return (
        <div className="blogCard -type-2">
            <div className="blogCard__image">
                <img
                    style={{height: "120px", width: "140px"}}
                    src={imgSrc}
                    alt="image"
                />
            </div>
            <div className="blogCard__content">
                <div className="blogCard__category">{category}</div>
                <h4 className="blogCard__title">
                    {" "}
                    <Link className="linkCustom" to={`/events/${id}`}>
                        {title}
                    </Link>
                </h4>
                <div className="blogCard__date">{date}</div>
            </div>
        </div>
    );
}