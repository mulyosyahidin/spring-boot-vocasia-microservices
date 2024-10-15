import {createBrowserRouter} from "react-router-dom";
import {HomePage} from "../pages/HomePage/Index.jsx";
import {BecomeInstructorPage} from "../pages/BecomeInstructorPage/Index.jsx";
import {LoginPage} from "../pages/Authentication/LoginPage/Index.jsx";
import {DashboardPage} from "../pages/Instructor/DashboardPage/Index.jsx";
import {CreateCoursePage} from "../pages/Instructor/Courses/CreateCoursePage/Index.jsx";
import {CourseIndexPage} from "../pages/Instructor/Courses/CourseIndexPage/Index.jsx";
import {UpdateThumbnailPage} from "../pages/Instructor/Courses/UpdateThumbnailPage/Index.jsx";
import {EditCoursePage} from "../pages/Instructor/Courses/EditCoursePage/Index.jsx";
import {CourseOverviewPage} from "../pages/Instructor/Courses/CourseOverviewPage/Index.jsx";
import {Logout} from "../pages/Logout/Logout.jsx";

import {DashboardPage as AdminDashboardPage} from "../pages/Admin/DashboardPage/Index.jsx";
import {CategoryIndexPage} from "../pages/Admin/Categories/CategoryIndexPage/Index.jsx";
import {CreateCategoryPage} from "../pages/Admin/Categories/CreateCategoryPage/Index.jsx";
import {EditCategoryPage} from "../pages/Admin/Categories/EditCategoryPage/Index.jsx";
import {RegisterPage} from "../pages/Authentication/RegisterPage/Index.jsx";

import {DashboardPage as UserDashboardPage} from "../pages/User/DashboardPage/Index.jsx";
import {CourseViewPage} from "../pages/Public/CourseViewPage/Index.jsx";
import {ProfilePage} from "../pages/Instructor/ProfilePage/Index.jsx";
import CourseCartPage from "../pages/Public/CourseCartPage/Index.jsx";
import {CourseCheckoutPage} from "../pages/Public/CourseCheckoutPage/Index.jsx";
import {OrderDataPage} from "../pages/User/OrderDataPage/Index.jsx";
import {TransactionIndexPage as UserTransactionIndexPage} from "../pages/User/Transactions/TransactionIndexPage/Index.jsx";
import {TransactionShowPage as UserTransactionShowPage} from "../pages/User/Transactions/TransactionShowPage/Index.jsx";
import {CoursesPage} from "../pages/User/CoursesPage/Index.jsx";
import {SingleCoursePage} from "../pages/User/SingleCoursePage/Index.jsx";
import {StudentIndexPage} from "../pages/Instructor/Students/StudentIndexPage/Index.jsx";
import {ShowStudentPage} from "../pages/Instructor/Students/ShowStudentPage/Index.jsx";
import {TransactionIndexPage} from "../pages/Instructor/Transactions/TransactionIndexPage/Index.jsx";
import {TransactionShowPage} from "../pages/Instructor/Transactions/TransactionShowPage/Index.jsx";
import {FinanceIndexPage} from "../pages/Instructor/Finance/FinanceIndexPage/Index.jsx";
import {WithdrawalRequestPage} from "../pages/Instructor/Finance/Withdrawal/WithdrawalRequestPage/Index.jsx";
import {WithdrawalIndexPage} from "../pages/Instructor/Finance/Withdrawal/WithdrawalIndexPage/Index.jsx";
import {
    WithdrawalRequestIndexPage
} from "../pages/Admin/Finance/WithdrawalRequest/WithdrawalRequestIndexPage/Index.jsx";
import {WithdrawalRequestShowPage} from "../pages/Admin/Finance/WithdrawalRequest/WithdrawalRequestShowPage/Index.jsx";
import {ChapterIndexPage} from "../pages/Instructor/Courses/Chapter/ChapterIndexPage/Index.jsx";
import {ChapterCreatePage} from "../pages/Instructor/Courses/Chapter/ChapterCreatePage/Index.jsx";
import {ChapterEditPage} from "../pages/Instructor/Courses/Chapter/ChapterEditPage/Index.jsx";
import {LessonIndexPage} from "../pages/Instructor/Courses/Lessons/LessonIndexPage/Index.jsx";
import {LessonCreatePage} from "../pages/Instructor/Courses/Lessons/LessonCreatePage/Index.jsx";
import {LessonEditPage} from "../pages/Instructor/Courses/Lessons/LessonEditPage/Index.jsx";

