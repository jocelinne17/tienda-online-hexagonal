package com.gmdhody.tiendaonline.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
}
