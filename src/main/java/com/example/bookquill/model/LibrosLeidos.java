package com.example.bookquill.model;

import jakarta.persistence.*;
import jdk.jfr.Name;

@Entity
@Table(name = "libros_leidos", uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_libro"}))

public class LibrosLeidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibroLeido;
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libros libro;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public LibrosLeidos() {
    }

    public LibrosLeidos(Libros libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
    }

    public Integer getIdLibroLeido() {
        return idLibroLeido;
    }

    public void setIdLibroLeido(Integer idLibroLeido) {
        this.idLibroLeido = idLibroLeido;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
