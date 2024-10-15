import {Meta} from "../../../components/commons/Meta.jsx";
import React from "react";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {Header} from "../../../components/Header/Index.jsx";
import {Link} from "react-router-dom";
import {useRecoilValue, useSetRecoilState} from "recoil";
import {courseCartAtom} from "../../../states/recoil/Atoms/CourseCart.jsx";
import {
    cartTotalDiscountSelector,
    cartTotalPriceSelector,
    cartTotalPriceWithoutDiscountSelector
} from "../../../states/recoil/Selectors/CourseCartSelector.jsx";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faX} from "@fortawesome/free-solid-svg-icons";
import {formatRupiah} from "../../../utils/new-utils.js";

const metaData = {
    title: "Keranjang Belanja",
};

const dark = false;

export const CourseCart = () => {
    const cart = useRecoilValue(courseCartAtom);
    const setCart = useSetRecoilState(courseCartAtom);

    const totalPrice = useRecoilValue(cartTotalPriceSelector);
    const totalPriceWithoutDiscount = useRecoilValue(cartTotalPriceWithoutDiscountSelector);
    const totalDiscount = useRecoilValue(cartTotalDiscountSelector);

    const handleRemoveFromCart = (index) => {
        const item = cart[index];

        setCart((prev) => prev.filter((elm) => elm !== item));
    };

    const handleSubmit = (e) => {

    }

    return (
        <>
            <Meta meta={metaData}/>
            <PreLoader/>
            <Header/>

            <div className="content-wrapper  js-content-wrapper ">

                <section className={`breadcrumbs ${dark ? "bg-dark-1" : ""} `}>
                    <div className="container">
                        <div className="row">
                            <div className="col-auto">
                                <div className="breadcrumbs__content">
                                    <div
                                        className={`breadcrumbs__item ${dark ? "text-dark-3" : ""} `}
                                    >
                                        <Link to="/">Beranda</Link>
                                    </div>

                                    <div
                                        className={`breadcrumbs__item ${dark ? "text-dark-3" : ""} `}
                                    >
                                        Keranjang Belanja
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section className="page-header -type-1">
                    <div className="container">
                        <div className="page-header__content">
                            <div className="row justify-center text-center">
                                <div className="col-auto">
                                    <div>
                                        <h1 className="page-header__title">Keranjang Belanja</h1>
                                    </div>

                                    <div>
                                        <p className="page-header__text">
                                            Periksa keranjang kamu, dan selesaikan pembayaran.
                                            Kursus akan tersedia di dasbor segera
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section className="layout-pt-md layout-pb-lg">
                    <div className="container">
                        {
                            cart.length > 0 && (
                                <div className="row justify-end">
                                    <div className="col-12">
                                        <div className="px-30 pr-60 py-25 rounded-8 bg-light-6 md:d-none">
                                            <div className="row justify-between">
                                                <div className="col-md-4">
                                                    <div className="fw-500 text-purple-1">Kursus</div>
                                                </div>
                                                <div className="col-md-2">
                                                    <div className="fw-500 text-purple-1">Harga</div>
                                                </div>
                                                <div className="col-md-1">
                                                    <div className="d-flex justify-end">
                                                        <div className="fw-500 text-purple-1">Hapus</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div className="px-30 pr-60 md:px-0">
                                            {cart.map((elm, i) => (
                                                <div
                                                    key={i}
                                                    className="row y-gap-20 justify-between items-center pt-30 pb-30 border-bottom-light"
                                                >
                                                    <div className="col-md-4">
                                                        <div className="d-flex items-center">
                                                            <div className="">
                                                                <div
                                                                    className="size-100 bg-image rounded-8 js-lazy"
                                                                    style={{backgroundImage: `url('${elm.featured_picture_url}')`}}
                                                                ></div>
                                                            </div>
                                                            <div className="fw-500 text-dark-1 ml-30">
                                                                <Link
                                                                    className="linkCustom"
                                                                    to={`/courses/${elm.slug}/${elm.id}`}
                                                                >
                                                                    {elm.title}{" "}
                                                                </Link>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div className="col-md-2 md:mt-15">
                                                        <div className="">
                                                            <div
                                                                className="shopCart-products__title d-none md:d-block mb-10">
                                                                Harga
                                                            </div>
                                                            <p>
                                                                {
                                                                    elm.is_free ? 'Gratis' : (
                                                                        elm.is_discount ? (
                                                                            formatRupiah(elm.price - elm.discount)
                                                                        ) : formatRupiah(elm.price)
                                                                    )
                                                                }
                                                            </p>
                                                        </div>
                                                    </div>

                                                    <div className="col-md-1">
                                                        <div
                                                            className="md:d-none d-flex justify-end">
                                                            <a href="#" onClick={() => handleRemoveFromCart(i)}>
                                                                <FontAwesomeIcon icon={faX}/>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            ))}
                                        </div>

                                    </div>

                                    <div className="col-xl-4 col-lg-5 layout-pt-lg">
                                        <div className="py-30 bg-light-4 rounded-8 border-light">
                                            <h5 className="px-30 text-20 fw-500">Total Belanja</h5>

                                            <div className="d-flex justify-between px-30 item mt-25">
                                                <div className="pt-15 fw-500 text-dark-1">Subtotal</div>
                                                <div className="pt-15 fw-500 text-dark-1">
                                                    {formatRupiah(totalPriceWithoutDiscount)}
                                                </div>
                                            </div>

                                            <div className="d-flex justify-between px-30 item">
                                                <div className="pt-15 fw-500 text-dark-1">Diskon</div>
                                                <div className="pt-15 fw-500 text-dark-1">
                                                    {formatRupiah(totalDiscount)}
                                                </div>
                                            </div>

                                            <div className="d-flex justify-between px-30 item border-top-dark mt-25">
                                                <div className="pt-15 fw-500 text-dark-1">Total</div>
                                                <div className="pt-15 fw-500 text-dark-1">
                                                    {formatRupiah(totalPrice)}
                                                </div>
                                            </div>
                                        </div>

                                        <Link
                                            to="/course-checkout"
                                            className="button -md -purple-1 text-white col-12 mt-30"
                                        >
                                            Lanjutkan Checkout
                                        </Link>
                                    </div>
                                </div>
                            )
                        }

                        {
                            cart.length == 0 && (
                                <div className="row justify-center">
                                    <div className="col-auto">
                                        <div className="text-18 lh-12 fw-500 text-dark-1">
                                            Keranjang belanja masih kosong
                                        </div>
                                    </div>
                                </div>
                            )
                        }
                    </div>
                </section>

            </div>
        </>
    );
}