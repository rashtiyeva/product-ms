package org.example.domain.product.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.domain.product.model.dto.PageDetailDto;
import org.example.domain.product.model.enums.CategoryStatus;
import org.example.domain.product.model.request.CategoryCreateRequest;
import org.example.domain.product.model.request.CategoryUpdateRequest;
import org.example.domain.product.model.response.CategoryResponse;
import org.example.domain.product.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
@Tag(name = "Category controller", description = "Controller for handle product categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody @Valid CategoryCreateRequest request,
            @RequestParam CategoryStatus status
    ) {
        return ResponseEntity.ok(categoryService.createCategory(request, status));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getCategoriesByStatus(
            @RequestParam CategoryStatus status,
            @RequestParam(defaultValue = "0") @Min(0) @Max(Integer.MAX_VALUE) int page,
            @RequestParam(defaultValue = "20") @Min(0) @Max(50) int size) {
        PageDetailDto pageDetailDto = new PageDetailDto(page, size);
        Page<CategoryResponse> products = categoryService.getCategoriesByStatus(status, pageDetailDto);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable @Positive Long id,
            @RequestParam CategoryStatus status
    ) {
        return ResponseEntity.ok(categoryService.getCategoryById(id, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable @Positive Long id,
            @RequestBody @Valid CategoryUpdateRequest request,
            @RequestParam CategoryStatus status
    ) {
        return ResponseEntity.ok(categoryService.updateCategory(id, status, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deleteCategory(
            @PathVariable @Positive Long id,
            @RequestParam CategoryStatus status
    ) {
        categoryService.deleteCategory(id, status);
        return ResponseEntity.noContent().build();
    }

}
