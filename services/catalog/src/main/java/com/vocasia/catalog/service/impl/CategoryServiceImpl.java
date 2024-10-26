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
import java.util.stream.Collectors;

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

        if (storeCategoryRequest.getType().equals("child")) {
            Category.Parent parent = new Category.Parent();

            parent.setId(storeCategoryRequest.getParent().getId());
            parent.setName(storeCategoryRequest.getParent().getName());
            parent.setSlug(storeCategoryRequest.getParent().getSlug());
            parent.setIcon(storeCategoryRequest.getParent().getIcon());
            parent.setCreatedAt(storeCategoryRequest.getParent().getCreatedAt());
            parent.setUpdatedAt(storeCategoryRequest.getParent().getUpdatedAt());

            category.setParent(parent);
        }
        else if (storeCategoryRequest.getType().equals("parent")) {
            category.setChildren(storeCategoryRequest.getChildren().stream().map(children -> {
                Category.Children child = new Category.Children();

                child.setId(children.getId());
                child.setName(children.getName());
                child.setSlug(children.getSlug());
                child.setIcon(children.getIcon());
                child.setCreatedAt(children.getCreatedAt());
                child.setUpdatedAt(children.getUpdatedAt());

                return child;
            }).collect(Collectors.toList()));
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category save(UpdateCategoryRequest updateCategoryRequest) {
        Category category = new Category();

        category.setId(updateCategoryRequest.getId());
        category.setType(updateCategoryRequest.getType());
        category.setParentId(updateCategoryRequest.getParentId());
        category.setName(updateCategoryRequest.getName());
        category.setSlug(updateCategoryRequest.getSlug());
        category.setIcon(updateCategoryRequest.getIcon());
        category.setCreatedAt(updateCategoryRequest.getCreatedAt());
        category.setUpdatedAt(updateCategoryRequest.getUpdatedAt());

        if (updateCategoryRequest.getType().equals("child")) {
            Category.Parent parent = new Category.Parent();

            parent.setId(updateCategoryRequest.getParent().getId());
            parent.setName(updateCategoryRequest.getParent().getName());
            parent.setSlug(updateCategoryRequest.getParent().getSlug());
            parent.setIcon(updateCategoryRequest.getParent().getIcon());
            parent.setCreatedAt(updateCategoryRequest.getParent().getCreatedAt());
            parent.setUpdatedAt(updateCategoryRequest.getParent().getUpdatedAt());

            category.setParent(parent);
        }
        else if (updateCategoryRequest.getType().equals("parent")) {
            category.setChildren(updateCategoryRequest.getChildren().stream().map(children -> {
                Category.Children child = new Category.Children();

                child.setId(children.getId());
                child.setName(children.getName());
                child.setSlug(children.getSlug());
                child.setIcon(children.getIcon());
                child.setCreatedAt(children.getCreatedAt());
                child.setUpdatedAt(children.getUpdatedAt());

                return child;
            }).collect(Collectors.toList()));
        }

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

        if (updateCategoryRequest.getType().equals("child")) {
            Category.Parent parent = new Category.Parent();

            parent.setId(updateCategoryRequest.getParent().getId());
            parent.setName(updateCategoryRequest.getParent().getName());
            parent.setSlug(updateCategoryRequest.getParent().getSlug());
            parent.setIcon(updateCategoryRequest.getParent().getIcon());
            parent.setCreatedAt(updateCategoryRequest.getParent().getCreatedAt());
            parent.setUpdatedAt(updateCategoryRequest.getParent().getUpdatedAt());

            category.setParent(parent);
        }
        else if (updateCategoryRequest.getType().equals("parent")) {
            category.setChildren(updateCategoryRequest.getChildren().stream().map(children -> {
                Category.Children child = new Category.Children();

                child.setId(children.getId());
                child.setName(children.getName());
                child.setSlug(children.getSlug());
                child.setIcon(children.getIcon());
                child.setCreatedAt(children.getCreatedAt());
                child.setUpdatedAt(children.getUpdatedAt());

                return child;
            }).collect(Collectors.toList()));
        }

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
