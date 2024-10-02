package com.vocasia.course.service;

import com.vocasia.course.entity.Category;
import com.vocasia.course.request.category.StoreCategoryRequest;
import com.vocasia.course.request.category.UpdateCategoryRequest;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {

    List<Category> index();
    Category show(Long id);

    List<Category> getOnlyParentCategories();

    Category store(StoreCategoryRequest storeCategoryRequest) throws IOException;
    Category update(Long categoryId, UpdateCategoryRequest updateCategoryRequest) throws IOException;

    void delete(Long categoryId);

}
