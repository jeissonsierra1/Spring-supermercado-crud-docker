package com.supermarket.crudsupermercado.controller;


import com.supermarket.crudsupermercado.dto.DtoSucursal;
import com.supermarket.crudsupermercado.service.IServiceSucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supermarket/sucursal")
public class ControllerSucursal {

    @Autowired
    private IServiceSucursal iServiceSucursal;

    @GetMapping

    public ResponseEntity<List<DtoSucursal>>  obtenerSucursales (){

        return ResponseEntity.ok(iServiceSucursal.traerTodo());
    }

    @PostMapping

    public ResponseEntity<DtoSucursal> crearSucursal (@RequestBody DtoSucursal dtoSucursal){


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iServiceSucursal.crearSucursal(dtoSucursal));
    }


    @PutMapping("/{id}")

    public ResponseEntity<DtoSucursal> actualizarSucursal (@PathVariable Long id,
                                                           @RequestBody DtoSucursal dtoSucursal){


        return ResponseEntity.ok(iServiceSucursal.actualizarSucursal(dtoSucursal, id));

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> eliminarSucurssal (@PathVariable Long id){


        iServiceSucursal.eliminarSucurasal(id);
        return ResponseEntity.noContent().build();


    }



}
