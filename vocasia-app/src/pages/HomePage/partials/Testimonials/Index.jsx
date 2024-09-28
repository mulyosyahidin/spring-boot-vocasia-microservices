import {Title} from "./partials/Title.jsx";
import {TestimonyItems} from "./partials/TestimonyItems.jsx";
import {Counters} from "./partials/Counters.jsx";

export default function Testimonials() {
    return (
        <section className="layout-pt-lg mt-80 layout-pb-lg bg-purple-1">
            <div className="container ">
                <Title/>

                <TestimonyItems/>
                <Counters/>
            </div>
        </section>
    );
}
