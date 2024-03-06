package com.sistema.postres.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Entity
public class Postre{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postre")
    private Integer id;

    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;

    @NotNull
    private Float precio;

    private String rutaImagen;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categoria_postre", joinColumns = @JoinColumn(name = "id_postre"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categorias;

    @Transient
    private MultipartFile imagen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }

    public Postre() {
        super();
    }

    public Postre(Integer id, String nombre, String descripcion, Float precio, String rutaImagen, List<Categoria> categorias, MultipartFile imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
        this.categorias = categorias;
        this.imagen = imagen;
    }

    public Postre(String nombre, String descripcion, Float precio, String rutaImagen, List<Categoria> categorias, MultipartFile imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
        this.categorias = categorias;
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Postre postre = (Postre) o;
        return Objects.equals(id, postre.id) && Objects.equals(nombre, postre.nombre) && Objects.equals(descripcion, postre.descripcion) && Objects.equals(precio, postre.precio) && Objects.equals(rutaImagen, postre.rutaImagen) && Objects.equals(categorias, postre.categorias) && Objects.equals(imagen, postre.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, precio, rutaImagen, categorias, imagen);
    }

    @Override
    public String toString() {
        return "Postre{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", rutaImagen='" + rutaImagen + '\'' +
                ", categorias=" + categorias +
                ", imagen=" + imagen +
                '}';
    }
}
