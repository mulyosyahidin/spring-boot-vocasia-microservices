import {
    getDataByStatus,
    show,
    update,
    store,
    uploadThumbnail,
    getAllChaptersByCourseId, addNewChapter, editChapterData, deleteChapterData, getChapterDataById
} from "../../../../services/courses/courseService.js";

export const findCourseById = async (id) => {
    try {
        const response = await show(id);

        if (response.success) {
            return response.data;
        }
    } catch (error) {
        console.log(error)
    }
}

export const getAllCourses = async (active) => {
    try {
        const response = await getDataByStatus(active);

        if (response.success) {
            return response.data.courses;
        }
    } catch (error) {
        console.error(error)
    }
}

export const createCourse = async (formData, setLoading, setErrors) => {
    try {
        const response = await store(formData);

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

export const updateThumbnail = async (id, formData, setLoading, setErrors) => {
    try {
        const response = await uploadThumbnail({id, formData});

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

export const updateCourse = async (id, formData, setLoading, setErrors) => {
    try {
        const response = await update(id, formData);

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

export const getChaptersByCourseId = async (courseId) => {
    try {
        const response = await getAllChaptersByCourseId(courseId);

        if (response.success) {
            return response.data.chapters;
        }
    } catch (error) {
        console.error(error)
    }
}

export const addChapter = async (courseId, formData, setLoading, setErrors) => {
    try {
        const response = await addNewChapter(courseId, formData);

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

export const updateChapter = async (courseId, chapterId, formData, setLoading, setErrors) => {
    try {
        const response = await editChapterData(courseId,chapterId, formData);

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

export const deleteChapter = async (courseId, chapterId) => {
    try {
        const response = await deleteChapterData(courseId, chapterId);

        if (response.success) {
            return response;
        }
    } catch (error) {
        console.error(error)
    }
}

export const getChapterById = async (courseId, chapterId) => {
    try {
        const response = await getChapterDataById(courseId, chapterId);

        if (response.success) {
            return response.data;
        }
    } catch (error) {
        console.error(error)
    }
}