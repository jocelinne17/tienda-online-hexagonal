package com.gmdhody.tiendaonline.config;

import com.gmdhody.tiendaonline.application.service.ClientService;
import com.gmdhody.tiendaonline.application.service.ProductService;
import com.gmdhody.tiendaonline.application.service.PurchaseService;
import com.gmdhody.tiendaonline.domain.port.out.ClientRepository;
import com.gmdhody.tiendaonline.domain.port.out.ClienteEventPublisher;
import com.gmdhody.tiendaonline.domain.port.out.ProductRepository;
import com.gmdhody.tiendaonline.domain.port.out.PurchaseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ClientService clientService(ClientRepository clientRepository, ClienteEventPublisher clienteEventPublisher) {
        return new ClientService(clientRepository, clienteEventPublisher);
    }


    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    public PurchaseService purchaseService(PurchaseRepository purchaseRepository, ProductRepository productRepository) {
        return new PurchaseService(purchaseRepository, productRepository);
    }
}
