import {Header} from "./Header/Index.jsx";
import {Sidebar} from "./Sidebar/Index.jsx";

export const AdminWrapper = ({children}) => {
    return (
        <div className="barba-container" data-barba="container">
            <main className="main-content">
                <Header/>

                <div className="content-wrapper js-content-wrapper overflow-hidden">
                    <div
                        id="dashboardOpenClose"
                        className="dashboard -home-9 js-dashboard-home-9"
                    >
                        <div className="dashboard__sidebar scroll-bar-1">
                            <Sidebar/>
                        </div>

                        <div className="dashboard__main">
                            {children}
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}