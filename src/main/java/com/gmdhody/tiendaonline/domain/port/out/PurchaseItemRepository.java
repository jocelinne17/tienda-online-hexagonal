package com.gmdhody.tiendaonline.domain.port.out;

import com.gmdhody.tiendaonline.domain.model.PurchaseItem;

import java.util.List;
import java.util.Optional;

public interface PurchaseItemRepository {
    PurchaseItem save(PurchaseItem purchaseItem);
    Optional<PurchaseItem> findById(Long id);
    List<PurchaseItem> findAll();
    void deleteById(Long id);
}
