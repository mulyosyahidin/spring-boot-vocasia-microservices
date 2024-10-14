import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import {Link, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {INSTRUCTOR} from "../../../../../config/consts.js";
import {Wrapper} from "../../../../../components/commons/Wrapper.jsx";
import {Meta} from "../../../../../components/commons/Meta.jsx";
import {PreLoader} from "../../../../../components/commons/PreLoader.jsx";
import {InstructorWrapper} from "../../../../../components/Instructors/InstructorWrapper/Index.jsx";
import {deleteById, findAll} from "../../../../../services/new/course/instructor/chapter-service.js";

const metaData = {
    title: 'Kelola Bab Kursus',
};

export const ChapterIndexPage = () => {
    const {id} = useParams();

    const [isLoading, setIsLoading] = useState(true);
    const [data, setData] = useState([]);

    const [currentOpenItem, setCurrentOpenItem] = useState();

    useEffect(() => {
        const fetchInitialData = async () => {
            setIsLoading(true);

            try {
                const findAllCourseChapters = await findAll(id);

                if (findAllCourseChapters.success) {
                    setData(findAllCourseChapters.data.data);
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

    const confirmDelete = async (chapterId) => {
        await withReactContent(Swal).fire({
            title: 'Apakah Anda yakin?',
            text: 'Menghapus bab juga akan menghapus semua pelajaran didalamnya. Tindakan ini tidak dapat dibatalkan',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Ya, hapus!',
            cancelButtonText: 'Batal',
        })
            .then(async (result) => {
                if (result.isConfirmed) {
                    try {
                        const doDeleteChapter = await deleteById(id, chapterId);

                        if (doDeleteChapter.success) {
                            await withReactContent(Swal).fire({
                                title: 'Berhasil!',
                                text: doDeleteChapter.message,
                                icon: 'success',
                            })
                                .then((isConfirmed) => {
                                    if (isConfirmed) {
                                        setData(data.filter((item) => item.chapter.id !== chapterId));
                                    }
                                })
                        }
                    }
                    catch (error) {
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
            });
    }

    return (
        <Wrapper needAuth role={INSTRUCTOR}>
            <Meta meta={metaData}/>
            <PreLoader/>

            <InstructorWrapper>
                <div className="dashboard__content bg-light-4">
                    <div className="row pb-50 mb-10">
                        <div className="col-auto">
                            <h1 className="text-30 lh-12 fw-700">Kelola Bab Kursus</h1>
                            <div className="mt-10">Kelola dan dan pelajaran</div>
                        </div>
                    </div>

                    {/*<div className={`row y-gap-60 ${currentEditedChapterId != null ? '' : 'd-none'}`}>*/}
                    {/*    <div className="col-12">*/}
                    {/*        <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">*/}
                    {/*            <div*/}
                    {/*                className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">*/}
                    {/*                <h2 className="text-17 lh-1 fw-500">Edit Bab</h2>*/}

                    {/*                <button className="button -sm -purple-1 text-white"*/}
                    {/*                        onClick={() => setCurrentEditedChapterId(null)}>*/}
                    {/*                    Tutup*/}
                    {/*                </button>*/}
                    {/*            </div>*/}

                    {/*            <div className="py-30 px-30">*/}
                    {/*                <form onSubmit={handleEditChapterSubmit} className="contact-form row y-gap-30">*/}
                    {/*                    <div className={'row'}>*/}
                    {/*                        <div className="col-12 col-md-6">*/}
                    {/*                            <InputField*/}
                    {/*                                label="Judul Bab"*/}
                    {/*                                name="title"*/}
                    {/*                                placeholder="Judul bab"*/}
                    {/*                                onChange={handleEditChapterChange}*/}
                    {/*                                value={editChapterFormData.title}*/}
                    {/*                            />*/}
                    {/*                        </div>*/}
                    {/*                        <div className="col-12 col-md-6">*/}
                    {/*                            <InputField*/}
                    {/*                                label="Total Durasi"*/}
                    {/*                                name="total_duration"*/}
                    {/*                                placeholder="Total durasi bab"*/}
                    {/*                                onChange={handleEditChapterChange}*/}
                    {/*                                value={editChapterFormData.total_duration}*/}
                    {/*                            />*/}
                    {/*                        </div>*/}
                    {/*                    </div>*/}

                    {/*                    <div className="row y-gap-20 justify-end pt-15">*/}
                    {/*                        <div className="col-auto">*/}
                    {/*                            <button className="button -sm -purple-1 text-white"*/}
                    {/*                                    disabled={isAddChapterLoading}>*/}
                    {/*                                {*/}
                    {/*                                    isAddChapterLoading ? (*/}
                    {/*                                        'Menyimpan...'*/}
                    {/*                                    ) : 'Simpan'*/}
                    {/*                                }*/}
                    {/*                            </button>*/}
                    {/*                        </div>*/}
                    {/*                    </div>*/}
                    {/*                </form>*/}
                    {/*            </div>*/}
                    {/*        </div>*/}
                    {/*    </div>*/}
                    {/*</div>*/}

                    {/*<div className={`row y-gap-60 ${isAddChapterSectionOpen ? '' : 'd-none'}`}>*/}
                    {/*    <div className="col-12">*/}
                    {/*        <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">*/}
                    {/*            <div*/}
                    {/*                className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">*/}
                    {/*                <h2 className="text-17 lh-1 fw-500">Tambah Bab Baru</h2>*/}

                    {/*                <button className="button -sm -purple-1 text-white"*/}
                    {/*                        onClick={() => setIsAddChapterSectionOpen(false)}>*/}
                    {/*                    Tutup*/}
                    {/*                </button>*/}
                    {/*            </div>*/}

                    {/*            <div className="py-30 px-30">*/}
                    {/*                <form onSubmit={handleAddChapterSubmit} className="contact-form row y-gap-30">*/}
                    {/*                    <div className={'row'}>*/}
                    {/*                        <div className="col-12 col-md-6">*/}
                    {/*                            <InputField*/}
                    {/*                                label="Judul Bab"*/}
                    {/*                                name="title"*/}
                    {/*                                placeholder="Judul bab"*/}
                    {/*                                onChange={handleAddChapterChange}*/}
                    {/*                                value={addChapterFormData.title}*/}
                    {/*                            />*/}
                    {/*                        </div>*/}
                    {/*                        <div className="col-12 col-md-6">*/}
                    {/*                            <InputField*/}
                    {/*                                label="Total Durasi"*/}
                    {/*                                name="total_duration"*/}
                    {/*                                placeholder="Total durasi bab"*/}
                    {/*                                onChange={handleAddChapterChange}*/}
                    {/*                                value={addChapterFormData.total_duration}*/}
                    {/*                            />*/}
                    {/*                        </div>*/}
                    {/*                    </div>*/}

                    {/*                    <div className="row y-gap-20 justify-end pt-15">*/}
                    {/*                        <div className="col-auto">*/}
                    {/*                            <button className="button -sm -purple-1 text-white"*/}
                    {/*                                    disabled={isAddChapterLoading}>*/}
                    {/*                                {*/}
                    {/*                                    isAddChapterLoading ? (*/}
                    {/*                                        'Menyimpan...'*/}
                    {/*                                    ) : 'Simpan'*/}
                    {/*                                }*/}
                    {/*                            </button>*/}
                    {/*                        </div>*/}
                    {/*                    </div>*/}
                    {/*                </form>*/}
                    {/*            </div>*/}
                    {/*        </div>*/}
                    {/*    </div>*/}
                    {/*</div>*/}

                    <div className="row y-gap-60">
                        <div className="col-12">
                            <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
                                <div
                                    className="d-flex items-center py-20 px-30 border-bottom-light justify-content-between">
                                    <h2 className="text-17 lh-1 fw-500">Konten Kursus</h2>

                                    <div className={'d-flex justify-content-between gap-2'}>
                                        <Link to={`/instructor/courses/${id}/chapters/create`}
                                              className={`button -sm -dark-2 text-white mr-10`}
                                        >
                                            Tambah Bab
                                        </Link>

                                        <Link to={`/instructor/courses/${id}/overview`}
                                              className="button -sm -dark-2 text-white">
                                            Ringkasan <i className={'fa fa-arrow-right ml-5'}/>
                                        </Link>
                                    </div>
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
                                            <div className="accordion -block-2 text-left js-accordion">
                                                {
                                                    data.map((item, index) => (
                                                        <div
                                                            key={index}
                                                            className={`accordion__item -dark-bg-dark-1 mt-10 ${
                                                                currentOpenItem === `${index}` ? "is-active" : ""
                                                            } `}
                                                        >
                                                            <div
                                                                className="accordion__button py-20 px-30 bg-light-4"
                                                                onClick={() =>
                                                                    setCurrentOpenItem((pre) =>
                                                                        pre === `${index}` ? "" : `${index}`,
                                                                    )
                                                                }
                                                            >
                                                                <div className="d-flex items-center">
                                                                    <div className="icon icon-drag mr-10"></div>
                                                                    <span className="text-16 lh-14 fw-500 text-dark-1">
                                                                {item.chapter.title}
                                                            </span>
                                                                </div>

                                                                <div className="d-flex x-gap-10 items-center">
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
                                                                    currentOpenItem === `${index}`
                                                                        ? {maxHeight: "none"}
                                                                        : {}
                                                                }
                                                            >
                                                                <div className="accordion__content__inner px-30 py-30">
                                                                    <div
                                                                        className="d-flex x-gap-2 y-gap-10 flex-wrap justify-content-end">
                                                                        <div>
                                                                            <a style={{cursor: "pointer"}}
                                                                                onClick={() => confirmDelete(item.chapter.id)}
                                                                                className="button -sm py-15 -purple-3 text-purple-1 fw-500 icon icon-bin mr-5"
                                                                            >
                                                                            </a>
                                                                        </div>
                                                                        <div>
                                                                            <Link
                                                                                to={`/instructor/courses/${id}/chapters/${item.chapter.id}/edit`}
                                                                                className="button -sm py-15 -purple-3 text-purple-1 fw-500 icon icon-edit mr-5"
                                                                            >
                                                                            </Link>
                                                                        </div>
                                                                        <div>
                                                                            <Link
                                                                                to={`/instructor/courses/${id}/chapters/${item.chapter.id}/lessons`}
                                                                                className="button -sm py-15 -purple-3 text-purple-1 fw-500"
                                                                            >
                                                                                Kelola Pelajaran
                                                                            </Link>
                                                                        </div>
                                                                    </div>

                                                                    {
                                                                        item.lessons && item.lessons.map((lesson, index) => (
                                                                            <div key={index}
                                                                                 className="d-flex x-gap-10 y-gap-10 mb-5 align-items-center">
                                                                                {
                                                                                    lesson.type === 'video' && (
                                                                                        <div className="icon icon-play"></div>
                                                                                    )
                                                                                }

                                                                                {
                                                                                    lesson.type === 'text' && (
                                                                                        <div className="icon icon-book"></div>
                                                                                    )
                                                                                }

                                                                                <div
                                                                                    className="text-16 lh-14 fw-500 text-dark-1">
                                                                                    {lesson.title}

                                                                                    {
                                                                                        lesson.type === 'video' && (
                                                                                            <span
                                                                                                className="text-14 lh-14 fw-400 text-dark-2 ml-5">
                                                                                        ({lesson.content_video_duration})
                                                                                    </span>
                                                                                        )
                                                                                    }
                                                                                </div>
                                                                            </div>
                                                                        ))
                                                                    }
                                                                </div>
                                                            </div>
                                                        </div>
                                                    ))
                                                }
                                            </div>
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