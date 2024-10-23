package com.vocasia.instructor.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instructor_student_courses")
public class InstructorStudentCourse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "instructor_student_id")
    @JsonProperty("instructor_student_id")
    private InstructorStudent instructorStudent;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("order_id")
    private Long orderId;

}
