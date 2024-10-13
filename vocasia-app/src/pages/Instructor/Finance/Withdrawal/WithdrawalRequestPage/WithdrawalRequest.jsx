import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../../../states/contexts/AuthContext.jsx";
import {getInstructorBalanceData, sendWithdrawalRequest} from "../../../../../services/instructors/finance-service.js";
import {InputField} from "../../../../../components/commons/Input/InputField.jsx";
import {SelectField} from "../../../../../components/commons/Input/SelectField.jsx";
import {TinyMCEField} from "../../../../../components/commons/Input/TinyMCEField.jsx";
import {Link, useNavigate} from "react-router-dom";
import {rupiahFormatter} from "../../../../../utils/utils.js";
import {createCourse} from "../../../Courses/_actions/CourseAction.jsx";

export const WithdrawalRequest = () => {
    const {sweetAlert} = useContext(AuthContext);
    const navigate = useNavigate();

    const [isLoading, setIsLoading] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [isAvailable, setIsAvailable] = useState(false);

    const [overview, setOverview] = useState({});
    const [formData, setFormData] = useState({
        amount: '',
        bank_account_name: '',
        bank_account_number: '',
        bank_name: '',
        remarks: 'Withdrawal request',
    });
    const [errors, setErrors] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                setIsLoading(true);

                const getData = await getInstructorBalanceData();

                setOverview(getData.instructor_balance);
                let availableAmount = getData.instructor_balance.current_balance - getData.instructor_balance.total_pending_withdrawal;

                if (availableAmount > 0) {
                    setIsAvailable(true);
                }

                setFormData({
                    amount: availableAmount,
                })
            } catch (e) {
                console.error(e);
            } finally {
                setIsLoading(false);
            }
        }

        fetchInitialData();
    }, []);

    const handleChange = (e) => {
        const {name, value} = e.target;

        setFormData((prevData) => ({...prevData, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);
        setErrors({});

        try {
            const sendRequest = await sendWithdrawalRequest(formData);

            if (sendRequest.success) {
               await sweetAlert.fire({
                    title: 'Berhasil!',
                    text: sendRequest.message,
                    icon: 'success',
                }).then((isConfirmed) => {
                    if (isConfirmed) {
                        navigate('/instructor/finances/withdrawal');
                    }
               })
            }
        } catch (error) {
            if (error.response) {
                const message = error.response.data.message;

                if (error.response.data.errors) {
                    const errors = error.response.data.errors;

                    if (errors.bankAccountName) {
                        setErrors((prevErrors) => ({
                            ...prevErrors,
                            bank_account_name: errors.bankAccountName,
                        }));
                    }

                    if (errors.bankAccountNumber) {
                        setErrors((prevErrors) => ({
                            ...prevErrors,
                            bank_account_number: errors.bankAccountNumber,
                        }));
                    }

                    if (errors.bankName) {
                        setErrors((prevErrors) => ({
                            ...prevErrors,
                            bank_name: errors.bankName,
                        }));
                    }
                } else {
                    sweetAlert.fire({
                        title: 'Terjadi Kesalahan!',
                        text: message,
                        icon: 'error',
                    });
                }
            }
        } finally {
            setIsSubmitted(false);
        }
    }

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Request Withdrawal</h1>
                    <div className="mt-10">Minta penarikan data</div>
                </div>
            </div>

            <div className="row y-gap-60">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                            <h2 className="text-17 lh-1 fw-500">Informasi Withdrawal</h2>

                            <Link to={'/instructor/finances/withdrawal'} className={'-md -white text-dark-1'}>
                                <i className="fa fa-arrow-left mr-10 ml-10"/>
                                Kembali
                            </Link>
                        </div>

                        <div className="py-30 px-30">
                            {
                                isLoading && (
                                    <div className={'text-center'}>
                                        Loading...
                                    </div>
                                )
                            }

                            {
                                (!isLoading && !isAvailable) && (
                                    <div className={'text-center'}>
                                        <div className="text-red-1">
                                            Maaf, saldo Anda tidak mencukupi untuk melakukan penarikan.
                                        </div>
                                    </div>
                                )
                            }

                            {
                                (!isLoading && isAvailable) && (
                                    <form onSubmit={handleSubmit} className="contact-form row y-gap-30">
                                        {
                                            errors.general && (
                                                <div className="col-12">
                                                    <div className="text-red-1">
                                                        <b>Error:</b> {errors.general}
                                                    </div>
                                                </div>
                                            )
                                        }

                                        <div className="col-12">
                                            <h4 className="text-15 lh-1 fw-500">
                                                Nominal Penarikan Tersedia
                                            </h4>
                                            <p className="mt-2">
                                                {rupiahFormatter.format(formData.amount)}
                                            </p>
                                        </div>

                                        <div className="col-12">
                                            <InputField
                                                label="Nama Bank"
                                                name="bank_name"
                                                placeholder="Nama bank Anda"
                                                value={formData.bank_name}
                                                onChange={handleChange}
                                                error={errors.bank_name}
                                            />
                                        </div>

                                        <div className="row">
                                            <div className="col-12 col-md-6">
                                                <InputField
                                                    label="Nomor Rekening"
                                                    name="bank_account_number"
                                                    placeholder="Nomor rekening bank Anda"
                                                    value={formData.bank_account_number}
                                                    onChange={handleChange}
                                                    error={errors.bank_account_number}
                                                    isRequired={true}
                                                />
                                            </div>
                                            <div className="col-12 col-md-6">
                                                <InputField
                                                    label="Nama Pemilik Rekening"
                                                    name="bank_account_name"
                                                    placeholder="Nama pemilik rekening"
                                                    value={formData.bank_account_name}
                                                    onChange={handleChange}
                                                    error={errors.bank_account_name}
                                                />
                                            </div>
                                        </div>

                                        <div className="row y-gap-20 justify-end pt-15">
                                            <div className="col-auto">
                                                <button className="button -md -purple-1 text-white" disabled={isSubmitted}>
                                                    {
                                                        isSubmitted ? (
                                                            'Tunggu...'
                                                        ) : 'Kirim Permintaan'
                                                    }
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                )
                            }
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}