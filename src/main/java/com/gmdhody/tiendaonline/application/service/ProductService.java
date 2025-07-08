package com.gmdhody.tiendaonline.application.service;

import com.gmdhody.tiendaonline.domain.model.Product;
import com.gmdhody.tiendaonline.domain.port.out.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product client) {
        return productRepository.save(client);
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product update(Long id, Product client) {
        client.setId(id);
        return productRepository.save(client);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
