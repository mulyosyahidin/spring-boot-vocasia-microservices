export const QnA = ({ activeTab, course }) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab == 4 ? "is-active" : ""} `}
        >
            Tanya Jawab
        </div>
    )
}