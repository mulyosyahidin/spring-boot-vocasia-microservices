import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {WithdrawalIndex} from "./WithdrawalIndex.jsx";

const metaData = {
    title: 'Riwayat Withdrawal',
};

export const WithdrawalIndexPage = () => {
    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <WithdrawalIndex/>
            </InstructorWrapper>
        </Wrapper>
    );
}