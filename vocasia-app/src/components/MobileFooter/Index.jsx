import React from "react";
import { Link } from "react-router-dom";

export const MobileFooter = () => {
    return (
        <>
            <div className="mobile-footer px-20 py-20 border-top-light js-mobile-footer">
                <div className="mobile-footer__number">
                    <div className="text-17 fw-500 text-dark-1">Kontak Kami</div>
                    <div className="text-17 fw-500 text-purple-1">+6282211110086</div>
                </div>

                <div className="lh-2 mt-10">
                    <div>
                        Jl. Lembah Nyiur Raya No.1 Blok J12, RT.5/RW.9, Pondok Kelapa, Durensawit, East Jakarta City, Jakarta 13450
                    </div>
                    <div>kontak@vocasia.id</div>
                </div>

                <div className="mobile-socials mt-10">
                    <Link
                        to="#"
                        className="d-flex items-center justify-center rounded-full size-40"
                    >
                        <i className="fa fa-facebook"></i>
                    </Link>

                    <Link
                        to="#"
                        className="d-flex items-center justify-center rounded-full size-40"
                    >
                        <i className="fa fa-twitter"></i>
                    </Link>

                    <Link
                        to="#"
                        className="d-flex items-center justify-center rounded-full size-40"
                    >
                        <i className="fa fa-instagram"></i>
                    </Link>

                    <Link
                        to="#"
                        className="d-flex items-center justify-center rounded-full size-40"
                    >
                        <i className="fa fa-linkedin"></i>
                    </Link>
                </div>
            </div>
        </>
    );
}
