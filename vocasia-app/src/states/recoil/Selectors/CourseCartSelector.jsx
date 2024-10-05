import { selector } from "recoil";
import {courseCartAtom} from "../Atoms/CourseCart.jsx";

const cartTotalPriceSelector = selector({
    key: 'cartTotalPrice',
    get: ({ get }) => {
        const cartCourses = get(courseCartAtom);

        return cartCourses.reduce((total, course) => {
            if (course.is_free) {
                return total;
            }

            if (course.is_discount) {
                return total + (course.price - course.discount);
            }

            return total + course.price;
        }, 0);
    },
});

const cartTotalPriceWithoutDiscountSelector = selector({
    key: 'cartTotalPriceWithoutDiscount',
    get: ({ get }) => {
        const cartCourses = get(courseCartAtom);

        return cartCourses.reduce((total, course) => {
            if (course.is_free) {
                return total;
            }

            return total + course.price;
        }, 0);
    },
});

const cartTotalDiscountSelector = selector({
    key: 'cartTotalDiscount',
    get: ({ get }) => {
        const cartCourses = get(courseCartAtom);

        return cartCourses.reduce((totalDiscount, course) => {
            if (course.is_free || !course.is_discount) {
                return totalDiscount; // Abaikan kursus gratis atau tanpa diskon
            }

            return totalDiscount + course.discount; // Tambahkan diskon kursus
        }, 0);
    },
});

const cartItemCountSelector = selector({
    key: 'cartItemCount',
    get: ({ get }) => {
        const cartCourses = get(courseCartAtom);
        return cartCourses.length;
    },
});

const coursesByIdSelector = selector({
    key: 'coursesById',
    get: ({ get }) => {
        const cartCourses = get(courseCartAtom);
        return (id) => cartCourses.find(course => course.id === id);
    },
});

export { cartTotalPriceSelector, cartItemCountSelector, coursesByIdSelector, cartTotalPriceWithoutDiscountSelector, cartTotalDiscountSelector};
