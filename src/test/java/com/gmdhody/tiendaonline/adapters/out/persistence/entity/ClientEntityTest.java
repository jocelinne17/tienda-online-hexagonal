package com.gmdhody.tiendaonline.adapters.out.persistence.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientEntityTest {

    @Test
    void testGettersAndSetters() {
        ClientEntity client = new ClientEntity();

        client.setId(1L);
        client.setNombre("Juan");
        client.setApellido("Pérez");
        client.setEmail("juan.perez@example.com");
        client.setTelefono("1234567890");
        client.setDireccion("Calle Falsa 123");

        assertEquals(1L, client.getId());
        assertEquals("Juan", client.getNombre());
        assertEquals("Pérez", client.getApellido());
        assertEquals("juan.perez@example.com", client.getEmail());
        assertEquals("1234567890", client.getTelefono());
        assertEquals("Calle Falsa 123", client.getDireccion());
    }
}
