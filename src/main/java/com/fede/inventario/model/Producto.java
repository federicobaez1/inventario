
package com.fede.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity  // Indica que esta clase es una entidad JPA
public class Producto {

    @Id  // Marca este campo como la clave primaria
    private Long id;
    private String nombre;
    private Double precio;

    // Constructor vacío (requerido por JPA)
    public Producto() {}

    // Constructor con parámetros
    public Producto(Long id, String nombre, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
