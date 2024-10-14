export const AUTHENTICATION_LOGIN = '/authentication/login';

export const COURSE_ADMIN_CATEGORIES = '/course/admin/categories';
export const COURSE_ADMIN_CATEGORIES_ONLY_PARENTS = '/course/admin/categories/only-parents';
export const COURSE_ADMIN_CATEGORIES_STORE = '/course/admin/categories';
export const COURSE_ADMIN_CATEGORIES_GET_BY_ID = '/course/admin/categories/:id';

export const COURSE_INSTRUCTOR_CATEGORIES = '/course/instructor/categories';
export const COURSE_INSTRUCTOR_COURSES = '/course/instructor/courses';
export const COURSE_INSTRUCTOR_COURSES_GET_BY_ID = '/course/instructor/courses/:id';
export const COURSE_INSTRUCTOR_COURSES_THUMBNAIL = '/course/instructor/courses/:id/thumbnail';
export const COURSE_INSTRUCTOR_COURSES_PUBLISH = '/course/instructor/courses/:id/publish';

export const COURSE_INSTRUCTOR_COURSES_GET_ALL = '/course/instructor/courses/all';
export const COURSE_INSTRUCTOR_COURSES_GET_DRAFT = '/course/instructor/courses/draft';
export const COURSE_INSTRUCTOR_COURSES_GET_PUBLISHED = '/course/instructor/courses/published';

export const COURSE_INSTRUCTOR_COURSES_CHAPTERS = '/course/instructor/courses/:id/chapters';
export const COURSE_INSTRUCTOR_COURSES_DELETE_BY_ID = '/course/instructor/courses/:id/chapters/:chapterId';
export const COURSE_INSTRUCTOR_COURSES_FIND_BY_ID = '/course/instructor/courses/:id/chapters/:chapterId';

export const COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS = '/course/instructor/courses/:courseId/chapters/:chapterId/lessons';
export const COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_CREATE = '/course/instructor/courses/:courseId/chapters/:chapterId/lessons';
export const COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_FIND_BY_ID = '/course/instructor/courses/:courseId/chapters/:chapterId/lessons/:lessonId';
export const COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_UPDATE = '/course/instructor/courses/:courseId/chapters/:chapterId/lessons/:lessonId';
export const COURSE_INSTRUCTOR_COURSES_CHAPTERS_LESSONS_DELETE = '/course/instructor/courses/:courseId/chapters/:chapterId/lessons/:lessonId';

export const INSTRUCTOR_INSTRUCTOR_STUDENTS = '/instructor/instructor/students';
export const INSTRUCTOR_INSTRUCTOR_STUDENTS_GET_BY_ID = '/instructor/instructor/students/:id';

export const INSTRUCTOR_INSTRUCTOR_PROFILE = '/instructor/instructor/profile';

export const ENROLLMENT_INSTRUCTOR_COURSES_STUDENTS = '/enrollment/instructor/courses/:id/students';

export const FINANCE_INSTRUCTOR_TRANSACTIONS = '/finance/instructor/transactions';
export const FINANCE_INSTRUCTOR_TRANSACTIONS_GET_BY_ID = '/finance/instructor/transactions/:id';
export const FINANCE_INSTRUCTOR_WITHDRAWAL = '/finance/instructor/withdrawal';
export const FINANCE_INSTRUCTOR_WITHDRAWAL_STORE = '/finance/instructor/withdrawal';

export const FINANCE_INSTRUCTOR_BALANCE_DATA = '/finance/instructor/balance/data';

export const FINANCE_INSTRUCTOR_COURSES_INCOME = '/finance/instructor/courses/:id/income';
