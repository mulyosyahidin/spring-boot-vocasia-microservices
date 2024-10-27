import gsap from "gsap";
import React, {useEffect} from "react";
import {ShapeRendering} from "../../../../components/commons/ShapeRendering.jsx";
import {Image} from "./partials/Image.jsx";
import {Content} from "./partials/Content.jsx";

export const Hero = () => {
    useEffect(() => {
        const parallaxIt = () => {
            const target = document.querySelectorAll(".js-mouse-move-container");

            target.forEach((container) => {
                const targets = container.querySelectorAll(".js-mouse-move");

                targets.forEach((el) => {
                    const movement = el.getAttribute("data-move");

                    document.addEventListener("mousemove", (e) => {
                        const relX = e.pageX - container.offsetLeft;
                        const relY = e.pageY - container.offsetTop;

                        gsap.to(el, {
                            x:
                                ((relX - container.offsetWidth / 2) / container.offsetWidth) *
                                Number(movement),
                            y:
                                ((relY - container.offsetHeight / 2) / container.offsetHeight) *
                                Number(movement),
                            duration: 0.2,
                        });
                    });
                });
            });
        };

        parallaxIt();
    }, []);

    return (
        <>
            <section className="masthead -type-1 js-mouse-move-container">
                <div className="masthead__bg">
                    <img src={"/assets/img/home-1/hero/bg.png"} alt="image"/>
                </div>

                <div className="container">
                    <div className="row y-gap-30 justify-between items-end">
                        <Content />
                        <Image/>
                    </div>
                </div>

                {/* animated shape start */}
                <ShapeRendering/>
                {/* animated shape end */}
            </section>
        </>
    );
};
