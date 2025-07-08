package com.gmdhody.tiendaonline.domain.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClientTest {
    @Test
    void debeCrearClienteYObtenerValores() {
        // Arrange
        Client cliente = new Client();
        cliente.setId(1L);
        cliente.setNombre("Joce");
        cliente.setApellido("Arce");
        cliente.setEmail("joce@example.com");
        cliente.setTelefono("1234567890");
        cliente.setDireccion("Calle Ficticia 123");

        // Assert
        assertEquals(1L, cliente.getId());
        assertEquals("Joce", cliente.getNombre());
        assertEquals("Arce", cliente.getApellido());
        assertEquals("joce@example.com", cliente.getEmail());
        assertEquals("1234567890", cliente.getTelefono());
        assertEquals("Calle Ficticia 123", cliente.getDireccion());
    }

    @Test
    void constructorPorDefectoNoDebeSerNulo() {
        Client cliente = new Client();
        assertNotNull(cliente);
    }
}

