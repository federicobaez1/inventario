package com.fede.inventario.repository;

import com.fede.inventario.model.DetalleInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleInventarioRepository extends JpaRepository<DetalleInventario, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