const routes = createBrowserRouter([
    {
        path: '/',
        element: <HomePage/>
    },
    {
        path: '/become-instructor',
        element: <BecomeInstructorPage/>
    },
    {
        path: '/auth/login',
        element: <LoginPage/>
    },
    {
        path: '/auth/register',
        element: <RegisterPage/>
    },
    {
        path: '/logout',
        element: <Logout/>,
    },

    // instructor
    {
        path: '/instructor',
        element: <DashboardPage/>,
    },
    {
        path: '/instructor/profile',
        element: <ProfilePage/>,
    },
    {
        path: '/instructor/courses',
        element: <CourseIndexPage/>,
    },
    {
        path: '/instructor/courses/create',
        element: <CreateCoursePage/>,
    },
    {
        path: '/instructor/courses/:id/update-thumbnail',
        element: <UpdateThumbnailPage/>,
    },
    {
        path: '/instructor/courses/:id/edit',
        element: <EditCoursePage/>,
    },
    {
        path: '/instructor/courses/:id/chapters',
        element: <ChapterIndexPage/>,
    },
    {
        path: '/instructor/courses/:id/chapters/create',
        element: <ChapterCreatePage/>,
    },
    {
        path: '/instructor/courses/:id/chapters/:chapterId/edit',
        element: <ChapterEditPage/>,
    },
    {
        path: '/instructor/courses/:courseId/chapters/:chapterId/lessons',
        element: <LessonIndexPage/>,
    },
    {
        path: '/instructor/courses/:courseId/chapters/:chapterId/lessons/create',
        element: <LessonCreatePage/>,
    },
    {
        path: '/instructor/courses/:courseId/chapters/:chapterId/lessons/:lessonId/edit',
        element: <LessonEditPage/>,
    },
    {
        path: '/instructor/courses/:courseId/overview',
        element: <CourseOverviewPage/>,
    },
    {
        path: '/instructor/students',
        element: <StudentIndexPage />,
    },
    {
        path: '/instructor/students/:id',
        element: <ShowStudentPage />,
    },
    {
        path: '/instructor/transactions',
        element: <TransactionIndexPage />,
    },
    {
        path: '/instructor/transactions/:id',
        element: <TransactionShowPage />,
    },
    {
        path: '/instructor/finances',
        element: <FinanceIndexPage />,
    },
    {
        path: '/instructor/finances/withdrawal',
        element: <WithdrawalIndexPage />,
    },
    {
        path: '/instructor/finances/withdrawal/request',
        element: <WithdrawalRequestPage />,
    },

    // admin
    {
        path: '/admin',
        element: <AdminDashboardPage/>,
    },
    {
        path: '/admin/categories',
        element: <CategoryIndexPage/>,
    },
    {
        path: '/admin/categories/create',
        element: <CreateCategoryPage/>,
    },
    {
        path: '/admin/categories/:categoryId/edit',
        element: <EditCategoryPage/>,
    },
    {
        path: '/admin/finances/withdrawal',
        element: <WithdrawalRequestIndexPage />,
    },
    {
        path: '/admin/finances/withdrawal/:id',
        element: <WithdrawalRequestShowPage />,
    },

    // user dashboard
    {
        path: '/users',
        element: <UserDashboardPage/>,
    },
    {
        path: '/users/courses',
        element: <CoursesPage/>
    },
    {
        path: '/users/transactions',
        element: <UserTransactionIndexPage />,
    },
    {
        path: '/users/transactions/:orderId',
        element: <UserTransactionShowPage />,
    },
    {
        path: '/users/orders/:orderId',
        element: <OrderDataPage/>
    },
    {
        path: '/users/courses/:enrollmentId',
        element: <SingleCoursePage/>
    },

    // public
    {
        path: '/courses/by-category/:categoryId',
        element: <div>Courses List</div>
    },
    {
        path: '/courses/view/:slug/:id',
        element: <CourseViewPage/>
    },
    {
        path: '/course-cart',
        element: <CourseCartPage/>
    },
    {
        path: '/course-checkout',
        element: <CourseCheckoutPage/>
    },
]);

export default routes;
