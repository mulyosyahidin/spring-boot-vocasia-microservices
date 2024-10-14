import React, {useContext, useEffect, useState} from "react";
import {InputField} from "../../../../../../components/commons/Input/InputField.jsx";
import {AuthContext} from "../../../../../../states/contexts/AuthContext.jsx";
import {TextAreaField} from "../../../../../../components/commons/Input/TextAreaField.jsx";
import {InputFileField} from "../../../../../../components/commons/Input/InputFileField.jsx";
import {useParams} from "react-router-dom";

export const Actions = ({request, process, isLoading, activeTab}) => {
    const {sweetAlert} = useContext(AuthContext);
    const {id} = useParams();

    const [isSubmitted, setIsSubmitted] = useState(false);

    const [errors, setErrors] = useState({});
    const [formData, setFormData] = useState({
        proof_document: '',
        amount: '',
        note: '',
    });

    useEffect(() => {
        if (request) {
            setFormData({
                amount: request.amount,
            });
        }
    }, [id]);

    const handleChange = (e) => {
        if (e.target.type === 'file') {
            setFormData({
                ...formData,
                [e.target.name]: e.target.files[0],
            });
        } else {
            setFormData({
                ...formData,
                [e.target.name]: e.target.value,
            });
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);

        try {
            const formDataToSend = new FormData();

            for (let key in formData) {
                formDataToSend.append(key, formData[key]);
            }

            const processWithdrawal = await processWithdrawalRequest(id, formDataToSend);

            if (processWithdrawal.success) {
                sweetAlert.fire({
                    icon: 'success',
                    title: 'Berhasil!',
                    text: processWithdrawal.message,
                }).then((isConfirmed) => {
                    if (isConfirmed) {
                        window.location.reload();
                    }
                })
            }
            else {
                sweetAlert.fire({
                    icon: 'error',
                    title: 'Terjadi Kesalahan!',
                    text: processWithdrawal.message,
                });
            }
        }
        catch (error) {
            console.log(error);

            if (error.message) {
                let msg = error.message;
                if (error.data.error) {
                    msg += ' : ' + error.data.error;
                }

                sweetAlert.fire({
                    icon: 'error',
                    title: 'Terjadi Kesalahan!',
                    text: msg,
                });
            }
        }
        finally {
            setIsSubmitted(false);
        }
    }

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 2 ? "is-active" : ""} `}
        >
            {
                (!isLoading && request.status === 'PENDING') && (
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
                            <InputField
                                label="Nominal"
                                name="amount"
                                placeholder="Nominal dibayar"
                                value={formData.amount}
                                onChange={handleChange}
                                error={errors.amount}
                                isRequired={true}
                            />
                        </div>

                        <div className="col-12">
                            <InputFileField
                                label="Dokumen Pengiriman"
                                name="proof_document"
                                placeholder="Dokumen Bukti Pengiriman"
                                onChange={handleChange}
                                error={errors.proof_document}
                            />
                        </div>

                        <div className="col-12">
                            <TextAreaField
                                label="Catatan"
                                name="note"
                                placeholder="Catatan"
                                value={formData.note}
                                onChange={handleChange}
                                error={errors.note}
                                isRequired={true}
                            />
                        </div>

                        <div className="row y-gap-20 justify-end pt-15">
                            <div className="col-auto">
                                <button className="button -md -purple-1 text-white" disabled={isSubmitted}>
                                    {
                                        isSubmitted ? (
                                            'Memproses...'
                                        ) : 'Proses'
                                    }
                                </button>
                            </div>
                        </div>

                    </form>
                )
            }

            {
                (!isLoading && request.status === 'PAID') && (
                    <>
                        <h4 className="text-15 lh-1 fw-400">
                            Jumlah Pembayaran
                        </h4>
                        <p className="mt-2">
                            {rupiahFormatter.format(process.amount)}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Tanggal
                        </h4>
                        <p className="mt-2">
                            {makeDateReadable({date: process.processed_at})}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Catatan
                        </h4>
                        <p className="mt-2">
                            {process.note}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Dokumen Pembayaran
                        </h4>
                        <p className="mt-2">
                            <a href={process.proof_document_url} className={'text-blue'} target={"_blank"}>{process.proof_document}</a>
                        </p>
                    </>
                )
            }
        </div>
    )
}