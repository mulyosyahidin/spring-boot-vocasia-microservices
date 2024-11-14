import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../components/commons/Meta.jsx";
import {ADMIN} from "../../../config/consts.js";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../components/Admin/AdminWrapper/Index.jsx";
import {OverviewCard} from "./partials/OverviewCard.jsx";

const metaData = {
    title: 'Admin Dashboard',
};

export const DashboardPage = () => {
    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Dashboard</h1>
                            <div className="mt-10">
                                Admin Dashboard
                            </div>
                        </div>
                    </div>

                    <OverviewCard />

                </div>
            </AdminWrapper>
        </Wrapper>
    );
}