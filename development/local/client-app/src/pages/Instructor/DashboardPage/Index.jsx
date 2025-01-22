import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Dashboard} from "./Dashboard.jsx";
import {Meta} from "../../../components/commons/Meta.jsx";
import {INSTRUCTOR} from "../../../config/consts.js";
import {InstructorWrapper} from "../../../components/Instructors/InstructorWrapper/Index.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";

const metaData = {
    title: 'Instructor Dashboard',
};

export const DashboardPage = () => {
    const instructorRole = INSTRUCTOR;

    return (
        <Wrapper needAuth role={instructorRole}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <Dashboard/>
            </InstructorWrapper>
        </Wrapper>
    );
}