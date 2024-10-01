package com.vocasia.course.service;

import com.vocasia.course.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> index();
    Category show(Long id);

}
