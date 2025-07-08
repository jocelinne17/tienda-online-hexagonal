package com.gmdhody.tiendaonline.application.service;

import com.gmdhody.tiendaonline.domain.model.Product;
import com.gmdhody.tiendaonline.domain.model.Purchase;
import com.gmdhody.tiendaonline.domain.model.PurchaseItem;
import com.gmdhody.tiendaonline.domain.port.out.ProductRepository;
import com.gmdhody.tiendaonline.domain.port.out.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseServiceTest {

    private PurchaseRepository purchaseRepository;
    private ProductRepository productRepository;
    private PurchaseService purchaseService;

    @BeforeEach
    void setUp() {
        purchaseRepository = mock(PurchaseRepository.class);
        productRepository = mock(ProductRepository.class);
        purchaseService = new PurchaseService(purchaseRepository, productRepository);
    }

    @Test
    void create_conStockSuficiente_debeGuardarCompraYDescontarStock() {
        // Preparar producto con stock suficiente
        Product producto = new Product();
        producto.setId(1L);
        producto.setNombre("Producto A");
        producto.setPrecio(100.0);
        producto.setStock(10);

        PurchaseItem item = new PurchaseItem();
        item.setProducto(producto);
        item.setCantidad(2);

        Purchase purchase = new Purchase();
        purchase.setItems(List.of(item));

        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(purchaseRepository.save(any(Purchase.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Purchase resultado = purchaseService.create(purchase);

        assertEquals(200.0, resultado.getPrecioTotal());
        assertEquals(8, producto.getStock()); // Stock descontado

        verify(productRepository).save(producto);
        verify(purchaseRepository).save(purchase);
    }

    @Test
    void create_conStockInsuficiente_debeLanzarExcepcion() {
        Product producto = new Product();
        producto.setId(2L);
        producto.setNombre("Producto B");
        producto.setPrecio(50.0);
        producto.setStock(1);

        PurchaseItem item = new PurchaseItem();
        item.setProducto(producto);
        item.setCantidad(5);

        Purchase purchase = new Purchase();
        purchase.setItems(List.of(item));

        when(productRepository.findById(2L)).thenReturn(Optional.of(producto));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> purchaseService.create(purchase));
        assertTrue(exception.getMessage().contains("Stock insuficiente"));

        verify(productRepository, never()).save(any());
        verify(purchaseRepository, never()).save(any());
    }

    @Test
    void getById_debeDevolverCompraSiExiste() {
        Purchase purchase = new Purchase();
        purchase.setId(1L);
        when(purchaseRepository.findById(1L)).thenReturn(Optional.of(purchase));

        Optional<Purchase> resultado = purchaseService.getById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void getAll_debeDevolverListaDeCompras() {
        when(purchaseRepository.findAll()).thenReturn(List.of(new Purchase(), new Purchase()));

        List<Purchase> compras = purchaseService.getAll();

        assertEquals(2, compras.size());
    }

    @Test
    void delete_debeEliminarCompraPorId() {
        doNothing().when(purchaseRepository).deleteById(1L);

        purchaseService.delete(1L);

        verify(purchaseRepository).deleteById(1L);
    }
}
