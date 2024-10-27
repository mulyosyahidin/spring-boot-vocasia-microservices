import React from "react";

export const Data = ({activeTab, student}) => {
    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 1 ? "is-active" : ""} `}
        >

            <h4 className="text-15 lh-1 fw-400">
                User ID
            </h4>
            <p className="mt-2">
                {student.id}
            </p>

            <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                Nama
            </h4>
            <p className="mt-2">
                {student.name}
            </p>

            <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                Email
            </h4>
            <p className="mt-2">
                {student.email}
            </p>

        </div>
    );
}