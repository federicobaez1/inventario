package com.fede.inventario.controller;

import com.fede.inventario.dto.DetalleInventarioDTO;
import com.fede.inventario.model.DetalleInventario;
import com.fede.inventario.model.Usuario;
import com.fede.inventario.repository.DepositoRepository;
import com.fede.inventario.repository.EmpleadoEquipoRepository;
import com.fede.inventario.repository.InventarioRepository;
import com.fede.inventario.repository.ProductoRepository;
import com.fede.inventario.repository.UsuarioRepository;
import com.fede.inventario.service.DetalleInventarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleInventarios")
public class DetalleInventarioController {

    @Autowired
    private DetalleInventarioService detalleInventarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EmpleadoEquipoRepository empleadoEquipoRepository;

    @Autowired
    private DepositoRepository depositoRepository;

    // GET - obtener todos
    @GetMapping
    public List<DetalleInventarioDTO> getAllDetalleInventarios() {
        return detalleInventarioService.obtenerTodos()
                .stream()
                .map(DetalleInventarioDTO::fromEntity)
                .toList();
    }

    // POST - crear uno nuevo
    @PostMapping
    public ResponseEntity<DetalleInventarioDTO> createDetalleInventario(@RequestBody DetalleInventarioDTO dto) {
        DetalleInventario detalle = new DetalleInventario();

        detalle.setInventario(inventarioRepository.findById(dto.inventarioId()).orElse(null));
        detalle.setProducto(productoRepository.findById(dto.productoId()).orElse(null));
        detalle.setEmpleadoEquipo(empleadoEquipoRepository.findById(dto.empleadoEquipoId()).orElse(null));
        detalle.setDeposito(depositoRepository.findById(dto.depositoId()).orElse(null));

        detalle.setCantidad(dto.cantidad());
        detalle.setObservaciones(dto.observaciones());
        detalle.setFechaConteo(dto.fechaConteo());
        detalle.setFechaRevision(dto.fechaRevision());
        detalle.setEstado(dto.estado());

        // asignar usuario logueado
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        

        DetalleInventario guardado = detalleInventarioService.guardar(detalle);

        return ResponseEntity.ok(DetalleInventarioDTO.fromEntity(guardado));
    }

    // GET - obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleInventarioDTO> getDetalleInventarioById(@PathVariable Long id) {
        return detalleInventarioService.obtenerPorId(id)
                .map(DetalleInventarioDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - actualizar existente
    @PutMapping("/{id}")
    public ResponseEntity<DetalleInventarioDTO> updateDetalleInventario(
            @PathVariable Long id,
            @RequestBody DetalleInventarioDTO dto) {

        return detalleInventarioService.obtenerPorId(id).map(detalle -> {

            detalle.setInventario(inventarioRepository.findById(dto.inventarioId()).orElse(null));
            detalle.setProducto(productoRepository.findById(dto.productoId()).orElse(null));
            detalle.setEmpleadoEquipo(empleadoEquipoRepository.findById(dto.empleadoEquipoId()).orElse(null));
            detalle.setDeposito(depositoRepository.findById(dto.depositoId()).orElse(null));

            detalle.setCantidad(dto.cantidad());
            detalle.setObservaciones(dto.observaciones());
            detalle.setFechaConteo(dto.fechaConteo());
            detalle.setFechaRevision(dto.fechaRevision());
            detalle.setEstado(dto.estado());

            DetalleInventario actualizado = detalleInventarioService.guardar(detalle);
            return ResponseEntity.ok(DetalleInventarioDTO.fromEntity(actualizado));

        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE - eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleInventario(@PathVariable Long id) {
        if (!detalleInventarioService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        detalleInventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
