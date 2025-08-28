package com.fede.inventario.controller;

import com.fede.inventario.dto.UsuarioDTO;
import com.fede.inventario.model.Rol;
import com.fede.inventario.model.Usuario;
import com.fede.inventario.repository.RolRepository;
import com.fede.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(u -> new UsuarioDTO(u.getId(), u.getUsername()))
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error", "Usuario ya existe"));
        }
        Rol rolUser = rolRepository.findByNombre("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRoles(Collections.singleton(rolUser));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(Collections.singletonMap("mensaje", "Usuario creado"));
    }

    @PutMapping("/{username}/password")
    public ResponseEntity<?> cambiarClave(@PathVariable String username, @RequestBody String nuevaClave) {
        var usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Usuario no encontrado"));
        }
        Usuario usuario = usuarioOpt.get();
        usuario.setPassword(passwordEncoder.encode(nuevaClave));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(Collections.singletonMap("mensaje", "Contraseña actualizada"));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String username) {
        var usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Usuario no encontrado"));
        }
        usuarioRepository.delete(usuarioOpt.get());
        return ResponseEntity.ok(Collections.singletonMap("mensaje", "Usuario eliminado"));
    }
}
