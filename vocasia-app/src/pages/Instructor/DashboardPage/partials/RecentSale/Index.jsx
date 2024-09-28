import {Link} from "react-router-dom";
import {teamMembers} from "./data.js";

export const RecentSale = () => {
    return (
        <div className="col-md-6">
            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                <div className="d-flex justify-between items-center py-20 px-30 border-bottom-light">
                    <h2 className="text-17 fw-500">Penjualan Terbaru</h2>
                    <Link
                        to="/instructors-list-2"
                        className="text-14 text-purple-1 underline"
                    >
                        Lihat Semua
                    </Link>
                </div>
                <div className="py-30 px-30">
                    <div className="y-gap-40">
                        {teamMembers.slice(0, 5).map((elm, i) => (
                            <div
                                key={i}
                                className={`d-flex ${i != 0 ? "border-top-light" : ""} `}
                            >
                                <img className="size-40" src={elm.image} alt="avatar"/>
                                <div className="ml-10 w-1/1">
                                    <h4 className="text-15 lh-1 fw-500">
                                        <Link
                                            className="linkCustom"
                                            to={`/instructors/${elm.id}`}
                                        >
                                            {elm.name}
                                        </Link>
                                    </h4>
                                    <div className="d-flex items-center x-gap-20 y-gap-10 flex-wrap pt-10">
                                        <div className="d-flex items-center">
                                            <i className="icon-message text-15 mr-10"></i>
                                            <div className="text-13 lh-1">
                                                {elm.reviews} Reviews
                                            </div>
                                        </div>
                                        <div className="d-flex items-center">
                                            <i className="icon-online-learning text-15 mr-10"></i>
                                            <div className="text-13 lh-1">
                                                {elm.students} Students
                                            </div>
                                        </div>
                                        <div className="d-flex items-center">
                                            <i className="icon-play text-15 mr-10"></i>
                                            <div className="text-13 lh-1">
                                                {elm.courses} Course
                                            </div>
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