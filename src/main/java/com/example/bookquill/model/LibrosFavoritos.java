package com.example.bookquill.model;

import com.example.bookquill.model.Genero;
import com.example.bookquill.model.Libros;
import com.example.bookquill.model.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "libros_favoritos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_libro"}))
public class LibrosFavoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibroFav;
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libros libros;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public LibrosFavoritos() {
    }

    public LibrosFavoritos(Libros libros, Usuario usuario) {
        this.libros = libros;
        this.usuario = usuario;
    }

    public Integer getIdLibroFav() {
        return idLibroFav;
    }

    public void setIdLibroFav(Integer idLibroFav) {
        this.idLibroFav = idLibroFav;
    }

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
