import {makeDateReadable, rupiahFormatter} from "../../../../../utils/utils.js";

export const Income = ({activeTab, transaction}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 4 ? "is-active" : ""} `}
        >
            <>
                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Harga Penjualan
                </h4>
                <p className="mt-2">
                    {rupiahFormatter.format(transaction.order_item.course_subtotal)}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Potongan Platform
                </h4>
                <p className="mt-2">
                    {rupiahFormatter.format(transaction.income.total_platform_fee)} ({transaction.income.platform_fee_in_percent}%)
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Pendapatan Saya
                </h4>
                <p className="mt-2">
                    {rupiahFormatter.format(transaction.income.total_instructor_income)}
                </p>
            </>
        </div>
    )
}