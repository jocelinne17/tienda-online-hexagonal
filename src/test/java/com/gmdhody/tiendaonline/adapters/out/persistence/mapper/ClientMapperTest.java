package com.gmdhody.tiendaonline.adapters.out.persistence.mapper;

import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ClientEntity;
import com.gmdhody.tiendaonline.domain.model.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {

    @Test
    void toEntity_ShouldMapDomainToEntity() {
        Client client = new Client();
        client.setId(1L);
        client.setNombre("Juan");
        client.setApellido("Perez");
        client.setEmail("juan.perez@mail.com");
        client.setTelefono("1234567890");
        client.setDireccion("Calle Falsa 123");

        ClientEntity entity = ClientMapper.toEntity(client);

        assertNotNull(entity);
        assertEquals(client.getId(), entity.getId());
        assertEquals(client.getNombre(), entity.getNombre());
        assertEquals(client.getApellido(), entity.getApellido());
        assertEquals(client.getEmail(), entity.getEmail());
        assertEquals(client.getTelefono(), entity.getTelefono());
        assertEquals(client.getDireccion(), entity.getDireccion());
    }

    @Test
    void toDomain_ShouldMapEntityToDomain() {
        ClientEntity entity = new ClientEntity();
        entity.setId(2L);
        entity.setNombre("Maria");
        entity.setApellido("Gomez");
        entity.setEmail("maria.gomez@mail.com");
        entity.setTelefono("0987654321");
        entity.setDireccion("Avenida Siempre Viva 742");

        Client client = ClientMapper.toDomain(entity);

        assertNotNull(client);
        assertEquals(entity.getId(), client.getId());
        assertEquals(entity.getNombre(), client.getNombre());
        assertEquals(entity.getApellido(), client.getApellido());
        assertEquals(entity.getEmail(), client.getEmail());
        assertEquals(entity.getTelefono(), client.getTelefono());
        assertEquals(entity.getDireccion(), client.getDireccion());
    }
}

