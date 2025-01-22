import {atom} from "recoil";

const loadCartFromLocalStorage = () => {
    const savedCart = localStorage.getItem('courseCart');
    return savedCart ? JSON.parse(savedCart) : [];
};

const courseCartAtom = atom({
    key: 'courseCart',
    default: loadCartFromLocalStorage(),
});

export {courseCartAtom};