import {Component} from "react";
import {Wrapper} from "../../../components/commons/Wrapper.jsx";
import {Dashboard} from "./Dashboard.jsx";
import {Meta} from "../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../components/commons/PreLoader.jsx";
import {UserWrapper} from "../../../components/Users/UserWrapper/Index.jsx";
import {STUDENT} from "../../../config/consts.js";

const metaData = {
    title: "User Dashboard"
}

export const DashboardPage = () => {
    return (
        <Wrapper needAuth role={STUDENT}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <UserWrapper>
                <Dashboard/>
            </UserWrapper>
        </Wrapper>
    );
}