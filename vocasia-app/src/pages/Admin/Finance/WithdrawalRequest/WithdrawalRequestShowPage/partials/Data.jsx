import React from "react";

export const Data = ({request, isLoading, instructor, activeTab}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 1 ? "is-active" : ""} `}
        >
            {
                !isLoading && (
                    <>
                        <h4 className="text-18 lh-1 fw-500 mb-10">
                            Data Withdrawal
                        </h4>

                        <h4 className="text-15 lh-1 fw-400">
                            Jumlah Withdrawal
                        </h4>
                        <p className="mt-2">
                            {rupiahFormatter.format(request.amount)}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Status
                        </h4>
                        <p className="mt-2">
                            {request.status}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Tanggal Request
                        </h4>
                        <p className="mt-2">
                            {makeDateReadable({date: request.requested_at})}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Nama Bank
                        </h4>
                        <p className="mt-2">
                            {request.bank_name}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Nama Pemilik Rekening
                        </h4>
                        <p className="mt-2">
                            {request.bank_account_name}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Nomor Rekening
                        </h4>
                        <p className="mt-2">
                            {request.bank_account_number}
                        </p>

                        <h4 className="text-18 lh-1 fw-500 mb-10 mt-30">
                            Data User
                        </h4>
                        <h4 className="text-15 lh-1 fw-400">
                            Nama
                        </h4>
                        <p className="mt-2">
                            {instructor.user && instructor.user.name}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Email
                        </h4>
                        <p className="mt-2">
                            {instructor.user && instructor.user.email}
                        </p>
                    </>
                )
            }
        </div>
    );
}