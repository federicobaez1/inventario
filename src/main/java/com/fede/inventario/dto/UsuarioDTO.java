package com.fede.inventario.dto;

public class UsuarioDTO {
    private Long id;
    private String username;

    public UsuarioDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
