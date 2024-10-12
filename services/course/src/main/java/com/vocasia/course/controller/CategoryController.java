package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.service.ICategoryService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService iCategoryService) {
        this.categoryService = iCategoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDto> getAllCategories() {
        logger.info("CategoryController.getAllCategories called");

        List<Category> categories = categoryService.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories.stream().map(CategoryMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> getCategoryById(@PathVariable Long categoryId) {
        logger.info("CategoryController.getCategoryById called");

        Category category = categoryService.findById(categoryId);

        Map<String, Object> response = new HashMap<>();
        response.put("category", CategoryMapper.mapToDto(category));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

}
