package org.example.domain.product.dao.repository;

import org.example.domain.product.dao.entity.Product;
import org.example.domain.product.model.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductStatus(ProductStatus status);

}

