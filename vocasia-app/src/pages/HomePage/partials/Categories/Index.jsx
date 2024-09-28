import "swiper/css";
import "swiper/css/pagination";

import {Title} from "./partials/Title.jsx";
import {CategorySlider} from "./partials/CategorySlider.jsx";

export const Categories = () => {

    return (
        <>
            <section className="layout-pt-md layout-pb-md">
                <div className="container">
                   <Title />

                    <CategorySlider />
                </div>
            </section>
        </>
    );
};
