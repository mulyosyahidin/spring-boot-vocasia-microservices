import React from "react";
import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";

export const Order = ({activeTab, order, isLoading}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 1 ? "is-active" : ""} `}
        >
            {
                !isLoading && (
                    <>
                        <h4 className="text-15 lh-1 fw-400">
                            Order ID
                        </h4>
                        <p className="mt-2">
                            #{order.order_number}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Jumlah Item
                        </h4>
                        <p className="mt-2">
                            {order.total_items}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Total Harga
                        </h4>
                        <p className="mt-2">
                            {formatRupiah(order.total_price)}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Tanggal
                        </h4>
                        <p className="mt-2">
                            {formatDate(order.created_at)}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Status Pembayaran
                        </h4>
                        <p className="mt-2">
                            {order.payment_status}
                        </p>
                    </>
                )
            }
        </div>
    );
}