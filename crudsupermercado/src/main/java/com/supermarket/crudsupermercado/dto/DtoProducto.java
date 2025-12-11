package com.supermarket.crudsupermercado.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoProducto {

    private Long id;
    private String nombre;

    private String categoria;
    private Double precio;
    private Integer cantidad;
}
