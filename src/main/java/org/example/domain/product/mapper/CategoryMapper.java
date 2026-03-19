package org.example.domain.product.mapper;

import org.example.domain.product.dao.entity.Category;
import org.example.domain.product.dao.entity.Product;
import org.example.domain.product.model.request.CategoryCreateRequest;
import org.example.domain.product.model.request.CategoryUpdateRequest;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.response.CategoryResponse;
import org.example.domain.product.model.response.ProductResponse;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "parent", ignore = true)
    Category mapToCategory(CategoryCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(CategoryUpdateRequest request, @MappingTarget Category category);

    @Mapping(source = "parent.id", target = "parentId")
    CategoryResponse mapToCategoryResponse(Category category);

    Set<CategoryResponse> mapToResponseSet(Set<Category> categories);

}
