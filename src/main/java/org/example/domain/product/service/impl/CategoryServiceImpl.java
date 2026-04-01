package org.example.domain.product.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.product.dao.entity.Category;
import org.example.domain.product.dao.repository.CategoryRepository;
import org.example.domain.product.exception.CategoryNotFoundException;
import org.example.domain.product.exception.ParentCategoryNotFoundException;
import org.example.domain.product.mapper.CategoryMapper;
import org.example.domain.product.model.dto.PageDetailDto;
import org.example.domain.product.model.enums.CategoryStatus;
import org.example.domain.product.model.request.CategoryCreateRequest;
import org.example.domain.product.model.request.CategoryUpdateRequest;
import org.example.domain.product.model.response.CategoryResponse;
import org.example.domain.product.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request, CategoryStatus status) {
        Category category = categoryMapper.mapToCategory(request);
        category.setStatus(CategoryStatus.ACTIVE);
        applyParent(category, status, request.parentId());
        Category saved = categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponse(saved);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryStatus status, CategoryUpdateRequest request) {
        Category category = categoryRepository.findByIdAndStatus(id, status)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryMapper.updateCategory(request, category);
        applyParent(category, status, request.parentId());
        Category updated = categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponse(updated);
    }

    @Override
    public Page<CategoryResponse> getCategoriesByStatus(CategoryStatus status, PageDetailDto pageDetail) {
        PageRequest defaultPageAble = getDefaultPageAble(pageDetail.getPage(), pageDetail.getSize());
        Page<Category> categories = categoryRepository.findAllByStatus(status, defaultPageAble);
        return categories.map(categoryMapper::mapToCategoryResponse);
    }

    @Override
    public CategoryResponse getCategoryById(Long id, CategoryStatus status) {
        Category category = categoryRepository.findByIdAndStatus(id, status)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public void deleteCategory(Long id, CategoryStatus status) {
        Category category = categoryRepository.findByIdAndStatus(id, status)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.deleteById(id);
    }

    private void applyParent(Category category, CategoryStatus status, Long parentId) {
        if (parentId != null) {
            Category parent = categoryRepository.findByIdAndStatus(parentId, status)
                    .orElseThrow(() -> new ParentCategoryNotFoundException(parentId));
            category.setParent(parent);
        } else {
            category.setParent(null); // root category
        }
    }

    private static PageRequest getDefaultPageAble(int page, int size) {
        int safePage = Math.max(page, 0);
        int safeSize = Math.min(Math.max(size, 1), 50);
        return PageRequest.of(safePage, safeSize, Sort.by("createdAt").descending());
    }

}
