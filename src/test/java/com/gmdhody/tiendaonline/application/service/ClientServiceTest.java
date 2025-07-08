package com.gmdhody.tiendaonline.application.service;

import com.gmdhody.tiendaonline.domain.model.Client;
import com.gmdhody.tiendaonline.domain.port.out.ClientRepository;
import com.gmdhody.tiendaonline.domain.port.out.ClienteEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private ClientRepository clientRepository;
    private ClienteEventPublisher clienteEventPublisher;
    private ClientService clientService;

    @BeforeEach
    void setup() {
        clientRepository = mock(ClientRepository.class);
        clienteEventPublisher = mock(ClienteEventPublisher.class);
        clientService = new ClientService(clientRepository, clienteEventPublisher);
    }

    @Test
    void crearCliente_debeGuardarYPublicarEvento() {
        Client cliente = new Client();
        cliente.setNombre("Joce");
        Client clienteGuardado = new Client();
        clienteGuardado.setId(1L);
        clienteGuardado.setNombre("Joce");

        when(clientRepository.save(cliente)).thenReturn(clienteGuardado);

        Client resultado = clientService.crearCliente(cliente);

        assertEquals(1L, resultado.getId());
        verify(clientRepository).save(cliente);
        verify(clienteEventPublisher).publicarClienteCreado(clienteGuardado);
    }

    @Test
    void getById_debeDevolverClienteSiExiste() {
        Client cliente = new Client();
        cliente.setId(1L);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Client> resultado = clientService.getById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void getAll_debeDevolverListaClientes() {
        when(clientRepository.findAll()).thenReturn(List.of(new Client(), new Client()));

        List<Client> clientes = clientService.getAll();

        assertEquals(2, clientes.size());
    }

    @Test
    void update_debeGuardarClienteConIdCorrecto() {
        Client cliente = new Client();
        cliente.setNombre("Joce");
        Client clienteActualizado = new Client();
        clienteActualizado.setId(1L);
        clienteActualizado.setNombre("Joce Updated");

        when(clientRepository.save(any(Client.class))).thenReturn(clienteActualizado);

        Client resultado = clientService.update(1L, cliente);

        assertEquals(1L, resultado.getId());
        assertEquals("Joce Updated", resultado.getNombre());
        verify(clientRepository).save(cliente);
    }

    @Test
    void delete_debeEliminarClientePorId() {
        doNothing().when(clientRepository).deleteById(1L);

        clientService.delete(1L);

        verify(clientRepository).deleteById(1L);
    }
}

