package com.vocasia.course.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lessons")
@ToString(callSuper = true)
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(name = "need_previous_lesson", nullable = false)
    private Boolean needPreviousLesson = true;

    @Column(name = "is_free", nullable = false)
    private Boolean isFree = false;

    @Column(name = "content_video_duration")
    private String contentVideoDuration;

    @Column(name = "content_video_url")
    private String contentVideoUrl;

    @Column(name = "content_text", columnDefinition = "TEXT")
    private String contentText;

    @Column(name = "content_file_url")
    private String contentFileUrl;

    @Column(name = "content_file_name")
    private String contentFileName;

    @Column(name = "content_file_size")
    private Integer contentFileSize;

    @Column(name = "content_file_type")
    private String contentFileType;

}

