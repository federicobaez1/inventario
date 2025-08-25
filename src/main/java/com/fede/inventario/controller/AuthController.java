package com.fede.inventario.controller;

import com.fede.inventario.model.Usuario;
import com.fede.inventario.dto.AuthRequest;
import com.fede.inventario.dto.AuthResponse;
import com.fede.inventario.dto.UsuarioDTO;
import com.fede.inventario.model.Rol;
import com.fede.inventario.repository.RolRepository;
import com.fede.inventario.repository.UsuarioRepository;
import com.fede.inventario.security.CustomUserDetailsService;
import com.fede.inventario.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
private AuthenticationManager authenticationManager;

@Autowired
private CustomUserDetailsService userDetailsService;

@Autowired
private JwtUtil jwtUtil;


    @PostMapping("/register")
    public String registerUser(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            return "Error: El nombre de usuario ya existe.";
        }

        // Asignar rol USER por defecto
        Rol rolUser = rolRepository.findByNombre("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRoles(Collections.singleton(rolUser));
        usuarioRepository.save(usuario);

        return "Usuario registrado exitosamente.";
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    } catch (AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña inválidos.");
    }
}

@PutMapping("/change-password")
public ResponseEntity<?> cambiarClave(@RequestBody AuthRequest req) {
    var usuarioOpt = usuarioRepository.findByUsername(req.getUsername());

    if (usuarioOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

    Usuario usuario = usuarioOpt.get();
    usuario.setPassword(passwordEncoder.encode(req.getPassword()));
    usuarioRepository.save(usuario);

    return ResponseEntity.ok("Contraseña actualizada correctamente");
}


@GetMapping("/users")
public List<UsuarioDTO> listarUsuarios() {
    return usuarioRepository.findAll()
            .stream()
            .map(u -> new UsuarioDTO(u.getId(), u.getUsername()))
            .toList();
}


@PutMapping("/users/{username}/password")
public ResponseEntity<?> cambiarClaveAdmin(@PathVariable String username, @RequestBody String nuevaClave) {
    var usuarioOpt = usuarioRepository.findByUsername(username);
    if (usuarioOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
    Usuario usuario = usuarioOpt.get();
    usuario.setPassword(passwordEncoder.encode(nuevaClave));
    usuarioRepository.save(usuario);
    return ResponseEntity.ok("Contraseña actualizada");
}

@PostMapping("/users")
public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
    if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario ya existe");
    }
    Rol rolUser = rolRepository.findByNombre("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    usuario.setRoles(Collections.singleton(rolUser));
    usuarioRepository.save(usuario);
    return ResponseEntity.ok("Usuario creado");
}

@DeleteMapping("/users/{username}")
public ResponseEntity<?> eliminarUsuario(@PathVariable String username) {
    var usuarioOpt = usuarioRepository.findByUsername(username);
    if (usuarioOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
    usuarioRepository.delete(usuarioOpt.get());
    return ResponseEntity.ok("Usuario eliminado");
}



}
