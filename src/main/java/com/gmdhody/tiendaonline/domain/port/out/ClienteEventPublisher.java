package com.gmdhody.tiendaonline.domain.port.out;

import com.gmdhody.tiendaonline.domain.model.Client;

public interface ClienteEventPublisher {
    void publicarClienteCreado(Client cliente);
}
