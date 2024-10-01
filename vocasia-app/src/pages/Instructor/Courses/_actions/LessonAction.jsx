import {getLessonsDataByChapterId, store} from "../../../../services/courses/lesson-service.js";

export const getChapterLessons = async (courseId, chapterId) => {
    try {
        console.log(`[2] getByChapterId(${courseId}, ${chapterId}) started...`);
        const response = await getLessonsDataByChapterId(courseId, chapterId);
        console.log(`[2] getByChapterId(${courseId}, ${chapterId}) response: `, response);

        if (response.success) {
            return response.data;
        }
    } catch (error) {
        console.error(error);
    }
}

export const storeLesson = async (courseId, chapterId, formData, setLoading, setErrors) => {
    try {
        const response = await store(courseId, chapterId, formData);

        if (response.success) {
            return response.data;
        } else {
            setErrors({general: response.message});
        }
    } catch (error) {
        if (error.response) {
            const {data, status} = error.response;

            if (status === 422) {
                const newErrors = data.errors.reduce((acc, err) => {
                    acc[err.field] = err.message;
                    return acc;
                }, {});
                setErrors(newErrors);
            } else {
                setErrors({general: data.message ?? 'Terjadi kesalahan. Silakan coba lagi.'});
            }
        } else {
            setErrors({general: 'Terjadi kesalahan. Silakan coba lagi.'});
        }
    } finally {
        setLoading(false);
    }
}