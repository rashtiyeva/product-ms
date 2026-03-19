package org.example.domain.product.service;

import org.example.domain.product.model.enums.CategoryStatus;
import org.example.domain.product.model.request.CategoryCreateRequest;
import org.example.domain.product.model.request.CategoryUpdateRequest;
import org.example.domain.product.model.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public interface CategoryService {
    CategoryResponse createCategory(CategoryCreateRequest request);

    CategoryResponse updateCategory(Long id, CategoryUpdateRequest request);

    Page<CategoryResponse> getCategoriesByStatus(CategoryStatus status, Pageable pageable);

    CategoryResponse getCategoryById(Long id);

    void deleteCategory(Long id);
}
