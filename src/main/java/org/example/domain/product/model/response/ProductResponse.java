package org.example.domain.product.model.response;

import org.example.domain.product.model.enums.Currency;
import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String title,
        String slug,
        String description,
        BigDecimal price,
        Currency currency,
        Boolean isLimitedEdition,
        Boolean isPreorder,
        Integer stockQuantity
) {}