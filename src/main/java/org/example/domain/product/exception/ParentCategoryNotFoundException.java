package org.example.domain.product.exception;

public class ParentCategoryNotFoundException extends RuntimeException {
    public ParentCategoryNotFoundException(Long parentId) {
        super("Parent category not found with id: " + parentId);
    }
}
