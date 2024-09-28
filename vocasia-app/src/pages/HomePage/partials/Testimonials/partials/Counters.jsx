import {counters} from "../data.js";

export const Counters = () => {
    return (
        <div className="row y-gap-30  counter__row">
            {counters.map((elm, i) => (
                <div
                    key={i}
                    className="col-lg-3 col-sm-6"
                    data-aos="fade-left"
                    data-aos-duration={(i + 1) * 350}
                >
                    <div className="counter -type-1">
                        <div className="counter__number">{elm.number}</div>
                        <div className="counter__title">{elm.title}</div>
                    </div>
                </div>
            ))}
        </div>
    );
}