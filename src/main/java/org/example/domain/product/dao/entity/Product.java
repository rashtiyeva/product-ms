package org.example.domain.product.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.domain.product.model.enums.Currency;
import org.example.domain.product.model.enums.ProductStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String slug;
    String description;
    BigDecimal price;

    @Enumerated(EnumType.STRING)
    Currency currency;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    boolean isLimitedEdition = false;

    boolean isPreorder = false;

    Integer stockQuantity;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
