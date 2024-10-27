import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {findById} from "../../../../../services/new/instructor/admin/instructor-service.js";

export const Data = ({activeTab}) => {
    const {instructorId} = useParams();

    const [isLoading, setIsLoading] = useState(true);

    const [instructor, setInstructor] = useState();
    const [user, setUser] = useState();

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findInstructorById = await findById(instructorId);

                if (findInstructorById.success) {
                    setInstructor(findInstructorById.data.instructor);
                    setUser(findInstructorById.data.user);
                }

                setIsLoading(false);
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
    }, []);

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
                        <h4 className="text-18 lh-1 fw-500">
                            Nama
                        </h4>
                        <p className="mt-2">
                            {user.name}
                        </p>

                        <h4 className="text-18 lh-1 fw-500" style={{marginTop: '15px'}}>
                            Email
                        </h4>
                        <p className="mt-2">
                            {user.email}
                        </p>

                        <h4 className="text-18 lh-1 fw-500" style={{marginTop: '15px'}}>
                            No. HP
                        </h4>
                        <p className="mt-2">
                            {instructor.phone_number}
                        </p>

                        <h4 className="text-18 lh-1 fw-500" style={{marginTop: '15px'}}>
                            Tentang Instruktur
                        </h4>
                        <p className="mt-15">
                            <div
                                dangerouslySetInnerHTML={{__html: instructor.summary}}
                            />
                        </p>
                    </>
                )
            }
        </div>
    );
}