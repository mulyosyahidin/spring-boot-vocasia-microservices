import React, {useEffect, useState} from "react";
import ModalVideoComponent from "../../../../components/commons/ModalVideo.jsx";
import {getYouTubeVideoId} from "../../../../utils/new-utils.js";

export const VideoPlayer = ({course, currentLesson}) => {
    const [isOpen, setIsOpen] = useState(false);

    return (
        <>
            {
                currentLesson.type === 'video' && (
                    <>
                        <h4 className={'mb-10'}>{currentLesson.title}</h4>

                        <div className="relative pt-40">
                            <img
                                className="w-1/1 rounded-16"
                                src={course.featured_picture_url}
                                alt="image"
                            />

                            <div className="absolute-full-center d-flex justify-center items-center">
                                <span
                                    style={{cursor: "pointer"}}
                                    onClick={() => setIsOpen(true)}
                                    className="d-flex justify-center items-center size-60 rounded-full bg-white"
                                >
                                    <span className="icon-play text-18"></span>
                                </span>
                            </div>
                        </div>
                        <ModalVideoComponent
                            videoId={`${getYouTubeVideoId(currentLesson.content_video_url)}`}
                            isOpen={isOpen}
                            setIsOpen={setIsOpen}
                        />
                    </>
                )
            }

            {
            currentLesson.type === 'text' && (
                    <>
                        <div
                            dangerouslySetInnerHTML={{__html: currentLesson.content_text}}
                        />
                    </>
                )
            }
        </>
    );
}
