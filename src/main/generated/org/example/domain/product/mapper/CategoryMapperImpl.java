package org.example.domain.product.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.domain.product.dao.entity.Category;
import org.example.domain.product.model.request.CategoryCreateRequest;
import org.example.domain.product.model.request.CategoryUpdateRequest;
import org.example.domain.product.model.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T12:12:59+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category mapToCategory(CategoryCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( request.name() );
        category.description( request.description() );

        return category.build();
    }

    @Override
    public void updateCategory(CategoryUpdateRequest request, Category category) {
        if ( request == null ) {
            return;
        }

        if ( request.name() != null ) {
            category.setName( request.name() );
        }
        if ( request.description() != null ) {
            category.setDescription( request.description() );
        }
    }

    @Override
    public CategoryResponse mapToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        Long parentId = null;
        Long id = null;
        String name = null;
        String description = null;

        parentId = categoryParentId( category );
        id = category.getId();
        name = category.getName();
        description = category.getDescription();

        CategoryResponse categoryResponse = new CategoryResponse( id, name, description, parentId );

        return categoryResponse;
    }

    @Override
    public Set<CategoryResponse> mapToResponseSet(Set<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        Set<CategoryResponse> set = LinkedHashSet.newLinkedHashSet( categories.size() );
        for ( Category category : categories ) {
            set.add( mapToCategoryResponse( category ) );
        }

        return set;
    }

    private Long categoryParentId(Category category) {
        Category parent = category.getParent();
        if ( parent == null ) {
            return null;
        }
        return parent.getId();
    }
}
