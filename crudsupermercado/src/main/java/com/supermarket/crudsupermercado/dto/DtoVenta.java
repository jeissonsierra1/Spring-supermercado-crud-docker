package com.supermarket.crudsupermercado.dto;

import com.supermarket.crudsupermercado.model.DetalleVenta;
import com.supermarket.crudsupermercado.model.Sucursal;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoVenta {

    private Long id;

    private LocalDate fecha;
    private String estado;
    private Double total;

    private Long idSucursal;

    private List<DtoDetalleVenta> detalleVentaList = new ArrayList<>();

}
