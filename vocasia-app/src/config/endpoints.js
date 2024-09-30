export const AUTH_LOGIN = '/auth/login';
export const INSTRUCTOR_REGISTER = '/instructors/register';

export const COURSES_GET_DRAFT = '/course/get/draft';

export const COURSES_CREATE_NEW_COURSE = '/course/create-courses/new';
export const COURSES_UPLOAD_THUMBNAIL = '/course/create-courses/:id/update-thumbnail';
export const COURSES_UPDATE = '/course/update-course/:id';
export const COURSES_SHOW = '/course/data/:id';
export const COURSES_CATEGORIES_GETALL = '/course/categories';

export const COURSES_CHAPTERS_GETALL = '/course/chapters/:courseId/get-all-chapters';
export const COURSES_CHAPTERS_CREATE = '/course/chapters/:courseId/add-chapter';
export const COURSES_CHAPTERS_UPDATE = '/course/chapters/:courseId/update-chapter/:chapterId';
export const COURSES_CHAPTERS_DELETE = '/course/chapters/:courseId/delete-chapter/:chapterId';