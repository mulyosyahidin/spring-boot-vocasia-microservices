import {useContext} from "react";
import {AuthContext} from "../../../states/contexts/AuthContext.jsx";
import {OverviewCard} from "./partials/OverviewCard/Index.jsx";
import {EarningChart} from "./partials/EarningChart/Index.jsx";

export const Dashboard = () => {
    const {user} = useContext(AuthContext);

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Dashboard</h1>
                    <div className="mt-10">
                        Halo, {user.name}!
                    </div>
                </div>
            </div>

            <OverviewCard/>
            <EarningChart/>

        </div>
    );
}