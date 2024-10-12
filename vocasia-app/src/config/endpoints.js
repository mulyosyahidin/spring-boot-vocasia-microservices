export const AUTH_LOGIN = '/auth/login'; // permitAll
export const AUTH_REGISTER = '/auth/register'; // permitAll
export const INSTRUCTOR_REGISTER = '/instructors/register'; // permitAll

export const COURSES_GET_ALL = '/course/courses/all'; // hasAnyRole("INSTRUCTOR", "ADMIN")
export const COURSES_GET_DRAFT = '/course/courses/draft'; // hasAnyRole("INSTRUCTOR", "ADMIN")
export const COURSES_GET_PUBLISHED = '/course/courses/published'; // hasAnyRole("INSTRUCTOR", "ADMIN")

export const COURSES_CREATE_NEW_COURSE = '/course/courses'; // hasRole("INSTRUCTOR")
export const COURSES_UPLOAD_THUMBNAIL = '/course/courses/:id/thumbnail'; // hasRole("INSTRUCTOR")
export const COURSES_UPDATE = '/course/courses/:id'; // hasRole("INSTRUCTOR")
export const COURSES_SHOW = '/course/courses/:id'; // permitAll
export const COURSES_CATEGORIES_GETALL = '/course/categories'; // permitAll
export const COURSES_PUBLISH = '/course/courses/:courseId/publish'; // permitAll

export const COURSES_CATEGORIES_ADMIN_GETALL = '/course/admin/categories';
export const COURSES_CATEGORIES_ADMIN_STORE = '/course/admin/categories';
export const COURSES_CATEGORIES_ADMIN_UPDATE = '/course/admin/categories/:categoryId';
export const COURSES_CATEGORIES_ADMIN_SHOW = '/course/admin/categories/:categoryId';
export const COURSES_CATEGORIES_ADMIN_DELETE = '/course/admin/categories/:categoryId';

export const COURSES_CHAPTERS_GETALL = '/course/:courseId/chapters'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_CREATE = '/course/:courseId/chapters'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_UPDATE = '/course/:courseId/chapters/:chapterId'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_DELETE = '/course/:courseId/chapters/:chapterId'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_SHOW = '/course/:courseId/chapters/:chapterId'; // permitAll

export const COURSES_CHAPTERS_LESSONS_INDEX = '/course/:courseId/chapters/:chapterId/lessons'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_LESSONS_STORE = '/course/:courseId/chapters/:chapterId/lessons'; // hasRole("INSTRUCTOR")

export const PUBLIC_COURSES_GET_CATEGORIES = '/course/public/categories'; // permitAll
export const PUBLIC_COURSES_GET_EDITOR_CHOICES = '/course/public/editor-choices'; // permitAll
export const PUBLIC_COURSES_SHOW_OVERVIEW = '/course/public/:slug/:courseId/overview'; // permitAll
export const PUBLIC_COURSES_SHOW_CHAPTERS = '/course/public/:slug/:courseId/chapters'; // permitAll

export const INSTRUCTORS_GET_PROFILE = '/instructors/profile/:instructorId'; // hasRole("INSTRUCTOR")
export const INSTRUCTORS_UPDATE_PROFILE = '/instructors/profile'; // hasRole("INSTRUCTOR")
export const INSTRUCTORS_COURSE_GET_STUDENTS = '/enrollment/course-data/:courseId/students';
export const INSTRUCTORS_COURSE_GET_INCOMES = '/finance/instructor-income/course-income/:courseId';

export const INSTRUCTORS_STUDENTS_GET_ALL = '/instructors/students'; // hasRole("INSTRUCTOR")
export const INSTRUCTORS_STUDENTS_GET_BY_ID = '/instructors/students/:studentId'; // hasRole("INSTRUCTOR")

export const INSTRUCTORS_TRANSACTIONS_GET_ALL = '/finance/instructor-sales';
export const INSTRUCTORS_TRANSACTIONS_GET_BY_INCOME_ID = '/finance/instructor-sales/:id';

export const USER_ORDER_PLACE_NEW_ORDER = '/order/place-new-order'; // hasRole("STUDENT")
export const USER_ORDER_GET_DATA = '/order/get-data/:orderId'; // hasRole("STUDENT")
export const USER_ENROLLMENT_GET_ALL = '/enrollment/courses'; // hasRole("STUDENT")
export const USER_ENROLLMENT_GET_BY_ID = '/enrollment/courses/:enrollmentId'; // hasRole("STUDENT")
