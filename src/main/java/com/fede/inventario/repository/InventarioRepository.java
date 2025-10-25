package com.fede.inventario.repository;

import com.fede.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
