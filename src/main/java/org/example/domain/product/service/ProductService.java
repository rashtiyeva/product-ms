package org.example.domain.product.service;

import org.example.domain.product.model.enums.ProductStatus;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.request.ProductPatchRequest;
import org.example.domain.product.model.request.ProductUpdateRequest;
import org.example.domain.product.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ProductResponse saveProduct(ProductCreateRequest request);

    Page<ProductResponse> getProductsByStatus(ProductStatus status, Pageable pageable);

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id,ProductUpdateRequest request);

    ProductResponse patchProduct(Long id, ProductPatchRequest request);

    void deleteProduct(Long id);
}
