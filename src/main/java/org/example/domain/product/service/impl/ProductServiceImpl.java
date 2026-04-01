package org.example.domain.product.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.product.dao.entity.Product;
import org.example.domain.product.dao.repository.ProductRepository;
import org.example.domain.product.exception.ProductNotFoundException;
import org.example.domain.product.mapper.ProductMapper;
import org.example.domain.product.model.dto.PageDetailDto;
import org.example.domain.product.model.dto.ProductPreviewDto;
import org.example.domain.product.model.enums.ProductStatus;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.request.ProductPatchRequest;
import org.example.domain.product.model.request.ProductUpdateRequest;
import org.example.domain.product.model.response.ProductPreviewResponse;
import org.example.domain.product.model.response.ProductResponse;
import org.example.domain.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductResponse saveProduct(ProductCreateRequest request) {
        Product product = productMapper.mapToProduct(request);
        product.setStatus(ProductStatus.ACTIVE);
        if (product.getStockQuantity() <= 0) {
            product.setStockQuantity(1);
        }
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductResponse(savedProduct);
    }

    @Override
    public Page<ProductResponse> getProductsByStatus(ProductStatus status, PageDetailDto pageDetail) {
        PageRequest defaultPageAble = getDefaultPageAble(pageDetail.getPage(), pageDetail.getSize());
        Page<Product> products = productRepository.findAllByStatus(status, defaultPageAble);
       return products.map(productMapper::mapToProductResponse);
    }

    @Override
    public Page<ProductPreviewResponse> getProductsPreviewsByStatus(ProductStatus status, PageDetailDto pageDetail) {
        PageRequest defaultPageAble = getDefaultPageAble(pageDetail.getPage(), pageDetail.getSize());
        Page<ProductPreviewDto> products = productRepository.findAllPreviewsByProductStatus(status, defaultPageAble);
        return products.map(productMapper::mapToProductPreviewResponse);
    }

    @Override
    public List<ProductPreviewResponse> getProductsByStatusCursor(ProductStatus status, Long afterId, int limit) {

        Pageable pageable = PageRequest.of(0, limit, Sort.by("id").ascending());

        List<ProductPreviewDto> products;

        if (afterId == null) {
            products = productRepository.findAllPreviewsByProductStatus(status, pageable).getContent();
        } else {
            products = productRepository.findByStatusAfterId(status, afterId, pageable);
        }

        return products.stream()
                .map(productMapper::mapToProductPreviewResponse)
                .toList();
    }

    @Override
    public ProductResponse getProduct(Long id,  ProductStatus status) {
        Product product = productRepository.findProductByIdAndStatus(id,status)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return productMapper.mapToProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductStatus status, ProductUpdateRequest request) {

        Product product = productRepository.findProductByIdAndStatus(id,status)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productMapper.updateProduct(request, product);

        Product updatedProduct = productRepository.save(product);

        return productMapper.mapToProductResponse(updatedProduct);
    }

    @Override
    public ProductResponse patchProduct(Long id, ProductStatus status, ProductPatchRequest request) {
        Product product = productRepository.findProductByIdAndStatus(id,status)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productMapper.patchProduct(request, product);

        Product updatedProduct = productRepository.save(product);

        return productMapper.mapToProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id,  ProductStatus status){

        Product product = productRepository.findProductByIdAndStatus(id,status)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);
    }

    private static PageRequest getDefaultPageAble(int page, int size) {
        int safePage = Math.max(page, 0);
        int safeSize = Math.min(Math.max(size, 1), 50);
        return PageRequest.of(safePage, safeSize, Sort.by("createdAt").descending());
    }


}
