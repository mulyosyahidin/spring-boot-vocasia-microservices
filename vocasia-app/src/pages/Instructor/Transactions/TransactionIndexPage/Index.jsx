import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {TransactionIndex} from "./TransactionIndex.jsx";

const metaData = {
    title: 'Data Transaksi',
};

export const TransactionIndexPage = () => {
    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <TransactionIndex/>
            </InstructorWrapper>
        </Wrapper>
    );
}