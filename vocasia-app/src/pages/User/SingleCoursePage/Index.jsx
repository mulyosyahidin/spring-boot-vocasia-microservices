import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {STUDENT} from "../../../config/consts.js";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {SingleCourse} from "./SingleCourse.jsx";

export const SingleCoursePage = () => {
    return (
        <Wrapper needAuth role={STUDENT}>
            <PreLoader/>

            <SingleCourse/>
        </Wrapper>
    );
}