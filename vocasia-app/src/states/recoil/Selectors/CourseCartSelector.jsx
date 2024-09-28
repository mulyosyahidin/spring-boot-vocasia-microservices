import { selector } from "recoil";
import {courseCartAtom} from "../Atoms/CourseCart.jsx";

const cartTotalPriceSelector = selector({
    key: 'cartTotalPrice',
    get: ({ get }) => {
        const cartCourses = get(courseCartAtom);
        return cartCourses.reduce((total, course) => total + course.price, 0);
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

export { cartTotalPriceSelector, cartItemCountSelector, coursesByIdSelector };
