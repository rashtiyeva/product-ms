package org.example.domain.product.service;

import org.example.domain.product.model.enums.ProductStatus;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.request.ProductUpdateRequest;
import org.example.domain.product.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ProductResponse saveProduct(ProductCreateRequest request);

    List<ProductResponse> getProductsByStatus(ProductStatus status);

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id,ProductUpdateRequest request);

    void deleteProduct(Long id);
}
