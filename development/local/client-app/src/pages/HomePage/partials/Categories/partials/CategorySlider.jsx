import {Swiper, SwiperSlide} from "swiper/react";
import {Navigation, Pagination} from "swiper";
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findAll} from "../../../../../services/new/course/public/category-service.js";

export const CategorySlider = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllCategories = await findAll();

                if (findAllCategories.success) {
                    setData(findAllCategories.data.data);

                    setIsLoading(false);
                }
            } catch (error) {
                console.error(error);

                if (error.message) {
                    let msg = error.message;
                    if (error.data.error) {
                        msg += ' : ' + error.data.error;
                    }

                    await withReactContent(Swal).fire({
                        title: 'Terjadi Kesalahan!',
                        text: msg,
                        icon: 'error',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        })
                }
            }
        }

        fetchInitialData();
    }, []);

    return (
        <div className="overflow-hidden pt-50 js-section-slider">
            {!isLoading && (
                <Swiper
                    modules={[Navigation, Pagination]}
                    pagination={{
                        el: ".swiper-paginationx",
                        clickable: true,
                    }}
                    navigation={{
                        nextEl: ".arrow-right-one",
                        prevEl: ".arrow-left-one",
                    }}
                    spaceBetween={30}
                    slidesPerView={1}
                    breakpoints={{
                        // when window width is >= 576px
                        450: {
                            slidesPerView: 2,
                        },
                        // when window width is >= 768px
                        768: {
                            slidesPerView: 4,
                        },
                        1200: {
                            // when window width is >= 992px
                            slidesPerView: 6,
                        },
                    }}
                    loop={true}
                >
                    {data.map((item, i) => (
                        <SwiperSlide key={i}>
                            <Link
                                to={`/courses/by-category/${item.id}`}
                                data-aos="fade-left"
                                data-aos-duration={(i + 1) * 350}
                                className="featureCard -type-1 -featureCard-hover linkCustomTwo"
                            >
                                <div className="featureCard__content">
                                    <div className="featureCard__icon">
                                        <img src={item.icon_url} alt="icon"/>
                                    </div>
                                    <div className="featureCard__title">
                                        {item.name}
                                    </div>
                                </div>
                            </Link>
                        </SwiperSlide>
                    ))}
                </Swiper>
            )}
        </div>
    );
}