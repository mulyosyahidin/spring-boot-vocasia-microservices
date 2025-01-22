import {ADMIN} from "../../../../config/consts.js";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import React, {useEffect, useState} from "react";
import {AdminWrapper} from "../../../../components/Admin/AdminWrapper/Index.jsx";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Link, useParams} from "react-router-dom";
import {
    approveSubmission,
    findById,
    rejectSubmission
} from "../../../../services/new/instructor/admin/instructor-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {formatDate} from "../../../../utils/new-utils.js";

const metaData = {
    title: 'Pengajuan Instruktur',
}

export const SubmissionShowPage = () => {
    const {id} = useParams();

    const [isLoading, setIsLoading] = useState(true);

    const [instructor, setInstructor] = useState();
    const [user, setUser] = useState();

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findInstructorById = await findById(id);

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
    }, [id]);

    const confirmApprove = async () => {
        const confirm = await withReactContent(Swal).fire({
            icon: 'question',
            title: 'Yakin Ingin Menerima?',
            text: 'Setelah diterima, instruktur dapat membuat kursus dan mempublikasikannya di platform',
            showCancelButton: true,
            confirmButtonText: 'Ya, Terima',
            cancelButtonText: 'Batal',
        });

        if (confirm.isConfirmed) {
            try {
                const doApprove = await approveSubmission(id);

                if (doApprove.success) {
                    await withReactContent(Swal).fire({
                        icon: 'success',
                        title: 'Berhasil!',
                        text: 'Pengajuan instruktur berhasil diterima',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        });
                }
            } catch (error) {
                console.error(error);

                if (error.data) {
                    let message = error.message;

                    if (error.data.error) {
                        message += `: (${error.data.error})`;
                    }

                    await withReactContent(Swal).fire({
                        icon: 'error',
                        title: 'Terjadi Kesalahan!',
                        text: message,
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        });
                } else {
                    if (error.message) {
                        await withReactContent(Swal).fire({
                            icon: 'error',
                            title: 'Terjadi Kesalahan!',
                            text: error.message,
                        })
                            .then((isConfirmed) => {
                                if (isConfirmed) {
                                    window.location.reload();
                                }
                            });
                    }
                }
            }
        }
    }

    const confirmReject = async () => {
        const confirm = await withReactContent(Swal).fire({
            icon: 'question',
            title: 'Yakin Ingin Menolak?',
            text: 'Jika ditolak, instruktur tidak dapat membuat kursus dan mempublikasikannya di platform',
            showCancelButton: true,
            confirmButtonText: 'Ya, Tolak',
            cancelButtonText: 'Batal',
        });

        if (confirm.isConfirmed) {
            try {
                const doApprove = await rejectSubmission(id);

                if (doApprove.success) {
                    await withReactContent(Swal).fire({
                        icon: 'success',
                        title: 'Berhasil!',
                        text: 'Pengajuan instruktur berhasil ditolak',
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        });
                }
            } catch (error) {
                console.error(error);

                if (error.data) {
                    let message = error.message;

                    if (error.data.error) {
                        message += `: (${error.data.error})`;
                    }

                    await withReactContent(Swal).fire({
                        icon: 'error',
                        title: 'Terjadi Kesalahan!',
                        text: message,
                    })
                        .then((isConfirmed) => {
                            if (isConfirmed) {
                                window.location.reload();
                            }
                        });
                } else {
                    if (error.message) {
                        await withReactContent(Swal).fire({
                            icon: 'error',
                            title: 'Terjadi Kesalahan!',
                            text: error.message,
                        })
                            .then((isConfirmed) => {
                                if (isConfirmed) {
                                    window.location.reload();
                                }
                            });
                    }
                }
            }
        }
    }

    return (
        <Wrapper needAuth role={ADMIN}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <AdminWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Pengajuan Instruktur</h1>
                            <div className="mt-10">Data pengajuan instruktur</div>
                        </div>
                    </div>

                    <div className="row y-gap-60">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex justify-content-between items-center py-20 px-30 border-bottom-light">
                                    <h2 className="text-17 lh-1 fw-500">Pengajuan Instruktur</h2>

                                    <Link to={'/admin/instructors/submissions'} className={'-md -white text-dark-1'}>
                                        <i className="fa fa-arrow-left mr-10 ml-10"/>
                                        Kembali
                                    </Link>
                                </div>

                                <div className="py-30 px-30">

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
                                                    Nama
                                                </h4>
                                                <p className="mt-2">
                                                    {user.name}
                                                </p>

                                                <h4 className="text-15 lh-1 fw-400 mt-20">
                                                    Email
                                                </h4>
                                                <p className="mt-2">
                                                    {user.email}
                                                </p>

                                                <h4 className="text-15 lh-1 fw-400 mt-20">
                                                    No. HP
                                                </h4>
                                                <p className="mt-2">
                                                    {instructor.phone_number}
                                                </p>

                                                {
                                                    instructor.status !== 'pending' && (
                                                        <>
                                                            <h4 className="text-15 lh-1 fw-400 mt-20">
                                                                Status
                                                            </h4>
                                                            <p className="mt-2">
                                                                {instructor.status}
                                                            </p>
                                                        </>
                                                    )
                                                }

                                                <h4 className="text-15 lh-1 fw-400 mt-20">
                                                    Ringkasan
                                                </h4>
                                                <p className="mt-2">
                                                    {instructor.summary}
                                                </p>

                                                <h4 className="text-15 lh-1 fw-400 mt-20">
                                                    Mendaftar Pada
                                                </h4>
                                                <p className="mt-2">
                                                    {formatDate(user.created_at)}
                                                </p>

                                                {
                                                    instructor.status === 'pending' && (
                                                        <div className="mt-30 d-flex ">
                                                            <a className="button -sm -purple-1 text-white mr-10" href={'#'}
                                                               onClick={() => confirmApprove()}>
                                                                Terima
                                                            </a>
                                                            <a className="button -sm -purple-1 text-white mr-10" href={'#'}
                                                               onClick={() => confirmReject()}>
                                                                Tolak
                                                            </a>
                                                        </div>
                                                    )
                                                }
                                            </>
                                        )
                                    }

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </AdminWrapper>
        </Wrapper>
    )
}