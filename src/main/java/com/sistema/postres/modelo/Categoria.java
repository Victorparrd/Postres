package com.sistema.postres.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Categoria {

    @Id
    @Column(name = "id_categoria")
    private Integer id;

    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Categoria() {
        super();
    }

    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria(Integer id) {
        this.id = id;
    }
}
