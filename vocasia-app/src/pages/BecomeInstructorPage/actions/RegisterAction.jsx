import {registerInstructor} from "../../../services/instructorService.js";

export const submitInstructorForm = async (formData, setLoading, setErrors, setSuccessMessage, resetFormData) => {
    setLoading(true);
    setErrors({});
    setSuccessMessage('');

    try {
        const response = await registerInstructor(formData);

        if (response.success) {
            setSuccessMessage(response.message);
            resetFormData();
        } else {
            setErrors({ general: response.message });
        }
    } catch (error) {
        if (error.response) {
            const { data } = error.response;
            if (error.response.status === 422) {
                const newErrors = data.errors.reduce((acc, err) => {
                    acc[err.field] = err.message;
                    return acc;
                }, {});
                setErrors(newErrors);
            }
        }
    } finally {
        setLoading(false);
    }
};
