package com.fede.inventario.repository;

import com.fede.inventario.model.EmpleadoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoEquipoRepository extends JpaRepository<EmpleadoEquipo, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
