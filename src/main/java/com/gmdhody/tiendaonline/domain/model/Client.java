package com.gmdhody.tiendaonline.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    public Client() {}

}