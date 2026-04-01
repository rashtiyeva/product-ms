package org.example.domain.product.exception;

import org.example.domain.product.exception.base.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id);
    }
}
