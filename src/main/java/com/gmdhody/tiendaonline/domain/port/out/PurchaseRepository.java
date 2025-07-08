package com.gmdhody.tiendaonline.domain.port.out;

import com.gmdhody.tiendaonline.domain.model.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    Purchase save(Purchase purchase);
    Optional<Purchase> findById(Long id);
    List<Purchase> findAll();
    void deleteById(Long id);
}
