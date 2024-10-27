package com.vocasia.course.service;

import com.vocasia.course.entity.Category;
import com.vocasia.course.request.category.StoreCategoryRequest;
import com.vocasia.course.request.category.UpdateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {

    List<Category> findAll();
    List<Category> findAllByParentId(Long id);
    List<Category> findAllByType(String type);

    Category findById(Long id);
    Category store(StoreCategoryRequest storeCategoryRequest) throws IOException;
    Category update(Long categoryId, UpdateCategoryRequest updateCategoryRequest) throws IOException;

    void delete(Long categoryId);

    Page<Category> findAll(Pageable pageable);

}
