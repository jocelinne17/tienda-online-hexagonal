package com.gmdhody.tiendaonline.application.service;

import com.gmdhody.tiendaonline.domain.model.Product;
import com.gmdhody.tiendaonline.domain.port.out.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void create_debeGuardarProducto() {
        Product product = new Product();
        product.setNombre("Producto A");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setNombre("Producto A");

        when(productRepository.save(product)).thenReturn(savedProduct);

        Product result = productService.create(product);

        assertNotNull(result.getId());
        assertEquals("Producto A", result.getNombre());
        verify(productRepository).save(product);
    }

    @Test
    void getById_debeDevolverProductoSiExiste() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void getAll_debeDevolverListaDeProductos() {
        when(productRepository.findAll()).thenReturn(List.of(new Product(), new Product()));

        List<Product> products = productService.getAll();

        assertEquals(2, products.size());
    }

    @Test
    void update_debeActualizarProductoConIdCorrecto() {
        Product product = new Product();
        product.setNombre("Producto B");

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setNombre("Producto B");

        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.update(1L, product);

        assertEquals(1L, result.getId());
        assertEquals("Producto B", result.getNombre());
        verify(productRepository).save(product);
    }

    @Test
    void delete_debeEliminarProductoPorId() {
        doNothing().when(productRepository).deleteById(1L);

        productService.delete(1L);

        verify(productRepository).deleteById(1L);
    }
}
