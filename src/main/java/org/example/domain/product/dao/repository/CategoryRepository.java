package org.example.domain.product.dao.repository;

import org.example.domain.product.dao.entity.Category;
import org.example.domain.product.model.enums.CategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByCategoryStatus(CategoryStatus status, Pageable pageable);
}