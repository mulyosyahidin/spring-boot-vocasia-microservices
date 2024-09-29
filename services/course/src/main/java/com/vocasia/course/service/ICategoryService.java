package com.vocasia.course.service;

import com.vocasia.course.dto.data.CategoryDto;
import com.vocasia.course.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<CategoryDto> findAll();
    CategoryDto findById(Long id);

}
