import React, {useEffect, useState} from "react";
import {formatRupiah} from "../../../../../utils/new-utils.js";

export const PlatformIncome = ({activeTab, transaction}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 6 ? "is-active" : ""} `}
        >
            <div className={'table-responsive'}>
                <table className="table table-borderless table-hover table-striped"
                       style={{width: "100%"}}>
                    <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Kursus</th>
                        <th scope="col">Pembayaran User</th>
                        <th scope="col">Fee Platform</th>
                        <th scope="col">Remarks</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        transaction.platform_incomes.map((item, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{item.course.title}</td>
                                <td>{formatRupiah(item.platform_income.total_user_payment)}</td>
                                <td>{formatRupiah(item.platform_income.total_platform_income)} ({item.platform_income.platform_fee_in_percent}%)</td>
                                <td>{item.platform_income.remarks}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                    <tfoot>
                    <tr>
                        <th colSpan="3">Total Fee Platform</th>
                        <th colSpan="2">
                            {
                                formatRupiah(
                                    transaction.platform_incomes.reduce((total, item) =>
                                        total + item.platform_income.total_platform_income, 0
                                    )
                                )
                            }
                        </th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    )
}