import {makeDateReadable, rupiahFormatter} from "../../../../../utils/utils.js";

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
                    {makeDateReadable({date: transaction.income.created_at})}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Remarks
                </h4>
                <p className="mt-2">
                    {transaction.income.remarks}
                </p>
            </>
        </div>
    )
}