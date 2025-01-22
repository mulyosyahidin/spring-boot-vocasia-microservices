export const Student = ({activeTab, transaction}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 3 ? "is-active" : ""} `}
        >
            <>
                <h4 className="text-15 lh-1 fw-400">
                    Nama
                </h4>
                <p className="mt-2">
                    {transaction.user.name}
                </p>

                <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                    Email
                </h4>
                <p className="mt-2">
                    {transaction.user.email}
                </p>
            </>
        </div>
    )
}