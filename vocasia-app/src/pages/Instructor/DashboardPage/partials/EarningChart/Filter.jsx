export const Filter = () => {
    return (
        <div className="">
            <div
                id="ddtwobutton"
                onClick={() => {
                    document
                        .getElementById("ddtwobutton")
                        .classList.toggle("-is-dd-active");
                    document
                        .getElementById("ddtwocontent")
                        .classList.toggle("-is-el-visible");
                }}
                className="dropdown js-dropdown js-category-active"
            >
                <div
                    className="dropdown__button d-flex items-center text-14 bg-white -dark-bg-dark-1 border-light rounded-8 px-20 py-10 text-14 lh-12"
                    data-el-toggle=".js-category-toggle"
                    data-el-toggle-active=".js-category-active"
                >
                    <span className="js-dropdown-title">This Week</span>
                    <i className="icon text-9 ml-40 icon-chevron-down"></i>
                </div>

                <div
                    id="ddtwocontent"
                    className="toggle-element -dropdown -dark-bg-dark-2 -dark-border-white-10 js-click-dropdown js-category-toggle"
                >
                    <div className="text-14 y-gap-15 js-dropdown-list">
                        <div>
                            <a href="#" className="d-block js-dropdown-link">
                                Animation
                            </a>
                        </div>

                        <div>
                            <a href="#" className="d-block js-dropdown-link">
                                Design
                            </a>
                        </div>

                        <div>
                            <a href="#" className="d-block js-dropdown-link">
                                Illustration
                            </a>
                        </div>

                        <div>
                            <a href="#" className="d-block js-dropdown-link">
                                Business
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}