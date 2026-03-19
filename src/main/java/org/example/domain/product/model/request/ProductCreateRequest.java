package org.example.domain.product.model.request;

import jakarta.validation.constraints.Min;
import org.example.domain.product.model.enums.Currency;

import java.math.BigDecimal;

public record ProductCreateRequest(
        String title,
        String slug,
        String description,
        BigDecimal price,
        Currency currency,
        boolean isLimitedEdition,
        boolean isPreorder,
        @Min(1)
        int stockQuantity
) {
}

