package com.gmdhody.tiendaonline.domain.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PurchaseItemTest {

    @Test
    void debeAsignarProductoYCantidadCorrectamente() {
        // Arrange
        Product producto = new Product();
        producto.setId(1L);
        producto.setNombre("Teclado");
        producto.setPrecio(250.0);
        producto.setStock(50);

        PurchaseItem item = new PurchaseItem();
        item.setProducto(producto);
        item.setCantidad(3);

        // Assert
        assertNotNull(item.getProducto());
        assertEquals("Teclado", item.getProducto().getNombre());
        assertEquals(3, item.getCantidad());
    }
}
