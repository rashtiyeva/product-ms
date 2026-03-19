package org.example.domain.product.model.response;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        Long parentId
) { }

