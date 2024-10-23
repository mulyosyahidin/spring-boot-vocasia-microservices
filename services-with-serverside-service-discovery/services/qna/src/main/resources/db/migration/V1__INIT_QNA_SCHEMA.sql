CREATE TABLE qna
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    course_id  BIGINT                NOT NULL,
    lesson_id  BIGINT                NOT NULL,
    user_id    BIGINT                NOT NULL,
    title      VARCHAR(255)          NULL,
    question   LONGTEXT              NULL,
    is_solved  BIT(1)                NULL,
    solved_at  datetime              NULL,
    CONSTRAINT pk_qna PRIMARY KEY (id)
);

CREATE TABLE qna_answers
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    qna_id  BIGINT                NOT NULL,
    user_id BIGINT                NOT NULL,
    answer  LONGTEXT              NULL,
    CONSTRAINT pk_qna_answers PRIMARY KEY (id)
);