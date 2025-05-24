package com.fede.inventario.controller;

import com.fede.inventario.model.Usuario;
import com.fede.inventario.dto.AuthRequest;
import com.fede.inventario.dto.AuthResponse;
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

}
