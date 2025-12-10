package com.supermarket.crudsupermercado.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoSucursal {

    private Long id;
    private String nombre;
    private  String direccion;
}
