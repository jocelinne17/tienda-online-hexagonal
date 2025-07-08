package com.gmdhody.tiendaonline.application.service;

import com.gmdhody.tiendaonline.domain.model.Client;
import com.gmdhody.tiendaonline.domain.port.in.CrearClienteUseCase;
import com.gmdhody.tiendaonline.domain.port.out.ClientRepository;
import com.gmdhody.tiendaonline.domain.port.out.ClienteEventPublisher;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;

public class ClientService implements CrearClienteUseCase {
    private final ClientRepository clientRepository;
    private final ClienteEventPublisher clienteEventPublisher;

    public ClientService(ClientRepository clientRepository,
                         ClienteEventPublisher clienteEventPublisher) {
        this.clientRepository = clientRepository;
        this.clienteEventPublisher = clienteEventPublisher;
    }

    @Override
    public Client crearCliente(Client client) {
        Client clienteGuardado = clientRepository.save(client);
        clienteEventPublisher.publicarClienteCreado(clienteGuardado); // enviar el objeto completo
        return clienteGuardado;
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
