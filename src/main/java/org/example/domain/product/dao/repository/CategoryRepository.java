package org.example.domain.product.dao.repository;

import org.example.domain.product.dao.entity.Category;
import org.example.domain.product.model.enums.CategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByStatus(CategoryStatus status, Pageable pageable);
    Optional<Category> findByIdAndStatus(Long id, CategoryStatus status);
}