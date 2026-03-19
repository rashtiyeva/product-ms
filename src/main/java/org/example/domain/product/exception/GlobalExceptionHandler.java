package org.example.domain.product.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.product.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex,
                                                             HttpStatus status,
                                                             String error,
                                                             HttpServletRequest request) {

        log.error("Exception at {}: {}", request.getRequestURI(), ex.getMessage(), ex);

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                error,
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex,
                                                               HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, "Product not found", request);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(CategoryNotFoundException ex,
                                                                HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, "Category not found", request);
    }

    @ExceptionHandler(ParentCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleParentCategoryNotFoundException(ParentCategoryNotFoundException ex,
                                                                HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, "Parent category not found", request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex,
                                                       HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", request);
    }
}


