package com.gmdhody.tiendaonline.adapters.out.persistence.repository;

import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientRepository extends JpaRepository<ClientEntity, Long> {
}
