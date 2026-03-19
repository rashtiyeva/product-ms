package org.example.domain.product.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.domain.product.dao.entity.Category;
import org.example.domain.product.dao.repository.CategoryRepository;
import org.example.domain.product.model.enums.CategoryStatus;
import org.example.domain.product.model.request.CategoryCreateRequest;
import org.example.domain.product.model.request.CategoryUpdateRequest;
import org.example.domain.product.model.response.CategoryResponse;
import org.example.domain.product.model.response.ProductResponse;
import org.example.domain.product.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping()
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody @Valid CategoryCreateRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @GetMapping()
    public ResponseEntity<Page<CategoryResponse>> getCategoriesByStatus(
            @RequestParam CategoryStatus status,
            @RequestParam (defaultValue = "0") @Min(0) int page,
            @RequestParam (defaultValue = "20") @Max(100) int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<CategoryResponse> products = categoryService.getCategoriesByStatus(status, pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable @Positive Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable @Positive Long id,
            @RequestBody @Valid CategoryUpdateRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deleteCategory(
            @PathVariable @Positive Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
