package com.gmdhody.tiendaonline.adapters.in.web;


import com.gmdhody.tiendaonline.application.service.ClientService;
import com.gmdhody.tiendaonline.domain.model.Client;
import com.gmdhody.tiendaonline.domain.port.in.CrearClienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client Management", description = "Operations related to client management")
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final CrearClienteUseCase crearClienteUseCase;

    public ClientController(ClientService clientService, CrearClienteUseCase crearClienteUseCase) {
        this.clientService = clientService;
        this.crearClienteUseCase = crearClienteUseCase;
    }

    @Operation(summary = "Crear una categoria")
    @ApiResponse(responseCode = "201", description = "")
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client nuevoCliente = crearClienteUseCase.crearCliente(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @Operation(summary = "Obtener todas las categorias")
    @ApiResponse(responseCode = "201", description = "")
    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @Operation(summary = "Obtener una categoria por ID")
    @ApiResponse(responseCode = "200", description = "")
    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return clientService.getById(id).orElseThrow();
    }

    @Operation(summary = "Actualizar una categoria")
    @ApiResponse(responseCode = "200", description = "")
    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

    @Operation(summary = "Eliminar una categoria")
    @ApiResponse(responseCode = "204", description = "")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}
