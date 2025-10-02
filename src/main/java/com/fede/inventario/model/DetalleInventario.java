package com.fede.inventario.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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
    private Date fechaConteo;
    private Date fechaRevision;
    private Integer estado;
    @ManyToOne
    private Deposito deposito;
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
    public Date getFechaConteo() {
        return fechaConteo;
    }
    public void setFechaConteo(Date fechaConteo) {
        this.fechaConteo = fechaConteo;
    }
    public Date getFechaRevision() {
        return fechaRevision;
    }
    public void setFechaRevision(Date fechaRevision) {
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
