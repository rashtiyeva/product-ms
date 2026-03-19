package org.example.domain.product.model.request;

import lombok.Data;

public record CategoryCreateRequest(

        String name,

        String description,

        Long parentId
)
{ }
