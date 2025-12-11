package com.supermarket.crudsupermercado.controller;


import com.supermarket.crudsupermercado.dto.DtoProducto;
import com.supermarket.crudsupermercado.service.IServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supermarket/producto")

public class ControllerProducto {

    @Autowired
    private IServiceProducto iServiceProducto;

    @GetMapping

    public ResponseEntity<List<DtoProducto>> traerProductos (){


        return ResponseEntity.ok(iServiceProducto.traerTodo());

    }



    @PostMapping

    public ResponseEntity<DtoProducto> crearProducto (@RequestBody DtoProducto dtoProducto){

        return ResponseEntity.status(HttpStatus.CREATED).body(iServiceProducto.crearProducto(dtoProducto));

    }

    @PutMapping("/{id}")

    public ResponseEntity<DtoProducto> actualizarProducto (@PathVariable Long id,
                                                           @RequestBody DtoProducto dtoProducto){

        return ResponseEntity.ok(iServiceProducto.actualizarProducto(dtoProducto, id));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto (@PathVariable Long id){

        iServiceProducto.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }



}
