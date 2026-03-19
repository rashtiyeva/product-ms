package org.example.domain.product.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.product.dao.entity.Category;
import org.example.domain.product.dao.repository.CategoryRepository;
import org.example.domain.product.exception.CategoryNotFoundException;
import org.example.domain.product.exception.ParentCategoryNotFoundException;
import org.example.domain.product.mapper.CategoryMapper;
import org.example.domain.product.model.enums.CategoryStatus;
import org.example.domain.product.model.request.CategoryCreateRequest;
import org.example.domain.product.model.request.CategoryUpdateRequest;
import org.example.domain.product.model.response.CategoryResponse;
import org.example.domain.product.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        Category category = categoryMapper.mapToCategory(request);
        category.setCategoryStatus(CategoryStatus.ACTIVE);
        applyParent(category, request.parentId());
        Category saved = categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponse(saved);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryMapper.updateCategory(request, category);
        applyParent(category, request.parentId());
        Category updated = categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponse(updated);
    }

    @Override
    public Page<CategoryResponse> getCategoriesByStatus(CategoryStatus status, Pageable pageable) {
        Page<Category> categories = categoryRepository.findAllByCategoryStatus(status, pageable);
        return categories.map(categoryMapper::mapToCategoryResponse);

    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
       categoryRepository.deleteById(id);
    }

    private void applyParent(Category category, Long parentId) {
        if (parentId != null) {
            Category parent = categoryRepository.findById(parentId)
                    .orElseThrow(() -> new ParentCategoryNotFoundException(parentId));
            category.setParent(parent);
        } else {
            category.setParent(null); // root category
        }
    }

}
