package com.fede.inventario.service;

import com.fede.inventario.model.Equipo;
import com.fede.inventario.repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    // Obtener todos los equipos
    public List<Equipo> obtenerTodos() {
        return equipoRepository.findAll();
    }

    // Obtener un equipo por ID
    public Optional<Equipo> obtenerPorId(Long id) {
        return equipoRepository.findById(id);
    }

    // Guardar o actualizar un equipo
    public Equipo guardar(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    // Eliminar un equipo por ID
    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }

    // Validar existencia
    public boolean existePorId(Long id) {
        return equipoRepository.existsById(id);
    }
}
