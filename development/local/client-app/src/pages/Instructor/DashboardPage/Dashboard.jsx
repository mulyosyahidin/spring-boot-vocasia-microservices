import {useContext} from "react";
import {AuthContext} from "../../../states/contexts/AuthContext.jsx";
import {OverviewCard} from "./partials/OverviewCard/Index.jsx";
import {EarningChart} from "./partials/EarningChart/Index.jsx";
import {INSTRUCTOR_AUTH_DATA} from "../../../config/consts.js";
import {WaitingStatus} from "./partials/WaitingStatus/Index.jsx";

export const Dashboard = () => {
    const {user} = useContext(AuthContext);
    const instructorData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));

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

            {
                instructorData.status === 'approved' ? (
                    <>
                        <OverviewCard/>
                        <EarningChart/>
                    </>
                ) : <WaitingStatus status={instructorData.status} />
            }

        </div>
    );
}