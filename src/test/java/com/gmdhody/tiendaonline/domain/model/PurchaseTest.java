package com.gmdhody.tiendaonline.domain.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PurchaseTest {
    @Test
    void debeCrearPurchaseYObtenerValores() {
        // Arrange
        Client cliente = new Client();
        cliente.setId(1L);
        cliente.setNombre("Joce");
        cliente.setApellido("Arce");
        cliente.setEmail("joce@example.com");

        Product producto = new Product();
        producto.setId(10L);
        producto.setNombre("Mouse");
        producto.setPrecio(150.0);
        producto.setStock(20);

        PurchaseItem item = new PurchaseItem();
        item.setProducto(producto);
        item.setCantidad(2);

        Purchase compra = new Purchase();
        compra.setId(100L);
        compra.setClient(cliente);
        compra.setItems(List.of(item));
        compra.setCantidadTotal(2);
        compra.setPrecioTotal(300.0);
        compra.setFecha(LocalDate.now());

        // Assert
        assertEquals(100L, compra.getId());
        assertEquals("Joce", compra.getClient().getNombre());
        assertNotNull(compra.getItems());
        assertEquals(1, compra.getItems().size());
        assertEquals("Mouse", compra.getItems().get(0).getProducto().getNombre());
        assertEquals(2, compra.getCantidadTotal());
        assertEquals(300.0, compra.getPrecioTotal());
        assertEquals(LocalDate.now(), compra.getFecha());
    }

    @Test
    void constructorPorDefectoNoDebeSerNulo() {
        Purchase compra = new Purchase();
        assertNotNull(compra);
    }
}

