import {registerInstructor} from "../../../services/instructorService.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

export const submitInstructorForm = async (formData, setLoading, setErrors, setSuccessMessage, resetFormData) => {
    setLoading(true);
    setErrors({});
    setSuccessMessage('');

    try {
        const response = await registerInstructor(formData);

        if (response.success) {
            setSuccessMessage(response.message);
            resetFormData();

            await withReactContent(Swal).fire({
                title: 'Berhasil!',
                text: 'Berhasil melakukan pendaftaran. Silahkan login ke akun Anda.',
                icon: 'success',
                confirmButtonText: 'OK'
            })
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
