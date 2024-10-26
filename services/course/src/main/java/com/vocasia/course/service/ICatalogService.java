package com.vocasia.course.service;

import com.vocasia.course.request.client.catalog.StoreCategoryRequest;
import com.vocasia.course.request.client.catalog.SyncCategoryRequest;
import com.vocasia.course.request.client.catalog.UpdateCategoryRequest;

public interface ICatalogService {

    void syncCategories(SyncCategoryRequest syncCategoryRequest, String correlationId);
    void save(StoreCategoryRequest storeCategoryRequest, String correlationId);
    void updateCategory(Long categoryId, UpdateCategoryRequest updateCategoryRequestToCatalog, String correlationId);
    void deleteCategory(Long categoryId, String correlationId);

}
