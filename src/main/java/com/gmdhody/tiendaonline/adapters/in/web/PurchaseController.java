package com.gmdhody.tiendaonline.adapters.in.web;

import com.gmdhody.tiendaonline.application.service.PurchaseService;
import com.gmdhody.tiendaonline.domain.model.Purchase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Purchase Management", description = "Operations related to purchase management")
@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @Operation(summary = "Crear una compra")
    @ApiResponse(responseCode = "201", description = "")
    @PostMapping
    public Purchase create(@RequestBody Purchase purchase) {
        return service.create(purchase);
    }

    @Operation(summary = "Obtener todas las compras")
    @ApiResponse(responseCode = "200", description = "")
    @GetMapping
    public List<Purchase> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Obtener una compra por ID")
    @ApiResponse(responseCode = "200", description = "")
    @GetMapping("/{id}")
    public Purchase getById(@PathVariable Long id) {
        return service.getById(id).orElseThrow();
    }

    @Operation(summary = "Actualizar una compra")
    @ApiResponse(responseCode = "200", description = "")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
