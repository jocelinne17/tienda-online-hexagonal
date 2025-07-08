package com.gmdhody.tiendaonline.adapters.out.persistence.repository;

import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity,Long> {
}
