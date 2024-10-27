import {PreLoader} from "../../components/commons/PreLoader.jsx";
import {Header} from "../../components/Header/Index.jsx";
import {Footer} from "../../components/Footer/Index.jsx";
import {Breadcrumb} from "../../components/commons/Breadcrumb.jsx";
import {breadcrumbLinks} from "./data.js";
import {Content} from "./partials/Content.jsx";
import {Join} from "./partials/Join.jsx";

export const BecomeInstructor = () => {
    return (
        <>
            <PreLoader/>
            <Header/>

            <div className="content-wrapper  js-content-wrapper overflow-hidden">
                <Breadcrumb links={breadcrumbLinks}/>

                <section className=" layout-pb-lg">
                    <div className="container">
                        <Content />
                    </div>
                </section>

                <Join />

                <Footer/>
            </div>
        </>
    );
}