package org.example.domain.product.model.response;

import org.example.domain.product.model.enums.Currency;
import java.math.BigDecimal;

public record ProductPreviewResponse(
        Long id,
        String title,
        String slug,
        BigDecimal price,
        Currency currency
) {}
