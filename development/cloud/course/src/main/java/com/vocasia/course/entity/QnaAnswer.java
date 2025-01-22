package com.vocasia.course.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qna_answers")
public class QnaAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qna_id", nullable = false)
    private Long qnaId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Lob
    private String answer;

    @Column(name = "is_instructor")
    private Boolean isInstructor;

}
