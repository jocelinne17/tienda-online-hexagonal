package com.gmdhody.tiendaonline.adapters.out.persistence.mapper;

import com.gmdhody.tiendaonline.adapters.out.persistence.entity.PurchaseEntity;
import com.gmdhody.tiendaonline.adapters.out.persistence.entity.PurchaseItemEntity;
import com.gmdhody.tiendaonline.domain.model.Purchase;
import com.gmdhody.tiendaonline.domain.model.PurchaseItem;

import java.util.List;
import java.util.stream.Collectors;

public class PurchaseMapper {
    public static PurchaseEntity toEntity(Purchase purchase) {
        PurchaseEntity entity = new PurchaseEntity();
        entity.setId(purchase.getId());
        entity.setFecha(purchase.getFecha());
        entity.setPrecioTotal(purchase.getPrecioTotal());
        entity.setClient(ClientMapper.toEntity(purchase.getClient()));

        List<PurchaseItemEntity> items = purchase.getItems().stream().map(item -> {
            PurchaseItemEntity itemEntity = new PurchaseItemEntity();
            itemEntity.setProduct(ProductMapper.toEntity(item.getProducto()));
            itemEntity.setCantidad(item.getCantidad());
            itemEntity.setPurchase(entity);
            return itemEntity;
        }).collect(Collectors.toList());

        entity.setItems(items);
        return entity;
    }

    public static Purchase toDomain(PurchaseEntity entity) {
        Purchase purchase = new Purchase();
        purchase.setId(entity.getId());
        purchase.setFecha(entity.getFecha());
        purchase.setPrecioTotal(entity.getPrecioTotal());
        purchase.setClient(ClientMapper.toDomain(entity.getClient()));

        List<PurchaseItem> items = entity.getItems().stream().map(itemEntity -> {
            PurchaseItem item = new PurchaseItem();
            item.setProducto(ProductMapper.toDomain(itemEntity.getProduct()));
            item.setCantidad(itemEntity.getCantidad());
            return item;
        }).collect(Collectors.toList());

        purchase.setItems(items);
        return purchase;
    }
}
