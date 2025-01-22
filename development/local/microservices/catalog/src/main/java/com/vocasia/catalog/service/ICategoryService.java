package com.vocasia.catalog.service;

import com.vocasia.catalog.entity.Category;
import com.vocasia.catalog.request.category.StoreCategoryRequest;
import com.vocasia.catalog.request.category.UpdateCategoryRequest;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();
    Category save(StoreCategoryRequest storeCategoryRequest);
    Category save(UpdateCategoryRequest updateCategoryRequest);
    Category findById(Long id);
    Category update(Category category, UpdateCategoryRequest updateCategoryRequest);
    void delete(Category category);
    void deleteAll();

    List<Category> findAllByType(String type);

}
