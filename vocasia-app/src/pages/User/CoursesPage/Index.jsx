import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {UserWrapper} from "../../../components/Users/UserWrapper/Index.jsx";
import {STUDENT} from "../../../config/consts.js";
import {Courses} from "./Courses.jsx";

const metaData = {
    title: "Kursus Saya"
}

export const CoursesPage = () => {
    return (
        <Wrapper needAuth role={STUDENT}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <UserWrapper>
                <Courses/>
            </UserWrapper>
        </Wrapper>
    );
}