package com.fede.inventario.service;

import com.fede.inventario.model.Producto;
import com.fede.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Guardar o actualizar un producto
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    // Eliminar un producto por ID
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    // Validar existencia
    public boolean existePorId(Long id) {
        return productoRepository.existsById(id);
    }
}
