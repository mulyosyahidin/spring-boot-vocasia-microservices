import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {AuthenticationHeader} from "../../../components/AuthenticationHeader/Index.jsx";
import {SideImage} from "../LoginPage/partials/SideImage.jsx";
import {RegisterForm} from "./partials/RegisterForm.jsx";

export const Register = () => {
    return (
        <>
            <PreLoader />
            <AuthenticationHeader/>

            <div className="content-wrapper js-content-wrapper overflow-hidden">
                <section className="form-page js-mouse-move-container">
                    <SideImage/>
                    <RegisterForm/>
                </section>
            </div>
        </>
    );
}