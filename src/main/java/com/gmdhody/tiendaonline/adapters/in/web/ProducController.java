package com.gmdhody.tiendaonline.adapters.in.web;

import com.gmdhody.tiendaonline.application.service.ProductService;
import com.gmdhody.tiendaonline.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Management", description = "Operations related to product management")
@RestController
@RequestMapping("/products")
public class ProducController {
    private final ProductService productService;

    public ProducController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Crear un producto")
    @ApiResponse(responseCode = "201", description = "")
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @Operation(summary = "Obtener todos los productos")
    @ApiResponse(responseCode = "201", description = "")
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @Operation(summary = "Obtener un producto por ID")
    @ApiResponse(responseCode = "200", description = "")
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id).orElseThrow();
    }

    @Operation(summary = "Actualizar un producto")
    @ApiResponse(responseCode = "200", description = "")
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product client) {
        return productService.update(id, client);
    }

    @Operation(summary = "Eliminar un producto")
    @ApiResponse(responseCode = "204", description = "")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
