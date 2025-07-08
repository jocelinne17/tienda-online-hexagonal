package com.gmdhody.tiendaonline.adapters.out.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {

    @Test
    void testGettersAndSetters() {
        ProductEntity product = new ProductEntity();

        product.setId(10L);
        product.setNombre("Teclado");
        product.setPrecio(499.99);
        product.setStock(15);

        assertEquals(10L, product.getId());
        assertEquals("Teclado", product.getNombre());
        assertEquals(499.99, product.getPrecio());
        assertEquals(15, product.getStock());
    }
}
