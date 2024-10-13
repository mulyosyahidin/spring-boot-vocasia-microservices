import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {STUDENT} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {UserWrapper} from "../../../../components/Users/UserWrapper/Index.jsx";
import {TransactionShow} from "./TransactionShow.jsx";

const metaData = {
    title: "Data Transaksi"
}

export const TransactionShowPage = () => {
    return (
        <Wrapper needAuth role={STUDENT}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <UserWrapper>
                <TransactionShow/>
            </UserWrapper>
        </Wrapper>
    );
}