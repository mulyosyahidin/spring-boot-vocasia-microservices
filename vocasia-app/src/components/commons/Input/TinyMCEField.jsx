import {Editor} from "@tinymce/tinymce-react";
import React from "react";

export const TinyMCEField = ({onInit, initialValue = null, height = 250, label = null, hint = null, error = null}) => {
    return (
        <>
            {
                label && <label className="text-16 lh-1 fw-500 text-dark-1 mb-10">{label}</label>
            }
            <Editor
                apiKey='mpjiz8kyrbnm9czwyjijsw9aah32immx0d614flkjzxci9c8'
                onInit={onInit}
                initialValue={initialValue}
                init={{
                    height: height,
                    menubar: true,
                    plugins: [
                        'advlist', 'autolink', 'lists', 'link', 'image', 'charmap', 'preview',
                        'anchor', 'searchreplace', 'visualblocks', 'code', 'fullscreen',
                        'insertdatetime', 'media', 'table', 'code', 'help', 'wordcount'
                    ],
                    toolbar: 'undo redo | blocks | ' +
                        'bold italic forecolor | alignleft aligncenter ' +
                        'alignright alignjustify | bullist numlist outdent indent | ' +
                        'removeformat | help',
                    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
                }}
            />

            {hint && <small className="text-dark-1">{hint}</small>}

            {error && (
                <div className={'mt-2'}>
                    <small className="text-red-1">{error}</small>
                </div>
            )}
        </>
    );
}