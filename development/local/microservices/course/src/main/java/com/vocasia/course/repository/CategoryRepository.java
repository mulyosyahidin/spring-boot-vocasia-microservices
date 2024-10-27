package com.vocasia.course.repository;

import com.vocasia.course.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByType(String type);
    List<Category> findByParentId(Long id);

}
