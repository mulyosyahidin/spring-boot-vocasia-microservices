import React from "react";
import { useRecoilState, useRecoilValue } from "recoil";
import { Link } from "react-router-dom";
import {courseCartAtom} from "../../../../states/recoil/Atoms/CourseCart.jsx";
import {cartTotalPriceSelector} from "../../../../states/recoil/Selectors/CourseCartSelector.jsx";

export const CourseCart = () => {
    const [cartCourses, setCartCourses] = useRecoilState(courseCartAtom);
    const totalPrice = useRecoilValue(cartTotalPriceSelector);

    const handleRemoveCart = (index) => {
        const item = cartCourses[index];
        setCartCourses((prev) => prev.filter((elm) => elm !== item));
    };

    return (
        <div className="header-cart bg-white -dark-bg-dark-1 rounded-8">
            <div
                className="px-30 pt-30 pb-10"
                style={{ maxHeight: "300px", overflowY: "scroll" }}
            >
                {cartCourses.map((elm, i) => (
                    <div key={i} className="row justify-between x-gap-40 pb-20">
                        <Link
                            style={{ textDecoration: "none" }}
                            to={`/courses/${elm.slug}/${elm.id}`}
                            className="col"
                        >
                            <div className="row x-gap-10 y-gap-10">
                                <div className="col-auto">
                                    <img
                                        style={{
                                            width: "80px",
                                            height: "80px",
                                            objectFit: "contain",
                                        }}
                                        src={elm.featured_picture_url}
                                        alt="image"
                                    />
                                </div>

                                <div className="col">
                                    <div className="text-dark-1 lh-15">{elm.title}</div>

                                    <div className="d-flex items-center mt-10">
                                        {!elm.is_free ? (
                                            <>
                                                {
                                                    elm.is_discount ? (
                                                        <>
                                                            <div
                                                                className="lh-12 fw-500 line-through text-light-1 mr-10">
                                                                {rupiahFormatter.format(elm.price)}
                                                            </div>
                                                            <div className="text-18 lh-12 fw-500 text-dark-1">
                                                                {rupiahFormatter.format(elm.price - elm.discount)}
                                                            </div>

                                                        </>
                                                    ) : (
                                                        <>
                                                            <div
                                                                className="lh-12 fw-500 text-light-1 mr-10">
                                                                {rupiahFormatter.format(elm.price)}
                                                            </div>
                                                        </>
                                                    )
                                                }
                                            </>
                                        ) : (
                                            <div className="text-18 lh-12 fw-500 text-dark-1">
                                                Gratis
                                            </div>
                                        )}
                                    </div>
                                </div>
                            </div>
                        </Link>

                        <div className="col-auto" onClick={() => handleRemoveCart(i)}>
                            <button>
                                <img src="/assets/img/menus/close.svg" alt="icon" />
                            </button>
                        </div>
                    </div>
                ))}
                {!cartCourses.length && (
                    <div className="p-20 pb-30 text-18 text-dark-1">
                        Keranjang belanja masih kosong
                    </div>
                )}
            </div>

            <div className="px-30 pt-20 pb-30 border-top-light">
                <div className="d-flex justify-between">
                    <div className="text-18 lh-12 text-dark-1 fw-500">Total:</div>
                    <div className="text-18 lh-12 text-dark-1 fw-500">{rupiahFormatter.format(totalPrice)}</div>
                </div>

                <div className="row x-gap-20 y-gap-10 pt-30">
                    {cartCourses.length ? (
                        <>
                            <Link
                                to={"/course-cart"}
                                style={{ textDecoration: "none" }}
                                className="col-sm-6"
                            >
                                <button className="button py-20 -dark-1 text-white -dark-button-white col-12">
                                    View Cart
                                </button>
                            </Link>
                            <Link
                                to={"/course-checkout"}
                                style={{ textDecoration: "none" }}
                                className="col-sm-6"
                            >
                                <button className="button py-20 -purple-1 text-white col-12">
                                    Checkout
                                </button>
                            </Link>
                        </>
                    ) : (
                        <Link
                            to={"/courses-list-1"}
                            style={{ textDecoration: "none" }}
                            className="col-12"
                        >
                            <button className="button py-20 -purple-1 text-white col-12">
                                Cari Kursus Lain
                            </button>
                        </Link>
                    )}
                </div>
            </div>
        </div>
    );
};
