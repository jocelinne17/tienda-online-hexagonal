package com.gmdhody.tiendaonline.adapters.out.persistence;

import com.gmdhody.tiendaonline.adapters.out.persistence.mapper.PurchaseMapper;
import com.gmdhody.tiendaonline.adapters.out.persistence.repository.JpaPurchaseRepository;
import com.gmdhody.tiendaonline.domain.model.Purchase;
import com.gmdhody.tiendaonline.domain.port.out.PurchaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PurchaseRepositoryAdapter implements PurchaseRepository {
    private final JpaPurchaseRepository jpaPurchaseRepository;

    public PurchaseRepositoryAdapter(JpaPurchaseRepository jpaPurchaseRepository) {
        this.jpaPurchaseRepository = jpaPurchaseRepository;
    }

    @Override
    public Purchase save(Purchase purchase) {
        var entity = PurchaseMapper.toEntity(purchase);
        return PurchaseMapper.toDomain(jpaPurchaseRepository.save(entity));
    }

    @Override
    public Optional<Purchase> findById(Long id) {
        return jpaPurchaseRepository.findById(id).map(PurchaseMapper::toDomain);
    }

    @Override
    public List<Purchase> findAll() {
        return jpaPurchaseRepository.findAll().stream().map(PurchaseMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaPurchaseRepository.deleteById(id);
    }
}
