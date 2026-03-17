package org.example.domain.product.dao.repository;

import org.example.domain.product.dao.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

//    Optional<Category> findBySlug(String slug);
//
//    List<Category> findByActiveTrue();
}