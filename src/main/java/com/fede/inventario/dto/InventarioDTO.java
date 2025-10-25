package com.fede.inventario.dto;

import com.fede.inventario.model.Inventario;

import java.util.Date;

public record InventarioDTO(
        Long id,
        String codigo,
        Integer estado,
        Date fecha,
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
