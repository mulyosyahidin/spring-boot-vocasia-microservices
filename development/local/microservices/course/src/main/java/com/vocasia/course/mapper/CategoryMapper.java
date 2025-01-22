package com.vocasia.course.mapper;

import com.vocasia.course.config.AwsConfigProperties;
import com.vocasia.course.dto.data.CategoryDto;
import com.vocasia.course.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public static CategoryDto mapToDto(Category category, Category parentCategory, List<Category> children) {
        CategoryDto categoryDto = new CategoryDto();
        String bucketUrl = String.format("https://%s.s3.%s.amazonaws.com/", awsConfigProperties.getS3().getBucket(), awsConfigProperties.getS3().getRegion());

        categoryDto.setId(category.getId());
        categoryDto.setType(category.getType());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setName(category.getName());
        categoryDto.setIcon(category.getIcon());
        categoryDto.setSlug(category.getSlug());
        categoryDto.setCreatedAt(category.getCreatedAt());
        categoryDto.setUpdatedAt(category.getUpdatedAt());

        if (category.getIcon() != null) {
            String iconUrl = bucketUrl + category.getIcon();

            categoryDto.setIconUrl(iconUrl);
        }

        if (category.getType().equals("child")) {
            CategoryDto.Parent parent = new CategoryDto.Parent();

            parent.setId(parentCategory.getId());
            parent.setName(parentCategory.getName());
            parent.setSlug(parentCategory.getSlug());
            parent.setType(parentCategory.getType());
            parent.setIcon(parentCategory.getIcon());
            parent.setCreatedAt(parentCategory.getCreatedAt());
            parent.setUpdatedAt(parentCategory.getUpdatedAt());

            if (parentCategory.getIcon() != null) {
                String iconUrl = bucketUrl + parentCategory.getIcon();

                parent.setIconUrl(iconUrl);
            }

            categoryDto.setParent(parent);
        }
        else if (category.getType().equals("parent")) {
            List<CategoryDto.Children> childrenDto = children.stream().map(child -> {
                CategoryDto.Children children1 = new CategoryDto.Children();

                children1.setId(child.getId());
                children1.setName(child.getName());
                children1.setSlug(child.getSlug());
                children1.setType(child.getType());
                children1.setIcon(child.getIcon());
                children1.setCreatedAt(child.getCreatedAt());
                children1.setUpdatedAt(child.getUpdatedAt());

                if (child.getIcon() != null) {
                    String iconUrl = bucketUrl + child.getIcon();

                    children1.setIconUrl(iconUrl);
                }

                return children1;
            }).toList();

            categoryDto.setChildren(childrenDto);
        }

        return categoryDto;
    }

}
