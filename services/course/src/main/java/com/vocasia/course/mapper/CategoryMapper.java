package com.vocasia.course.mapper;

import com.vocasia.course.dto.data.CategoryDto;
import com.vocasia.course.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public static CategoryDto mapToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setSlug(category.getSlug());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());

        if (category.getChildren() != null) {
            List<CategoryDto> childrenDtos = category.getChildren().stream()
                    .map(CategoryMapper::mapToDto)
                    .collect(Collectors.toList());
            dto.setChildren(childrenDtos);
        } else {
            dto.setChildren(new ArrayList<>());
        }

        return dto;
    }

    public static Category mapToEntity(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setSlug(dto.getSlug());
        category.setCreatedAt(dto.getCreatedAt());
        category.setUpdatedAt(dto.getUpdatedAt());

        // Map children if any
        if (dto.getChildren() != null) {
            List<Category> childrenEntities = dto.getChildren().stream()
                    .map(CategoryMapper::mapToEntity)
                    .collect(Collectors.toList());
            category.setChildren(childrenEntities);
        }

        return category;
    }

}

