import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../../states/contexts/AuthContext.jsx";
import {Link, useParams} from "react-router-dom";
import {addChapter, findCourseById, getChaptersByCourseId, updateChapter, deleteChapter} from "../_actions/CourseAction.jsx";
import {InputField} from "../../../../components/commons/Input/InputField.jsx";

export const Chapter = () => {
    const {sweetAlert} = useContext(AuthContext);
    const {id} = useParams();

    // add chapter: start
    const [isAddChapterSectionOpen, setIsAddChapterSectionOpen] = useState(false);
    const [isAddChapterLoading, setIsAddChapterLoading] = useState(false);
    const [addChapterFormData, setAddChapterFormData] = useState({
        title: '',
        total_duration: '',
    });
    const [addChapterErrors, setAddChapterErrors] = useState({});

    const handleAddChapterChange = (e) => {
        const {name, value} = e.target;

        setAddChapterFormData((prevData) => ({...prevData, [name]: value}));
    };

    const handleAddChapterSubmit = async (e) => {
        e.preventDefault();

        setIsAddChapterLoading(true);

        const finalFormData = {
            ...addChapterFormData,
        };

        const chapter = await addChapter(id, finalFormData, setIsAddChapterLoading, setAddChapterErrors);

        if (course) {
            setChapters([...chapters, chapter.chapter]);

            sweetAlert.fire({
                icon: 'success',
                title: 'Berhasil',
                text: 'Berhasil menambah bab baru',
            });

            setAddChapterFormData({
                title: '',
                total_duration: '',
            });
        }
    }

    const [currentOpenItem, setCurrentOpenItem] = useState();

    const [course, setCourse] = useState();
    const [chapters, setChapters] = useState([]);
    // add chapter: end

    // edit chapter: start
    const [currentEditedChapterId, setCurrentEditedChapterId] = useState(null);
    const [currentEditedChapter, setCurrentEditedChapter] = useState({});
    const [editChapterErrors, setEditChapterErrors] = useState({});
    const [isEditChapterLoading, setIsEditChapterLoading] = useState(false);
    const [editChapterFormData, setEditChapterFormData] = useState({
        title: '',
        total_duration: '',
    });
    const handleEditChapterChange = (e) => {
        const {name, value} = e.target;

        setEditChapterFormData((prevData) => ({...prevData, [name]: value}));
    }

    const handleEditChapterSubmit = async (e) => {
        e.preventDefault();

        setIsEditChapterLoading(true);

        const finalFormData = {
            ...editChapterFormData,
        };

        const chapter = await updateChapter(id, currentEditedChapterId, finalFormData, setIsEditChapterLoading, setEditChapterErrors);
        const updatedChapter = chapter.chapter;

        if (course) {
            setChapters((prevChapters) =>
                prevChapters.map((chapter) =>
                    chapter.id === currentEditedChapterId ? updatedChapter : chapter
                )
            );

            setCurrentEditedChapter({});
            setCurrentEditedChapterId(null);

            sweetAlert.fire({
                icon: 'success',
                title: 'Berhasil',
                text: 'Berhasil memperbarui data bab',
            });

            setEditChapterFormData({
                title: '',
                total_duration: '',
            });
        }
    }
    // edit chapter: end

    // delete chapter: start
    const confirmDeleteChapter = async (chapterId) => {
        sweetAlert.fire({
            icon: 'warning',
            title: 'Apakah Anda yakin?',
            text: 'Apakah Anda yakin ingin menghapus bab ini?',
            showCancelButton: true,
            confirmButtonText: 'Ya, hapus',
            cancelButtonText: 'Batal',
        }).then(async (result) => {
            if (result.isConfirmed) {
                const response = await deleteChapter(id, chapterId);
                console.log('confirmDeleteChapter::response', response);

                if (response.success) {
                    setChapters((prevChapters) =>
                        prevChapters.filter((chapter) => chapter.id !== chapterId)
                    );

                    sweetAlert.fire({
                        icon: 'success',
                        title: 'Berhasil',
                        text: 'Berhasil menghapus bab',
                    });
                }
            }
        });
    }
    // delete chapter: end

    useEffect(() => {
        const fetchInitialData = async () => {
            try {
                const getCourse = await findCourseById(id);
                const course = getCourse.course;
                setCourse(course);

                const getAllChapters = await getChaptersByCourseId(id);
                setChapters(getAllChapters);

                const chapter = chapters.find((chapter) => chapter.id === currentEditedChapterId);
                if (chapter != null) {
                    setCurrentEditedChapter(chapter);
                    setEditChapterFormData({
                        title: chapter.title,
                        total_duration: chapter.total_duration,
                    });
                }
            } catch (error) {
                console.error('Error fetching initial data:', error);
            }
        };

        fetchInitialData();
    }, [id, currentEditedChapterId]);

    return (
        <div className="dashboard__content bg-light-4">
            <div className="row pb-50 mb-10">
                <div className="col-auto">
                    <h1 className="text-30 lh-12 fw-700">Kelola Bab Kursus</h1>
                    <div className="mt-10">Kelola dan dan pelajaran</div>
                </div>
            </div>

            <div className={`row y-gap-60 ${currentEditedChapterId != null ? '' : 'd-none'}`}>
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div
                            className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                            <h2 className="text-17 lh-1 fw-500">Edit Bab</h2>

                            <button className="button -sm -purple-1 text-white"
                                    onClick={() => setCurrentEditedChapterId(null)}>
                                Tutup
                            </button>
                        </div>

                        <div className="py-30 px-30">
                            <form onSubmit={handleEditChapterSubmit} className="contact-form row y-gap-30">
                                <div className={'row'}>
                                    <div className="col-12 col-md-6">
                                        <InputField
                                            label="Judul Bab"
                                            name="title"
                                            placeholder="Judul bab"
                                            onChange={handleEditChapterChange}
                                            value={editChapterFormData.title}
                                        />
                                    </div>
                                    <div className="col-12 col-md-6">
                                        <InputField
                                            label="Total Durasi"
                                            name="total_duration"
                                            placeholder="Total durasi bab"
                                            onChange={handleEditChapterChange}
                                            value={editChapterFormData.total_duration}
                                        />
                                    </div>
                                </div>

                                <div className="row y-gap-20 justify-end pt-15">
                                    <div className="col-auto">
                                        <button className="button -sm -purple-1 text-white"
                                                disabled={isAddChapterLoading}>
                                            {
                                                isAddChapterLoading ? (
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

            <div className={`row y-gap-60 ${isAddChapterSectionOpen ? '' : 'd-none'}`}>
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div
                            className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                            <h2 className="text-17 lh-1 fw-500">Tambah Bab Baru</h2>

                            <button className="button -sm -purple-1 text-white"
                                    onClick={() => setIsAddChapterSectionOpen(false)}>
                                Tutup
                            </button>
                        </div>

                        <div className="py-30 px-30">
                            <form onSubmit={handleAddChapterSubmit} className="contact-form row y-gap-30">
                                <div className={'row'}>
                                    <div className="col-12 col-md-6">
                                        <InputField
                                            label="Judul Bab"
                                            name="title"
                                            placeholder="Judul bab"
                                            onChange={handleAddChapterChange}
                                            value={addChapterFormData.title}
                                        />
                                    </div>
                                    <div className="col-12 col-md-6">
                                        <InputField
                                            label="Total Durasi"
                                            name="total_duration"
                                            placeholder="Total durasi bab"
                                            onChange={handleAddChapterChange}
                                            value={addChapterFormData.total_duration}
                                        />
                                    </div>
                                </div>

                                <div className="row y-gap-20 justify-end pt-15">
                                    <div className="col-auto">
                                        <button className="button -sm -purple-1 text-white"
                                                disabled={isAddChapterLoading}>
                                            {
                                                isAddChapterLoading ? (
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

            <div className="row y-gap-60">
                <div className="col-12">
                    <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                        <div
                            className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                            <h2 className="text-17 lh-1 fw-500">Konten Kursus</h2>

                            <div className={'d-flex justify-content-between gap-2'}>
                                <a className={`button -sm -dark-2 text-white ${isAddChapterSectionOpen ? 'd-none' : ''}`}
                                   onClick={() => setIsAddChapterSectionOpen(true)}>
                                    Tambah Bab
                                </a>
                                <Link to={`/instructor/courses/${id}/overview`}
                                      className="button -sm -dark-2 text-white">
                                    Ringkasan <i className={'fa fa-arrow-right ml-5'}/>
                                </Link>
                            </div>
                        </div>

                        <div className="py-30 px-30">
                            <div className={'row pt-30'}>
                                <div className="col-12">
                                    <div className="accordion -block-2 text-left js-accordion">
                                        {
                                            chapters.map((chapter, index) => (
                                                <div
                                                    key={index}
                                                    className={`accordion__item -dark-bg-dark-1 mt-10 ${
                                                        currentOpenItem == `${index}` ? "is-active" : ""
                                                    } `}
                                                >
                                                    <div
                                                        className="accordion__button py-20 px-30 bg-light-4"
                                                        onClick={() =>
                                                            setCurrentOpenItem((pre) =>
                                                                pre == `${index}` ? "" : `${index}`,
                                                            )
                                                        }
                                                    >
                                                        <div className="d-flex items-center">
                                                            <div className="icon icon-drag mr-10"></div>
                                                            <span className="text-16 lh-14 fw-500 text-dark-1">
                                                                {chapter.title}
                                                            </span>
                                                        </div>

                                                        <div className="d-flex x-gap-10 items-center">
                                                            <a href="#" className="icon icon-edit mr-5"
                                                               onClick={() => setCurrentEditedChapterId(chapter.id)}></a>
                                                            <a href="#" className="icon icon-bin" onClick={() => confirmDeleteChapter(chapter.id)}></a>
                                                            <div className="accordion__icon mr-0">
                                                                <div
                                                                    className="d-flex items-center justify-center icon icon-chevron-down"></div>
                                                                <div
                                                                    className="d-flex items-center justify-center icon icon-chevron-up"></div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div
                                                        className="accordion__content"
                                                        style={
                                                            currentOpenItem == `${index}`
                                                                ? {maxHeight: "100px"}
                                                                : {}
                                                        }
                                                    >
                                                        <div className="accordion__content__inner px-30 py-30">
                                                            <div
                                                                className="d-flex x-gap-10 y-gap-10 flex-wrap justify-content-end">
                                                                <div>
                                                                    <a
                                                                        href="#"
                                                                        className="button -sm py-15 -purple-3 text-purple-1 fw-500"
                                                                    >
                                                                        Add Article +
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            ))
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    );
}