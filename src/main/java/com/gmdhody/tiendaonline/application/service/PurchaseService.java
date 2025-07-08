package com.gmdhody.tiendaonline.application.service;

import com.gmdhody.tiendaonline.domain.model.Product;
import com.gmdhody.tiendaonline.domain.model.Purchase;
import com.gmdhody.tiendaonline.domain.model.PurchaseItem;
import com.gmdhody.tiendaonline.domain.port.out.ProductRepository;
import com.gmdhody.tiendaonline.domain.port.out.PurchaseRepository;

import java.util.List;
import java.util.Optional;

public class PurchaseService {
    private final PurchaseRepository repo;
    private final ProductRepository productRepo;

    public PurchaseService(PurchaseRepository repo, ProductRepository productRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
    }

    public Purchase create(Purchase purchase) {
        double total = 0;

        for (PurchaseItem item : purchase.getItems()) {
            Product productoActual = productRepo.findById(item.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getProducto().getId()));

            if (productoActual.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para producto: " + productoActual.getNombre());
            }

            // Descontar stock
            productoActual.setStock(productoActual.getStock() - item.getCantidad());
            productRepo.save(productoActual);

            // Sumar al total
            total += productoActual.getPrecio() * item.getCantidad();

            // Actualizar referencia del producto
            item.setProducto(productoActual);
        }

        purchase.setPrecioTotal(total);

        return repo.save(purchase);
    }

    public Optional<Purchase> getById(Long id) {
        return repo.findById(id);
    }

    public List<Purchase> getAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
