export const Students = ({ activeTab, course }) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab == 3 ? "is-active" : ""} `}
        >
            Students
        </div>
    )
}