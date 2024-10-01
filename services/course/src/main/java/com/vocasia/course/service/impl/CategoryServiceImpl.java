package com.vocasia.course.service.impl;

import com.vocasia.course.entity.Category;
import com.vocasia.course.exception.ResourceNotFoundException;
import com.vocasia.course.repository.CategoryRepository;
import com.vocasia.course.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

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


}
