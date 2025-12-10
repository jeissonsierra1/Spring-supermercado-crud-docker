package com.supermarket.crudsupermercado.repository;

import com.supermarket.crudsupermercado.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryProducto extends JpaRepository<Producto,Long> {

   Optional<Producto>  findByNombre (String nombre);

}
