package com.vocasia.catalog.controller;

import com.vocasia.catalog.dto.ResponseDto;
import com.vocasia.catalog.entity.Category;
import com.vocasia.catalog.mapper.CategoryMapper;
import com.vocasia.catalog.service.ICategoryService;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService iCategoryService) {
        this.categoryService = iCategoryService;
    }

    @GetMapping("/categories/only-parents")
    public ResponseEntity<ResponseDto> getOnlyParentCategories() {
        logger.info("CategoryController.getOnlyParentCategories called");

        List<Category> categories = categoryService.findAllByType("parent");

        Map<String, Object> response = new HashMap<>();
        response.put("data", categories.stream().map(CategoryMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

}
