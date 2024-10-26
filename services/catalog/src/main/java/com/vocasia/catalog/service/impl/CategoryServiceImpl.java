package com.vocasia.catalog.service.impl;

import com.vocasia.catalog.entity.Category;
import com.vocasia.catalog.exception.ResourceNotFoundException;
import com.vocasia.catalog.repository.CategoryRepository;
import com.vocasia.catalog.request.category.StoreCategoryRequest;
import com.vocasia.catalog.request.category.UpdateCategoryRequest;
import com.vocasia.catalog.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(StoreCategoryRequest storeCategoryRequest) {
        Category category = new Category();

        category.setId(storeCategoryRequest.getId());
        category.setType(storeCategoryRequest.getType());
        category.setParentId(storeCategoryRequest.getParentId());
        category.setName(storeCategoryRequest.getName());
        category.setSlug(storeCategoryRequest.getSlug());
        category.setIcon(storeCategoryRequest.getIcon());
        category.setCreatedAt(storeCategoryRequest.getCreatedAt());
        category.setUpdatedAt(storeCategoryRequest.getUpdatedAt());

        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category update(Category category, UpdateCategoryRequest updateCategoryRequest) {
        category.setType(updateCategoryRequest.getType());
        category.setParentId(updateCategoryRequest.getParentId());
        category.setName(updateCategoryRequest.getName());
        category.setSlug(updateCategoryRequest.getSlug());
        category.setIcon(updateCategoryRequest.getIcon());
        category.setUpdatedAt(updateCategoryRequest.getUpdatedAt());

        return categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

}
