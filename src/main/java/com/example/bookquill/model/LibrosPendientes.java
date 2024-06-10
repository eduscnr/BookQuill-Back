package com.example.bookquill.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros_pendientes", uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_libro"}))
public class LibrosPendientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibroPendiente;
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libros libro;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public LibrosPendientes() {
    }

    public LibrosPendientes(Libros libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
    }

    public Integer getIdLibroPendiente() {
        return idLibroPendiente;
    }

    public void setIdLibroPendiente(Integer idLibroPendiente) {
        this.idLibroPendiente = idLibroPendiente;
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
