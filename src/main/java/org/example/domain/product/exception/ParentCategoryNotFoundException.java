package org.example.domain.product.exception;

import org.example.domain.product.exception.base.NotFoundException;

public class ParentCategoryNotFoundException extends NotFoundException {
    public ParentCategoryNotFoundException(Long parentId) {
        super("Parent category not found with id: " + parentId);
    }
}
