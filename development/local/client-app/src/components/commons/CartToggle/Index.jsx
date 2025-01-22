import { useRecoilValue } from "recoil";
import { useState } from "react";
import {courseCartAtom} from "../../../states/recoil/Atoms/CourseCart.jsx";
import {CourseCart} from "./partials/CourseCart.jsx";

export const CartToggle = ({ allClasses, parentClassess }) => {
    const [activeCart, setActiveCart] = useState(false);
    const cartCourses = useRecoilValue(courseCartAtom); // Mengambil nilai kursus dari Recoil state

    return (
        <>
            <div className={parentClassess ? parentClassess : ""}>
                <button
                    style={{ position: "relative" }}
                    onClick={() => setActiveCart((prev) => !prev)}
                    className={`${allClasses ? allClasses : ""}`}
                    data-el-toggle=".js-cart-toggle"
                >
                    <i className="text-20 icon icon-basket"></i>
                    <div className="cartProductCount">
                        {cartCourses.length > 9 ? "9+" : cartCourses.length}
                    </div>
                </button>

                <div
                    className={`toggle-element js-cart-toggle ${
                        activeCart ? "-is-el-visible" : ""
                    }`}
                >
                    <CourseCart />
                </div>
            </div>
        </>
    );
};
