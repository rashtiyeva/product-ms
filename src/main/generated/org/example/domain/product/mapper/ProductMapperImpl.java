package org.example.domain.product.mapper;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.example.domain.product.dao.entity.Product;
import org.example.domain.product.model.enums.Currency;
import org.example.domain.product.model.request.ProductCreateRequest;
import org.example.domain.product.model.request.ProductPatchRequest;
import org.example.domain.product.model.request.ProductUpdateRequest;
import org.example.domain.product.model.response.ProductResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T12:13:00+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product mapToProduct(ProductCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.title( request.title() );
        product.slug( request.slug() );
        product.description( request.description() );
        product.price( request.price() );
        product.currency( request.currency() );
        product.isLimitedEdition( request.isLimitedEdition() );
        product.isPreorder( request.isPreorder() );
        product.stockQuantity( request.stockQuantity() );

        return product.build();
    }

    @Override
    public void updateProduct(ProductUpdateRequest request, Product product) {
        if ( request == null ) {
            return;
        }

        if ( request.title() != null ) {
            product.setTitle( request.title() );
        }
        if ( request.slug() != null ) {
            product.setSlug( request.slug() );
        }
        if ( request.description() != null ) {
            product.setDescription( request.description() );
        }
        if ( request.price() != null ) {
            product.setPrice( request.price() );
        }
        if ( request.currency() != null ) {
            product.setCurrency( request.currency() );
        }
        product.setStockQuantity( request.stockQuantity() );
    }

    @Override
    public void patchProduct(ProductPatchRequest request, Product product) {
        if ( request == null ) {
            return;
        }

        if ( request.title() != null ) {
            product.setTitle( request.title() );
        }
        if ( request.slug() != null ) {
            product.setSlug( request.slug() );
        }
        if ( request.description() != null ) {
            product.setDescription( request.description() );
        }
        if ( request.price() != null ) {
            product.setPrice( request.price() );
        }
        if ( request.currency() != null ) {
            product.setCurrency( request.currency() );
        }
        product.setStockQuantity( request.stockQuantity() );
    }

    @Override
    public ProductResponse mapToProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String slug = null;
        String description = null;
        BigDecimal price = null;
        Currency currency = null;
        Integer stockQuantity = null;

        id = product.getId();
        title = product.getTitle();
        slug = product.getSlug();
        description = product.getDescription();
        price = product.getPrice();
        currency = product.getCurrency();
        stockQuantity = product.getStockQuantity();

        Boolean isLimitedEdition = null;
        Boolean isPreorder = null;

        ProductResponse productResponse = new ProductResponse( id, title, slug, description, price, currency, isLimitedEdition, isPreorder, stockQuantity );

        return productResponse;
    }
}
