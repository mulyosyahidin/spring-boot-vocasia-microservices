import {Link} from "react-router-dom";
import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {CourseIndex} from "./CourseIndex.jsx";

const metaData = {
    title: 'Kursus Saya',
};

export const CourseIndexPage = () => {
    const instructorRole = INSTRUCTOR;

    return (
        <Wrapper needAuth role={instructorRole}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <CourseIndex/>
            </InstructorWrapper>
        </Wrapper>
    );
}