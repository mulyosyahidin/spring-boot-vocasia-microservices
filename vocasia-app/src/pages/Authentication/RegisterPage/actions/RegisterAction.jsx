import { register } from "../../../../services/authenticationService.js";

export const submitRegisterForm = async (formData, setLoading, setErrors, setSuccessMessage) => {
    try {
        const response = await register(formData);

        if (response.success) {
            setSuccessMessage(response.message);

            return response.data;
        } else {
            setErrors({ general: response.message });
        }
    } catch (error) {
        if (error.response) {
            const { data, status } = error.response;
            if (status === 422) {
                const newErrors = data.errors.reduce((acc, err) => {
                    acc[err.field] = err.message;
                    return acc;
                }, {});
                setErrors(newErrors);
            } else if (status === 401) {
                setErrors(prevErrors => ({ ...prevErrors, general: data.message }));
            }
        } else {
            setErrors({ general: 'Terjadi kesalahan. Silakan coba lagi.' });
        }
    } finally {
        setLoading(false);
    }
};
