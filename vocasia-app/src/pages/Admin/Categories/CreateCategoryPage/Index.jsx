import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {ADMIN} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";
import {CreateCategory} from "./CreateCategory.jsx";

const metaData = {
    title: 'Tambah Kategori Kursus',
};

export const CreateCategoryPage = () => {
    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <CreateCategory />
            </AdminWrapper>
        </Wrapper>
    );
}