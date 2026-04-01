package org.example.domain.product.model.request;

public record CategoryCreateRequest(

        String name,

        String description,

        Long parentId
) {
}
