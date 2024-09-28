import {atom} from "recoil";

const courseCartAtom = atom({
    key: 'courseCart',
    default: [],
});


export {courseCartAtom};