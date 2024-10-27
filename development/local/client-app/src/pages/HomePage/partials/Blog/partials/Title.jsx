import React from "react";

export const Title = () => {
    return (
        <div className="col-lg-6">
            <div
                className="sectionTitle "
                data-aos="fade-left"
                data-aos-duration={600}
            >
                <h2 className="sectionTitle__title ">Blog dan Berita</h2>

                <p className="sectionTitle__text ">
                    Informasi terbaru seputar kursus online, event, dan berita terkini
                    yang sedang hangat diperbincangkan.
                </p>
            </div>
        </div>
    );
}