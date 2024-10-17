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
import {TransactionIndexPage as AdminTransactionIndexPage} from "../pages/Admin/Transactions/TransactionIndexPage/Index.jsx";
import {TransactionShowPage as AdminTransactionShowPage} from "../pages/Admin/Transactions/TransactionShowPage/Index.jsx";
import {FinanceIndexPage as AdminFinanceIndexPage} from "../pages/Admin/Finance/FinanceIndexPage/Index.jsx";
import {InstructorIndexPage} from "../pages/Admin/Instructors/InstructorIndexPage/Index.jsx";
import {InstructorShowPage} from "../pages/Admin/Instructors/InstructorShowPage/Index.jsx";
import {StudentIndexPage as AdminStudentIndexPage} from "../pages/Admin/Students/StudentIndexPage/Index.jsx";
import {InstructorStudentPage} from "../pages/Admin/Instructors/InstructorShowPage/InstructorStudentPage/Index.jsx";
import {StudentShowPage} from "../pages/Admin/Students/StudentShowPage/Index.jsx";
import {CourseQnaPage} from "../pages/User/CourseQnaPage/Index.jsx";
import {CourseQnaPage as InstructorCourseQnaPage} from "../pages/Instructor/Courses/CourseQnaPage/Index.jsx";

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
        path: '/instructor/courses/:courseId/qna/:lessonId/:qnaId',
        element: <InstructorCourseQnaPage/>,
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
        path: '/admin/withdrawal',
        element: <WithdrawalRequestIndexPage />,
    },
    {
        path: '/admin/withdrawal/:id',
        element: <WithdrawalRequestShowPage />,
    },
    {
        path: '/admin/transactions',
        element: <AdminTransactionIndexPage />,
    },
    {
        path: '/admin/transactions/:id',
        element: <AdminTransactionShowPage />,
    },
    {
        path: '/admin/finances',
        element: <AdminFinanceIndexPage />
    },
    {
        path: '/admin/instructors',
        element: <InstructorIndexPage />
    },
    {
        path: '/admin/instructors/:instructorId',
        element: <InstructorShowPage />
    },
    {
        path: '/admin/instructors/:instructorId/students/:id',
        element: <InstructorStudentPage />
    },
    {
        path: '/admin/students',
        element: <AdminStudentIndexPage/>,
    },
    {
        path: '/admin/students/:userId',
        element: <StudentShowPage/>,
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
    {
        path: '/users/courses/:enrollmentId/qna/:lessonId/:questionId',
        element: <CourseQnaPage/>
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
