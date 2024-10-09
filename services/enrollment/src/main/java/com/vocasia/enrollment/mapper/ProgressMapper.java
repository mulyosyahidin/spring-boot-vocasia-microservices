package com.vocasia.enrollment.mapper;

import com.vocasia.enrollment.dto.data.ProgressDto;
import com.vocasia.enrollment.entity.Progress;
import org.springframework.stereotype.Component;

@Component
public class ProgressMapper {

    public static ProgressDto mapToDto(Progress progress) {
        ProgressDto progressDto = new ProgressDto();

        progressDto.setId(progress.getId());
        progressDto.setEnrollmentId(progress.getEnrollmentId());
        progressDto.setLessonId(progress.getLessonId());
        progressDto.setStatus(progress.getStatus());
        progressDto.setWatchedDuration(progress.getWatchedDuration());
        progressDto.setLastAccessed(progress.getLastAccessed());
        progressDto.setCreatedAt(progress.getCreatedAt());
        progressDto.setUpdatedAt(progress.getUpdatedAt());

        return progressDto;
    }

    public static Progress mapToEntity(ProgressDto progressDto) {
        Progress progress = new Progress();

        progress.setId(progressDto.getId());
        progress.setEnrollmentId(progressDto.getEnrollmentId());
        progress.setLessonId(progressDto.getLessonId());
        progress.setStatus(progressDto.getStatus());
        progress.setWatchedDuration(progressDto.getWatchedDuration());
        progress.setLastAccessed(progressDto.getLastAccessed());
        progress.setCreatedAt(progressDto.getCreatedAt());
        progress.setUpdatedAt(progressDto.getUpdatedAt());

        return progress;
    }

}
