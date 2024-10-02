import {createBrowserRouter} from "react-router-dom";
import {HomePage} from "../pages/HomePage/Index.jsx";
import {BecomeInstructorPage} from "../pages/BecomeInstructorPage/Index.jsx";
import {LoginPage} from "../pages/Authentication/LoginPage/Index.jsx";
import {DashboardPage} from "../pages/Instructor/DashboardPage/Index.jsx";
import {CreateCoursePage} from "../pages/Instructor/Courses/CreateCoursePage/Index.jsx";
import {CourseIndexPage} from "../pages/Instructor/Courses/CourseIndexPage/Index.jsx";
import {UpdateThumbnailPage} from "../pages/Instructor/Courses/UpdateThumbnailPage/Index.jsx";
import {EditCoursePage} from "../pages/Instructor/Courses/EditCoursePage/Index.jsx";
import {ChapterPage} from "../pages/Instructor/Courses/ChapterPage/Index.jsx";
import {ManageLessonsPage} from "../pages/Instructor/Courses/ManageLessonsPage/Index.jsx";
import {CreateLessonPage} from "../pages/Instructor/Courses/CreateLessonPage/Index.jsx";
import {CourseOverviewPage} from "../pages/Instructor/Courses/CourseOverviewPage/Index.jsx";
import {Logout} from "../pages/Logout/Logout.jsx";

import {DashboardPage as AdminDashboardPage} from "../pages/Admin/DashboardPage/Index.jsx";
import {CategoryIndexPage} from "../pages/Admin/Categories/CategoryIndexPage/Index.jsx";
import {CreateCategoryPage} from "../pages/Admin/Categories/CreateCategoryPage/Index.jsx";
import {EditCategoryPage} from "../pages/Admin/Categories/EditCategoryPage/Index.jsx";

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
        path: '/logout',
        element: <Logout/>,
    },
    {
        path: '/instructor',
        element: <DashboardPage/>,
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
        element: <ChapterPage/>,
    },
    {
        path: '/instructor/courses/:courseId/chapters/:chapterId/lessons',
        element: <ManageLessonsPage/>,
    },
    {
        path: '/instructor/courses/:courseId/chapters/:chapterId/lessons/create',
        element: <CreateLessonPage/>,
    },
    {
        path: '/instructor/courses/:courseId/overview',
        element: <CourseOverviewPage/>,
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
]);

export default routes;
