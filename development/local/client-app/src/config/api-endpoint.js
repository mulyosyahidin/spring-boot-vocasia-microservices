export const AUTHENTICATION_LOGIN = '/authentication/login';
export const AUTHENTICATION_REGISTER = '/authentication/register';

export const AUTHENTICATION_ADMIN_STUDENTS = '/authentication/admin/students';
export const AUTHENTICATION_ADMIN_STUDENTS_GET_BY_ID = '/authentication/admin/students/:userId';

export const COURSE_ADMIN_CATEGORIES = '/course/admin/categories';
export const COURSE_ADMIN_CATEGORIES_ONLY_PARENTS = '/course/admin/categories/only-parents';
export const COURSE_ADMIN_CATEGORIES_STORE = '/course/admin/categories';
export const COURSE_ADMIN_CATEGORIES_GET_BY_ID = '/course/admin/categories/:id';
export const COURSE_ADMIN_CATEGORIES_SYNC = '/course/admin/categories/sync';

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

export const COURSE_STUDENT_GET_BY_ID = '/course/student/courses/:id';
export const COURSE_STUDENT_COURSE_CONTENTS = '/course/student/courses/:id/contents';

export const CATALOG_PUBLIC_CATEGORIES = '/catalog/public/categories/only-parents';

export const CATALOG_PUBLIC_COURSES_LATESTS = '/catalog/public/courses/latests';

export const COURSE_PUBLIC_COURSES_EDITOR_CHOICES = '/course/public/courses/editor-choices';
export const COURSE_PUBLIC_COURSES_GET_BY_SLUG_ID = '/course/public/courses/:slug/:id';
export const COURSE_PUBLIC_COURSES_GET_CONTENTS_BY_SLUG_ID = '/course/public/courses/:slug/:id/contents';

export const COURSE_ADMIN_INSTRUCTOR_COURSES = '/course/admin/instructor-courses';

export const COURSE_ADMIN_COURSES = '/course/admin/courses';
export const COURSE_ADMIN_COURSES_GET_BY_ID = '/course/admin/courses/:courseId';

export const INSTRUCTOR_REGISTER = '/instructor/register';

export const INSTRUCTOR_INSTRUCTOR_STUDENTS = '/instructor/instructor/students';
export const INSTRUCTOR_INSTRUCTOR_STUDENTS_GET_BY_ID = '/instructor/instructor/students/:id';

export const INSTRUCTOR_INSTRUCTOR_PROFILE = '/instructor/instructor/profile';

export const INSTRUCTOR_ADMIN_INSTRUCTORS = '/instructor/admin/instructors';
export const INSTRUCTOR_ADMIN_INSTRUCTORS_GET_BY_ID = '/instructor/admin/instructors/:instructorId';

export const INSTRUCTOR_ADMIN_STUDENTS = '/instructor/admin/students';
export const INSTRUCTOR_ADMIN_STUDENTS_GET_BY_ID = '/instructor/admin/students/:id';

export const INSTRUCTOR_OVERVIEW = '/instructor/overview';

export const ENROLLMENT_INSTRUCTOR_COURSES_STUDENTS = '/enrollment/instructor/courses/:id/students';
export const ENROLLMENT_USER_COURSES_IS_USER_ENROLLED = '/enrollment/student/courses/:id/is-user-enrolled';

export const ENROLLMENT_STUDENT_COURSES = '/enrollment/student/courses';
export const ENROLLMENT_STUDENT_COURSES_GET_BY_ID = '/enrollment/student/courses/:id';

export const ENROLLMENT_STUDENT_PROGRESS_SET_LAST_LESSON_ID = '/enrollment/student/progress/:enrollmentId/set-last-access-lesson';
export const ENROLLMENT_STUDENT_PROGRESS_START_LESSON = '/enrollment/student/progress/:enrollmentId/start-lesson/:lessonId';
export const ENROLLMENT_STUDENT_PROGRESS_COMPLETE_LESSON = '/enrollment/student/progress/:enrollmentId/complete-lesson/:lessonId';
export const ENROLLMENT_STUDENT_PROGRESS_IS_LESSON_COMPLETE = '/enrollment/student/progress/:enrollmentId/is-lesson-complete/:lessonId';

export const ENROLLMENT_ADMIN_STUDENT_COURSES = '/enrollment/admin/student/courses';

export const FINANCE_INSTRUCTOR_TRANSACTIONS = '/finance/instructor/transactions';
export const FINANCE_INSTRUCTOR_TRANSACTIONS_GET_BY_ID = '/finance/instructor/transactions/:id';
export const FINANCE_INSTRUCTOR_WITHDRAWAL = '/finance/instructor/withdrawal';
export const FINANCE_INSTRUCTOR_WITHDRAWAL_STORE = '/finance/instructor/withdrawal';

export const FINANCE_INSTRUCTOR_BALANCE_DATA = '/finance/instructor/balance/data';
export const FINANCE_INSTRUCTOR_COURSES_INCOME = '/finance/instructor/courses/:id/income';

export const FINANCE_ADMIN_WITHDRAWAL_REQUEST = '/finance/admin/withdrawal/request';
export const FINANCE_ADMIN_WITHDRAWAL_REQUEST_GET_BY_ID = '/finance/admin/withdrawal/request/:id';
export const FINANCE_ADMIN_WITHDRAWAL_REQUEST_PROCESS_BY_ID = '/finance/admin/withdrawal/request/:id/process';

export const FINANCE_ADMIN_BALANCE_DATA = '/finance/admin/balance/data';

export const FINANCE_ADMIN_TRANSACTIONS = '/finance/admin/transactions';
export const FINANCE_ADMIN_INSTRUCTOR_BALANCE_DATA = '/finance/admin/instructor/balance/data';
export const FINANCE_ADMIN_INSTRUCTOR_WITHDRAWAL_REQUEST = '/finance/admin/instructor/withdrawal/request';

export const ORDER_STUDENT_ORDERS = '/order/student/orders';
export const ORDER_STUDENT_PLACE_NEW_ORDER = '/order/student/place-new-order';
export const ORDER_STUDENT_ORDERS_GET_BY_ID = '/order/student/orders/:id';

export const ORDER_ADMIN_TRANSACTIONS = '/order/admin/transactions';
export const ORDER_ADMIN_TRANSACTIONS_FIND_BY_ID = '/order/admin/transactions/:id';
export const ORDER_ADMIN_STUDENT_TRANSACTIONS = '/order/admin/student/transactions';

export const QNA_STUDENT_IS_I_ASK_THIS_LESSON = '/qna/student/is-i-ask-this-lesson/:lessonId';
export const QNA_STUDENT_QUESTIONS_GET_ALL = '/qna/student/questions/:courseId/:lessonId';
export const QNA_STUDENT_QUESTIONS_STORE = '/qna/student/questions/:courseId/:lessonId';
export const QNA_STUDENT_QUESTIONS_ANSWERS_GET_ALL = '/qna/student/questions/:lessonId/qna/:qnaId';
export const QNA_STUDENT_QUESTIONS_ANSWERS_STORE = '/qna/student/questions/:lessonId/qna/:qnaId';

export const QNA_INSTRUCTOR_COURSES_QUESTIONS = '/qna/instructor/courses/:courseId/questions';
export const QNA_INSTRUCTOR_COURSES_QUESTIONS_QNA = '/qna/instructor/courses/:courseId/questions/:qnaId';
export const QNA_INSTRUCTOR_COURSES_QUESTIONS_QNA_STORE = '/qna/instructor/courses/:courseId/questions/:qnaId';
