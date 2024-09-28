import React from "react";

export const Title = () => {
    return (
        <div className="row justify-center text-center">
            <div className="col-auto">
                <div className="sectionTitle ">
                    <h2
                        className="sectionTitle__title text-white"
                        data-aos="fade-up"
                        data-aos-duration={800}
                    >
                        Kenapa harus belajar di Vocasia?
                    </h2>

                    <p
                        className="sectionTitle__text text-white"
                        data-aos="fade-up"
                        data-aos-duration={800}
                    >
                        Vocasia adalah platform kursus online yang menyediakan berbagai macam kursus yang dapat membantu
                        meningkatkan kemampuan dan keterampilan di berbagai bidang bagi mahasiswa, professional dan
                        umum.
                    </p>
                </div>
            </div>
        </div>
    );
}