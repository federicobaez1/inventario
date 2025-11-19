package com.fede.inventario.dto;

import com.fede.inventario.model.EmpleadoEquipo;

public record EmpleadoEquipoDTO(
        Long id,
        Long empleadoId,
        String empleadoNombre,
        Long equipoId,
        String equipoNombre,
        Integer tipoRol,
        Integer asignado,
        Integer pendiente
) {
    // Método auxiliar para convertir de entidad a DTO
    public static EmpleadoEquipoDTO fromEntity(EmpleadoEquipo e) {
        return new EmpleadoEquipoDTO(
                e.getId(),
                e.getEmpleado().getId(),
                e.getEmpleado().getNombre(),
                e.getEquipo().getId(),
                e.getEquipo().getNombre(),
                e.getTipoRol(),
                e.getAsignado(),
                e.getPendiente()
        );
    }
}
