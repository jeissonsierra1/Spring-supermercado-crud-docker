package com.supermarket.crudsupermercado.service;

import com.supermarket.crudsupermercado.dto.DtoDetalleVenta;
import com.supermarket.crudsupermercado.dto.DtoProducto;
import com.supermarket.crudsupermercado.dto.DtoVenta;
import com.supermarket.crudsupermercado.mappers.Mapper;
import com.supermarket.crudsupermercado.model.DetalleVenta;
import com.supermarket.crudsupermercado.model.Producto;
import com.supermarket.crudsupermercado.model.Sucursal;
import com.supermarket.crudsupermercado.model.Venta;
import com.supermarket.crudsupermercado.notFoundException.NotFoundException;
import com.supermarket.crudsupermercado.repository.RepositoryProducto;
import com.supermarket.crudsupermercado.repository.RepositorySucursal;
import com.supermarket.crudsupermercado.repository.RepositoryVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class ServiceVenta implements IServiceVenta{


    @Autowired
    private RepositoryVenta repositoryVenta;
    @Autowired
    private RepositorySucursal repositorySucursal;
    @Autowired
    private RepositoryProducto repositoryProducto;

    @Override
    public List<DtoVenta> traerTodo() {

        return repositoryVenta.findAll().stream()
                .map(Mapper::toDto).toList();


    }

    @Override
    public DtoVenta crearVenta(DtoVenta DtoVenta) {

        Venta venta = Mapper.noDTO(DtoVenta);

        Sucursal sucursal= repositorySucursal.findById(DtoVenta.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("No se encontro sucursal"));

        venta.setSucursal(sucursal);


        List<DetalleVenta> list = new ArrayList<>();

        Double total= 0.0;

        for (DtoDetalleVenta d: DtoVenta.getDetalleVentaList()){
            DetalleVenta detalle= new DetalleVenta();

             detalle.setCantproduct(d.getCantproduct());
             detalle.setPrecio(d.getPrecio());
            Producto producto = repositoryProducto.findByNombre(d.getNombreProduct())
                    .orElseThrow(() -> new NotFoundException("No se encontro el producto"));
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            total = total + (d.getCantproduct() *d.getPrecio() );

            list.add(detalle);


        }
        venta.setTotal(total);
        venta.setDetalleVentaList(list);

        return Mapper.toDto(repositoryVenta.save(venta));


    }

    @Override
    public DtoVenta actualizarVenta(DtoVenta DtoVenta, Long id) {

        Venta venta = repositoryVenta.findById(id)
                .orElseThrow(() ->  new NotFoundException("No se encontro la venta que desea actualizar"));


        venta.setFecha(DtoVenta.getFecha());
        venta.setEstado(DtoVenta.getEstado());
        venta.setTotal(DtoVenta.getTotal());
        Sucursal sucursal = repositorySucursal.findById(DtoVenta.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("No se encontro sucursal"));
        venta.setSucursal(sucursal);

        List<DetalleVenta> list = new ArrayList<>();
        DetalleVenta detalle= new DetalleVenta();

        for (DtoDetalleVenta d: DtoVenta.getDetalleVentaList()){

            detalle.setId(d.getId());
            detalle.setCantproduct(d.getCantproduct());
            detalle.setPrecio(d.getPrecio());
            Producto producto = repositoryProducto.findByNombre(d.getNombreProduct())
                    .orElseThrow(() -> new NotFoundException("No se encontro producto"));
            detalle.setVenta(venta);
            detalle.setProducto(producto);

            list.add(detalle);

        }
        venta.setDetalleVentaList(list);



        return Mapper.toDto(repositoryVenta.save(venta));
    }

    @Override
    public void eliminarVenta(Long id) {

        if(!repositoryVenta.existsById(id)){

            throw new NotFoundException("No existe la venta a eliminar");
        }

        repositoryVenta.deleteById(id);

    }

    @Override
    public DtoProducto productoMasVendido() {

        String nombreProducto= repositoryVenta.findAll().stream()
                 .map(Mapper::toDto)
                 .flatMap(venta -> venta.getDetalleVentaList().stream())
                 .max(Comparator.comparing(c -> c.getCantproduct()))
                 .map(n -> n.getNombreProduct())
                 .orElseThrow(() -> new NotFoundException("No se encontro") );





            return Mapper.toDto(repositoryProducto.findByNombre(nombreProducto)
                    .orElseThrow(() -> new NotFoundException("No se encontro")));

        }

}
