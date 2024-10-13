import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {STUDENT} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {UserWrapper} from "../../../../components/Users/UserWrapper/Index.jsx";
import {TransactionIndex} from "./TransactionIndex.jsx";

const metaData = {
    title: "Data Transaksi"
}

export const TransactionIndexPage = () => {
    return (
        <Wrapper needAuth role={STUDENT}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <UserWrapper>
                <TransactionIndex/>
            </UserWrapper>
        </Wrapper>
    );
}
