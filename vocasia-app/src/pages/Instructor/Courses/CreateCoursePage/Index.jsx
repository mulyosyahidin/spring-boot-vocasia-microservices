import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {CreateCourse} from "./CreateCourse.jsx";

const metaData = {
    title: 'Buat Kursus Baru',
};

export const CreateCoursePage = () => {
    const instructorRole = INSTRUCTOR;

    return (
        <Wrapper needAuth role={instructorRole}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <CreateCourse/>
            </InstructorWrapper>
        </Wrapper>
    );
}