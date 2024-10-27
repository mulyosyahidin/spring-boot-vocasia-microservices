import React from "react";
import {Helmet, HelmetProvider} from "react-helmet-async";

export const Meta = ({meta}) => {
    return (
        <HelmetProvider>
            <Helmet>
                <title>{meta?.title} | Vocasia</title>

                <meta name="description"
                      content="Situs kursus online bersertifikat terbaik untuk meningkatkan kemampuan &amp; keterampilan di berbagai bidang bagi mahasiswa, professional dan umum."/>
            </Helmet>
        </HelmetProvider>
    );
}
