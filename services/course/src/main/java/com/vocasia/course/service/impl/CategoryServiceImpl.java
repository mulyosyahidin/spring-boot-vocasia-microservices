package com.vocasia.course.service.impl;

import com.vocasia.course.config.AwsConfigProperties;
import com.vocasia.course.entity.Category;
import com.vocasia.course.exception.ResourceNotFoundException;
import com.vocasia.course.packages.aws.service.IAwsService;
import com.vocasia.course.repository.CategoryRepository;
import com.vocasia.course.request.category.StoreCategoryRequest;
import com.vocasia.course.request.category.UpdateCategoryRequest;
import com.vocasia.course.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final AwsConfigProperties awsConfigProperties;

    private final IAwsService awsService;

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> index() {
        return categoryRepository.findAll();
    }

    @Override
    public Category show(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori tidak ditemukan"));
    }

    @Override
    public List<Category> getOnlyParentCategories() {
        return categoryRepository.findByParentIsNull();
    }

    @Override
    public Category store(StoreCategoryRequest storeCategoryRequest) throws IOException {
        Category category = new Category();

        category.setName(storeCategoryRequest.getName());
        category.setSlug(storeCategoryRequest.getSlug());
        category.setParent(storeCategoryRequest.getParentId() != null ? categoryRepository.findById(storeCategoryRequest.getParentId()).orElse(null) : null);

        if (storeCategoryRequest.getIcon() != null) {
            String bucketName = awsConfigProperties.getS3().getBucket();
            MultipartFile icon = storeCategoryRequest.getIcon();

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(icon.getOriginalFilename()));

            String contentType = icon.getContentType();
            long fileSize = icon.getSize();
            InputStream inputStream = icon.getInputStream();

            awsService.uploadFile(bucketName, fileName, fileSize, contentType, inputStream);

            category.setIcon(fileName);
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long categoryId, UpdateCategoryRequest updateCategoryRequest) throws IOException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Kategori tidak ditemukan"));

        category.setName(updateCategoryRequest.getName());
        category.setParent(updateCategoryRequest.getParentId() != null ?
                categoryRepository.findById(updateCategoryRequest.getParentId()).orElse(null) : null);

        if (updateCategoryRequest.getIcon() != null) {
            // Delete old icon if exists
            if (category.getIcon() != null) {
                awsService.deleteFile(awsConfigProperties.getS3().getBucket(), category.getIcon());
            }

            MultipartFile icon = updateCategoryRequest.getIcon();
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(icon.getOriginalFilename()));
            String contentType = icon.getContentType();
            long fileSize = icon.getSize();
            InputStream inputStream = icon.getInputStream();

            awsService.uploadFile(awsConfigProperties.getS3().getBucket(), fileName, fileSize, contentType, inputStream);

            category.setIcon(fileName);
        }

        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori tidak ditemukan"));

        if (category.getIcon() != null) {
            awsService.deleteFile(awsConfigProperties.getS3().getBucket(), category.getIcon());
        }

        categoryRepository.delete(category);
    }

}
