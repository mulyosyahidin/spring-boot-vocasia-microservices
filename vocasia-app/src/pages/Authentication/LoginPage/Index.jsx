import {Meta} from "../../../components/commons/Meta.jsx";
import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Login} from "./Login.jsx";

const metaData = {
    title: 'Login'
};

export const LoginPage = () => {
    return (
        <Wrapper>
            <Meta meta={metaData} />

            <Login />
        </Wrapper>
    );
}