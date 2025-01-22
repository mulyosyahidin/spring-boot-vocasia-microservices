import React, {useEffect, useState} from "react";
import {findById} from "../../../../../services/new/authentication/admin/student-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {useParams} from "react-router-dom";
import {formatDate} from "../../../../../utils/new-utils.js";

export const Data = ({activeTab}) => {
    const {userId} = useParams();

    const [isLoading, setIsLoading] = useState(true);
    const [user, setUser] = useState({});

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findStudentById = await findById(userId);

                if (findStudentById.success) {
                    setUser(findStudentById.data.user);

                    setIsLoading(false);
                }
            } catch (error) {
                console.error(error);

                if (error.message) {
                    let msg = error.message;
                    if (error.data.error) {
                        msg += ' : ' + error.data.error;
                    }

                    await withReactContent(Swal).fire({
                        title: 'Terjadi Kesalahan!',
                        text: msg,
                        icon: 'error',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        })
                }
            }
        }

        fetchInitialData();
    }, [userId]);

    return (
        <div
            className={`tabs__pane -tab-item-1 ${activeTab === 1 ? "is-active" : ""} `}
        >
            {
                isLoading && (
                    <>
                        <span>
                            <i className={'fa fa-spinner fa-spin mr-5'}></i>
                            <strong role="status">Loading...</strong>
                        </span>
                    </>
                )
            }

            {
                !isLoading && (
                    <>
                        <h4 className="text-15 lh-1 fw-400">
                            User ID
                        </h4>
                        <p className="mt-2">
                            {user.id}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Nama
                        </h4>
                        <p className="mt-2">
                            {user.name}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Email
                        </h4>
                        <p className="mt-2">
                            {user.email}
                        </p>

                        <h4 className="text-15 lh-1 fw-400" style={{marginTop: '15px'}}>
                            Terdaftar Pada
                        </h4>
                        <p className="mt-2">
                            {formatDate(user.created_at)}
                        </p>
                    </>
                )
            }

        </div>
    );
}