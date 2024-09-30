import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {Link, useNavigate, useParams} from "react-router-dom";
import {findCourseById, updateThumbnail} from "../_actions/CourseAction.jsx";
import {generateAWSObjectUrl} from "../../../../utils/utils.js";
import {apiBaseUrl, AUTH_ACCESS_TOKEN} from "../../../../config/consts.js";
import {COURSES_UPLOAD_THUMBNAIL} from "../../../../config/endpoints.js";

export const UpdateThumbnail = () => {
    const {sweetAlert} = useContext(AuthContext);
    const {id} = useParams();

    const [course, setCourse] = useState();
    const [previewImage, setPreviewImage] = useState();

    const [loading, setLoading] = useState(false);
    const [errors, setErrors] = useState({});

    const navigate = useNavigate();

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getCourse = await findCourseById(id);
                const course = getCourse.course;

                if (course.featured_picture != '') {
                    const coursePictureUrl = course.featured_picture_url;
                    setPreviewImage(coursePictureUrl);
                }
            } catch (error) {
                console.error('Error fetching initial data:', error);
            }
        };

        fetchInitialData();
    }, []);

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

        setLoading(true);

        const formData = new FormData();
        formData.append("picture", document.getElementById("imageUpload1").files[0]);

        const result = await updateThumbnail(id, formData, setLoading, setErrors);

        if (result) {
            sweetAlert.fire({
                icon: 'success',
                title: 'Berhasil',
                text: 'Berhasil memperbarui gambar thumbnail kursus',

            }).then(() => {

            })
        }
    }

    return (
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
                        <div className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                            <h2 className="text-17 lh-1 fw-500">Thumbnail Kursus</h2>

                            <Link to={`/instructor/courses/${id}/chapters`} className="button -sm -dark-2 text-white">
                                Bab <i className={'fa fa-arrow-right ml-5'}/>
                            </Link>
                        </div>

                        <div className="py-30 px-30">
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

                                        <div className="absolute-full-center d-flex justify-end py-20 px-20">
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
                                            relevan dengan kursus Anda. Foto ini akan menjadi thumbnail yang ditampilkan di halaman kursus.
                                            Pilih foto dengan rasio 4:3 (misalnya 800x600 piksel) untuk hasil terbaik dengan format .jpg, .jpeg, .gif, atau .png.
                                        </p>
                                    </div>
                                </div>

                                <div className="row y-gap-20 justify-end pt-15">
                                    <div className="col-auto">
                                        <button className="button -md -purple-1 text-white" disabled={loading}>
                                            {
                                                loading ? (
                                                    'Menyimpan...'
                                                ) : 'Simpan'
                                            }
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    );
}