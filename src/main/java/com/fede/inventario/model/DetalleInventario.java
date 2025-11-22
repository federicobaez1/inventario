package com.fede.inventario.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DetalleInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Inventario inventario;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private EmpleadoEquipo empleadoEquipo;

    private Double cantidad;

    private String observaciones;

    private LocalDate fechaConteo;

    private LocalDate fechaRevision;

    private Integer estado;

    @ManyToOne
    private Deposito deposito;


    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public EmpleadoEquipo getEmpleadoEquipo() {
        return empleadoEquipo;
    }

    public void setEmpleadoEquipo(EmpleadoEquipo empleadoEquipo) {
        this.empleadoEquipo = empleadoEquipo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaConteo() {
        return fechaConteo;
    }

    public void setFechaConteo(LocalDate fechaConteo) {
        this.fechaConteo = fechaConteo;
    }

    public LocalDate getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(LocalDate fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }
}
