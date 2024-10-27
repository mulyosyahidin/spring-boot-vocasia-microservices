import React from "react";
import {Link} from "react-router-dom";
import {formatRupiah} from "../../../../../../utils/new-utils.js";

export const Courses = ({activeTab, courses}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 2 ? "is-active" : ""} `}
        >
            <div className={'table-responsive'}>
                <table className="table table-borderless table-hover table-striped"
                       style={{width: "100%"}}>
                    <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Judul</th>
                        <th scope="col">Order</th>
                        <th scope="col">Harga</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        courses.map((item, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{item.course.title}</td>
                                <td>#{item.order.order_number}</td>
                                <td>{formatRupiah(item.order_item.course_subtotal)}</td>
                                <td>
                                    <Link to={`/admin/transactions/${item.order.id}`}>
                                        Detail
                                    </Link>
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        </div>
    );
}