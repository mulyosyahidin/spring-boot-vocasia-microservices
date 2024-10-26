package com.vocasia.catalog.service;

import com.vocasia.catalog.entity.Category;
import com.vocasia.catalog.request.category.StoreCategoryRequest;
import com.vocasia.catalog.request.category.UpdateCategoryRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();
    Category save(StoreCategoryRequest storeCategoryRequest);
    Category findById(Long id);
    Category update(Category category, UpdateCategoryRequest updateCategoryRequest);
    void delete(Category category);
    void deleteAll();

}
