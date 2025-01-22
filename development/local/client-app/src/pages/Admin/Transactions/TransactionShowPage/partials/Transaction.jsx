import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";

export const Transaction = ({activeTab, transaction}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 1 ? "is-active" : ""} `}
        >
            <>
                <h4 className="text-15 lh-1 fw-400">
                    Order
                </h4>
                <p className="mt-2">
                    #{transaction.order.order_number}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Tanggal
                </h4>
                <p className="mt-2">
                    {formatDate(transaction.order.created_at)}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Jumlah Kursus
                </h4>
                <p className="mt-2">
                    {transaction.order.total_items}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Total Harga
                </h4>
                <p className="mt-2">
                    {formatRupiah(transaction.order.total_price)}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Total Diskon
                </h4>
                <p className="mt-2">
                    {formatRupiah(transaction.order.total_discount)}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Status Pembayaran
                </h4>
                <p className="mt-2">
                    {transaction.order.payment_status}
                </p>
            </>
        </div>
    )
}