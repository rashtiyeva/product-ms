package org.example.domain.product.dao.repository;

import org.example.domain.product.dao.entity.Product;
import org.example.domain.product.model.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByProductStatus(ProductStatus status, Pageable pageable);

}

