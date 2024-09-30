package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.dto.data.CategoryDto;
import com.vocasia.course.service.ICategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Validated
@Tag(name = "Category Controller", description = "Controller untuk kategori")
public class CategoryController {

    private final ICategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseDto index() {
        List<CategoryDto> categories = categoryService.findAll();

        return new ResponseDto(true, "Berhasil mendapatkan data kategori", categories, null);
    }

    @GetMapping("/{id}")
    public ResponseDto getCategoryById(@PathVariable Long id) {
        CategoryDto category = categoryService.findById(id);

        return new ResponseDto(true, "Berhasil mendapatkan data kategori", category, null);
    }

    @PostMapping
    public ResponseDto store() {
        return new ResponseDto(true, "Berhasil menambah data kategori", null, null);
    }

}
