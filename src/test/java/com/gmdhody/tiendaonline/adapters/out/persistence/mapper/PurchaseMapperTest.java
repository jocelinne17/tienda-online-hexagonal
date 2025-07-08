package com.gmdhody.tiendaonline.adapters.out.persistence.mapper;

import com.gmdhody.tiendaonline.adapters.out.persistence.entity.PurchaseEntity;
import com.gmdhody.tiendaonline.adapters.out.persistence.entity.PurchaseItemEntity;
import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ProductEntity;
import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ClientEntity;
import com.gmdhody.tiendaonline.domain.model.Purchase;
import com.gmdhody.tiendaonline.domain.model.PurchaseItem;
import com.gmdhody.tiendaonline.domain.model.Product;
import com.gmdhody.tiendaonline.domain.model.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseMapperTest {

    @Test
    void toEntity_ShouldMapDomainToEntity() {
        Client client = new Client();
        client.setId(1L);
        client.setNombre("Juan");
        client.setApellido("Perez");
        client.setEmail("juan@example.com");
        client.setTelefono("123456789");
        client.setDireccion("Calle Falsa 123");

        Product product1 = new Product();
        product1.setId(10L);
        product1.setNombre("Producto 1");
        product1.setPrecio(100.0);
        product1.setStock(50);

        PurchaseItem item1 = new PurchaseItem();
        item1.setProducto(product1);
        item1.setCantidad(2);

        Purchase purchase = new Purchase();
        purchase.setId(100L);
        purchase.setFecha(LocalDate.of(2025,7,8));
        purchase.setPrecioTotal(200.0);
        purchase.setClient(client);
        purchase.setItems(List.of(item1));

        PurchaseEntity entity = PurchaseMapper.toEntity(purchase);

        assertNotNull(entity);
        assertEquals(purchase.getId(), entity.getId());
        assertEquals(purchase.getFecha(), entity.getFecha());
        assertEquals(purchase.getPrecioTotal(), entity.getPrecioTotal());
        assertNotNull(entity.getClient());
        assertEquals(client.getId(), entity.getClient().getId());

        assertNotNull(entity.getItems());
        assertEquals(1, entity.getItems().size());

        PurchaseItemEntity itemEntity = entity.getItems().get(0);
        assertEquals(product1.getId(), itemEntity.getProduct().getId());
        assertEquals(item1.getCantidad(), itemEntity.getCantidad());
        assertEquals(entity, itemEntity.getPurchase());
    }

    @Test
    void toDomain_ShouldMapEntityToDomain() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setNombre("Juan");
        clientEntity.setApellido("Perez");
        clientEntity.setEmail("juan@example.com");
        clientEntity.setTelefono("123456789");
        clientEntity.setDireccion("Calle Falsa 123");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(10L);
        productEntity.setNombre("Producto 1");
        productEntity.setPrecio(100.0);
        productEntity.setStock(50);

        PurchaseItemEntity itemEntity = new PurchaseItemEntity();
        itemEntity.setId(1000L);
        itemEntity.setProduct(productEntity);
        itemEntity.setCantidad(2);

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(100L);
        purchaseEntity.setFecha(LocalDate.of(2025,7,8));
        purchaseEntity.setPrecioTotal(200.0);
        purchaseEntity.setClient(clientEntity);
        purchaseEntity.setItems(List.of(itemEntity));

        itemEntity.setPurchase(purchaseEntity); // para mantener referencia bidireccional si aplica

        Purchase purchase = PurchaseMapper.toDomain(purchaseEntity);

        assertNotNull(purchase);
        assertEquals(purchaseEntity.getId(), purchase.getId());
        assertEquals(purchaseEntity.getFecha(), purchase.getFecha());
        assertEquals(purchaseEntity.getPrecioTotal(), purchase.getPrecioTotal());
        assertNotNull(purchase.getClient());
        assertEquals(clientEntity.getId(), purchase.getClient().getId());

        assertNotNull(purchase.getItems());
        assertEquals(1, purchase.getItems().size());

        PurchaseItem item = purchase.getItems().get(0);
        assertEquals(productEntity.getId(), item.getProducto().getId());
        assertEquals(itemEntity.getCantidad(), item.getCantidad());
    }
}
