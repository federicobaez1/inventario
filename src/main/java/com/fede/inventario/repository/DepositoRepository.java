package com.fede.inventario.repository;

import com.fede.inventario.model.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
