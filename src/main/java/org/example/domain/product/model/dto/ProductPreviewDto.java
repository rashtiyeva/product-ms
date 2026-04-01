package org.example.domain.product.model.dto;

import org.example.domain.product.model.enums.Currency;

import java.math.BigDecimal;

public record ProductPreviewDto(
        Long id,
        String title,
        BigDecimal price,
        Currency currency,
        String slug
) {
}
