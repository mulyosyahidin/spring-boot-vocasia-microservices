package com.vocasia.catalog.controller.admin;

import com.vocasia.catalog.dto.ResponseDto;
import com.vocasia.catalog.dto.data.CategoryDto;
import com.vocasia.catalog.entity.Category;
import com.vocasia.catalog.mapper.CategoryMapper;
import com.vocasia.catalog.request.category.StoreCategoryRequest;
import com.vocasia.catalog.request.category.SyncCategoryRequest;
import com.vocasia.catalog.request.category.UpdateCategoryRequest;
import com.vocasia.catalog.service.ICategoryService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminCategoryController {

    private final Logger logger = LoggerFactory.getLogger(AdminCategoryController.class);

    private final ICategoryService categoryService;

    public AdminCategoryController(ICategoryService iCategoryService) {
        this.categoryService = iCategoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDto> getAllCategories(@RequestHeader("vocasia-correlation-id") String correlationId) {
        logger.info("AdminCategoryController.getAllCategories called");

        List<Category> categories = categoryService.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories.stream().map(CategoryMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseDto> saveCategory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    @Valid @RequestBody StoreCategoryRequest storeCategoryRequest) {
        logger.info("AdminCategoryController.saveCategory called");

        Category oldCategory = categoryService.findById(storeCategoryRequest.getId());

        if (oldCategory != null) {
            return ResponseEntity
                    .status(HttpStatus.SC_CONFLICT)
                    .body(new ResponseDto(false, "Data kategori sudah ada", null, null));
        }

        Category category = null;

        try {
            category = categoryService.save(storeCategoryRequest);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal menyimpan data kategori", null, e.getMessage()));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("category", CategoryMapper.mapToDto(category));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menyimpan data kategori", response, null));
    }

    @PostMapping("/categories/sync")
    public ResponseEntity<ResponseDto> syncCategories(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @RequestBody SyncCategoryRequest syncCategoryRequest) {
        logger.info("AdminCategoryController.syncCategories called");

        categoryService.deleteAll();

        try {
            for (CategoryDto categoryDto : syncCategoryRequest.getCategories()) {
                StoreCategoryRequest storeCategoryRequest = CategoryMapper.mapToStoreRequest(categoryDto);

                categoryService.save(storeCategoryRequest);
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal menyinkronkan data kategori", null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menyinkronkan data kategori", null, null));
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> updateCategory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @PathVariable("categoryId") Long categoryId,
                                                      @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        logger.info("AdminCategoryController.updateCategory called");

        updateCategoryRequest.setId(categoryId);

        Category category = categoryService.findById(categoryId);

        if (category == null) {
            category = categoryService.save(updateCategoryRequest);
            Map<String, Object> response = new HashMap<>();
            response.put("category", CategoryMapper.mapToDto(category));

            return ResponseEntity
                    .status(HttpStatus.SC_CREATED)
                    .body(new ResponseDto(true, "Berhasil menyimpan data kategori", response, null));
        }

        Category updatedCategory = categoryService.update(category, updateCategoryRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("category", CategoryMapper.mapToDto(updatedCategory));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mengubah data kategori", response, null));
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> deleteCategory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @PathVariable("categoryId") Long categoryId) {
        logger.info("AdminCategoryController.deleteCategory called");

        Category category = categoryService.findById(categoryId);

        if (category == null) {
            return ResponseEntity
                    .status(HttpStatus.SC_NOT_FOUND)
                    .body(new ResponseDto(false, "Data kategori tidak ditemukan", null, null));
        }

        categoryService.delete(category);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menghapus data kategori", null, null));
    }

}
