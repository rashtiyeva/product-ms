package org.example.domain.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.product.dao.entity.Product;
import org.example.domain.product.dao.repository.ProductRepository;
import org.example.domain.product.exception.ProductNotFoundException;
import org.example.domain.product.mapper.ProductMapper;
import org.example.domain.product.model.enums.ProductStatus;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.request.ProductUpdateRequest;
import org.example.domain.product.model.response.ProductResponse;
import org.example.domain.product.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductResponse saveProduct(ProductCreateRequest request) {
        Product product = productMapper.mapToProduct(request);
        product.setProductStatus(ProductStatus.ACTIVE);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getProductsByStatus(ProductStatus status) {
       List<Product> activeProducts = productRepository.findAllByProductStatus(status);
       return activeProducts.stream()
               .map(productMapper::mapToProductResponse)
               .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return productMapper.mapToProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productMapper.updateProduct(request, product);

        Product updatedProduct = productRepository.save(product);

        return productMapper.mapToProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

//        product.setDeleted(true);
        productRepository.delete(product);
    }

}
