package com.gmdhody.tiendaonline.adapters.out.persistence.mapper;

import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ProductEntity;
import com.gmdhody.tiendaonline.domain.model.Product;

public class ProductMapper {
    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setNombre(product.getNombre());
        entity.setPrecio(product.getPrecio());
        entity.setStock(product.getStock());
        return entity;
    }

    public static Product toDomain(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setNombre(entity.getNombre());
        product.setPrecio(entity.getPrecio());
        product.setStock(entity.getStock());
        return product;
    }
}
