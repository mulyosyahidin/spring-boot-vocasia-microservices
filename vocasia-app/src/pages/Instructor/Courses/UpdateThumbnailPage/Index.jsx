import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {UpdateThumbnail} from "./UpdateThumbnail.jsx";

const metaData = {
    title: 'Update Thumbnail Kursus',
};

export const UpdateThumbnailPage = () => {
    const instructorRole = INSTRUCTOR;

    return (
        <Wrapper needAuth role={instructorRole}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <UpdateThumbnail/>
            </InstructorWrapper>
        </Wrapper>
    );
}