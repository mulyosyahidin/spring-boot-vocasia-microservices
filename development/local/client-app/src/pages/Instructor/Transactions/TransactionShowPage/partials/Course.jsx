import {formatRupiah} from "../../../../../utils/new-utils.js";

export const Course = ({activeTab, transaction}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 2 ? "is-active" : ""} `}
        >
            <>
                <h4 className="text-15 lh-1 fw-400">
                    Kursus
                </h4>
                <p className="mt-2">
                    {transaction.course.title}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Harga Penjualan
                </h4>
                <p className="mt-2">
                    {
                        transaction.order_item.course_is_free ? 'Gratis' : (
                            transaction.order_item.course_is_discount ? formatRupiah(transaction.order_item.course_price - transaction.order_item.course_discount) :
                                formatRupiah(transaction.order_item.course_price)
                        )
                    }
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Harga Saat Ini
                </h4>
                <p className="mt-2">
                    {
                        transaction.course.is_free ? 'Gratis' : (
                            transaction.course.is_discount ? formatRupiah(transaction.course.price - transaction.course.discount) :
                                formatRupiah(transaction.course.price)
                        )
                    }
                </p>
            </>
        </div>
    )
}