package com.fede.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EmpleadoEquipo {
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Equipo equipo;
    @ManyToOne
    private Empleado empleado;
    private Integer tipoRol;
    private Integer asignado;
    private Integer pendiente;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Equipo getEquipo() {
        return equipo;
    }
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Integer getTipoRol() {
        return tipoRol;
    }
    public void setTipoRol(Integer tipoRol) {
        this.tipoRol = tipoRol;
    }
    public Integer getAsignado() {
        return asignado;
    }
    public void setAsignado(Integer asignado) {
        this.asignado = asignado;
    }
    public Integer getPendiente() {
        return pendiente;
    }
    public void setPendiente(Integer pendiente) {
        this.pendiente = pendiente;
    }

    
}
