package com.gmdhody.tiendaonline.adapters.out.persistence.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseEntityTest {

    @Test
    void testGettersAndSetters() {
        PurchaseEntity purchase = new PurchaseEntity();

        // Mock ClientEntity
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setNombre("Juan");
        client.setApellido("Perez");
        client.setEmail("juan.perez@example.com");
        client.setTelefono("123456789");
        client.setDireccion("Calle Falsa 123");

        // Mock PurchaseItemEntity
        PurchaseItemEntity item1 = new PurchaseItemEntity();
        // Aquí debes setear las propiedades necesarias de item1, ejemplo:
        // item1.setId(1L);
        // item1.setCantidad(2);
        // item1.setProducto(...); etc.

        PurchaseItemEntity item2 = new PurchaseItemEntity();
        // Igual que el anterior, setear propiedades si quieres.

        purchase.setId(100L);
        purchase.setClient(client);
        purchase.setItems(List.of(item1, item2));
        purchase.setPrecioTotal(1500.50);
        purchase.setFecha(LocalDate.of(2025, 7, 8));

        assertEquals(100L, purchase.getId());
        assertEquals(client, purchase.getClient());
        assertEquals(2, purchase.getItems().size());
        assertEquals(1500.50, purchase.getPrecioTotal());
        assertEquals(LocalDate.of(2025, 7, 8), purchase.getFecha());
    }
}
