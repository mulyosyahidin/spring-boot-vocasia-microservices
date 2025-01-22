import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {INSTRUCTOR} from "../../../../../config/consts.js";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {Link, useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {InputField} from "../../../../../components/commons/Input/InputField.jsx";
import {findBalanceData} from "../../../../../services/new/finance/instructor/balance-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {saveWithdrawal} from "../../../../../services/new/finance/instructor/withdrawal-service.js";
import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";

const metaData = {
    title: 'Riwayat Withdrawal',
};

export const WithdrawalRequestPage = () => {
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
            setIsLoading(true);

            try {
                const findMyBalanceData = await findBalanceData();

                if (findMyBalanceData.success) {
                    const balanceData = findMyBalanceData.data.instructor_balance;
                    let availableAmount = balanceData.current_balance - balanceData.total_pending_withdrawal;

                    if (availableAmount > 0) {
                        setIsAvailable(true);
                    }

                    setFormData({
                        amount: availableAmount,
                    })

                    setIsLoading(false);
                }
            } catch (error) {
                console.error(error);

                if (error.message) {
                    let msg = error.message;
                    if (error.data.error) {
                        msg += ' : ' + error.data.error;
                    }

                    await withReactContent(Swal).fire({
                        title: 'Terjadi Kesalahan!',
                        text: msg,
                        icon: 'error',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        });
                }
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
            const doSaveWithdrawal = await saveWithdrawal(formData);

            if (doSaveWithdrawal.success) {
                await withReactContent(Swal).fire({
                    icon: 'success',
                    title: 'Berhasil!',
                    text: doSaveWithdrawal.message,
                })
                    .then((isConfirmed) => {
                        if (isConfirmed) {
                            navigate('/instructor/finances/withdrawal');
                        }
                    });
            }
        } catch (error) {
            console.error(error);

            if (error.errors) {
                const getErrors = error.errors;
                const newErrors = {};

                Object.keys(getErrors).forEach((field) => {
                    newErrors[field] = getErrors[field][0];
                });

                setErrors(newErrors);
                setIsSubmitted(false);
            } else if (error.data) {
                let message = error.message;

                if (error.data.error) {
                    message += `: (${error.data.error})`;
                }

                await withReactContent(Swal).fire({
                    icon: 'error',
                    title: 'Terjadi Kesalahan!',
                    text: message,
                })
                    .then((isConfirmed) => {
                        if (isConfirmed) {
                            window.location.reload();
                        }
                    });
            } else {
                if (error.message) {
                    setErrors({general: error.message});

                    setIsSubmitted(false);
                }
            }
        }
    }

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
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
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Informasi Withdrawal</h2>

                                    <Link to={'/instructor/finances/withdrawal'} className={'-md -white text-dark-1'}>
                                        <i className="fa fa-arrow-left mr-10 ml-10"/>
                                        Kembali
                                    </Link>
                                </div>

                                <div className="py-30 px-30">
                                    {
                                        isLoading && (
                                            <>
                                                <span>
                                                    <i className={'fa fa-spinner fa-spin mr-5'}></i>
                                                    <strong role="status">Loading...</strong>
                                                </span>
                                            </>
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
                                                        {formatRupiah(formData.amount)}
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
                                                        <button className="button -md -purple-1 text-white"
                                                                disabled={isSubmitted}>
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
            </InstructorWrapper>
        </Wrapper>
    );
}