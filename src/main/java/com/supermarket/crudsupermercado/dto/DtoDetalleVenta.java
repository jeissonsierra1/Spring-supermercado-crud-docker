package com.supermarket.crudsupermercado.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoDetalleVenta {

    private Long id;
    private Integer cantproduct;
    private String nombreProduct;
    private Double precio;
    private Double subtotal;
}
