import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../../../components/Admin/AdminWrapper/Index.jsx";
import {WithdrawalRequestShow} from "./WithdrawalRequestShow.jsx";

const metaData = {
    title: 'Data Withdrawal',
};

export const WithdrawalRequestShowPage = () => {
    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <WithdrawalRequestShow />
            </AdminWrapper>
        </Wrapper>
    );
}