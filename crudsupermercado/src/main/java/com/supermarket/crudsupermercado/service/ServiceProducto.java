package com.supermarket.crudsupermercado.service;

import com.supermarket.crudsupermercado.dto.DtoProducto;
import com.supermarket.crudsupermercado.mappers.Mapper;
import com.supermarket.crudsupermercado.model.Producto;
import com.supermarket.crudsupermercado.notFoundException.NotFoundException;
import com.supermarket.crudsupermercado.repository.RepositoryProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service

public class ServiceProducto implements IServiceProducto {

    @Autowired
    private RepositoryProducto repo;

    @Override
    public List<DtoProducto> traerTodo() {

        return repo.findAll().stream()
                .map(Mapper::toDto).toList();

    }



    @Override
    public DtoProducto crearProducto(DtoProducto DtoProducto) {

        Producto pro = Mapper.noDTO(DtoProducto);

        return Mapper.toDto(repo.save(pro));
    }

    @Override
    public DtoProducto actualizarProducto(DtoProducto DtoProducto, Long id) {

        Producto producto = repo.findById(id).
                orElseThrow(() -> new NotFoundException("El producto no existe"));

        producto.setNombre(DtoProducto.getNombre());
        producto.setCategoria(DtoProducto.getCategoria());
        producto.setPrecio(DtoProducto.getPrecio());
        producto.setCantidad(DtoProducto.getCantidad());

        return Mapper.toDto(repo.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {

        if(!repo.existsById(id)){

            throw new NotFoundException("El producto a eliminar no existe");
        }

        repo.deleteById(id);

    }


}
