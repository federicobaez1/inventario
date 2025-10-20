package com.fede.inventario.controller;

import com.fede.inventario.model.Equipo;
import com.fede.inventario.service.EquipoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Marca esta clase como un controlador REST
@RequestMapping("/equipos")  // Define la ruta base para este controlador
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    // Endpoint GET para obtener todos los equipos
    @GetMapping
    public List<Equipo> getAllEquipos() {
        return equipoService.obtenerTodos();
    }

    // Endpoint POST para crear un nuevo equipo
    @PostMapping
    public Equipo createEquipo(@RequestBody Equipo equipo) {
        return equipoService.guardar(equipo);
    }

    
    // Obtener un equipo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Long id) {
        return equipoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    // Eliminar un equipo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Long id) {
        if (!equipoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    

// Actualizar un equipo existente
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipoDetails) {
        return equipoService.obtenerPorId(id).map(equipo -> {
            equipo.setNombre(equipoDetails.getNombre());
            
            

            Equipo actualizado = equipoService.guardar(equipo);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
