export const Earnings = ({ activeTab, course }) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab == 5 ? "is-active" : ""} `}
        >
            Penghasilan
        </div>
    )
}