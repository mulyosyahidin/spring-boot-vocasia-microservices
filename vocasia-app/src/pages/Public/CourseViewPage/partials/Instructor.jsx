import React, {useEffect} from "react";

export default function Instructor({instructor}) {
    useEffect(() => {
        console.log(instructor);
    }, []);

    return (
        <div id="instructors" className="pt-60 lg:pt-40">
            <h2 className="text-20 fw-500">Instruktur</h2>

            <div className="mt-30">
                <div className="d-flex x-gap-20 y-gap-20 items-center flex-wrap">
                    <div className="size-120">
                        <img
                            className="object-cover"
                            src={`${instructor.user.profile_picture_url}`}
                            alt="image"
                        />
                    </div>

                    <div className="">
                        <h5 className="text-17 lh-14 fw-500">{instructor.user.name}</h5>
                        <p className="mt-5">Instruktur</p>

                        <div className="d-flex x-gap-20 y-gap-10 flex-wrap items-center pt-10">
                            <div className="d-flex items-center">
                                <div className="d-flex items-center mr-8">
                                    <div className="icon-star text-11 text-yellow-1"></div>
                                    <div className="text-14 lh-12 text-yellow-1 ml-5">4.5</div>
                                </div>
                                <div className="text-13 lh-1">Instructor Rating</div>
                            </div>

                            <div className="d-flex items-center text-light-1">
                                <div className="icon-comment text-13 mr-8"></div>
                                <div className="text-13 lh-1">23,987 Reviews</div>
                            </div>

                            <div className="d-flex items-center text-light-1">
                                <div className="icon-person-3 text-13 mr-8"></div>
                                <div className="text-13 lh-1">692 Students</div>
                            </div>

                            <div className="d-flex items-center text-light-1">
                                <div className="icon-wall-clock text-13 mr-8"></div>
                                <div className="text-13 lh-1">15 Course</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="mt-30">
                    <div
                        dangerouslySetInnerHTML={{__html: instructor.summary}}
                    />
                </div>
            </div>
        </div>
    );
}
