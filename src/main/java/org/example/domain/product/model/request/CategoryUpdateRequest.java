package org.example.domain.product.model.request;

public record CategoryUpdateRequest(

        String name,

        String description,

        Long parentId
) {
}
