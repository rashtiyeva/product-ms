package org.example.domain.product.service;

import org.example.domain.product.model.dto.PageDetailDto;
import org.example.domain.product.model.enums.ProductStatus;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.request.ProductPatchRequest;
import org.example.domain.product.model.request.ProductUpdateRequest;
import org.example.domain.product.model.response.ProductPreviewResponse;
import org.example.domain.product.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ProductResponse saveProduct(ProductCreateRequest request);

    Page<ProductResponse> getProductsByStatus(ProductStatus status, PageDetailDto pageDetail);

    Page<ProductPreviewResponse> getProductsPreviewsByStatus(ProductStatus status, PageDetailDto pageDetail);

    List<ProductPreviewResponse> getProductsByStatusCursor(ProductStatus status, Long afterId, int limit);

    ProductResponse getProduct(Long id, ProductStatus status);

    ProductResponse updateProduct(Long id, ProductStatus status, ProductUpdateRequest request);

    ProductResponse patchProduct(Long id, ProductStatus status, ProductPatchRequest request);

    void deleteProduct(Long id, ProductStatus status);
}
