import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../config/consts.js";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {ShowStudent} from "./ShowStudent.jsx";

export const ShowStudentPage = () => {
    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <PreLoader/>

            <InstructorWrapper>
                <ShowStudent/>
            </InstructorWrapper>
        </Wrapper>
    );
}