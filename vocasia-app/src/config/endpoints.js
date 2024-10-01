export const AUTH_LOGIN = '/auth/login'; // permitAll
export const INSTRUCTOR_REGISTER = '/instructors/register'; // permitAll

export const COURSES_GET_DRAFT = '/course/courses/draft'; // hasAnyRole("INSTRUCTOR", "ADMIN")

export const COURSES_CREATE_NEW_COURSE = '/course/courses'; // hasRole("INSTRUCTOR")
export const COURSES_UPLOAD_THUMBNAIL = '/course/courses/:id/thumbnail'; // hasRole("INSTRUCTOR")
export const COURSES_UPDATE = '/course/courses/:id'; // hasRole("INSTRUCTOR")
export const COURSES_SHOW = '/course/courses/:id'; // permitAll
export const COURSES_CATEGORIES_GETALL = '/course/categories'; // permitAll

export const COURSES_CHAPTERS_GETALL = '/course/:courseId/chapters'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_CREATE = '/course/:courseId/chapters'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_UPDATE = '/course/:courseId/chapters/:chapterId'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_DELETE = '/course/:courseId/chapters/:chapterId'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_SHOW = '/course/:courseId/chapters/:chapterId'; // permitAll

export const COURSES_CHAPTERS_LESSONS_INDEX = '/course/:courseId/chapters/:chapterId/lessons'; // hasRole("INSTRUCTOR")
export const COURSES_CHAPTERS_LESSONS_STORE = '/course/:courseId/chapters/:chapterId/lessons'; // hasRole("INSTRUCTOR")
