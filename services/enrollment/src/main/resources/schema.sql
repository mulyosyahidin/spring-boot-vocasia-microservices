CREATE TABLE IF NOT EXISTS enrollments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    enrollment_date DATETIME NULL,
    status VARCHAR(255) NOT NULL DEFAULT 'active',
    progress_percentage DECIMAL(5, 2) DEFAULT 0.0,
    completion_date DATETIME NULL,
    CONSTRAINT uq_enrollment UNIQUE (user_id, course_id)
);

CREATE TABLE IF NOT EXISTS progress (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          enrollment_id BIGINT NOT NULL,
                          lesson_id BIGINT NOT NULL,
                          status VARCHAR(255) NOT NULL,
                          watched_duration VARCHAR(50) NOT NULL,  -- Menyimpan durasi ditonton (misalnya dalam format "HH:mm:ss")
                          last_accessed DATETIME NOT NULL,
                          CONSTRAINT fk_enrollment FOREIGN KEY (enrollment_id) REFERENCES enrollments(id) ON DELETE CASCADE,
                          CONSTRAINT fk_lesson FOREIGN KEY (lesson_id) REFERENCES lessons(id) ON DELETE CASCADE -- Jika ada tabel lessons
);
