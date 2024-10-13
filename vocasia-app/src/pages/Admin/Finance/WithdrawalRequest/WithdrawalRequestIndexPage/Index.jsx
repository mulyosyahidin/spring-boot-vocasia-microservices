import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../../../components/Admin/AdminWrapper/Index.jsx";
import {WithdrawalRequestIndex} from "./WithdrawalRequestIndex.jsx";

const metaData = {
    title: 'Riwayat Withdrawal',
};

export const WithdrawalRequestIndexPage = () => {
    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <WithdrawalRequestIndex />
            </AdminWrapper>
        </Wrapper>
    );
}