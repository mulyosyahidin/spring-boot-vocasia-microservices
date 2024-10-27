package com.vocasia.course.controller.admin;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.exception.CustomFeignException;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.request.category.StoreCategoryRequest;
import com.vocasia.course.request.category.UpdateCategoryRequest;
import com.vocasia.course.request.client.catalog.category.SyncCategoryRequest;
import com.vocasia.course.service.ICatalogService;
import com.vocasia.course.service.ICategoryService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@Validated
public class AdminCategoryController {

    private final Logger logger = LoggerFactory.getLogger(AdminCategoryController.class);

    private final ICategoryService categoryService;
    private final ICatalogService catalogService;

    public AdminCategoryController(ICategoryService iCategoryService, ICatalogService catalogService) {
        this.categoryService = iCategoryService;
        this.catalogService = catalogService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDto> getAllCategories(@RequestParam(defaultValue = "1") int page) {
        logger.info("AdminCategoryController.getAllCategories called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit);

        Page<Category> categories = categoryService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> categoriesData = categories.getContent().stream().map(category -> {
            Map<String, Object> categoryData = new HashMap<>();

            categoryData.put("category", CategoryMapper.mapToDto(category));

            if (category.getType().equals("child")) {
                Category parentCategory = categoryService.findById(category.getParentId());

                categoryData.put("parent", CategoryMapper.mapToDto(parentCategory));
            } else if (category.getType().equals("parent")) {
                List<Category> childCategories = categoryService.findAllByParentId(category.getId());

                categoryData.put("children", childCategories.stream().map(CategoryMapper::mapToDto));
            }

            return categoryData;
        }).toList();

        pagination.put("total_page", categories.getTotalPages());
        pagination.put("per_page", categories.getSize());
        pagination.put("current_page", categories.getNumber() + 1);
        pagination.put("total_items", categories.getTotalElements());

        response.put("data", categoriesData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

    @GetMapping("/categories/only-parents")
    public ResponseEntity<ResponseDto> getOnlyParentCategories() {
        logger.info("AdminCategoryController.getOnlyParentCategories called");

        List<Category> categories = categoryService.findAllByType("parent");

        Map<String, Object> response = new HashMap<>();
        response.put("data", categories.stream().map(CategoryMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

    @PostMapping("/categories/sync")
    public ResponseEntity<ResponseDto> syncCategories(@RequestHeader("vocasia-correlation-id") String correlationId) {
        logger.info("AdminCategoryController.syncCategories called");

        List<Category> allCategories = categoryService.findAll();

        try {
            SyncCategoryRequest syncCategoryRequest = new SyncCategoryRequest();
            syncCategoryRequest.setCategories(allCategories.stream().map(category -> {
                Category parentCategory = null;
                List<Category> childCategories = null;

               if (category.getType().equals("child")) {
                   parentCategory = categoryService.findById(category.getParentId());

               }
               else if (category.getType().equals("parent")) {
                   childCategories = categoryService.findAllByParentId(category.getId());
               }

                return CategoryMapper.mapToDto(category, parentCategory, childCategories);
            }).toList());

            catalogService.syncCategories(syncCategoryRequest, correlationId);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil melakukan sinkronisasi kategori ke catalog service", null, null));
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseDto> createCategory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @RequestParam(value = "icon", required = false) @Valid MultipartFile icon,
                                                      @RequestParam("name") String name,
                                                      @RequestParam(value = "parent_id", required = false) Long parentId) {
        logger.info("AdminCategoryController.createCategory called");

        StoreCategoryRequest storeCategoryRequest = new StoreCategoryRequest();

        storeCategoryRequest.setName(name);
        storeCategoryRequest.setIcon(icon);
        storeCategoryRequest.setParentId(parentId);
        storeCategoryRequest.setType(parentId == null ? "parent" : "child");

        Map<String, Object> response = new HashMap<>();

        try {
            Category category = categoryService.store(storeCategoryRequest);

            response.put("category", CategoryMapper.mapToDto(category));

            com.vocasia.course.request.client.catalog.category.StoreCategoryRequest storeCategoryRequestToCatalog = new com.vocasia.course.request.client.catalog.category.StoreCategoryRequest();

            storeCategoryRequestToCatalog.setId(category.getId());
            storeCategoryRequestToCatalog.setType(category.getType());
            storeCategoryRequestToCatalog.setParentId(category.getParentId());
            storeCategoryRequestToCatalog.setName(category.getName());
            storeCategoryRequestToCatalog.setSlug(category.getSlug());
            storeCategoryRequestToCatalog.setIcon(category.getIcon());
            storeCategoryRequestToCatalog.setCreatedAt(category.getCreatedAt());
            storeCategoryRequestToCatalog.setUpdatedAt(category.getUpdatedAt());

            if (category.getType().equals("child")) {
                Category parentCategory = categoryService.findById(category.getParentId());

                storeCategoryRequestToCatalog.setParent(getStoreParent(parentCategory));
            }
            else {
                List<Category> childCategories = categoryService.findAllByParentId(category.getId());

                storeCategoryRequestToCatalog.setChildren(childCategories.stream().map(AdminCategoryController::getStoreChildren).toList());
            }

            catalogService.saveCategory(storeCategoryRequestToCatalog, correlationId);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal mengupload icon", null, e.getMessage()));
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan kategori", response, null));
    }

    private static com.vocasia.course.request.client.catalog.category.StoreCategoryRequest.Parent getStoreParent(Category parentCategory) {
        com.vocasia.course.request.client.catalog.category.StoreCategoryRequest.Parent parent = new com.vocasia.course.request.client.catalog.category.StoreCategoryRequest.Parent();

        parent.setId(parentCategory.getId());
        parent.setType(parentCategory.getType());
        parent.setParentId(parentCategory.getParentId());
        parent.setName(parentCategory.getName());
        parent.setSlug(parentCategory.getSlug());
        parent.setIcon(parentCategory.getIcon());
        parent.setCreatedAt(parentCategory.getCreatedAt());
        parent.setUpdatedAt(parentCategory.getUpdatedAt());

        return parent;
    }

    private static com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest.Parent getUpdateParent(Category parentCategory) {
        com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest.Parent parent = new com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest.Parent();

        parent.setId(parentCategory.getId());
        parent.setType(parentCategory.getType());
        parent.setParentId(parentCategory.getParentId());
        parent.setName(parentCategory.getName());
        parent.setSlug(parentCategory.getSlug());
        parent.setIcon(parentCategory.getIcon());
        parent.setCreatedAt(parentCategory.getCreatedAt());
        parent.setUpdatedAt(parentCategory.getUpdatedAt());

        return parent;
    }

    private static com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest.Children getUpdateChildren(Category parentCategory) {
        com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest.Children children = new com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest.Children();

        children.setId(parentCategory.getId());
        children.setType(parentCategory.getType());
        children.setParentId(parentCategory.getParentId());
        children.setName(parentCategory.getName());
        children.setSlug(parentCategory.getSlug());
        children.setIcon(parentCategory.getIcon());
        children.setCreatedAt(parentCategory.getCreatedAt());
        children.setUpdatedAt(parentCategory.getUpdatedAt());

        return children;
    }

    private static com.vocasia.course.request.client.catalog.category.StoreCategoryRequest.Children getStoreChildren(Category parentCategory) {
        com.vocasia.course.request.client.catalog.category.StoreCategoryRequest.Children children = new com.vocasia.course.request.client.catalog.category.StoreCategoryRequest.Children();

        children.setId(parentCategory.getId());
        children.setType(parentCategory.getType());
        children.setParentId(parentCategory.getParentId());
        children.setName(parentCategory.getName());
        children.setSlug(parentCategory.getSlug());
        children.setIcon(parentCategory.getIcon());
        children.setCreatedAt(parentCategory.getCreatedAt());
        children.setUpdatedAt(parentCategory.getUpdatedAt());

        return children;
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> getCategoryById(@PathVariable Long categoryId) {
        logger.info("AdminCategoryController.getCategoryById called");

        Category category = categoryService.findById(categoryId);

        Map<String, Object> response = new HashMap<>();
        response.put("category", CategoryMapper.mapToDto(category));

        if (category.getType().equals("parent")) {
            List<Category> childCategories = categoryService.findAllByParentId(category.getId());

            response.put("children", childCategories.stream().map(CategoryMapper::mapToDto));
        } else if (category.getType().equals("child")) {
            Category parentCategory = categoryService.findById(category.getParentId());

            response.put("parent", CategoryMapper.mapToDto(parentCategory));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kategori", response, null));
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> updateCategory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @PathVariable Long categoryId,
                                                      @RequestParam(value = "icon", required = false) MultipartFile icon,
                                                      @RequestParam("name") String name,
                                                      @RequestParam(value = "parent_id", required = false) Long parentId) {
        logger.info("AdminCategoryController.updateCategory called");

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest();

        updateCategoryRequest.setName(name);
        updateCategoryRequest.setIcon(icon);
        updateCategoryRequest.setParentId(parentId);

        Map<String, Object> response = new HashMap<>();

        try {
            Category updatedCategory = categoryService.update(categoryId, updateCategoryRequest);

            response.put("category", CategoryMapper.mapToDto(updatedCategory));

            com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest updateCategoryRequestToCatalog = getUpdateCategoryRequest(updatedCategory);

            if (updatedCategory.getType().equals("child")) {
                Category parentCategory = categoryService.findById(updatedCategory.getParentId());

                updateCategoryRequestToCatalog.setParent(getUpdateParent(parentCategory));
            }
            else if (updatedCategory.getType().equals("parent")) {
                List<Category> childCategories = categoryService.findAllByParentId(updatedCategory.getId());

                updateCategoryRequestToCatalog.setChildren(childCategories.stream().map(AdminCategoryController::getUpdateChildren).toList());
            }

            catalogService.updateCategory(categoryId, updateCategoryRequestToCatalog, correlationId);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal mengupload icon", null, e.getMessage()));
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui kategori", response, null));
    }

    private static com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest getUpdateCategoryRequest(Category updatedCategory) {
        com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest updateCategoryRequestToCatalog = new com.vocasia.course.request.client.catalog.category.UpdateCategoryRequest();

        updateCategoryRequestToCatalog.setType(updatedCategory.getType());
        updateCategoryRequestToCatalog.setParentId(updatedCategory.getParentId());
        updateCategoryRequestToCatalog.setName(updatedCategory.getName());
        updateCategoryRequestToCatalog.setSlug(updatedCategory.getSlug());
        updateCategoryRequestToCatalog.setIcon(updatedCategory.getIcon());
        updateCategoryRequestToCatalog.setCreatedAt(updatedCategory.getCreatedAt());
        updateCategoryRequestToCatalog.setUpdatedAt(updatedCategory.getUpdatedAt());

        return updateCategoryRequestToCatalog;
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<ResponseDto> deleteCategory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @PathVariable Long categoryId) {
        logger.info("AdminCategoryController.deleteCategory called");

        categoryService.delete(categoryId);

        try {
            catalogService.deleteCategory(categoryId, correlationId);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menghapus data kategori", null, null));
    }

}
