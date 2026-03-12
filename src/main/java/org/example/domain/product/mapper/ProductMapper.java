package org.example.domain.product.mapper;

import org.example.domain.product.dao.entity.Product;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.request.ProductUpdateRequest;
import org.example.domain.product.model.response.ProductResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(ProductUpdateRequest request, @MappingTarget Product product);

    Product mapToProduct(ProductCreateRequest request);

    ProductResponse mapToProductResponse(Product product);

}
