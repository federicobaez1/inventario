package com.fede.inventario.controller;

import com.fede.inventario.dto.EmpleadoEquipoDTO;
import com.fede.inventario.model.EmpleadoEquipo;
import com.fede.inventario.model.Usuario;
import com.fede.inventario.repository.UsuarioRepository;
import com.fede.inventario.service.EmpleadoEquipoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Marca esta clase como un controlador REST
@RequestMapping("/empleadoEquipos")  // Define la ruta base para este controlador
public class EmpleadoEquipoController {

    @Autowired
    private EmpleadoEquipoService empleadoEquipoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Endpoint GET para obtener todos los empleadoEquipos
    @GetMapping
    public List<EmpleadoEquipoDTO> getAllEmpleadoEquipos() {
        return empleadoEquipoService.obtenerTodos()
            .stream()
            .map(EmpleadoEquipoDTO::fromEntity)
            .toList();
    }

    // Endpoint POST para crear un nuevo empleadoEquipo
    @PostMapping
    public EmpleadoEquipo createEmpleadoEquipo(@RequestBody EmpleadoEquipo empleadoEquipo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // nombre del usuario autenticado
    
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        
        return empleadoEquipoService.guardar(empleadoEquipo);
    }

    
    // Obtener un empleadoEquipo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoEquipoDTO> getEmpleadoEquipoById(@PathVariable Long id) {
        return empleadoEquipoService.obtenerPorId(id)
            .map(EmpleadoEquipoDTO::fromEntity)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    
    // Eliminar un empleadoEquipo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleadoEquipo(@PathVariable Long id) {
        if (!empleadoEquipoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        empleadoEquipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    

// Actualizar un empleadoEquipo existente
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoEquipo> updateEmpleadoEquipo(@PathVariable Long id, @RequestBody EmpleadoEquipo empleadoEquipoDetails) {
        return empleadoEquipoService.obtenerPorId(id).map(empleadoEquipo -> {
            empleadoEquipo.setEquipo(empleadoEquipoDetails.getEquipo());
                    empleadoEquipo.setEmpleado(empleadoEquipoDetails.getEmpleado());
                    empleadoEquipo.setTipoRol(empleadoEquipoDetails.getTipoRol());
                    empleadoEquipo.setAsignado(empleadoEquipoDetails.getAsignado());
                    empleadoEquipo.setPendiente(empleadoEquipoDetails.getPendiente());
            

            EmpleadoEquipo actualizado = empleadoEquipoService.guardar(empleadoEquipo);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
