ALTER TABLE lessons
    ADD attachment_file_name VARCHAR(255) NULL AFTER content_text;

ALTER TABLE lessons
    ADD attachment_file_url VARCHAR(255) NULL AFTER attachment_file_name;

ALTER TABLE lessons
    ADD attachment_link VARCHAR(255) NULL AFTER attachment_file_url;

ALTER TABLE lessons
    ADD attachment_link_name VARCHAR(255) NULL AFTER attachment_link;

ALTER TABLE lessons
    ADD attachment_type VARCHAR(255) NULL AFTER attachment_link_name;
