package com.gmdhody.tiendaonline.adapters.infrastructure.kafka;

import com.gmdhody.tiendaonline.domain.model.Client;
import com.gmdhody.tiendaonline.domain.port.out.ClienteEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaClienteProducer implements ClienteEventPublisher {

    private final KafkaTemplate<String, Client> kafkaTemplate;

    @Value("${kafka.topic.clientes_creados}")
    private String topic;

    public KafkaClienteProducer(KafkaTemplate<String, Client> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicarClienteCreado(Client cliente) {
        kafkaTemplate.send(topic, cliente);
    }
}


