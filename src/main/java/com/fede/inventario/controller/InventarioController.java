package com.fede.inventario.controller;

import com.fede.inventario.dto.InventarioDTO;
import com.fede.inventario.model.Inventario;
import com.fede.inventario.model.Usuario;
import com.fede.inventario.repository.UsuarioRepository;
import com.fede.inventario.service.InventarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Marca esta clase como un controlador REST
@RequestMapping("/inventarios")  // Define la ruta base para este controlador
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Endpoint GET para obtener todos los inventarios
    @GetMapping
    public List<InventarioDTO> getAllInventarios() {
        return inventarioService.obtenerTodos()
            .stream()
            .map(InventarioDTO::fromEntity)
            .toList();
    }

    // Endpoint POST para crear un nuevo inventario
    @PostMapping
    public Inventario createInventario(@RequestBody Inventario inventario) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // nombre del usuario autenticado
    
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        inventario.setUsuario(usuario);
        return inventarioService.guardar(inventario);
    }

    
    // Obtener un inventario por ID
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> getInventarioById(@PathVariable Long id) {
        return inventarioService.obtenerPorId(id)
            .map(InventarioDTO::fromEntity)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    
    // Eliminar un inventario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        if (!inventarioService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    

// Actualizar un inventario existente
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> updateInventario(@PathVariable Long id, @RequestBody Inventario inventarioDetails) {
        return inventarioService.obtenerPorId(id).map(inventario -> {
            inventario.setCodigo(inventarioDetails.getCodigo());
            inventario.setFecha(inventarioDetails.getFecha());
            inventario.setEstado(inventarioDetails.getEstado());
            inventario.setObservaciones(inventarioDetails.getObservaciones());
            

            Inventario actualizado = inventarioService.guardar(inventario);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
