package com.fede.inventario.repository;

import com.fede.inventario.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
