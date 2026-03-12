package org.example.domain.product.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.domain.product.model.enums.ProductStatus;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.request.ProductUpdateRequest;
import org.example.domain.product.model.response.ProductResponse;
import org.example.domain.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductCreateRequest request) {
        return ResponseEntity.ok(productService.saveProduct(request));
    }

    @GetMapping("/active")
    //TODO: add pagination later
    public ResponseEntity<List<ProductResponse>> getProductsByStatus(
            @RequestParam ProductStatus status
    ) {
        return ResponseEntity.ok(productService.getProductsByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable @Positive Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable @Positive Long id,
            @RequestBody @Valid ProductUpdateRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(
            @PathVariable @Positive Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
