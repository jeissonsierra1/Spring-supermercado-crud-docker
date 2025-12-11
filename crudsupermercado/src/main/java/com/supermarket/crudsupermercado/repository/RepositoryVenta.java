package com.supermarket.crudsupermercado.repository;

import com.supermarket.crudsupermercado.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RepositoryVenta extends JpaRepository<Venta, Long> {



}
