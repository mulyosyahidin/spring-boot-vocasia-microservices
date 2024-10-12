import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {StudentIndex} from "./StudentIndex.jsx";

const metaData = {
    title: 'Siswa Saya',
};

export const StudentIndexPage = () => {
    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <StudentIndex/>
            </InstructorWrapper>
        </Wrapper>
    );
}