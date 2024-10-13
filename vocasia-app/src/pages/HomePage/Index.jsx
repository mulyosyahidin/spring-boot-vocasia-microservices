import {Home} from "./Home.jsx";
import {Wrapper} from "../../components/commons/Wrapper.jsx";
import {Meta} from "../../components/commons/Meta.jsx";
import {useEffect} from "react";
import {apiBaseUrl} from "../../config/consts.js";

const metaData = {
    title: 'Kursus Online Bersertifikasi Terbaik No.1 Di Indonesia'
};

export const HomePage = () => {
    return (
        <Wrapper>
            <Meta meta={metaData} />

            <Home />
        </Wrapper>
    );
}