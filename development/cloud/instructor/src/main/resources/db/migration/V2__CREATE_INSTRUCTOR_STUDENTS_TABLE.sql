CREATE TABLE instructor_students
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    instructor_id BIGINT                NULL,
    user_id       BIGINT                NULL,
    created_at    DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_instructor_students PRIMARY KEY (id)
);

CREATE TABLE instructor_student_courses
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    instructor_student_id BIGINT                NULL,
    course_id             BIGINT                NULL,
    order_id              BIGINT                NULL,
    created_at            DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at            DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_instructor_student_courses PRIMARY KEY (id)
);

ALTER TABLE instructor_student_courses
    ADD CONSTRAINT FK_INSTRUCTOR_STUDENT_COURSES_ON_INSTRUCTOR_STUDENT FOREIGN KEY (instructor_student_id) REFERENCES instructor_students (id);