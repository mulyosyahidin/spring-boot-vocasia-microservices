package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.dto.data.CategoryDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.service.ICategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Category Controller", description = "Controller untuk kategori")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseDto index() {
        List<Category> categories = categoryService.index();

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories.stream().map(CategoryMapper::mapToDto));

        return new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseDto show(@PathVariable Long categoryId) {
        Category category = categoryService.show(categoryId);

        Map<String, Object> response = new HashMap<>();
        response.put("category", CategoryMapper.mapToDto(category));

        return new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null);
    }

    @PostMapping
    public ResponseDto store() {
        return new ResponseDto(true, "Berhasil menambah data kategori", null, null);
    }

}
