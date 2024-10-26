package com.vocasia.catalog.mapper;

import com.vocasia.catalog.config.AwsConfigProperties;
import com.vocasia.catalog.dto.data.CategoryDto;
import com.vocasia.catalog.entity.Category;
import com.vocasia.catalog.request.category.StoreCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private static AwsConfigProperties awsConfigProperties;

    @Autowired
    public CategoryMapper(AwsConfigProperties awsConfigProperties, ApplicationContext applicationContext) {
        CategoryMapper.awsConfigProperties = awsConfigProperties;
    }

    public static CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setType(category.getType());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setName(category.getName());
        categoryDto.setIcon(category.getIcon());
        categoryDto.setSlug(category.getSlug());
        categoryDto.setCreatedAt(category.getCreatedAt());
        categoryDto.setUpdatedAt(category.getUpdatedAt());

        if (category.getIcon() != null) {
            String bucketUrl = String.format("https://%s.s3.%s.amazonaws.com/", awsConfigProperties.getS3().getBucket(), awsConfigProperties.getS3().getRegion());
            String iconUrl = bucketUrl + category.getIcon();

            categoryDto.setIconUrl(iconUrl);
        }

        return categoryDto;
    }

    public static StoreCategoryRequest mapToStoreRequest(CategoryDto categoryDto) {
        StoreCategoryRequest storeCategoryRequest = new StoreCategoryRequest();

        storeCategoryRequest.setId(categoryDto.getId());
        storeCategoryRequest.setType(categoryDto.getType());
        storeCategoryRequest.setParentId(categoryDto.getParentId());
        storeCategoryRequest.setName(categoryDto.getName());
        storeCategoryRequest.setSlug(categoryDto.getSlug());
        storeCategoryRequest.setIcon(categoryDto.getIcon());
        storeCategoryRequest.setCreatedAt(categoryDto.getCreatedAt());
        storeCategoryRequest.setUpdatedAt(categoryDto.getUpdatedAt());

        return storeCategoryRequest;
    }

}
