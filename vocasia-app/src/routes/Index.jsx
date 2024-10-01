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
    }
]);

export default routes;
