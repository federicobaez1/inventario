package com.fede.inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/productos/**").permitAll() // 👈 Permitir todo acceso a /productos
                .anyRequest().authenticated()
            .and()
            .httpBasic(); // 👈 Habilitar autenticación básica

        return http.build();
    }
}

