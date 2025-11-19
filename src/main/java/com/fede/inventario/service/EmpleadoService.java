package com.fede.inventario.service;

import com.fede.inventario.model.Empleado;
import com.fede.inventario.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    // Obtener todos los empleados
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    // Obtener un empleado por ID
    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    // Guardar o actualizar un empleado
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Eliminar un empleado por ID
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }

    // Validar existencia
    public boolean existePorId(Long id) {
        return empleadoRepository.existsById(id);
    }
}
