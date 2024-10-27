package com.vocasia.course.service;

import com.vocasia.course.request.client.catalog.category.StoreCategoryRequest;
import com.vocasia.course.request.client.catalog.category.SyncCategoryRequest;
import com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest;
import com.vocasia.course.request.client.catalog.course.StoreCourseRequest;

public interface ICatalogService {

    void syncCategories(SyncCategoryRequest syncCategoryRequest, String correlationId);
    void saveCategory(StoreCategoryRequest storeCategoryRequest, String correlationId);
    void updateCategory(Long categoryId, UpdateCategoryRequest updateCategoryRequestToCatalog, String correlationId);
    void deleteCategory(Long categoryId, String correlationId);

    void saveCourse(StoreCourseRequest storeCourseRequest, String correlationId);

}
