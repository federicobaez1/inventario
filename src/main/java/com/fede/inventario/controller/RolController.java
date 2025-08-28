package com.fede.inventario.controller;

import com.fede.inventario.dto.RolDTO;
import com.fede.inventario.repository.RolRepository;
import com.fede.inventario.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public List<RolDTO> listarRoles() {
        return rolRepository.findAll()
                .stream()
                .map(r -> new RolDTO(r.getId(), r.getNombre()))
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> crearRol(@RequestBody Rol rol) {
        if (rolRepository.findByNombre(rol.getNombre()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body("El rol ya existe");
        }
        Rol nuevoRol = rolRepository.save(rol);
        return ResponseEntity.ok(new RolDTO(nuevoRol.getId(), nuevoRol.getNombre()));
    }
}
