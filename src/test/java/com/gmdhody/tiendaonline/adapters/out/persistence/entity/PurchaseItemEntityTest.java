package com.gmdhody.tiendaonline.adapters.out.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseItemEntityTest {

    @Test
    void testGettersAndSetters() {
        PurchaseItemEntity purchaseItem = new PurchaseItemEntity();

        // Crear mocks para ProductEntity y PurchaseEntity
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setNombre("Producto 1");
        product.setPrecio(100.0);
        product.setStock(10);

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(1L);

        purchaseItem.setId(10L);
        purchaseItem.setProduct(product);
        purchaseItem.setCantidad(5);
        purchaseItem.setPurchase(purchase);

        assertEquals(10L, purchaseItem.getId());
        assertEquals(product, purchaseItem.getProduct());
        assertEquals(5, purchaseItem.getCantidad());
        assertEquals(purchase, purchaseItem.getPurchase());
    }
}

