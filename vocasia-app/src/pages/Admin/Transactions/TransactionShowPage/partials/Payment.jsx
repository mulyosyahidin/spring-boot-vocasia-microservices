import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";

export const Payment = ({activeTab, transaction}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 4 ? "is-active" : ""} `}
        >
            <>
                <h4 className="text-15 lh-1 fw-400">
                    Biaya Layanan
                </h4>
                <p className="mt-2">
                    {formatRupiah(transaction.payment.additional_fee)}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Total Bayar
                </h4>
                <p className="mt-2">
                    {formatRupiah(transaction.payment.total_payment)}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Kadaluarsa Pada
                </h4>
                <p className="mt-2">
                    {formatDate(transaction.payment.payment_expire_at)}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                   Status
                </h4>
                <p className="mt-2">
                    {transaction.payment.payment_status}
                </p>
            </>
        </div>
    )
}