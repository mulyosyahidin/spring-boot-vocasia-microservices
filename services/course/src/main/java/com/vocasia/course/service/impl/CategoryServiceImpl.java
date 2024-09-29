package com.vocasia.course.service.impl;

import com.vocasia.course.dto.data.CategoryDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.exception.ResourceNotFoundException;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.repository.CategoryRepository;
import com.vocasia.course.service.ICategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();

        // Filter hanya kategori induk (parent == null)
        return categories.stream()
                .filter(category -> category.getParent() == null)
                .map(categoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori tidak ditemukan dengan ID: " + id));

        return categoryMapper.mapToDto(category);
    }

}
