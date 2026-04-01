package org.example.domain.product.exception;

import org.example.domain.product.exception.base.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException(Long id) {
        super("Category not found with id: " + id);
    }
}
