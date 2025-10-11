package com.fede.inventario.controller;

import com.fede.inventario.model.Deposito;
import com.fede.inventario.service.DepositoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Marca esta clase como un controlador REST
@RequestMapping("/depositos")  // Define la ruta base para este controlador
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    // Endpoint GET para obtener todos los depositos
    @GetMapping
    public List<Deposito> getAllDepositos() {
        return depositoService.obtenerTodos();
    }

    // Endpoint POST para crear un nuevo deposito
    @PostMapping
    public Deposito createDeposito(@RequestBody Deposito deposito) {
        return depositoService.guardar(deposito);
    }

    
    // Obtener un deposito por ID
    @GetMapping("/{id}")
    public ResponseEntity<Deposito> getDepositoById(@PathVariable Long id) {
        return depositoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    // Eliminar un deposito por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeposito(@PathVariable Long id) {
        if (!depositoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        depositoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    

// Actualizar un deposito existente
    @PutMapping("/{id}")
    public ResponseEntity<Deposito> updateDeposito(@PathVariable Long id, @RequestBody Deposito depositoDetails) {
        return depositoService.obtenerPorId(id).map(deposito -> {
            deposito.setNombre(depositoDetails.getNombre());
            
            

            Deposito actualizado = depositoService.guardar(deposito);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
