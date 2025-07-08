package com.gmdhody.tiendaonline.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Purchase {
    private Long id;
    private Client client;
    private List<PurchaseItem> items;
    private int cantidadTotal;
    private double precioTotal;
    private LocalDate fecha;
}
