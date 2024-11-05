import {Header} from "../Header/Index.jsx";
import {Sidebar} from "../Sidebar/Index.jsx";
import {Footer} from "../Footer/Index.jsx";
import {INSTRUCTOR_AUTH_DATA} from "../../../config/consts.js";
import {DefaultSidebar} from "../DefaultSidebar/Index.jsx";

export const InstructorWrapper = ({children}) => {
    const instructorData = JSON.parse(localStorage.getItem(INSTRUCTOR_AUTH_DATA));

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
                            {
                                instructorData.status === 'approved' ? <Sidebar /> : <DefaultSidebar />
                            }
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