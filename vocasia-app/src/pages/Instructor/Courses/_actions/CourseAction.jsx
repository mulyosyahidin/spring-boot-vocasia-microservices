import {index, show, store, uploadThumbnail} from "../../../../services/courses/courseService.js";

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

export const getAllCourses = async () => {
    try {
        const response = await index();

        if (response.success) {
            return response.data;
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