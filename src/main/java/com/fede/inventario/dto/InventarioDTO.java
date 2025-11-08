package com.fede.inventario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fede.inventario.model.Inventario;

import java.time.LocalDate;

public record InventarioDTO(
        Long id,
        String codigo,
        Integer estado,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate fecha,
        String observaciones
) {
    // Método auxiliar para convertir de entidad a DTO
    public static InventarioDTO fromEntity(Inventario inv) {
        return new InventarioDTO(
                inv.getId(),
                inv.getCodigo(),
                inv.getEstado(),
                inv.getFecha(),
                inv.getObservaciones()
        );
    }
}
