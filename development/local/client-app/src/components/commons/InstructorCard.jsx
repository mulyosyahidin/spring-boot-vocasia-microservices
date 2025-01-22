import {Link} from "react-router-dom";
import React from "react";

export const InstructorCard = (instructor) => {
    const {image, name, role, rating, students, courses} = instructor.instructor;

    return (
        <div className="teamCard -type-1 -teamCard-hover">
            <div className="teamCard__image">
                <img
                    style={{height: "100%", width: "100%"}}
                    src={image}
                    alt="image"
                />
                <div className="teamCard__socials">
                    <div className="d-flex x-gap-20 y-gap-10 justify-center items-center h-100">
                        {instructor.socialProfile?.map((itm, i) => (
                            <Link key={i} to={itm.url ? itm.url : "#"}>
                                <i className={`${itm.icon} text-white`}></i>
                            </Link>
                        ))}
                    </div>
                </div>
            </div>
            <div className="teamCard__content">
                <h4 className="teamCard__title">
                    <Link className="linkCustom" to={`/instructors/${instructor.id}`}>
                        {instructor.name}
                    </Link>
                </h4>
                <p className="teamCard__text">{instructor.role}</p>

                <div className="row items-center y-gap-10 x-gap-10 pt-10">
                    <div className="col-auto">
                        <div className="d-flex items-center">
                            <div className="icon-star text-yellow-1 text-11 mr-5"></div>
                            <div className="text-14 lh-12 text-yellow-1 fw-500">
                                {instructor.rating}
                            </div>
                        </div>
                    </div>

                    <div className="col-auto">
                        <div className="d-flex items-center">
                            <div className="icon-online-learning text-light-1 text-11 mr-5"></div>
                            <div className="text-14 lh-12">
                                {instructor.students} Students
                            </div>
                        </div>
                    </div>

                    <div className="col-auto">
                        <div className="d-flex items-center">
                            <div className="icon-play text-light-1 text-11 mr-5"></div>
                            <div className="text-14 lh-12">
                                {instructor.courses} Course
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}