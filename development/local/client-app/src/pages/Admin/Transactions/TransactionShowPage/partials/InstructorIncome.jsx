import React, {useEffect, useState} from "react";
import {formatRupiah} from "../../../../../utils/new-utils.js";

export const InstructorIncome = ({activeTab, transaction}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 5 ? "is-active" : ""} `}
        >
            <div className={'table-responsive'}>
                <table className="table table-borderless table-hover table-striped"
                       style={{width: "100%"}}>
                    <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Kursus</th>
                        <th scope="col">Pembayaran User</th>
                        <th scope="col">Pendapatan Instruktur</th>
                        <th scope="col">Remarks</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        transaction.instructor_incomes.map((item, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{item.course.title}</td>
                                <td>{formatRupiah(item.instructor_income.total_user_payment)}</td>
                                <td>{formatRupiah(item.instructor_income.total_instructor_income)}</td>
                                <td>{item.instructor_income.remarks}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                    <tfoot>
                    <tr>
                        <th colSpan="3">Total Pendapatan Instruktur</th>
                        <th>
                            {
                                formatRupiah(
                                    transaction.instructor_incomes.reduce((total, item) =>
                                        total + item.instructor_income.total_instructor_income, 0
                                    )
                                )
                            }
                        </th>
                        <th></th>
                    </tr>
                    </tfoot>
                </table>
            </div>

        </div>
    )
}