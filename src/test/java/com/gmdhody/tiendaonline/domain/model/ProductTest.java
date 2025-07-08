package com.gmdhody.tiendaonline.domain.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    void debeCrearProductoYObtenerValores() {
        // Arrange
        Product producto = new Product();
        producto.setId(1L);
        producto.setNombre("Laptop");
        producto.setPrecio(999.99);
        producto.setStock(10);

        // Assert
        assertEquals(1L, producto.getId());
        assertEquals("Laptop", producto.getNombre());
        assertEquals(999.99, producto.getPrecio());
        assertEquals(10, producto.getStock());
    }

    @Test
    void constructorPorDefectoNoDebeSerNulo() {
        Product producto = new Product();
        assertNotNull(producto);
    }
}

