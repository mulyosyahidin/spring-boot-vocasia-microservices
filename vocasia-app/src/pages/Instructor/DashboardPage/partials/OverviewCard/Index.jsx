import {overview} from "./data.js";

export const OverviewCard = () => {
    return (
        <div className="row y-gap-30">
            {overview.map((elm, i) => (
                <div key={i} className="col-xl-3 col-md-6">
                    <div
                        className="d-flex justify-between items-center py-35 px-30 rounded-16 bg-white -dark-bg-dark-1 shadow-4">
                        <div>
                            <div className="lh-1 fw-500">{elm.title}</div>
                            <div className="text-24 lh-1 fw-700 text-dark-1 mt-20">
                                {elm.value}
                            </div>
                        </div>

                        <i className={`text-40 ${elm.iconClass} text-purple-1`}></i>
                    </div>
                </div>
            ))}
        </div>
    );
}