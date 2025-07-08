package com.gmdhody.tiendaonline.adapters.out.persistence;


import com.gmdhody.tiendaonline.adapters.out.persistence.mapper.ClientMapper;
import com.gmdhody.tiendaonline.adapters.out.persistence.repository.JpaClientRepository;
import com.gmdhody.tiendaonline.domain.model.Client;
import com.gmdhody.tiendaonline.domain.port.out.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClientRepositoryAdapter implements ClientRepository{
    private final JpaClientRepository jpaClientRepository;

    public ClientRepositoryAdapter(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public Client save(Client client) {
        var entity = ClientMapper.toEntity(client);
        var saved = jpaClientRepository.save(entity);
        return ClientMapper.toDomain(saved);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return jpaClientRepository.findById(id).map(ClientMapper::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return jpaClientRepository.findAll().stream()
                .map(ClientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaClientRepository.deleteById(id);
    }
}
