package com.supermarket.crudsupermercado.mappers;

import com.supermarket.crudsupermercado.dto.DtoDetalleVenta;
import com.supermarket.crudsupermercado.dto.DtoProducto;
import com.supermarket.crudsupermercado.dto.DtoSucursal;
import com.supermarket.crudsupermercado.dto.DtoVenta;
import com.supermarket.crudsupermercado.model.Producto;
import com.supermarket.crudsupermercado.model.Sucursal;
import com.supermarket.crudsupermercado.model.Venta;

import java.util.List;

public class Mapper {

    // PRODUCTO A PRODUCTODTO

    public static DtoProducto toDto (Producto producto){

        if (producto==null) return null;

        return DtoProducto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .precio(producto.getPrecio())
                .cantidad(producto.getCantidad())
                .build();


    }
    // PRODUCTODTO A PRODUCTO

    public static Producto noDTO (DtoProducto dtoProducto){

        return Producto.builder()

                .nombre(dtoProducto.getNombre())
                .categoria(dtoProducto.getCategoria())
                .precio(dtoProducto.getPrecio())
                .cantidad(dtoProducto.getCantidad())
                .build();

    }

    // VENTA A VENTA DTO

    public static DtoVenta toDto (Venta venta){
         if (venta==null) return null;


        List<DtoDetalleVenta> dtoDetalleVentaList = venta.getDetalleVentaList().stream()
                .map(detalle -> DtoDetalleVenta.builder()
                        .id(detalle.getProducto().getId())
                        .cantproduct(detalle.getCantproduct())
                        .nombreProduct(detalle.getProducto().getNombre())
                        .precio(detalle.getPrecio())
                        .subtotal(detalle.getPrecio() * detalle.getCantproduct())
                        .build()).toList();

        Double total = dtoDetalleVentaList.stream()
                .map(suma -> suma.getSubtotal())
                .reduce(0.0, Double::sum);

        return DtoVenta.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .estado(venta.getEstado())
                .total(total)
                .idSucursal(venta.getSucursal().getId())
                .detalleVentaList(dtoDetalleVentaList)
                .build();


    }

    // VENTADTO A VENTA

    public static Venta noDTO (DtoVenta dtoVenta){

        // sin sucursal y detalleVentaList, eso lo hacemos en el service
        return Venta.builder()

                .fecha(dtoVenta.getFecha())
                .estado(dtoVenta.getEstado())

                .build();

    }


    // SUCURSAL A SUCURSAL DTO

    public static DtoSucursal toDto (Sucursal sucursal){

        if (sucursal==null) return null;

        return DtoSucursal.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();

    }


    // SUCURSALDTO A SUCURSAL

    public static Sucursal noDTO (DtoSucursal dtoSucursal){


        return Sucursal.builder()

                .nombre(dtoSucursal.getNombre())
                .direccion(dtoSucursal.getDireccion())
                .build();

    }


}
