import {formatDate, formatRupiah} from "../../../../../utils/new-utils.js";
import React from "react";

export const Course = ({activeTab, transaction}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 2 ? "is-active" : ""} `}
        >
            <>
                <div className={'table-responsive'}>
                    <table className="table table-borderless table-hover table-striped"
                           style={{width: "100%"}}>
                        <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Judul</th>
                            <th scope="col">Harga</th>
                            <th scope="col">Gratis / Diskon</th>
                            <th scope="col">Subtotal</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            transaction.items.map((item, index) => (
                                <tr key={index}>
                                    <td>{index + 1}</td>
                                    <td>{item.course_title}</td>
                                    <td>{formatRupiah(item.course_price)}</td>
                                    <td>
                                        {item.is_free ? 'Ya' : 'Tidak'}
                                        /
                                        {item.course_is_discount ? formatRupiah(item.course_discount) : 'Tidak'}
                                    </td>
                                    <td>{formatRupiah(item.course_subtotal)}</td>
                                </tr>
                            ))
                        }
                        </tbody>
                    </table>
                </div>
            </>
        </div>
    )
}