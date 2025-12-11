package com.supermarket.crudsupermercado.controller;

import com.supermarket.crudsupermercado.dto.DtoProducto;
import com.supermarket.crudsupermercado.dto.DtoVenta;
import com.supermarket.crudsupermercado.service.IServiceVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supermarket/venta")

public class ControllerVenta {

    @Autowired
    private IServiceVenta iServiceVenta;

    @GetMapping
    public ResponseEntity<List<DtoVenta>> obtenerVentas (){


        return ResponseEntity.ok(iServiceVenta.traerTodo());
    }

    @GetMapping("/producto-mas-vendido")

    public ResponseEntity<DtoProducto> productoMasVendido (){

        return ResponseEntity.ok(iServiceVenta.productoMasVendido());


    }

    @PostMapping

    public ResponseEntity<DtoVenta> crearVenta (@RequestBody DtoVenta dtoVenta){

        return ResponseEntity.status(HttpStatus.CREATED).body(iServiceVenta.crearVenta(dtoVenta));


    }

    @PutMapping("/{id}")

    public ResponseEntity<DtoVenta> actualizarVenta (@PathVariable Long id,
                                                     @RequestBody DtoVenta dtoVenta){


        return ResponseEntity.ok(iServiceVenta.actualizarVenta(dtoVenta, id));

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> eliminarVenta (@PathVariable Long id){

        iServiceVenta.eliminarVenta(id);
        return ResponseEntity.noContent().build();

    }

}
