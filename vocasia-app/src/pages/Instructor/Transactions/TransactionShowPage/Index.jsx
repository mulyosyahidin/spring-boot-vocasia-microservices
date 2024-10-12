import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../config/consts.js";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {TransactionShow} from "./TransactionShow.jsx";

export const TransactionShowPage = () => {
    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <PreLoader/>

            <InstructorWrapper>
                <TransactionShow/>
            </InstructorWrapper>
        </Wrapper>
    );
}