package com.gmdhody.tiendaonline.domain.port.out;

import com.gmdhody.tiendaonline.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
