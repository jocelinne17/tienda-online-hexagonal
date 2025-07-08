package com.gmdhody.tiendaonline.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseItem {
    private Product producto;
    private int cantidad;
}
