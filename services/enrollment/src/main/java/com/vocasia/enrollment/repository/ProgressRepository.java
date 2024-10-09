package com.vocasia.enrollment.repository;

import com.vocasia.enrollment.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
