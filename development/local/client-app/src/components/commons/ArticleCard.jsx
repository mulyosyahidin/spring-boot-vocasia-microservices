import {Link} from "react-router-dom";
import React from "react";

export const ArticleCard = (elm) => {
    const {imageSrc, category, title, date, id} = elm.elm;

    return (
        <div className="blogCard -type-1">
            <div className="blogCard__image">
                <img
                    style={{width: "100%"}}
                    src={imageSrc}
                    alt="image"
                />
            </div>
            <div className="blogCard__content">
                <div className="blogCard__category">{category}</div>
                <h4 className="blogCard__title">
                    <Link className="linkCustom" to={`/blogs/${id}`}>
                        {title}
                    </Link>
                </h4>
                <div className="blogCard__date">{date}</div>
            </div>
        </div>
    );
}