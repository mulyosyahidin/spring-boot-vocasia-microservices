import {useEffect, useState} from "react";
import {rupiahFormatter} from "../../../../utils/utils.js";

export default function PinContent({ course }) {
    const [isOpen, setIsOpen] = useState(false);
    const [screenWidth, setScreenWidth] = useState(window.innerWidth);

    useEffect(() => {
        const handleResize = () => {
            setScreenWidth(window.innerWidth);
        };

        window.addEventListener("resize", handleResize);

        return () => {
            window.removeEventListener("resize", handleResize);
        };
    }, []);

    return (
        <>
            <div
                id="js-pin-content"
                style={
                    screenWidth < 991
                        ? { height: "fit-content", right: "0%" }
                        : { height: "100%", right: "7%", paddingTop: "80px" }
                }
                className="courses-single-info js-pin-content"
            >
                <div
                    style={{ position: "sticky", top: "100px" }}
                    className="bg-white shadow-2 rounded-8 border-light py-10 px-10"
                >
                    <div className="relative">
                        <img className="w-1/1" src={course.featured_picture_url} alt="image" />
                        {/*<div className="absolute-full-center d-flex justify-center items-center">*/}
                        {/*    <div*/}
                        {/*        onClick={() => setIsOpen(true)}*/}
                        {/*        className="d-flex justify-center items-center size-60 rounded-full bg-white js-gallery cursor"*/}
                        {/*        data-gallery="gallery1"*/}
                        {/*    >*/}
                        {/*        <div className="icon-play text-18"></div>*/}
                        {/*    </div>*/}
                        {/*</div>*/}
                    </div>

                    <div className="courses-single-info__content scroll-bar-1 pt-30 pb-20 px-20">
                        <div className="d-flex justify-between items-center mb-30">
                            {!course.is_free ? (
                               course.is_discount ? (
                                   <>
                                       <div className="text-24 lh-1 text-dark-1 fw-500">
                                           {`${rupiahFormatter.format(course.price - course.discount)}`}
                                       </div>
                                       <div className="lh-1 line-through">
                                           {`${rupiahFormatter.format(course.price)}`}
                                       </div>
                                   </>
                               ) : (
                                   <>
                                   </>
                               )
                            ) : (
                                <>
                                    <div className="text-24 lh-1 text-dark-1 fw-500">Gratis!</div>
                                    <div></div>
                                </>
                            )}
                        </div>

                        {/*<button*/}
                        {/*    className="button -md -purple-1 text-white w-1/1"*/}
                        {/*    onClick={() => addCourseToCart(pageItem.id)}*/}
                        {/*>*/}
                        {/*    {isAddedToCartCourses(pageItem.id)*/}
                        {/*        ? "Already Added"*/}
                        {/*        : "Add To Cart"}*/}
                        {/*</button>*/}
                        {/*<button className="button -md -outline-dark-1 text-dark-1 w-1/1 mt-10">*/}
                        {/*    Buy Now*/}
                        {/*</button>*/}

                        <div className="text-14 lh-1 text-center mt-30">
                            Akses seumur hidup
                        </div>

                        <div className="mt-25">
                            <div className="d-flex justify-between py-8 ">
                                <div className="d-flex items-center text-dark-1">
                                    <div className="icon-video-file"></div>
                                    <div className="ml-10">Bab</div>
                                </div>
                                <div>{course.chapter_count}</div>
                            </div>

                            <div className="d-flex justify-between py-8 border-top-light">
                                <div className="d-flex items-center text-dark-1">
                                    <div className="icon-video-file"></div>
                                    <div className="ml-10">Pelajaran</div>
                                </div>
                                <div>{course.lesson_count}</div>
                            </div>

                            {/*<div className="d-flex justify-between py-8 border-top-light">*/}
                            {/*    <div className="d-flex items-center text-dark-1">*/}
                            {/*        <div className="icon-puzzle"></div>*/}
                            {/*        <div className="ml-10">Quizzes</div>*/}
                            {/*    </div>*/}
                            {/*    <div>3</div>*/}
                            {/*</div>*/}

                            <div className="d-flex justify-between py-8 border-top-light">
                                <div className="d-flex items-center text-dark-1">
                                    <div className="icon-clock-2"></div>
                                    <div className="ml-10">Durasi</div>
                                </div>
                                <div>{course.total_duration}</div>
                            </div>

                            <div className="d-flex justify-between py-8 border-top-light">
                                <div className="d-flex items-center text-dark-1">
                                    <div className="icon-bar-chart-2"></div>
                                    <div className="ml-10">Level</div>
                                </div>
                                <div>{course.level}</div>
                            </div>

                            <div className="d-flex justify-between py-8 border-top-light">
                                <div className="d-flex items-center text-dark-1">
                                    <div className="icon-translate"></div>
                                    <div className="ml-10">Bahasa</div>
                                </div>
                                <div>{course.language}</div>
                            </div>

                            <div className="d-flex justify-between py-8 border-top-light">
                                <div className="d-flex items-center text-dark-1">
                                    <div className="icon-badge"></div>
                                    <div className="ml-10">Sertifikat</div>
                                </div>
                                <div>
                                    {
                                        course.is_free ? 'Tidak' : 'Ya'
                                    }
                                </div>
                            </div>

                            <div className="d-flex justify-between py-8 border-top-light">
                                <div className="d-flex items-center text-dark-1">
                                    <div className="icon-infinity"></div>
                                    <div className="ml-10">Akses seumur hidup</div>
                                </div>
                                <div>Ya</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/*<ModalVideoComponent*/}
            {/*    videoId={"LlCwHnp3kL4"}*/}
            {/*    isOpen={isOpen}*/}
            {/*    setIsOpen={setIsOpen}*/}
            {/*/>*/}
        </>
    );
}
