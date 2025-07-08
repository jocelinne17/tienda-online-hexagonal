package com.gmdhody.tiendaonline.aplication.service;

import com.gmdhody.tiendaonline.domain.model.Client;
import com.gmdhody.tiendaonline.domain.port.out.ClientRepository;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client update(Long id, Client client) {
        client.setId(id);
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
