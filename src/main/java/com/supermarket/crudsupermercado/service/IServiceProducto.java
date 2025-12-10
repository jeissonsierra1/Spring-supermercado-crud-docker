package com.supermarket.crudsupermercado.service;

import com.supermarket.crudsupermercado.dto.DtoProducto;


import java.util.List;

public interface IServiceProducto {


    List<DtoProducto> traerTodo ();
    DtoProducto crearProducto (DtoProducto DtoProducto);
    DtoProducto actualizarProducto (DtoProducto DtoProducto, Long id);
    void eliminarProducto(Long id);


}
