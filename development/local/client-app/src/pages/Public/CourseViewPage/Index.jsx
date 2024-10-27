import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Link, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {Meta} from "../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import Star from "./partials/Star.jsx";
import PinContent from "./partials/PinContent.jsx";
import Overview from "./partials/Overview.jsx";
import CourseContent from "./partials/CourseContent.jsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findByIdAndSlug, findContentsByIdAndSlug} from "../../../services/new/course/public/course-service.js";
import {Header} from "../../../components/Header/Index.jsx";
import {formatDate} from "../../../utils/new-utils.js";
import Instructor from "./partials/Instructor.jsx";
import {findIsUserHasEnrollThisCourse} from "../../../services/new/enrollment/user/course-service.js";

const dark = true;
const menuItems = [
    {id: 1, href: "#overview", text: "Ringkasan", isActive: true},
    {id: 2, href: "#course-content", text: "Konten", isActive: false},
    {id: 3, href: "#instructors", text: "Instruktur", isActive: false},
];

export const CourseViewPage = () => {
    const {slug, id} = useParams();

    const [isLoading, setIsLoading] = useState(true);

    const [course, setCourse] = useState({});
    const [category, setCategory] = useState({});
    const [instructor, setInstructor] = useState({});
    const [chapters, setChapters] = useState({});
    const [isUserEnrolled, setIsUserEnrolled] = useState(false);

    const [metaData, setMetaData] = useState({
        title: "Kursus Online Bersertifikasi Terbaik No.1 Di Indonesia",
    });

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const [findCourseBySlugAndId, findContents, isUserEnrolled] = await Promise.all([
                    findByIdAndSlug(slug, id),
                    findContentsByIdAndSlug(slug, id),
                    findIsUserHasEnrollThisCourse(id),
                ]);

                if (findCourseBySlugAndId.success) {
                    setCourse(findCourseBySlugAndId.data.course);
                    setCategory(findCourseBySlugAndId.data.category);
                    setInstructor(findCourseBySlugAndId.data.instructor);
                }

                if (findContents.success) {
                    setChapters(findContents.data.chapters);
                }

                if (isUserEnrolled.success) {
                    console.log(isUserEnrolled);
                    setIsUserEnrolled(isUserEnrolled.data.is_user_enrolled);
                }

                setIsLoading(false);
            } catch (error) {
                console.error(error);

                if (error.message) {
                    let msg = error.message;
                    if (error.data.error) {
                        msg += ' : ' + error.data.error;
                    }

                    await withReactContent(Swal).fire({
                        title: 'Terjadi Kesalahan!',
                        text: msg,
                        icon: 'error',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        })
                }
            }
        }

        fetchInitialData();
    }, []);

    return (
        <Wrapper>
            <Meta meta={metaData}/>
            <Header/>

            <div className="content-wrapper  js-content-wrapper ">
                {
                    isLoading && (
                        <>
                            <section className={`breadcrumbs ${dark ? "bg-dark-1" : ""} `}>
                                <div className="container">
                                    <div className="row">
                                        <div className="col-auto">
                                            <div className="breadcrumbs__content">
                                                <div
                                                    className={`breadcrumbs__item ${dark ? "text-dark-3" : ""} `}
                                                >
                                                    <Link to="/">Beranda</Link>
                                                </div>

                                                <div
                                                    className={`breadcrumbs__item ${dark ? "text-dark-3" : ""} `}
                                                >
                                                    <Link to="/courses">Kursus</Link>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>

                            <div id="js-pin-container" className="js-pin-container relative">
                                <section className="page-header -type-5 bg-dark-1">
                                    <div className="page-header__bg">
                                        <div
                                            className="bg-image js-lazy"
                                            data-bg="img/event-single/bg.png"
                                        ></div>
                                    </div>

                                    <div className="container">
                                        <div className="page-header__content pt-90 pb-90">
                                            <div className="row y-gap-30 relative">
                                                <div className="col-xl-7 col-lg-8">
                                                    <div>
                                                        <h1 className="text-30 lh-14 text-white pr-60 lg:pr-0">
                                                            ...
                                                        </h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>

                                <section className="layout-pt-md layout-pb-md">
                                    <div className="container">
                                        <div className="row">
                                            <div className="col-lg-12">
                                                <span>
                                                    <i className={'fa fa-spinner fa-spin mr-5'}></i>
                                                    <strong role="status">Loading...</strong>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </section>

                            </div>
                        </>
                    )
                }

                {
                    !isLoading && (
                        <>
                            <section className={`breadcrumbs ${dark ? "bg-dark-1" : ""} `}>
                                <div className="container">
                                    <div className="row">
                                        <div className="col-auto">
                                            <div className="breadcrumbs__content">
                                                <div
                                                    className={`breadcrumbs__item ${dark ? "text-dark-3" : ""} `}
                                                >
                                                    <Link to="/">Beranda</Link>
                                                </div>

                                                <div
                                                    className={`breadcrumbs__item ${dark ? "text-dark-3" : ""} `}
                                                >
                                                    <Link to="/courses">Kursus</Link>
                                                </div>

                                                <div
                                                    className={`breadcrumbs__item ${dark ? "text-dark-3" : ""} `}
                                                >
                                                    <Link to={`/courses/by-category/${category.id}`}>{category.name}</Link>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>

                            <div id="js-pin-container" className="js-pin-container relative">
                                <section className="page-header -type-5 bg-dark-1">
                                    <div className="page-header__bg">
                                        <div
                                            className="bg-image js-lazy"
                                            data-bg="img/event-single/bg.png"
                                        ></div>
                                    </div>

                                    <div className="container">
                                        <div className="page-header__content pt-90 pb-90">
                                            <div className="row y-gap-30 relative">
                                                <div className="col-xl-7 col-lg-8">
                                                    <div>
                                                        <h1 className="text-30 lh-14 text-white pr-60 lg:pr-0">
                                                            {course.title}
                                                        </h1>
                                                    </div>

                                                    <p className="col-xl-9 mt-20">
                                                        <div
                                                            dangerouslySetInnerHTML={{__html: course.short_description}}
                                                        />
                                                    </p>

                                                    <div className="d-flex x-gap-30 y-gap-10 items-center flex-wrap pt-20">
                                                        <div className="d-flex items-center">
                                                            <div className="text-14 lh-1 text-yellow-1 mr-10">
                                                                {course.rating}
                                                            </div>
                                                            <div className="d-flex x-gap-5 items-center">
                                                                <Star star={course.rating} textSize={"text-11"}/>
                                                            </div>
                                                            <div className="text-14 lh-1 text-light-1 ml-10">
                                                                ({course.rating_count} rating)
                                                            </div>
                                                        </div>

                                                        <div className="d-flex items-center text-light-1">
                                                            <div className="icon icon-person-3 text-13"></div>
                                                            <div className="text-14 ml-8">
                                                                {course.enrollment_count} siswa
                                                            </div>
                                                        </div>

                                                        <div className="d-flex items-center text-light-1">
                                                            <div className="icon icon-wall-clock text-13"></div>
                                                            <div className="text-14 ml-8">Last
                                                                updated {formatDate(course.updated_at)}</div>
                                                        </div>
                                                    </div>

                                                    <div className="d-flex items-center pt-20">
                                                        <div
                                                            className="bg-image size-30 rounded-full js-lazy"
                                                            style={{
                                                                backgroundImage: `url('${instructor.user.profile_picture_url}')`,
                                                            }}
                                                        ></div>
                                                        <div className="text-14 lh-1 ml-10">
                                                            {instructor.user.name}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>

                                <PinContent course={course} isUserEnrolled={isUserEnrolled}/>

                                <section className="layout-pt-md layout-pb-md">
                                    <div className="container">
                                        <div className="row">
                                            <div className="col-lg-8">
                                                <div className="page-nav-menu -line">
                                                    <div className="d-flex x-gap-30">
                                                        {menuItems.map((item) => (
                                                            <div key={item.id}>
                                                                <a
                                                                    href={item.href}
                                                                    className={`pb-12 page-nav-menu__link ${
                                                                        item.isActive ? "is-active" : ""
                                                                    }`}
                                                                >
                                                                    {item.text}
                                                                </a>
                                                            </div>
                                                        ))}
                                                    </div>
                                                </div>

                                                <Overview course={course}/>
                                                <CourseContent chapters={chapters} course={course}/>
                                                <Instructor instructor={instructor}/>

                                            </div>
                                        </div>
                                    </div>
                                </section>

                            </div>
                        </>
                    )
                }
            </div>
        </Wrapper>
    );
}