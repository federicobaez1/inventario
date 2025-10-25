package com.fede.inventario.service;

import com.fede.inventario.model.Inventario;
import com.fede.inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    // Obtener todos los inventarios
    public List<Inventario> obtenerTodos() {
        return inventarioRepository.findAll();
    }

    // Obtener un inventario por ID
    public Optional<Inventario> obtenerPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    // Guardar o actualizar un inventario
    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    // Eliminar un inventario por ID
    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }

    // Validar existencia
    public boolean existePorId(Long id) {
        return inventarioRepository.existsById(id);
    }
}
