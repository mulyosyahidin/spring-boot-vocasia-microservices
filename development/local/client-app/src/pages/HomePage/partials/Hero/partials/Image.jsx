import React from "react";
import {content} from "../data.jsx";

const {title, text_underline, info_hero, starts} = content;

export const Image = () => {
    return (
        <div
            className="col-xl-6 col-lg-6"
            data-aos="fade-up"
            data-aos-delay="700"
        >
            <div className="masthead-image">
                <div className="masthead-image__el1">
                    <img
                        className="js-mouse-move"
                        data-move="40"
                        style={{objectFit: "cover"}}
                        src={"/assets/img/masthead/1.png"}
                        alt="image"
                    />
                    <div
                        data-move="30"
                        className="lg:d-none img-el -w-250 px-20 py-20 d-flex items-center bg-white rounded-8 js-mouse-move"
                    >
                        <div
                            className="size-50 d-flex justify-center items-center bg-red-2 rounded-full">
                            <img src={"/assets/img/masthead/1.svg"} alt="icon"/>
                        </div>
                        <div className="ml-20">
                            <div className="text-orange-1 text-16 fw-500 lh-1">
                                100+
                            </div>
                            <div className="mt-3">Kursus Gratis</div>
                        </div>
                    </div>
                </div>

                <div className="masthead-image__el2">
                    <img
                        className="js-mouse-move"
                        data-move="70"
                        src={"/assets/img/masthead/2.png"}
                        style={{objectFit: "cover"}}
                        alt="image"
                    />
                    <div
                        data-move="60"
                        className="lg:d-none img-el -w-260 px-20 py-20 d-flex items-center bg-white rounded-8 js-mouse-move"
                    >
                        <img src={"/assets/img/masthead/4.png"} alt="icon"/>
                        <div className="ml-20">
                            <div className="text-dark-1 text-16 fw-500 lh-1">
                                Martin MS
                            </div>
                            <div className="mt-3">Mahasiswa</div>
                            <div className="d-flex x-gap-5 mt-3">
                                {starts.map((start, index) => (
                                    <div key={index}>
                                        <div className={start}></div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>

                <div className="masthead-image__el3">
                    <img
                        className="js-mouse-move"
                        data-move="40"
                        src={"/assets/img/masthead/3.png"}
                        style={{objectFit: "cover"}}
                        alt="image"
                    />
                    <div
                        data-move="30"
                        className="shadow-4 img-el -w-260 px-30 py-20 d-flex items-center bg-white rounded-8 js-mouse-move"
                    >
                        <div className="img-el__side">
                            <div
                                className="size-50 d-flex justify-center items-center bg-purple-1 rounded-full">
                                <img
                                    style={{objectFit: "cover"}}
                                    src={"/assets/img/masthead/2.svg"}
                                    alt="icon"
                                />
                            </div>
                        </div>
                        <div className="">
                            <div className="text-purple-1 text-16 fw-500 lh-1">
                                Selamat!
                            </div>
                            <div className="mt-3">Menyelesaikan sebuah kursus</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}