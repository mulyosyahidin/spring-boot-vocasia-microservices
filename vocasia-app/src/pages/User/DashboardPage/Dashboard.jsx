import React, {useContext} from "react";
import {AuthContext} from "../../../states/contexts/AuthContext.jsx";

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

            <div className="row y-gap-30 pt-30">

            </div>
        </div>
    );
}