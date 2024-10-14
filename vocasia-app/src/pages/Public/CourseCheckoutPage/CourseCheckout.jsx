import {Meta} from "../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {Header} from "../../../components/Header/Index.jsx";
import React, {useContext, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {useRecoilValue, useSetRecoilState} from "recoil";
import {courseCartAtom} from "../../../states/recoil/Atoms/CourseCart.jsx";
import {
    cartTotalDiscountSelector,
    cartTotalPriceSelector,
    cartTotalPriceWithoutDiscountSelector
} from "../../../states/recoil/Selectors/CourseCartSelector.jsx";
import {authStatusSelector} from "../../../states/recoil/Selectors/AuthStatusSelector.jsx";
import {AuthContext} from "../../../states/contexts/AuthContext.jsx";

const metaData = {
    title: "Checkout"
};
const dark = false;

export const CourseCheckout = () => {
    const {sweetAlert} = useContext(AuthContext);

    const cart = useRecoilValue(courseCartAtom);
    const setCart = useSetRecoilState(courseCartAtom);

    const totalPrice = useRecoilValue(cartTotalPriceSelector);
    const totalPriceWithoutDiscount = useRecoilValue(cartTotalPriceWithoutDiscountSelector);
    const totalDiscount = useRecoilValue(cartTotalDiscountSelector);

    const authStatus = useRecoilValue(authStatusSelector);

    const navigate = useNavigate();

    const [isLoading, setIsLoading] = useState(false);

    const handleSubmit = async () => {
        setIsLoading(true);

        try {
            const order = await placeNewOrder(cart);

            if (order) {
                sweetAlert.fire({
                    title: 'Berhasil',
                    text: 'Berhasil membuat order, silahkan lakukan pembayaran dalam 24 jam.',
                    icon: 'success',
                }).then(() => {
                    setCart([]);
                    navigate(`/users/orders/${order.order.id}`);
                });
            }
        } catch (error) {
            console.log(error);

            if (error.message) {
                let msg = error.message;

                if (error.data) {
                    if (error.data.error) {
                        msg += ' : ' + error.data.error;
                    }
                }

                sweetAlert.fire({
                    icon: 'error',
                    title: 'Terjadi Kesalahan!',
                    text: msg,
                }).then((isConfirmed) => {
                    if (isConfirmed) {
                        navigate(`/users/transactions`);
                    }
                })
            }
        } finally {
            setIsLoading(false);
        }
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
                                        Checkout
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
                                        <h1 className="page-header__title">Checkout</h1>
                                    </div>

                                    <div>
                                        <p className="page-header__text">
                                            Lengkapi data dan lakukan pembayaran.
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section className="layout-pt-md layout-pb-lg">
                    <div className="container">
                        <div className="row y-gap-50">
                            <div className="col-lg-12">
                                <div className="">
                                    <div className="pt-30 pb-15 bg-white border-light rounded-8 bg-light-4">
                                        <h5 className="px-30 text-20 fw-500">Keranjang</h5>

                                        <div className="d-flex justify-between px-30 mt-25">
                                            <div className="py-15 fw-500 text-dark-1">Kursus</div>
                                            <div className="py-15 fw-500 text-dark-1">Subtotal</div>
                                        </div>

                                        {cart.map((elm, i) => (
                                            <div
                                                key={i}
                                                className={`d-flex justify-between ${
                                                    i === 0 ? "border-top-dark" : ""
                                                }  px-30`}
                                            >
                                                <div className="py-15 text-grey">
                                                    <Link className="linkCustom" to={`/courses/${elm.slug}/${elm.id}`}>
                                                        {elm.title}
                                                    </Link>
                                                </div>
                                                <div className="py-15 text-grey">
                                                    {
                                                        elm.is_free ? 'Gratis' : (
                                                            elm.is_discount ? rupiahFormatter.format(elm.price - elm.discount) : rupiahFormatter.format(elm.price)
                                                        )
                                                    }
                                                </div>
                                            </div>
                                        ))}


                                        <div className="d-flex justify-between border-top-dark px-30">
                                            <div className="py-15 fw-500 text-dark-1">Total</div>
                                            <div className="py-15 fw-500 text-dark-1">
                                                {rupiahFormatter.format(totalPrice)}
                                            </div>
                                        </div>
                                    </div>

                                    <div className="mt-30">
                                        {
                                            authStatus.isLoggedIn && (
                                                <button className="button -md -black col-12 -uppercase text-white"
                                                        disabled={isLoading} onClick={() => handleSubmit()}>
                                                    {
                                                        isLoading ? 'Mohon Tunggu...' : 'Checkout'
                                                    }
                                                </button>
                                            )
                                        }

                                        {
                                            !authStatus.isLoggedIn && (
                                                <Link to={'/auth/login'}
                                                      className="button -md -black col-12 -uppercase text-white">
                                                    Login untuk Melanjutkan
                                                </Link>
                                            )
                                        }
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </section>

            </div>
        </>
    );
}