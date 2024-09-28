import {Link, useLocation} from "react-router-dom";
import {sidebarItems} from "./data.js";

export const Sidebar = () => {
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
            {sidebarItems.map((item) => (
                <div
                    key={item.id}
                    className={`sidebar__item ${isActive(item.href, item.activeWhen) ? "-is-active" : ""}`}
                >
                    <Link
                        to={item.href}
                        className="d-flex items-center text-17 lh-1 fw-500"
                    >
                        <i className={`${item.iconClass} mr-15`}></i>
                        {item.text}
                    </Link>
                </div>
            ))}
        </div>
    );
};
