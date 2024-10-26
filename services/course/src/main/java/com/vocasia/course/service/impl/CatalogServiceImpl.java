package com.vocasia.course.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.exception.CustomFeignException;
import com.vocasia.course.request.client.catalog.category.StoreCategoryRequest;
import com.vocasia.course.request.client.catalog.category.SyncCategoryRequest;
import com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest;
import com.vocasia.course.request.client.catalog.course.StoreCourseRequest;
import com.vocasia.course.service.ICatalogService;
import com.vocasia.course.service.client.CatalogFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class CatalogServiceImpl implements ICatalogService {

    private final Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class);

    private final CatalogFeignClient catalogFeignClient;

    @Override
    public void syncCategories(SyncCategoryRequest syncCategoryRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> catalogFeignClientSyncCategoriesResponseEntity = catalogFeignClient.syncCategories(correlationId, syncCategoryRequest);
            ResponseDto responseBody = catalogFeignClientSyncCategoriesResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();

            logger.info("CatalogServiceImpl.syncCategories:: $data: {}", data);
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public void saveCategory(StoreCategoryRequest storeCategoryRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> catalogFeignClientsaveCategoryResponseEntity = catalogFeignClient.saveCategory(correlationId, storeCategoryRequest);
            ResponseDto responseBody = catalogFeignClientsaveCategoryResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();

            logger.info("CatalogServiceImpl.save:: $data: {}", data);
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public void updateCategory(Long categoryId, UpdateCategoryRequest updateCategoryRequestToCatalog, String correlationId) {
        try {
            ResponseEntity<ResponseDto> catalogFeignClientUpdateCategoryResponseEntity = catalogFeignClient.updateCategory(correlationId, categoryId, updateCategoryRequestToCatalog);
            ResponseDto responseBody = catalogFeignClientUpdateCategoryResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();

            logger.info("CatalogServiceImpl.update:: $data: {}", data);
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public void deleteCategory(Long categoryId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> catalogFeignClientDeleteCategoryResponseEntity = catalogFeignClient.deleteCategory(correlationId, categoryId);
            ResponseDto responseBody = catalogFeignClientDeleteCategoryResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();

            logger.info("CatalogServiceImpl.delete:: $data: {}", data);
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public void saveCourse(StoreCourseRequest storeCourseRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> catalogFeignClientSaveCourseResponseEntity = catalogFeignClient.saveCourse(correlationId, storeCourseRequest);
            ResponseDto responseBody = catalogFeignClientSaveCourseResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();

            logger.info("CatalogServiceImpl.saveCourse:: $data: {}", data);
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
