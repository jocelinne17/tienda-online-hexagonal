package com.gmdhody.tiendaonline.adapters.in.web;

import com.gmdhody.tiendaonline.domain.model.Client;
import com.gmdhody.tiendaonline.domain.port.in.CrearClienteUseCase;
import com.gmdhody.tiendaonline.application.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @MockitoBean
    private CrearClienteUseCase crearClienteUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateClient() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setNombre("Juan");
        client.setApellido("Perez");
        client.setEmail("juan.perez@example.com");

        when(crearClienteUseCase.crearCliente(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"));
    }

    @Test
    public void testGetAllClients() throws Exception {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setNombre("Juan");
        Client client2 = new Client();
        client2.setId(2L);
        client2.setNombre("Ana");

        when(clientService.getAll()).thenReturn(Arrays.asList(client1, client2));

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nombre").value("Ana"));
    }

    @Test
    public void testGetClientById() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setNombre("Juan");

        when(clientService.getById(1L)).thenReturn(Optional.of(client));

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    public void testUpdateClient() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setNombre("Juan Updated");

        when(clientService.update(Mockito.eq(1L), any(Client.class))).thenReturn(client);

        mockMvc.perform(put("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan Updated"));
    }

    @Test
    public void testDeleteClient() throws Exception {
        Mockito.doNothing().when(clientService).delete(1L);

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk()); // Puedes cambiar a isNoContent() si devuelves 204
    }
}
