package com.supermarket.crudsupermercado.service;

import com.supermarket.crudsupermercado.dto.DtoSucursal;
import com.supermarket.crudsupermercado.model.Sucursal;

import java.util.List;

public interface IServiceSucursal {

    List<DtoSucursal> traerTodo ();
    DtoSucursal crearSucursal (DtoSucursal sucursal);
    DtoSucursal actualizarSucursal (DtoSucursal sucursal, Long id);
    void eliminarSucurasal (Long id);
}
