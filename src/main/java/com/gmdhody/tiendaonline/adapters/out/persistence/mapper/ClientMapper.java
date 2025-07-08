package com.gmdhody.tiendaonline.adapters.out.persistence.mapper;

import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ClientEntity;
import com.gmdhody.tiendaonline.domain.model.Client;

public class ClientMapper {
    public static ClientEntity toEntity(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId());
        entity.setNombre(client.getNombre());
        entity.setApellido(client.getApellido());
        entity.setEmail(client.getEmail());
        entity.setTelefono(client.getTelefono());
        entity.setDireccion(client.getDireccion());
        return entity;
    }

    public static Client toDomain(ClientEntity entity) {
        Client client = new Client();
        client.setId(entity.getId());
        client.setNombre(entity.getNombre());
        client.setApellido(entity.getApellido());
        client.setEmail(entity.getEmail());
        client.setTelefono(entity.getTelefono());
        client.setDireccion(entity.getDireccion());
        return client;
    }
}
