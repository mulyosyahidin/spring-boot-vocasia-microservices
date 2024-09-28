import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {CourseIndex} from "../../Courses/CourseIndexPage/CourseIndex.jsx";

const metaData = {
    title: 'Siswa Saya',
};

export const StudentIndexPage = () => {
    const instructorRole = INSTRUCTOR;

    return (
        <Wrapper needAuth role={instructorRole}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <StudentIndex/>
            </InstructorWrapper>
        </Wrapper>
    );
}