package org.example.domain.product.model.request;

import org.example.domain.product.model.enums.Currency;
import org.example.domain.product.model.enums.ProductStatus;
import java.math.BigDecimal;

public record ProductUpdateRequest(
        String title,
        String slug,
        String description,
        BigDecimal price,
        Currency currency,
        ProductStatus productStatus,
        Boolean isLimitedEdition,
        Boolean isPreorder,
        Integer stockQuantity
) {}