package com.fede.inventario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fede.inventario.model.DetalleInventario;

import java.time.LocalDate;

public record DetalleInventarioDTO(
        Long id,

        Long inventarioId,
        String inventarioCodigo,

        Long productoId,
        String productoNombre,

        Long empleadoEquipoId,
        String empleadoNombre,
        String equipoNombre,

        Long depositoId,
        String depositoNombre,

        Double cantidad,
        String observaciones,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate fechaConteo,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate fechaRevision,

        Integer estado
) {

    public static DetalleInventarioDTO fromEntity(DetalleInventario d) {
        return new DetalleInventarioDTO(
                d.getId(),

                d.getInventario() != null ? d.getInventario().getId() : null,
                d.getInventario() != null ? d.getInventario().getCodigo() : null,

                d.getProducto() != null ? d.getProducto().getId() : null,
                d.getProducto() != null ? d.getProducto().getNombre() : null,

                d.getEmpleadoEquipo() != null ? d.getEmpleadoEquipo().getId() : null,
                d.getEmpleadoEquipo() != null ? d.getEmpleadoEquipo().getEmpleado().getNombre() : null,
                d.getEmpleadoEquipo() != null ? d.getEmpleadoEquipo().getEquipo().getNombre() : null,

                d.getDeposito() != null ? d.getDeposito().getId() : null,
                d.getDeposito() != null ? d.getDeposito().getNombre() : null,

                d.getCantidad(),
                d.getObservaciones(),
                d.getFechaConteo(),
                d.getFechaRevision(),
                d.getEstado()
        );
    }
}
