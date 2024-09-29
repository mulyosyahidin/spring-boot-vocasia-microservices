package com.vocasia.course.mapper;

import com.vocasia.course.dto.data.CategoryDto;
import com.vocasia.course.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto mapToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setSlug(category.getSlug());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());

        // Set children
        if (category.getChildren() != null) {
            List<CategoryDto> childrenDtos = category.getChildren().stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
            dto.setChildren(childrenDtos);
        }

        return dto;
    }

}

