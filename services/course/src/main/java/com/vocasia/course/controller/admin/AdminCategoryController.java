package com.vocasia.course.controller.admin;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.request.category.StoreCategoryRequest;
import com.vocasia.course.request.category.UpdateCategoryRequest;
import com.vocasia.course.service.ICategoryService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/admin")
@Validated
public class AdminCategoryController {

    private final Logger logger = LoggerFactory.getLogger(AdminCategoryController.class);

    private final ICategoryService categoryService;

    public AdminCategoryController(ICategoryService iCategoryService) {
        this.categoryService = iCategoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDto> getAllCategories() {
        logger.debug("AdminCategoryController.getAllCategories called");

        List<Category> categories = categoryService.getOnlyParentCategories();

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories.stream().map(CategoryMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseDto> createCategory(@RequestParam(value = "icon", required = false) @Valid MultipartFile icon,
                                                      @RequestParam("name") String name,
                                                      @RequestParam(value = "parent_id", required = false) Long parentId) {
        logger.debug("AdminCategoryController.createCategory called");

        StoreCategoryRequest storeCategoryRequest = new StoreCategoryRequest();

        storeCategoryRequest.setName(name);
        storeCategoryRequest.setIcon(icon);
        storeCategoryRequest.setParentId(parentId);

        Map<String, Object> response = new HashMap<>();

        try {
            Category category = categoryService.store(storeCategoryRequest);

            response.put("course", CategoryMapper.mapToDto(category));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal mengupload icon", null, e.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan kategori", response, null));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> getCategoryById(@PathVariable Long categoryId) {
        logger.debug("AdminCategoryController.getCategoryById called");

        Category category = categoryService.findById(categoryId);

        Map<String, Object> response = new HashMap<>();
        response.put("category", CategoryMapper.mapToDto(category));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> updateCategory(@PathVariable Long categoryId,
                                                      @RequestParam(value = "icon", required = false) MultipartFile icon,
                                                      @RequestParam("name") String name,
                                                      @RequestParam(value = "parent_id", required = false) Long parentId) {
        logger.debug("AdminCategoryController.updateCategory called");

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest();

        updateCategoryRequest.setName(name);
        updateCategoryRequest.setIcon(icon);
        updateCategoryRequest.setParentId(parentId);

        Map<String, Object> response = new HashMap<>();

        try {
            Category updatedCategory = categoryService.update(categoryId, updateCategoryRequest);

            response.put("category", CategoryMapper.mapToDto(updatedCategory));
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal mengupload icon", null, e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.SC_NOT_FOUND)
                    .body(new ResponseDto(false, "Kategori tidak ditemukan", null, e.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui kategori", response, null));
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable Long categoryId) {
        logger.debug("AdminCategoryController.deleteCategory called");

        categoryService.delete(categoryId);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menghapus data kategori", null, null));
    }

}
