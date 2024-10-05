import {PreLoader} from "../../components/commons/PreLoader.jsx";
import {Header} from "../../components/Header/Index.jsx";
import {Hero} from "./partials/Hero/Index.jsx";
import {Categories} from "./partials/Categories/Index.jsx";
import {Courses} from "./partials/Courses/Index.jsx";
import Testimonials from "./partials/Testimonials/Index.jsx";
import {Featured} from "./partials/Featured/Index.jsx";
import {Instructors} from "./partials/Instructors/Index.jsx";
import WhyVocasia from "./partials/WhyVocasia/Index.jsx";
import GetApp from "./partials/GetApp.jsx";
import {Blog} from "./partials/Blog/Index.jsx";
import {Join} from "./partials/Join.jsx";
import {Footer} from "../../components/Footer/Index.jsx";

export const Home = () => {
    return (
        <>
            <PreLoader/>
            <Header />

            <div className="content-wrapper js-content-wrapper overflow-hidden">
                <Hero/>
                <Categories />
                <Courses/>
                <Testimonials/>
                <Featured />
                <WhyVocasia/>
                {/*<Instructors/>*/}
                <GetApp/>
                {/*<Blog/>*/}
                <Join/>
                <Footer />
            </div>
        </>
    );
}