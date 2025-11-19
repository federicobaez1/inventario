package com.fede.inventario.service;

import com.fede.inventario.model.EmpleadoEquipo;
import com.fede.inventario.repository.EmpleadoEquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoEquipoService {

    private final EmpleadoEquipoRepository empleadoEquipoRepository;

    public EmpleadoEquipoService(EmpleadoEquipoRepository empleadoEquipoRepository) {
        this.empleadoEquipoRepository = empleadoEquipoRepository;
    }

    // Obtener todos los empleadoEquipos
    public List<EmpleadoEquipo> obtenerTodos() {
        return empleadoEquipoRepository.findAll();
    }

    // Obtener un empleadoEquipo por ID
    public Optional<EmpleadoEquipo> obtenerPorId(Long id) {
        return empleadoEquipoRepository.findById(id);
    }

    // Guardar o actualizar un empleadoEquipo
    public EmpleadoEquipo guardar(EmpleadoEquipo empleadoEquipo) {
        return empleadoEquipoRepository.save(empleadoEquipo);
    }

    // Eliminar un empleadoEquipo por ID
    public void eliminar(Long id) {
        empleadoEquipoRepository.deleteById(id);
    }

    // Validar existencia
    public boolean existePorId(Long id) {
        return empleadoEquipoRepository.existsById(id);
    }
}
