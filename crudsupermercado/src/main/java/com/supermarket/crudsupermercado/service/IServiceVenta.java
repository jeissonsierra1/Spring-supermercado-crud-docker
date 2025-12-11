package com.supermarket.crudsupermercado.service;


import com.supermarket.crudsupermercado.dto.DtoProducto;
import com.supermarket.crudsupermercado.dto.DtoVenta;

import java.util.List;

public interface IServiceVenta {

    List<DtoVenta> traerTodo ();
    DtoVenta crearVenta (DtoVenta DtoVenta);
    DtoVenta actualizarVenta (DtoVenta DtoVenta, Long id);
    void eliminarVenta(Long id);
    DtoProducto productoMasVendido  ();
}
