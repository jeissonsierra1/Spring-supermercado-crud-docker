package com.supermarket.crudsupermercado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class DetalleVenta {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantproduct;
    private Double precio;
    @ManyToOne(fetch = FetchType.LAZY)
    private Venta venta;
    @ManyToOne
    private Producto producto;

}
