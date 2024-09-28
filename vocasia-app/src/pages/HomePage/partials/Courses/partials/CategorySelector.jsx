import React from "react";
import {categories} from "../data.js";

export const CategorySelector = ({ category, setCategory }) => {
    return (
        <div className="tabs__controls flex-wrap pt-50 d-flex justify-center x-gap-10 js-tabs-controls">
            {categories.map((cat, i) => (
                <div key={i} onClick={() => setCategory(cat)}>
                    <button
                        className={`tabs__button px-15 py-8 rounded-8 js-tabs-button ${
                            category === cat ? "tabActive" : ""
                        }`}
                        data-tab-target=".-tab-item-2"
                        type="button"
                    >
                        {cat}
                    </button>
                </div>
            ))}
        </div>
    );
};
