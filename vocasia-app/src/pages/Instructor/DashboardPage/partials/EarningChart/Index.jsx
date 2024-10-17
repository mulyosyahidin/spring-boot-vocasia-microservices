import {Charts} from "./Partials/Charts.jsx";

export const EarningChart = () => {
    return (
        <div className="row y-gap-30 pt-30">
            <div className="col-xl-12 col-md-6">
                <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                    <div className="d-flex justify-between items-center py-20 px-30 border-bottom-light">
                        <h2 className="text-17 lh-1 fw-500">Statistik Pendapatan</h2>
                    </div>
                    <div className="py-40 px-30">
                        <Charts/>
                    </div>
                </div>
            </div>
        </div>
    );
}