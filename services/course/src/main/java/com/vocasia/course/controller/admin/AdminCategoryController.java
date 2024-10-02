package com.vocasia.course.controller.admin;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;
import com.vocasia.course.request.category.StoreCategoryRequest;
import com.vocasia.course.request.category.UpdateCategoryRequest;
import com.vocasia.course.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
@Tag(name = "Admin Category Controller", description = "Controller kategori untuk admin")
public class AdminCategoryController {

    private final ICategoryService categoryService;

    public AdminCategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Mendapatkan semua kategori",
            description = "Mendapatkan semua kategori kursus"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mendapatkan data"
    )
    @GetMapping("/categories")
    public ResponseDto index() {
        List<Category> categories = categoryService.getOnlyParentCategories();

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories.stream().map(CategoryMapper::mapToDto));

        return new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null);
    }

    @Operation(
            summary = "Menambah kategori baru",
            description = "Menambahkan kategori kursus baru"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Berhasil menambahkan data"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Data yang diberikan tidak valid"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @PostMapping("/categories")
    public ResponseEntity<ResponseDto> store(@RequestParam(value = "icon", required = false) @Valid MultipartFile icon, @RequestParam("name") String name, @RequestParam(value = "parent_id", required = false) Long parentId) {
        StoreCategoryRequest storeCategoryRequest = new StoreCategoryRequest();

        storeCategoryRequest.setName(name);
        storeCategoryRequest.setIcon(icon);
        storeCategoryRequest.setParentId(parentId);

        Map<String, Object> response = new HashMap<>();

        try {
            Category category = categoryService.store(storeCategoryRequest);

            response.put("course", CategoryMapper.mapToDto(category));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(false, "Gagal mengupload icon", null, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(true, "Berhasil menambahkan kategori", response, null));
    }

    @Operation(
            summary = "Mendapatkan data kategori",
            description = "Mendapatkan data kategori kursus berdasarkan ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mendapatkan data"
    )
    @GetMapping("/categories/{categoryId}")
    public ResponseDto show(@PathVariable Long categoryId) {
        Category category = categoryService.show(categoryId);

        Map<String, Object> response = new HashMap<>();
        response.put("category", CategoryMapper.mapToDto(category));

        return new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null);
    }

    @Operation(
            summary = "Memperbarui data kategori",
            description = "Memperbarui data kategori kursus"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil memperbarui data"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Data yang diberikan tidak valid"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long categoryId,
                                              @RequestParam(value = "icon", required = false) MultipartFile icon,
                                              @RequestParam("name") String name,
                                              @RequestParam(value = "parent_id", required = false) Long parentId) {
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest();

        updateCategoryRequest.setName(name);
        updateCategoryRequest.setIcon(icon);
        updateCategoryRequest.setParentId(parentId);

        Map<String, Object> response = new HashMap<>();

        try {
            Category updatedCategory = categoryService.update(categoryId, updateCategoryRequest);

            response.put("category", CategoryMapper.mapToDto(updatedCategory));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal mengupload icon", null, e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(false, "Kategori tidak ditemukan", null, e.getMessage()));
        }

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil memperbarui kategori", response, null));
    }

    @Operation(
            summary = "Menghapus data kategori",
            description = "Menghapus data kategori kursus"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil menghapus data"
    )
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> destroy(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menghapus data kategori", null, null));
    }

}
