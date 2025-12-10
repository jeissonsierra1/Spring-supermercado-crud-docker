package com.supermarket.crudsupermercado.service;

import com.supermarket.crudsupermercado.dto.DtoSucursal;
import com.supermarket.crudsupermercado.mappers.Mapper;
import com.supermarket.crudsupermercado.model.Sucursal;
import com.supermarket.crudsupermercado.notFoundException.NotFoundException;
import com.supermarket.crudsupermercado.repository.RepositorySucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ServiceSucursal implements IServiceSucursal{

    @Autowired
    private RepositorySucursal sucu;

    @Override
    public List<DtoSucursal> traerTodo() {

            return sucu.findAll().stream()
                .map(Mapper::toDto).toList();
    }

    @Override
    public DtoSucursal crearSucursal(DtoSucursal sucursal) {

        Sucursal sucursal1 = Mapper.noDTO(sucursal);

        return Mapper.toDto(sucu.save(sucursal1));


    }

    @Override
    public DtoSucursal actualizarSucursal(DtoSucursal sucursal, Long id) {

        Sucursal nuevo = sucu.findById(id).orElseThrow(() -> new NotFoundException("No se encontro Sucursal") );


        nuevo.setDireccion(sucursal.getDireccion());
        nuevo.setNombre(sucursal.getNombre());


        return Mapper.toDto(sucu.save(nuevo));
    }


    @Override
    public void eliminarSucurasal(Long id) {

        if (!sucu.existsById(id)){

            throw new NotFoundException("No se puede eliminar sucursal porque no existe");
        }

        sucu.deleteById(id);

    }
}
