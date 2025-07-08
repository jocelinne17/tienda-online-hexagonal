package com.gmdhody.tiendaonline.adapters.out.persistence.mapper;

import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ProductEntity;
import com.gmdhody.tiendaonline.domain.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void toEntity_ShouldMapDomainToEntity() {
        Product product = new Product();
        product.setId(10L);
        product.setNombre("Laptop");
        product.setPrecio(1500.0);
        product.setStock(5);

        ProductEntity entity = ProductMapper.toEntity(product);

        assertNotNull(entity);
        assertEquals(product.getId(), entity.getId());
        assertEquals(product.getNombre(), entity.getNombre());
        assertEquals(product.getPrecio(), entity.getPrecio());
        assertEquals(product.getStock(), entity.getStock());
    }

    @Test
    void toDomain_ShouldMapEntityToDomain() {
        ProductEntity entity = new ProductEntity();
        entity.setId(20L);
        entity.setNombre("Smartphone");
        entity.setPrecio(800.0);
        entity.setStock(10);

        Product product = ProductMapper.toDomain(entity);

        assertNotNull(product);
        assertEquals(entity.getId(), product.getId());
        assertEquals(entity.getNombre(), product.getNombre());
        assertEquals(entity.getPrecio(), product.getPrecio());
        assertEquals(entity.getStock(), product.getStock());
    }
}
