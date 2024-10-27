import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../components/commons/Meta.jsx";
import {Register} from "./Register.jsx";

const metaData = {
    title: 'Daftar'
};

export const RegisterPage = () => {
    return (
        <Wrapper>
            <Meta meta={metaData} />

            <Register />
        </Wrapper>
    );
}