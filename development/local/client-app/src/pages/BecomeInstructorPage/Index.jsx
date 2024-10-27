import {Wrapper} from "../../components/commons/Wrapper.jsx";
import {Meta} from "../../components/commons/Meta.jsx";
import {BecomeInstructor} from "./BecomeInstructorPage.jsx";

const metaData = {
    title: 'Menjadi Instruktur di Vocasia'
};

export const BecomeInstructorPage = () => {
    return (
        <Wrapper>
            <Meta meta={metaData} />

            <BecomeInstructor />
        </Wrapper>
    );
}