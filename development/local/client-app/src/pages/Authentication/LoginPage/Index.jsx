import {Meta} from "../../../components/commons/Meta.jsx";
import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {AuthenticationHeader} from "../../../components/AuthenticationHeader/Index.jsx";
import {SideImage} from "./partials/SideImage.jsx";
import {LoginForm} from "./partials/LoginForm.jsx";

const metaData = {
    title: 'Login'
};

export const LoginPage = () => {
    return (
        <Wrapper>
            <Meta meta={metaData} />

            <PreLoader />
            <AuthenticationHeader/>

            <div className="content-wrapper js-content-wrapper overflow-hidden">
                <section className="form-page js-mouse-move-container">
                    <SideImage/>
                    <LoginForm/>
                </section>
            </div>
        </Wrapper>
    );
}