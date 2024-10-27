import React from "react";

export const SideImage = () => {
    return (
        <div
            className="col-xl-5 col-lg-6 order-1 order-lg-2"
            data-aos="fade-up"
        >
            <div className="about-image">
                <img
                    style={{height: "100%", width: "100%"}}
                    src="/assets/img/about/1.png"
                    alt="image"
                />
            </div>
        </div>
    );
}