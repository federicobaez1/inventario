package com.fede.inventario.service;

import com.fede.inventario.model.DetalleInventario;
import com.fede.inventario.repository.DetalleInventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleInventarioService {

    private final DetalleInventarioRepository detalleInventarioRepository;

    public DetalleInventarioService(DetalleInventarioRepository detalleInventarioRepository) {
        this.detalleInventarioRepository = detalleInventarioRepository;
    }

    // Obtener todos los detalleInventarios
    public List<DetalleInventario> obtenerTodos() {
        return detalleInventarioRepository.findAll();
    }

    // Obtener un detalleInventario por ID
    public Optional<DetalleInventario> obtenerPorId(Long id) {
        return detalleInventarioRepository.findById(id);
    }

    // Guardar o actualizar un detalleInventario
    public DetalleInventario guardar(DetalleInventario detalleInventario) {
        return detalleInventarioRepository.save(detalleInventario);
    }

    // Eliminar un detalleInventario por ID
    public void eliminar(Long id) {
        detalleInventarioRepository.deleteById(id);
    }

    // Validar existencia
    public boolean existePorId(Long id) {
        return detalleInventarioRepository.existsById(id);
    }
}
