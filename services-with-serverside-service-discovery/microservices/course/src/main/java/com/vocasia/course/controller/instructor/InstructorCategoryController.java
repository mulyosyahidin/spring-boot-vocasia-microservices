package com.vocasia.course.controller.instructor;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.service.ICategoryService;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
@Validated
public class InstructorCategoryController {

    private final Logger logger = LoggerFactory.getLogger(InstructorCategoryController.class);

    private final ICategoryService categoryService;

    public InstructorCategoryController(ICategoryService iCategoryService) {
        this.categoryService = iCategoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDto> getAllCategories() {
        logger.info("InstructorCategoryController.getAllCategories called");

        List<Category> categories = categoryService.findAllByType("parent");
        List<Map<String, Object>> categoriesData = categories.stream().map(category -> {
            Map<String, Object> categoryData = new HashMap<>();

            List<Category> childCategories = categoryService.findAllByParentId(category.getId());

            categoryData.put("category", CategoryMapper.mapToDto(category));
            categoryData.put("children", childCategories.stream().map(CategoryMapper::mapToDto));

            return categoryData;
        }).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", categoriesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

}
