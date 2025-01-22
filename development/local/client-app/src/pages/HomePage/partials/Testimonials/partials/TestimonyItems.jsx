import {useEffect, useState} from "react";
import {Swiper, SwiperSlide} from "swiper/react";
import {Navigation, Pagination} from "swiper";
import {testimonials} from "../data.js";

export const TestimonyItems = () => {
    const [showSlider, setShowSlider] = useState(false);

    useEffect(() => {
        setShowSlider(true);
    }, []);

    return (
        <div className="js-section-slider pt-50">
            {showSlider && (
                <Swiper
                    className="overflow-visible"
                    // {...setting}
                    modules={[Navigation, Pagination]}
                    navigation={{
                        nextEl: ".icon-arrow-right",
                        prevEl: ".icon-arrow-left",
                    }}
                    loop={true}
                    spaceBetween={30}
                    slidesPerView={1}
                    breakpoints={{
                        // when window width is >= 576px
                        450: {
                            slidesPerView: 1,
                        },
                        // when window width is >= 768px
                        768: {
                            slidesPerView: 2,
                        },
                        1200: {
                            // when window width is >= 992px
                            slidesPerView: 3,
                        },
                    }}
                >
                    {testimonials.map((elm, i) => (
                        <SwiperSlide key={i} className="swiper-slide">
                            <div
                                className="testimonials -type-1"
                                data-aos="fade-left"
                                data-aos-duration={(i + 1) * 550}
                            >
                                <div className="testimonials__content">
                                    <h4 className="testimonials__title">{elm.comment}</h4>
                                    <p className="testimonials__text">
                                        {`“${elm.description}”`}
                                    </p>

                                    <div className="testimonials-footer">
                                        <div className="testimonials-footer__image">
                                            <img src={elm.imageSrc} alt="image"/>
                                        </div>

                                        <div className="testimonials-footer__content">
                                            <div className="testimonials-footer__title">
                                                {elm.name}
                                            </div>
                                            <div className="testimonials-footer__text">
                                                {elm.position}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </SwiperSlide>
                    ))}
                </Swiper>
            )}

            <div className="d-flex x-gap-20 items-center justify-end pt-60 lg:pt-40">
                <div className="col-auto">
                    <button
                        className="button -outline-white text-white size-50 rounded-full d-flex justify-center items-center js-prev">
                        <i className="icon icon-arrow-left text-24"></i>
                    </button>
                </div>
                <div className="col-auto">
                    <button
                        className="button -outline-white text-white size-50 rounded-full d-flex justify-center items-center js-next">
                        <i className="icon icon-arrow-right text-24"></i>
                    </button>
                </div>
            </div>
        </div>
    );
}