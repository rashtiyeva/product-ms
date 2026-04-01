package org.example.domain.product.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.domain.product.model.dto.PageDetailDto;
import org.example.domain.product.model.enums.ProductStatus;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.request.ProductPatchRequest;
import org.example.domain.product.model.request.ProductUpdateRequest;
import org.example.domain.product.model.response.ProductPreviewResponse;
import org.example.domain.product.model.response.ProductResponse;
import org.example.domain.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductCreateRequest request) {
        return ResponseEntity.ok(productService.saveProduct(request));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProductsByStatus(
            @RequestParam ProductStatus status,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int size
    ) {
        PageDetailDto pageDetailDto = new PageDetailDto(page, size);
        Page<ProductResponse> products = productService.getProductsByStatus(status, pageDetailDto);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/preview")
    public ResponseEntity<Page<ProductPreviewResponse>> getProductsPreviewsByStatus(
            @RequestParam ProductStatus status,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(0) @Max(50) int size

    ) {
        PageDetailDto pageDetailDto = new PageDetailDto(page, size);
        Page<ProductPreviewResponse> products = productService.getProductsPreviewsByStatus(status, pageDetailDto);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/cursor")
    public ResponseEntity<List<ProductPreviewResponse>> getProductsByStatusCursor(
            @RequestParam ProductStatus status,
            @RequestParam(required = false) Long afterId,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit
    ) {
        List<ProductPreviewResponse> products = productService.getProductsByStatusCursor(status, afterId, limit);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable @Positive Long id,
            @RequestParam ProductStatus status
    ) {
        return ResponseEntity.ok(productService.getProduct(id, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable @Positive Long id,
            @RequestParam ProductStatus status,
            @RequestBody @Valid ProductUpdateRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, status,  request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> patchProduct(
            @PathVariable @Positive Long id,
            @RequestParam ProductStatus status,
            @RequestBody ProductPatchRequest request
    ) {
        return ResponseEntity.ok(productService.patchProduct(id, status, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(
            @PathVariable @Positive Long id,
            @RequestParam ProductStatus status
    ) {
        productService.deleteProduct(id, status);
        return ResponseEntity.noContent().build();
    }


}
