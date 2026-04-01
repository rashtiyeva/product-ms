package org.example.domain.product.dao.repository;

import org.example.domain.product.dao.entity.Product;
import org.example.domain.product.model.dto.ProductPreviewDto;
import org.example.domain.product.model.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByStatus(ProductStatus status, Pageable pageable);
    Optional<Product> findProductByIdAndStatus(Long id, ProductStatus status);

    @Query("""
        SELECT new org.example.domain.product.model.dto.ProductPreviewDto(
            p.id,
            p.title,
            p.price,
            p.currency,
            p.slug
        )
        FROM Product p
        WHERE p.status = :status
    """)
    Page<ProductPreviewDto> findAllPreviewsByProductStatus(
            @Param("status") ProductStatus status,
            Pageable pageable
    );

    @Query("""
        SELECT new org.example.domain.product.model.dto.ProductPreviewDto(
            p.id,
            p.title,
            p.price,
            p.currency,
            p.slug
        )
        FROM Product p
        WHERE p.status = :status AND p.id > :afterId
        ORDER BY p.id ASC
    """)
    List<ProductPreviewDto> findByStatusAfterId(
            @Param("status") ProductStatus status,
            @Param("afterId") Long afterId,
            Pageable pageable
    ); //stream?
}

