package com.gmdhody.tiendaonline.adapters.out.persistence;

import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ProductEntity;
import com.gmdhody.tiendaonline.adapters.out.persistence.mapper.ProductMapper;
import com.gmdhody.tiendaonline.adapters.out.persistence.repository.JpaProductRepository;
import com.gmdhody.tiendaonline.domain.model.Product;
import com.gmdhody.tiendaonline.domain.port.out.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        return ProductMapper.toDomain(jpaProductRepository.save(entity));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll().stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaProductRepository.deleteById(id);
    }
}
