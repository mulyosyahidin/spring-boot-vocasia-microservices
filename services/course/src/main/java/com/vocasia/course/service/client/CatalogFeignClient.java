package com.vocasia.course.service.client;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.request.client.catalog.StoreCategoryRequest;
import com.vocasia.course.request.client.catalog.SyncCategoryRequest;
import com.vocasia.course.request.client.catalog.UpdateCategoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "catalog")
public interface CatalogFeignClient {

    @PostMapping("/api/admin/categories/sync")
    public ResponseEntity<ResponseDto> syncCategories(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      SyncCategoryRequest syncCategoryRequest);

    @PostMapping("/api/admin/categories")
    public ResponseEntity<ResponseDto> saveCategory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    StoreCategoryRequest storeCategoryRequest);

    @PutMapping("/api/admin/categories/{categoryId}")
    ResponseEntity<ResponseDto> updateCategory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                               @PathVariable("categoryId") Long categoryId,
                                               UpdateCategoryRequest updateCategoryRequestToCatalog);

    @DeleteMapping("/api/admin/categories/{categoryId}")
    ResponseEntity<ResponseDto> deleteCategory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                               @PathVariable("categoryId") Long categoryId);

}
