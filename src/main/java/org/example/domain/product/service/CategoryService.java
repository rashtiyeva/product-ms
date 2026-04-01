package org.example.domain.product.service;

import org.example.domain.product.model.dto.PageDetailDto;
import org.example.domain.product.model.enums.CategoryStatus;
import org.example.domain.product.model.request.CategoryCreateRequest;
import org.example.domain.product.model.request.CategoryUpdateRequest;
import org.example.domain.product.model.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface CategoryService {

    CategoryResponse createCategory(CategoryCreateRequest request, CategoryStatus status);

    CategoryResponse updateCategory(Long id, CategoryStatus status, CategoryUpdateRequest request);

    Page<CategoryResponse> getCategoriesByStatus(CategoryStatus status, PageDetailDto pageDetailDto);

    CategoryResponse getCategoryById(Long id, CategoryStatus status);


    void deleteCategory(Long id, CategoryStatus status);
}
