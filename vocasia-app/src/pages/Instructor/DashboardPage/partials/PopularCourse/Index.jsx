import {recentCourses} from "./data.js";

export const PopularCourse = () => {
    return (
        <div className="col-md-6">
            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                <div className="d-flex justify-between items-center py-20 px-30 border-bottom-light">
                    <h2 className="text-17 lh-1 fw-500">Kursus Terpopuler</h2>
                    <a href="#" className="text-14 text-purple-1 underline">
                        Lihat Semua
                    </a>
                </div>
                <div className="py-30 px-30">
                    <div className="y-gap-40">
                        {recentCourses.map((elm, i) => (
                            <div
                                key={i}
                                className={`d-flex ${i != 0 ? "border-top-light" : ""} `}
                            >
                                <div className="shrink-0">
                                    <img src={elm.imageSrc} alt="image"/>
                                </div>
                                <div className="ml-15">
                                    <h4 className="text-15 lh-16 fw-500">{elm.title}</h4>
                                    <div className="d-flex items-center x-gap-20 y-gap-10 flex-wrap pt-10">
                                        <div className="d-flex items-center">
                                            <img
                                                className="size-16 object-cover mr-8"
                                                src={elm.authorImg}
                                                alt="icon"
                                            />
                                            <div className="text-14 lh-1">{elm.title}</div>
                                        </div>
                                        <div className="d-flex items-center">
                                            <i className="icon-document text-16 mr-8"></i>
                                            <div className="text-14 lh-1">
                                                {elm.lessonCount} lesson
                                            </div>
                                        </div>
                                        <div className="d-flex items-center">
                                            <i className="icon-clock-2 text-16 mr-8"></i>
                                            <div className="text-14 lh-1">{`${Math.floor(
                                                elm.duration / 60,
                                            )}h ${Math.floor(elm.duration % 60)}m`}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}