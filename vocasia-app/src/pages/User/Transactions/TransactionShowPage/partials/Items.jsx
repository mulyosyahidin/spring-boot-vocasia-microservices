import {rupiahFormatter} from "../../../../../utils/utils.js";
import React from "react";

export const Items = ({activeTab, items, isLoading}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 2 ? "is-active" : ""} `}
        >
            {
                !isLoading && (
                    <>
                        <div className={'table-responsive'}>
                            <table className="table table-borderless table-hover table-striped"
                                   style={{width: "100%"}}>
                                <thead>
                                <tr>
                                    <th scope="col">No</th>
                                    <th scope="col">Judul</th>
                                    <th scope="col">Harga</th>
                                    <th scope="col">Diskon</th>
                                    <th scope="col">Sub Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    items.map((item, index) => {
                                        return (
                                            <tr key={item.id}>
                                                <td>{index+1}</td>
                                                <td>{item.course_title}</td>
                                                <td>
                                                    {
                                                        item.course_is_free ? 'Gratis' : rupiahFormatter.format(item.course_price)
                                                    }
                                                </td>
                                                <td>
                                                    {
                                                        item.course_is_free ? '-' : (
                                                            item.course_is_discount ? rupiahFormatter.format(item.course_discount) : '-'
                                                        )
                                                    }
                                                </td>
                                                <td>{rupiahFormatter.format(item.course_subtotal)}</td>
                                            </tr>
                                        )
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </>
                )
            }
        </div>
    );
}