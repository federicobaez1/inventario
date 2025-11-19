package com.fede.inventario.controller;

import com.fede.inventario.model.Empleado;
import com.fede.inventario.service.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Marca esta clase como un controlador REST
@RequestMapping("/empleados")  // Define la ruta base para este controlador
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // Endpoint GET para obtener todos los empleados
    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.obtenerTodos();
    }

    // Endpoint POST para crear un nuevo empleado
    @PostMapping
    public Empleado createEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    
    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        return empleadoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    // Eliminar un empleado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        if (!empleadoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    

// Actualizar un empleado existente
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetails) {
        return empleadoService.obtenerPorId(id).map(empleado -> {
            empleado.setNombre(empleadoDetails.getNombre());
            
            

            Empleado actualizado = empleadoService.guardar(empleado);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
