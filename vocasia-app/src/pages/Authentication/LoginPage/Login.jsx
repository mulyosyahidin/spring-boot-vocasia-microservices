import {AuthenticationHeader} from "../../../components/AuthenticationHeader/Index.jsx";
import {SideImage} from "./partials/SideImage.jsx";
import {LoginForm} from "./partials/LoginForm.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";

export const Login = () => {
    return (
        <>
            <PreLoader />
            <AuthenticationHeader/>

            <div className="content-wrapper js-content-wrapper overflow-hidden">
                <section className="form-page js-mouse-move-container">
                    <SideImage/>
                    <LoginForm/>
                </section>
            </div>
        </>
    );
}