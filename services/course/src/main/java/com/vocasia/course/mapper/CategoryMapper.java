package com.vocasia.course.mapper;

import com.vocasia.course.config.AwsConfigProperties;
import com.vocasia.course.dto.data.CategoryDto;
import com.vocasia.course.entity.Category;
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

}
