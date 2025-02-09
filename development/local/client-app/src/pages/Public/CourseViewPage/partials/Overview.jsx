import React, { useState } from "react";

export default function Overview({course}) {
    const [showMore, setShowMore] = useState(false);

    return (
        <div id="overview" className="pt-60 lg:pt-40 to-over">
            <h4 className="text-18 fw-500">Deskripsi</h4>

            <div
                className={`show-more  mt-30 js-show-more ${
                    showMore ? "is-active" : ""
                } `}
            >
                <div
                    className="show-more__content "
                    style={showMore ? {maxHeight: "1000px"} : {}}
                >
                    <div
                        dangerouslySetInnerHTML={{__html: course.description}}
                    />
                </div>

                <button
                    onClick={() => setShowMore((pre) => !pre)}
                    className="show-more__button text-purple-1 fw-500 underline mt-30"
                >
                    Show more
                </button>
            </div>

            {/*<div className="mt-60">*/}
            {/*    <h4 className="text-20 mb-30">What you'll learn</h4>*/}
            {/*    <div className="row x-gap-100 justfiy-between">*/}
            {/*        <div className="col-md-6">*/}
            {/*            <div className="y-gap-20">*/}
            {/*                {learnList.slice(0, 6).map((elm, i) => (*/}
            {/*                    <div key={i} className="d-flex items-center">*/}
            {/*                        <div className="d-flex justify-center items-center border-light rounded-full size-20 mr-10">*/}
            {/*                            <FontAwesomeIcon*/}
            {/*                                icon={faCheck}*/}
            {/*                                style={{ transform: "scale(0.7)", opacity: "0.7" }}*/}
            {/*                            />*/}
            {/*                        </div>*/}
            {/*                        <p>{elm}</p>*/}
            {/*                    </div>*/}
            {/*                ))}*/}
            {/*            </div>*/}
            {/*        </div>*/}

            {/*        <div className="col-md-6">*/}
            {/*            <div className="y-gap-20">*/}
            {/*                {learnList.slice(6).map((elm, i) => (*/}
            {/*                    <div key={i} className="d-flex items-center">*/}
            {/*                        <div className="d-flex justify-center items-center border-light rounded-full size-20 mr-10">*/}
            {/*                            <FontAwesomeIcon*/}
            {/*                                icon={faCheck}*/}
            {/*                                style={{ transform: "scale(0.7)", opacity: "0.7" }}*/}
            {/*                            />*/}
            {/*                        </div>*/}
            {/*                        <p>{elm}</p>*/}
            {/*                    </div>*/}
            {/*                ))}*/}
            {/*            </div>*/}
            {/*        </div>*/}
            {/*    </div>*/}
            {/*</div>*/}

            {/*<div className="mt-60">*/}
            {/*    <h4 className="text-20">Requirements</h4>*/}
            {/*    <ul className="ul-list y-gap-15 pt-30">*/}
            {/*        {requirements.map((elm, i) => (*/}
            {/*            <li key={i}>{elm}</li>*/}
            {/*        ))}*/}
            {/*    </ul>*/}
            {/*</div>*/}
        </div>
    );
}
