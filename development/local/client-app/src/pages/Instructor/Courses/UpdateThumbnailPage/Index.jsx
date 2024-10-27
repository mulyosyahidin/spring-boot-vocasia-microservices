import {INSTRUCTOR} from "../../../../config/consts.js";
import {Wrapper} from "../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {Link, useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {findById, updateThumbnail} from "../../../../services/new/course/instructor/course-service.js";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

const metaData = {
    title: 'Update Thumbnail Kursus',
};

export const UpdateThumbnailPage = () => {
    const {id} = useParams();

    const [isLoading, setIsLoading] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);

    const [course, setCourse] = useState();
    const [previewImage, setPreviewImage] = useState();

    const [errors, setErrors] = useState({});

    const navigate = useNavigate();

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findCourseById = await findById(id);

                if (findCourseById.success) {
                    const course = findCourseById.data.course;

                    if (course.featured_picture !== '') {
                        const coursePictureUrl = course.featured_picture_url;

                        setPreviewImage(coursePictureUrl);
                    }

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

        };

        fetchInitialData();
    }, [id]);

    const handleImageChange = (event) => {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();

            reader.onloadend = () => {
                setPreviewImage(reader.result);
            };

            reader.readAsDataURL(file);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitted(true);

        try {
            const formData = new FormData();
            formData.append("picture", document.getElementById("imageUpload1").files[0]);

            const doUpdateThumbnail = await updateThumbnail(id, formData);
            if (doUpdateThumbnail) {
                if (doUpdateThumbnail.success) {
                    await withReactContent(Swal).fire({
                        icon: 'success',
                        title: 'Berhasil!',
                        text: doUpdateThumbnail.message,
                    });
                }

                setIsSubmitted(false);
            }
        } catch (error) {
            console.error(error);

            if (error.errors) {
                const getErrors = error.errors;
                const newErrors = {};

                Object.keys(getErrors).forEach((field) => {
                    newErrors[field] = getErrors[field][0];
                });

                setErrors(newErrors);
                setIsSubmitted(false);
            } else if (error.data) {
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
                    setErrors({general: error.message});
                }
            }
        }
    }

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Update Thumbnail Kursus</h1>
                            <div className="mt-10">Pilih gambar unggulan untuk kursus Anda</div>
                        </div>
                    </div>

                    <div className="row y-gap-60">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                                    <h2 className="text-17 lh-1 fw-500">Thumbnail Kursus</h2>

                                    <Link to={`/instructor/courses/${id}/chapters`}
                                          className="button -sm -dark-2 text-white">
                                        Bab <i className={'fa fa-arrow-right ml-5'}/>
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
                                            <form onSubmit={handleSubmit} className={'contact-form'}
                                                  encType={'multipart/form-data'}>

                                                <div className="d-flex lg:flex-column">
                                                    <div
                                                        className="relative shrink-0"
                                                        style={
                                                            previewImage
                                                                ? {}
                                                                : {backgroundColor: "#f2f3f4", width: 250, height: 200}
                                                        }
                                                    >
                                                        {previewImage && (
                                                            <img
                                                                className="w-1/1"
                                                                style={{
                                                                    width: "250px",
                                                                    height: "200px",
                                                                    objectFit: "contain",
                                                                }}
                                                                src={previewImage}
                                                                alt="image"
                                                            />
                                                        )}

                                                        <div
                                                            className="absolute-full-center d-flex justify-end py-20 px-20">
                                        <span
                                            style={{cursor: "pointer"}}
                                            onClick={() => {
                                                document.getElementById("imageUpload1").value = "";
                                                setPreviewImage("");
                                            }}
                                            className="d-flex justify-center items-center bg-white size-40 rounded-8 shadow-1"
                                        >
                                            <i className="icon-bin text-16"></i>
                                        </span>
                                                        </div>
                                                    </div>

                                                    <div className="w-1/1 ml-30 lg:ml-0 lg:mt-20">
                                                        <div className="form-upload col-12">
                                                            <div className="form-upload__wrap">
                                                                <input
                                                                    type="text"
                                                                    name="name"
                                                                    placeholder={"Pilih file..."}
                                                                />
                                                                <button className="button -dark-3 text-white">
                                                                    <label
                                                                        style={{cursor: "pointer"}}
                                                                        htmlFor="imageUpload1"
                                                                    >
                                                                        Pilih File
                                                                    </label>
                                                                    <input
                                                                        required
                                                                        id="imageUpload1"
                                                                        type="file"
                                                                        accept="image/*"
                                                                        onChange={handleImageChange}
                                                                        style={{display: "none"}}
                                                                    />
                                                                </button>
                                                            </div>
                                                        </div>

                                                        <p className="mt-10">
                                                            Pilih sebuah foto yang menarik dan
                                                            relevan dengan kursus Anda. Foto ini akan menjadi thumbnail yang
                                                            ditampilkan di halaman kursus.
                                                            Pilih foto dengan rasio 4:3 (misalnya 800x600 piksel) untuk
                                                            hasil
                                                            terbaik dengan format .jpg, .jpeg, .gif, atau .png.
                                                        </p>
                                                    </div>
                                                </div>

                                                <div className="row y-gap-20 justify-end pt-15">
                                                    <div className="col-auto">
                                                        <button className="button -md -purple-1 text-white"
                                                                disabled={isSubmitted}>
                                                            {
                                                                isSubmitted ? (
                                                                    'Menyimpan...'
                                                                ) : 'Simpan'
                                                            }
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        )
                                    }
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </InstructorWrapper>
        </Wrapper>
    );
}