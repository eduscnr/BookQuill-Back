package com.example.bookquill.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer idLibro;
    private String titulo;
    private String autor;
    private String descripcion;
    private String estrellas;
    @Column(name = "url_imagen")
    private String urlImagen;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_genero")
    private Genero genero;
    
    public Libros() {
    }

    public Libros(Integer idLibro, String titulo, String autor, String descripcion, String urlImagen, Genero genero) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.genero = genero;
    }

    public Libros(String titulo, String autor, String descripcion, String urlImagen, Genero genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.genero = genero;
    }

    public Libros(String titulo, String autor, String descripcion, String urlImagen) {
        this.titulo = titulo;
        this.autor = autor;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(String estrellas) {
        this.estrellas = estrellas;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", descripción='" + descripcion + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                ", genero=" + genero +
                '}';
    }
}
