package com.fede.inventario.controller;

import com.fede.inventario.model.Producto;
import com.fede.inventario.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Marca esta clase como un controlador REST
@RequestMapping("/productos")  // Define la ruta base para este controlador
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Endpoint GET para obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.obtenerTodos();
    }

    // Endpoint POST para crear un nuevo producto
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    
    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (!productoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    

// Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        return productoService.obtenerPorId(id).map(producto -> {
            producto.setNombre(productoDetails.getNombre());
            producto.setPrecio(productoDetails.getPrecio());
            

            Producto actualizado = productoService.guardar(producto);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
