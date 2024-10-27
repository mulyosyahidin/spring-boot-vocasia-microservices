import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Dashboard} from "./Dashboard.jsx";
import {Meta} from "../../../components/commons/Meta.jsx";
import {ADMIN} from "../../../config/consts.js";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../components/Admin/AdminWrapper/Index.jsx";

const metaData = {
    title: 'Admin Dashboard',
};

export const DashboardPage = () => {
    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <Dashboard/>
            </AdminWrapper>
        </Wrapper>
    );
}