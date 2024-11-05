import {Link, useLocation} from "react-router-dom";

export const DefaultSidebar = () => {
    const {pathname} = useLocation();

    const isActive = (href, activeWhen) => {
        if (pathname === href) {
            return true;
        }
        if (activeWhen.endsWith('*')) {
            return pathname.startsWith(activeWhen.slice(0, -1));
        }
        return false;
    };

    return (
        <div className="sidebar -dashboard">
            <div
                className={`sidebar__item -is-active`}
            >
                <Link
                    to={'/instructor/dashboard'}
                    className="d-flex items-center text-17 lh-1 fw-500"
                >
                    <i className={`text-20 icon-discovery mr-15`}></i>
                   Dashboard
                </Link>
            </div>
        </div>
    );
};
