package com.fede.inventario.repository;

import com.fede.inventario.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
