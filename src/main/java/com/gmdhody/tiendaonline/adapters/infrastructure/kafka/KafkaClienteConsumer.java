package com.gmdhody.tiendaonline.adapters.infrastructure.kafka;

import com.gmdhody.tiendaonline.domain.model.Client;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaClienteConsumer {

    @KafkaListener(topics = "${kafka.topic.clientes_creados}", groupId = "grupo-clientes")
    public void escucharClienteCreado(Client cliente) {
        System.out.println("📧 Enviando correo de bienvenida a: " + cliente.getEmail());
        // Aquí simulas el envío del correo o la acción que necesites
    }
}
